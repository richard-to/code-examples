import hashlib
import os
import random
import re
import shutil
import subprocess
import time

from os.path import join


class DockerProcFactory(object):
    def init_app(self, app):
        self.container_name = app.config['DOCKER_CONTAINER']
        self.mem = app.config['DOCKER_MEM']
        self.volume = app.config['DOCKER_VOLUME']

    def create_proc(self, directory, classname, type):
        volume = ':'.join([directory, self.volume])
        proc = subprocess.Popen([
            "docker", "run", "--rm", 
            "-m", self.mem, 
            "-v", volume, 
            self.container_name, 
            type, classname],
            stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        return proc


class Workspace(object):
    def __init__(self):
        self.filepath = None
        self.test_dir = None
        self.test_filepath = None       
        self.directory = None
        self.filename = None
        self.classname = None

    def set_directory(self, base_dir, prefix):
        temp_dir = ''.join([prefix, str(time.time()), '_', str(random.randint(1, 1000)), '/'])
        self.directory = join(base_dir, temp_dir)
        return self

    def set_filepath(self, classname, ext):
        self.classname = classname
        self.filename = ''.join([classname, ext])        
        self.filepath = join(self.directory, self.filename)
        return self

    def set_testdir(self, base_dir, path):
        print path
        m = hashlib.md5()        
        m.update(path)
        self.test_dir = join(base_dir, m.hexdigest())
        return self

    def write_stub(self, code, stub_marker):
        stub_filepath = join(self.test_dir, self.filename)
        with open(stub_filepath, 'r') as file:
            code = file.read().replace(stub_marker, code)
        return self.write_code(code)

    def write_code(self, code):
        os.mkdir(self.directory)
        with open(self.filepath, "wb") as file:
            file.write(code)
        return self

    def copy_tests(self, classname, test_suffix, ext):
        test_filename = ''.join([classname, test_suffix, ext])
        self.test_filepath = join(self.directory, test_filename)
        shutil.copyfile(join(self.test_dir, test_filename), self.test_filepath)
        return self

    def remove(self):
        try:
            os.remove(self.filepath)
        except OSError:
            pass

        if self.test_filepath:
            try:
                os.remove(self.test_filepath)
            except OSError:
                pass

        try:
            os.rmdir(self.directory)
        except OSError:
            pass


class WorkspaceFactory(object):
    def init_app(self, app):
        self.base_dir = app.instance_path + app.config['COMPILE_QUEUE_DIR']
        self.test_dir = app.instance_path + app.config['TEST_DIR']        
        self.test_suffix = app.config['TEST_SUFFIX']
        self.test_stub_marker = app.config['TEST_STUB_MARKER']

    def create_example(self, classname, code, prefix, ext):
        workspace = Workspace()
        workspace.set_directory(self.base_dir, prefix) \
            .set_filepath(classname, ext).write_code(code)
        return workspace

    def create_exercise(self, classname, code, prefix, ext, url_path):
        workspace = Workspace()
        workspace.set_directory(self.base_dir, prefix) \
            .set_filepath(classname, ext).write_code(code) \
            .set_testdir(self.test_dir, url_path) \
            .copy_tests(classname, self.test_suffix, ext)
        return workspace

    def create_stub_exercise(self, classname, code, prefix, ext, url_path):
        workspace = Workspace()
        workspace.set_directory(self.base_dir, prefix) \
            .set_filepath(classname, ext) \
            .set_testdir(self.test_dir, url_path) \
            .write_stub(code, self.test_stub_marker) \
            .copy_tests(classname, self.test_suffix, ext)
        return workspace


class CodebotService(object):
    def init(self, workspace_factory, docker_proc_factory, classname_regex, prefix, ext, type):
        self.workspace_factory = workspace_factory
        self.docker_proc_factory = docker_proc_factory
        self.classname_regex = classname_regex       
        self.prefix = prefix
        self.ext = ext
        self.type = type

    def is_valid_classname(self, classname):
        return self.classname_regex.match(classname)

    def create_workspace(self, classname, code, url_path):
        return self.workspace_factory.create_example(classname, code, self.prefix, self.ext)

    def run(self, workspace):
        proc = self.docker_proc_factory.create_proc(
            workspace.directory, workspace.classname, self.type)
        stdout, stderr = proc.communicate()

        workspace.remove()

        if stderr:
            return stderr
        else:
            return stdout


class JaveExampleService(CodebotService):
    def init(self, workspace_factory, docker_proc_factory):
        super(JaveExampleService, self).init(
            workspace_factory, 
            docker_proc_factory,
            re.compile("[a-zA-Z_$][a-zA-Z\d_$]*"),
            'java_',
            '.java',
            'java-example')


class JaveExerciseService(CodebotService):
    def init(self, workspace_factory, docker_proc_factory):
        super(JaveExerciseService, self).init(
            workspace_factory, 
            docker_proc_factory,
            re.compile("[a-zA-Z_$][a-zA-Z\d_$]*"),
            'java_',
            '.java',
            'java-exercise')

    def create_workspace(self, classname, code, url_path):
        return self.workspace_factory.create_exercise(classname, code, self.prefix, self.ext, url_path)


class JaveStubExerciseService(JaveExerciseService):
    def create_workspace(self, classname, code, url_path):
        return self.workspace_factory.create_stub_exercise(classname, code, self.prefix, self.ext, url_path)


class CppExampleService(CodebotService):
    def init(self, workspace_factory, docker_proc_factory):
        super(CppExampleService, self).init(
            workspace_factory, 
            docker_proc_factory,
            re.compile("[a-zA-Z_$][a-zA-Z\d_$]*"),
            'cpp_',
            '.cpp',
            'cpp-example')
