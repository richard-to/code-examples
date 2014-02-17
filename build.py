import distutils.core

from os import listdir, makedirs
from os.path import isfile, isdir, join, basename, exists, splitext, getmtime

from jinja2 import Environment, PackageLoader
from yaml import load, dump


class FileHandler(object):

    def __init__(self, file_actions, default_action):
        self.file_actions = file_actions
        self.default_action = default_action

    def process(self, filename, src_dir, dest_dir):
        filename_without_ext, ext = splitext(filename)
        if ext in self.file_actions:
            self.file_actions[ext].process(filename, src_dir, dest_dir)
        else:
            self.default_action.process(filename, src_dir, dest_dir)


class DirHandler(object):
    IGNORE_PREFIX = '_'

    def __init__(self, src_dir, dest_dir, file_handler):
        self.file_handler = file_handler
        self.src_basedir = src_dir
        self.dest_basedir = dest_dir

    def process(self):
        self._walk(self.src_basedir)

    def _walk(self, directory):
        for handle in listdir(directory):
            handle_path = join(directory, handle)
            if isdir(handle_path) and not handle.startswith(self.IGNORE_PREFIX):
                self._walk(handle_path)
            elif isfile(handle_path):
                dest_dir = directory.replace(self.src_basedir, self.dest_basedir)
                self.file_handler.process(handle, directory, dest_dir)


class CopyHandler(object):
    def process(self, filename, src_dir, dest_dir):
        src_path = join(src_dir, filename)
        dest_path = join(dest_dir, filename)

        if exists(dest_path) and getmtime(src_path) <= getmtime(dest_path):
            return

        if not exists(dest_dir):
            makedirs(dest_dir)

        with open(src_path, 'r') as fr:
            with open(dest_path, 'w') as fw:
                fw.write(fr.read())


class JavaHandler(object):
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

    def process(self, filename, src_dir, dest_dir):
        output_name = filename.replace(self.EXT_JAVA, self.EXT_HTML).lower()

        src_path = join(src_dir, filename)
        dest_path = join(dest_dir, output_name)

        if exists(dest_path) and getmtime(src_path) <= getmtime(dest_path):
            return

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

    TEMPLATES_DIR = 'src/_templates'
    EXAMPLES_TPL_NAME = 'examples.tpl.html'

    env = Environment(loader=PackageLoader(__name__, TEMPLATES_DIR))
    examples_tpl = env.get_template(EXAMPLES_TPL_NAME)

    file_actions = {
        JavaHandler.EXT_JAVA: JavaHandler(examples_tpl)
    }

    default_action = CopyHandler()
    file_handler = FileHandler(file_actions, default_action)
    dir_handler = DirHandler(SRC_DIR, SITE_DIR, file_handler)
    dir_handler.process()


if __name__ == '__main__':
    main()
