import os
import random
import re
import shutil
import subprocess
import time

from flask import Flask, escape, request

javaClassNameRegex = re.compile("[a-zA-Z_$][a-zA-Z\d_$]*")

app = Flask(__name__)
app.debug = True

base_dir = '/vagrant/app/compile_queue/'

@app.route('/compile', methods=['POST'])
def compile():
    className = request.form['className']
    if not javaClassNameRegex.match(className):
        return escape("Invalid file name.")
    code = request.form['code']

    temp_dir = ''.join(['java_', str(time.time()), '_', str(random.randint(1, 1000)), '/'])
    directory = ''.join([base_dir, temp_dir])
    filepath = ''.join([directory, className, '.java'])
    os.mkdir(directory)

    with open(filepath, "wb") as file:
        file.write(code)

    volume = ''.join([directory, ":/root/code"])

    proc = subprocess.Popen(
        ["docker", "run", "-m=16m", "-v", volume, "rto/java", "/root/compile_and_run_java.sh", className],
        stdout=subprocess.PIPE, stderr=subprocess.PIPE)

    stdout, stderr = proc.communicate()

    try:
        os.remove(''.join([directory, className, '.java']))
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
    app.run(host='192.168.245.5')
