import distutils.core
import json 

from os import listdir, makedirs
from os.path import isfile, isdir, join, basename, exists, splitext, getmtime

from jinja2 import Environment, PackageLoader
from yaml import load, dump


class ExtKey(object):
    CPP = '.cpp'
    HTML = '.html'    
    JAVA = '.java'


class ParserKey(object):
    STATE_NONE = 0
    STATE_META = 1
    STATE_CODE = 2

    DELIM_START_YAML = '---'
    DELIM_END_YAML = '...'
    DELIM_START_CODE = '*/'


class MetaKey(object):
    CODE = 'code'
    CLASSNAME = 'classname'
    TAGS = 'tags'    
    TITLE =  'title'
    TYPE = 'type'

    TYPE_DEFAULT = 'default'
    TYPE_STUB = 'stub'

    URL = 'url'


class TplKey(object):
    ACE_MODE = 'ace_mode'
    CLASSNAME = 'classname'    
    CODE = 'code'
    ENDPOINT = 'endpoint'
    FILE_TYPE = 'file_type'
    MENU_INCLUDE = 'menu_include'
    SEARCH_DATA_URL = 'search_data_url'


class HandlerMeta(object):
    def __init__(self, src_basedir, dest_basedir, test_basedir):
        self.src_basedir = src_basedir
        self.dest_basedir = dest_basedir
        self.test_basedir = test_basedir

    def convertToDestUrl(self, path):
        return path.replace(self.src_basedir, '')

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
    TESTFILE_SUFFIX = 'Test'

    def __init__(self, exercise_folder, template, template_vars):
        self.exercise_folder = exercise_folder
        self.template = template
        self.template_vars = template_vars
        self.cache = []

    def compatible(self, handle, handle_path):
        return isdir(handle_path) and handle_path.endswith(join(self.exercise_folder, handle))

    def process(self, handle, src_dir, handler_meta):

        filename = ''.join([handle, ExtKey.JAVA])
        output_name = ''.join([handle.lower(), ExtKey.HTML])
        dest_dir = handler_meta.convertToDest(src_dir)
        dest_url = handler_meta.convertToDestUrl(src_dir)
        src_path = join(src_dir, handle, filename)
        dest_path = join(dest_dir, output_name)
        dest_url_path = join(dest_url, output_name)

        testfile = ''.join([handle, self.TESTFILE_SUFFIX, ExtKey.JAVA])
        test_dir = handler_meta.getTestDir()

        code = []
        meta = []
        state = ParserKey.STATE_NONE

        # TODO(richard-to): Maybe use state pattern here later on
        with open(src_path, 'r') as f:
            for line in f:
                if line.rstrip() == ParserKey.DELIM_START_YAML:
                    state = ParserKey.STATE_META
                    meta.append(line)
                elif line.rstrip() == ParserKey.DELIM_END_YAML:
                    state = ParserKey.STATE_NONE
                    meta.append(line)
                elif line.rstrip() == ParserKey.DELIM_START_CODE:
                    state = ParserKey.STATE_CODE
                elif state == ParserKey.STATE_META:
                    meta.append(line)
                elif state == ParserKey.STATE_CODE:
                    code.append(line)

        meta = load(''.join(meta))
        meta[MetaKey.CLASSNAME] = filename[:-len(ExtKey.JAVA)]

        code = ''.join(code)

        if MetaKey.TYPE not in meta:
            meta[MetaKey.TYPE] = MetaKey.TYPE_DEFAULT

        if meta[MetaKey.TYPE] == MetaKey.TYPE_DEFAULT:
            meta[MetaKey.CODE] = code.strip()
        else:
            with open(join(test_dir, filename), 'w') as fw:
                fw.write(code)

        if not exists(dest_dir):
            makedirs(dest_dir)

        for key in self.template_vars:
            meta[key] = self.template_vars[key]

        with open(dest_path, 'w') as f:
            f.write(self.template.render(meta))

        with open(join(src_dir, handle, testfile), 'r') as fr:
            with open(join(test_dir, testfile), 'w') as fw:
                fw.write(fr.read())


class JavaExampleHandler(object):
    def __init__(self, template, template_vars):
        self.template = template
        self.template_vars = template_vars
        self.cache = []

    def compatible(self, handle, handle_path):
        ext = None
        if isfile(handle_path):
            filename, ext = splitext(handle)
        return ext == ExtKey.JAVA

    def process(self, filename, src_dir, handler_meta):
        output_name = filename.replace(ExtKey.JAVA, ExtKey.HTML).lower()
        dest_dir = handler_meta.convertToDest(src_dir)
        dest_url = handler_meta.convertToDestUrl(src_dir)
        src_path = join(src_dir, filename)
        dest_path = join(dest_dir, output_name)
        dest_url_path = join(dest_url, output_name)

        code = []
        meta = []
        state = ParserKey.STATE_NONE

        # TODO(richard-to): Maybe use state pattern here later on
        with open(src_path, 'r') as f:
            for line in f:
                if line.rstrip() == ParserKey.DELIM_START_YAML:
                    state = ParserKey.STATE_META
                    meta.append(line)
                elif line.rstrip() == ParserKey.DELIM_END_YAML:
                    state = ParserKey.STATE_NONE
                    meta.append(line)
                elif line.rstrip() == ParserKey.DELIM_START_CODE:
                    state = ParserKey.STATE_CODE
                elif state == ParserKey.STATE_META:
                    meta.append(line)
                elif state == ParserKey.STATE_CODE:
                    code.append(line)

        meta = load(''.join(meta))

        code = ''.join(code)
        meta[MetaKey.CODE] = code.strip()

        if MetaKey.CLASSNAME not in meta:
            meta[MetaKey.CLASSNAME] = filename[:-len(ExtKey.JAVA)]

        if not exists(dest_dir):
            makedirs(dest_dir)

        for key in self.template_vars:
            meta[key] = self.template_vars[key]

        with open(dest_path, 'w') as f:
            f.write(self.template.render(meta))

        cache_object = {}
        cache_object[MetaKey.TITLE] = meta[MetaKey.TITLE]
        cache_object[MetaKey.URL] = dest_url_path
        cache_object[MetaKey.TAGS] = meta.get(MetaKey.TAGS, "")

        self.cache.append(cache_object)            


class CPPExampleHandler(object):
    def __init__(self, template, template_vars):
        self.template = template
        self.template_vars = template_vars
        self.cache = []        

    def compatible(self, handle, handle_path):
        ext = None
        if isfile(handle_path):
            filename, ext = splitext(handle)
        return ext == ExtKey.CPP

    def process(self, filename, src_dir, handler_meta):
        output_name = filename.replace(ExtKey.CPP, ExtKey.HTML).lower()
        dest_dir = handler_meta.convertToDest(src_dir)
        dest_url = handler_meta.convertToDestUrl(src_dir)
        src_path = join(src_dir, filename)
        dest_path = join(dest_dir, output_name)
        dest_url_path = join(dest_url, output_name)

        code = []
        meta = []
        state = ParserKey.STATE_NONE

        # TODO(richard-to): Maybe use state pattern here later on
        with open(src_path, 'r') as f:
            for line in f:
                if line.rstrip() == ParserKey.DELIM_START_YAML:
                    state = ParserKey.STATE_META
                    meta.append(line)
                elif line.rstrip() == ParserKey.DELIM_END_YAML:
                    state = ParserKey.STATE_NONE
                    meta.append(line)
                elif line.rstrip() == ParserKey.DELIM_START_CODE:
                    state = ParserKey.STATE_CODE
                elif state == ParserKey.STATE_META:
                    meta.append(line)
                elif state == ParserKey.STATE_CODE:
                    code.append(line)

        meta = load(''.join(meta))

        code = ''.join(code)
        meta[MetaKey.CODE] = code.strip()

        if MetaKey.CLASSNAME not in meta:
            meta[MetaKey.CLASSNAME] = filename[:-len(ExtKey.CPP)]

        if not exists(dest_dir):
            makedirs(dest_dir)

        for key in self.template_vars:
            meta[key] = self.template_vars[key]

        with open(dest_path, 'w') as f:
            f.write(self.template.render(meta))

        cache_object = {}
        cache_object[MetaKey.TITLE] = meta[MetaKey.TITLE]
        cache_object[MetaKey.URL] = dest_url_path
        cache_object[MetaKey.TAGS] = meta.get(MetaKey.TAGS, "")

        self.cache.append(cache_object)


class SearchDataWriterJSON(object):
    def write(self, data, dest_dir, file):
        if not exists(dest_dir):
            makedirs(dest_dir)

        dest_path = join(dest_dir, file)        
        with open(dest_path, 'w') as f:
            f.write(json.dumps(data))        


class BuildConfig(object):
    SRC_DIR = 'src'
    SITE_DIR = 'site'
    TEST_DIR = 'api_server/instance/tests'

    COPY_FILES = ['.js', '.css', '.png', '.gif']
    IGNORE_DIRS = '_'

    DATA_DIR = 'data'

    EXERCISES_DIR = 'exercises'

    TEMPLATES_DIR = 'src/_templates'    

    EXAMPLES_TPL_NAME = 'examples.tpl.html'
    EXERCISES_TPL_NAME = 'exercises.tpl.html'

    def __init__(self, pkg_name):
        self.env = Environment(loader=PackageLoader(pkg_name, self.TEMPLATES_DIR))
        self.examples_tpl = self.env.get_template(self.EXAMPLES_TPL_NAME)
        self.exercises_tpl = self.env.get_template(self.EXERCISES_TPL_NAME)

    def get_tpl_path_for(self, path):
        return ''.join([self.TEMPLATES_DIR, path])

    def get_data_path(self):
        return join(self.SITE_DIR, self.DATA_DIR)

    def get_data_path_for(self, file):
        return join(self.SITE_DIR, self.DATA_DIR, file)

def main():

    build_config = BuildConfig(__name__)

    handler_meta = HandlerMeta(BuildConfig.SRC_DIR, BuildConfig.SITE_DIR, BuildConfig.TEST_DIR)

    skipdir_handler = SkipDirHandler(BuildConfig.IGNORE_DIRS)

    java_exercise_handler = JavaExerciseHandler(
        BuildConfig.EXERCISES_DIR, 
        build_config.exercises_tpl,
        {
            TplKey.ENDPOINT: '/api/compile/java/exercise',
            TplKey.ACE_MODE: 'java',
            TplKey.FILE_TYPE: 'java',
            TplKey.MENU_INCLUDE: 'includes/java-exercises-sidebar.html'
        }
    )
    
    copy_handler = CopyHandler(BuildConfig.COPY_FILES)

    java_example_handler = JavaExampleHandler(
        build_config.examples_tpl, 
        {
            TplKey.ENDPOINT: '/api/compile/java',
            TplKey.ACE_MODE: 'java',
            TplKey.FILE_TYPE: 'java',
            TplKey.MENU_INCLUDE: 'includes/java-examples-sidebar.html',
            TplKey.SEARCH_DATA_URL: '/data/java-examples.json'            
        }
    )

    cpp_example_handler = CPPExampleHandler(
        build_config.examples_tpl,
        {
            TplKey.ENDPOINT: '/api/compile/cpp',
            TplKey.ACE_MODE: 'c_cpp',
            TplKey.FILE_TYPE: 'cpp',
            TplKey.MENU_INCLUDE: 'includes/cpp-examples-sidebar.html',
            TplKey.SEARCH_DATA_URL: '/data/cpp-examples.json'
        }
    )

    dirwalker = DirWalker([skipdir_handler, java_exercise_handler, copy_handler, java_example_handler, cpp_example_handler])
    dirwalker.walk(handler_meta)


    search_data_writer = SearchDataWriterJSON()


    search_data_writer.write(
        java_example_handler.cache, 
        build_config.get_data_path(),
        'java-examples.json'
    )

    search_data_writer.write(
        cpp_example_handler.cache, 
        build_config.get_data_path(),
        'cpp-examples.json'
    )

if __name__ == '__main__':
    main()
