import os
import random
import re
import shutil
import subprocess
import time

from os.path import join
from flask import Flask, escape, request

javaClassNameRegex = re.compile("[a-zA-Z_$][a-zA-Z\d_$]*")

app = Flask(__name__, instance_relative_config=True, static_url_path = '', static_folder = '/vagrant/site')
app.config.from_object('default_settings')
app.config.from_pyfile('settings.cfg', silent=True)

BASE_DIR = app.instance_path + app.config['COMPILE_QUEUE_DIR']
TESTS_DIR = app.instance_path + app.config['TESTS_DIR']

TYPE_EXAMPLE = '1'
TYPE_EXERCISE = '2'

JAVA_EXT = '.java'
TEST_FILE_SUFFIX = 'Test'
STUB_MARKER = '//INSERT_SNIPPET'


@app.route('/compile/java', methods=['POST'])
def compile_java():
    className = request.form['className']
    if not javaClassNameRegex.match(className):
        return escape("Invalid file name.")

    code = request.form['code']

    temp_dir = ''.join(['java_', str(time.time()), '_', str(random.randint(1, 1000)), '/'])
    filename =  ''.join([className, JAVA_EXT])

    directory = join(BASE_DIR, temp_dir)
    filepath = join(directory, filename)

    os.mkdir(directory)
    with open(filepath, "wb") as file:
        file.write(code)

    volume = ':'.join([directory, app.config['DOCKER_VOLUME']])

    proc = subprocess.Popen(
        ["docker", "run", "-n=false", "-rm", "-m", "64m", "-v", volume, "rto/java", TYPE_EXAMPLE, className],
        stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    stdout, stderr = proc.communicate()

    try:
        os.remove(filepath)
    except OSError:
        pass

    try:
        os.rmdir(directory)
    except OSError:
        pass

    if stderr:
        return escape(stderr)
    else:
        return escape(stdout)


@app.route('/compile/java/exercise', methods=['POST'])
def compile_java_exercise():
    className = request.form['className']
    if not javaClassNameRegex.match(className):
        return escape("Invalid file name.")

    code = request.form['code']

    temp_dir = ''.join(['java_', str(time.time()), '_', str(random.randint(1, 1000)), '/'])

    filename =  ''.join([className, JAVA_EXT])
    testfilename = ''.join([className, TEST_FILE_SUFFIX, JAVA_EXT])

    directory = join(BASE_DIR, temp_dir)
    filepath = join(directory, filename)

    stubfilepath = join(TESTS_DIR, filename)
    testfilepath = join(directory, testfilename)

    os.mkdir(directory)

    if request.form['type'] == 'stub':
        with open(stubfilepath, 'r') as file:
            code = file.read().replace(STUB_MARKER, code)

    with open(filepath, "wb") as file:
        file.write(code)

    shutil.copyfile(join(TESTS_DIR, testfilename), testfilepath)

    volume = ':'.join([directory, app.config['DOCKER_VOLUME']])

    proc = subprocess.Popen(
        ["docker", "run", "-n=false", "-rm", "-m", "64m", "-v", volume, "rto/java", TYPE_EXERCISE, className],
        stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    stdout, stderr = proc.communicate()

    try:
        os.remove(filepath)
    except OSError:
        pass

    try:
        os.remove(testfilepath)
    except OSError:
        pass

    try:
        os.rmdir(directory)
    except OSError:
        pass

    if stderr:
        return escape(stderr)
    else:
        return escape(stdout)


if __name__ == '__main__':
    app.run(host=app.config['HOST'], port=app.config['PORT'])

