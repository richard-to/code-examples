import os
import random
import re
import shutil
import subprocess
import time

from flask import Flask, escape, request

javaClassNameRegex = re.compile("[a-zA-Z_$][a-zA-Z\d_$]*")

app = Flask(__name__, instance_relative_config=True)
app.config.from_object('default_settings')
app.config.from_pyfile('settings.cfg', silent=True)

BASE_DIR = app.instance_path + app.config['COMPILE_QUEUE_DIR']


@app.route('/compile', methods=['POST'])
def compile():
    className = request.form['className']
    if not javaClassNameRegex.match(className):
        return escape("Invalid file name.")
    code = request.form['code']

    temp_dir = ''.join(['java_', str(time.time()), '_', str(random.randint(1, 1000)), '/'])

    directory = ''.join([BASE_DIR, temp_dir])
    filepath = ''.join([directory, className, '.java'])
    os.mkdir(directory)

    with open(filepath, "wb") as file:
        file.write(code)

    volume = ''.join([directory, ":/home/javabot/code"])

    proc = subprocess.Popen(
        ["docker", "run", "-n=false", "-m", "64m", "-v", volume, "rto/java", className],
        stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    stdout, stderr = proc.communicate()

    try:
        os.remove(filepath)
    except OSError:
        pass

    try:
        os.remove(''.join([directory, className, '.class']))
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

