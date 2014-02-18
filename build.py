import distutils.core

from os import listdir, makedirs
from os.path import isfile, isdir, join, basename, exists, splitext, getmtime

from jinja2 import Environment, PackageLoader
from yaml import load, dump


class HandlerMeta(object):
    def __init__(self, src_basedir, dest_basedir, test_basedir):
        self.src_basedir = src_basedir
        self.dest_basedir = dest_basedir
        self.test_basedir = test_basedir

    def convertToDest(self, path):
        return path.replace(self.src_basedir, self.dest_basedir)

    def getTestDir(self):
        return self.test_basedir


class DirWalker(object):
    def __init__(self, handlers):
        self.handlers = handlers

    def walk(self, handler_meta):
        self._walk(handler_meta.src_basedir, handler_meta)

    def _walk(self, directory, handler_meta):
        for handle in listdir(directory):
            handle_path = join(directory, handle)
            compatible = False
            for handler in self.handlers:
                if handler.compatible(handle, handle_path):
                    compatible = True
                    handler.process(handle, directory, handler_meta)
                    break
            if not compatible and isdir(handle_path):
                self._walk(handle_path, handler_meta)


class SkipDirHandler(object):
    def __init__(self, prefix):
        self.prefix = prefix

    def compatible(self, handle, handle_path):
        if isdir(handle_path):
            return handle.startswith(self.prefix)
        return False

    def process(self, filename, src_dir, handler_meta):
        pass


class CopyHandler(object):
    def __init__(self, filetypes=[]):
        self.filetypes = filetypes

    def compatible(self, handle, handle_path):
        ext = None
        if isfile(handle_path):
            filename, ext = splitext(handle)
        return ext in self.filetypes

    def process(self, filename, src_dir, handler_meta):
        dest_dir = handler_meta.convertToDest(src_dir)
        src_path = join(src_dir, filename)
        dest_path = join(dest_dir, filename)

        if exists(dest_path) and getmtime(src_path) <= getmtime(dest_path):
            return

        if not exists(dest_dir):
            makedirs(dest_dir)

        with open(src_path, 'r') as fr:
            with open(dest_path, 'w') as fw:
                fw.write(fr.read())


class JavaExerciseHandler(object):
    STATE_NONE = 0
    STATE_META = 1
    STATE_CODE = 2

    DELIM_START_YAML = '---'
    DELIM_END_YAML = '...'
    DELIM_START_CODE = '*/'

    EXT_JAVA = '.java'
    EXT_HTML = '.html'

    TESTFILE_SUFFIX = 'Test'

    META_CODE = 'code'
    META_CLASS_NAME = 'class_name'
    META_TYPE = 'type'

    META_TYPE_DEFAULT = 'default'
    META_TYPE_STUB = 'stub'

    def __init__(self, exercise_folder, template):
        self.exercise_folder = exercise_folder
        self.template = template

    def compatible(self, handle, handle_path):
        return isdir(handle_path) and handle_path.endswith(join(self.exercise_folder, handle))

    def process(self, handle, src_dir, handler_meta):

        filename = ''.join([handle, self.EXT_JAVA])
        output_name = ''.join([handle.lower(), self.EXT_HTML])
        dest_dir = handler_meta.convertToDest(src_dir)
        src_path = join(src_dir, handle, filename)
        dest_path = join(dest_dir, output_name)

        testfile = ''.join([handle, self.TESTFILE_SUFFIX, self.EXT_JAVA])
        test_dir = handler_meta.getTestDir()

        code = []
        meta = []
        state = self.STATE_NONE

        # TODO(richard-to): Maybe use state pattern here later on
        with open(src_path, 'r') as f:
            for line in f:
                if line.rstrip() == self.DELIM_START_YAML:
                    state = self.STATE_META
                    meta.append(line)
                elif line.rstrip() == self.DELIM_END_YAML:
                    state = self.STATE_NONE
                    meta.append(line)
                elif line.rstrip() == self.DELIM_START_CODE:
                    state = self.STATE_CODE
                elif state == self.STATE_META:
                    meta.append(line)
                elif state == self.STATE_CODE:
                    code.append(line)

        meta = load(''.join(meta))
        meta[self.META_CLASS_NAME] = filename[:-len(self.EXT_JAVA)]

        code = ''.join(code)

        if self.META_TYPE not in meta:
            meta[self.META_TYPE] = self.META_TYPE_DEFAULT

        if meta[self.META_TYPE] == self.META_TYPE_DEFAULT:
            meta[self.META_CODE] = code.strip()
        else:
            with open(join(test_dir, filename), 'w') as fw:
                fw.write(code)

        if not exists(dest_dir):
            makedirs(dest_dir)

        with open(dest_path, 'w') as f:
            f.write(self.template.render(meta))

        with open(join(src_dir, handle, testfile), 'r') as fr:
            with open(join(test_dir, testfile), 'w') as fw:
                fw.write(fr.read())


class JavaExampleHandler(object):
    STATE_NONE = 0
    STATE_META = 1
    STATE_CODE = 2

    DELIM_START_YAML = '---'
    DELIM_END_YAML = '...'
    DELIM_START_CODE = '*/'

    EXT_JAVA = '.java'
    EXT_HTML = '.html'

    META_CODE = 'code'
    META_CLASS_NAME = 'class_name'

    def __init__(self, template):
        self.template = template

    def compatible(self, handle, handle_path):
        ext = None
        if isfile(handle_path):
            filename, ext = splitext(handle)
        return ext == self.EXT_JAVA

    def process(self, filename, src_dir, handler_meta):
        output_name = filename.replace(self.EXT_JAVA, self.EXT_HTML).lower()
        dest_dir = handler_meta.convertToDest(src_dir)
        src_path = join(src_dir, filename)
        dest_path = join(dest_dir, output_name)

        code = []
        meta = []
        state = self.STATE_NONE

        # TODO(richard-to): Maybe use state pattern here later on
        with open(src_path, 'r') as f:
            for line in f:
                if line.rstrip() == self.DELIM_START_YAML:
                    state = self.STATE_META
                    meta.append(line)
                elif line.rstrip() == self.DELIM_END_YAML:
                    state = self.STATE_NONE
                    meta.append(line)
                elif line.rstrip() == self.DELIM_START_CODE:
                    state = self.STATE_CODE
                elif state == self.STATE_META:
                    meta.append(line)
                elif state == self.STATE_CODE:
                    code.append(line)

        meta = load(''.join(meta))

        code = ''.join(code)
        meta[self.META_CODE] = code.strip()

        if self.META_CLASS_NAME not in meta:
            meta[self.META_CLASS_NAME] = filename[:-len(self.EXT_JAVA)]

        if not exists(dest_dir):
            makedirs(dest_dir)

        with open(dest_path, 'w') as f:
            f.write(self.template.render(meta))


def main():

    # TODO(richard-to): Move hardcoded settings out of here
    SRC_DIR = 'src'
    SITE_DIR = 'site'
    TEST_DIR = 'api_server/instance/tests'

    COPY_FILES = ['.js', '.css']
    IGNORE_DIRS = '_'
    EXERCISES_DIR = 'exercises'

    TEMPLATES_DIR = 'src/_templates'
    EXAMPLES_TPL_NAME = 'examples.tpl.html'
    EXERCISES_TPL_NAME = 'exercises.tpl.html'
    SIDEBAR_TPL_NAME = 'sidebar.tpl.html'

    SIDEBAR_INCLUDE = ''.join([TEMPLATES_DIR, '/includes/sidebar.html'])

    env = Environment(loader=PackageLoader(__name__, TEMPLATES_DIR))
    examples_tpl = env.get_template(EXAMPLES_TPL_NAME)
    exercises_tpl = env.get_template(EXERCISES_TPL_NAME)

    handler_meta = HandlerMeta(SRC_DIR, SITE_DIR, TEST_DIR)

    skipdir_handler = SkipDirHandler('_')
    java_exercise_handler = JavaExerciseHandler(EXERCISES_DIR, env.get_template(exercises_tpl))
    copy_handler = CopyHandler(COPY_FILES)
    java_example_handler = JavaExampleHandler(env.get_template(examples_tpl))

    dirwalker = DirWalker([skipdir_handler, java_exercise_handler, copy_handler, java_example_handler])
    dirwalker.walk(handler_meta)


if __name__ == '__main__':
    main()
