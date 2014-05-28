import distutils.core
import json 
import hashlib

from os import listdir, makedirs
from os.path import isfile, isdir, join, basename, exists, splitext, getmtime

import markdown

from jinja2 import Environment, PackageLoader
from yaml import load, dump


# Define some constants

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
    CLASSNAME = 'classname'    
    CODE = 'code'
    EXERCISES = 'exercises'
    INSTRUCTIONS = 'instructions'    
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
    JS_EDITOR = 'js_editor'
    RUN_LABEL = 'run_label'
    RESET_LABEL = 'reset_label'
    INFO_LABEL = 'info_label'


def parseCodeFile(src_path):
    """Simple lexer to parse code files and separate metadata.

    Metadata will be returned as a dict containing all parameters
    found in source code.

    Args:
        src_path: Full path of file to read and parse.
    Returns:
        A list with source code and metadata areas separated.
    """
    code = []
    meta = []        
    state = ParserKey.STATE_NONE        
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
    return [code, meta]


class HandlerMeta(object):
    """Helper methods to translate filepath from source to destination."""

    def __init__(self, src_basedir, dest_basedir, test_basedir):
        self.src_basedir = src_basedir
        self.dest_basedir = dest_basedir
        self.test_basedir = test_basedir

    def convertToDestUrl(self, path):
        """Gets source path to destination url."""
        return path.replace(self.src_basedir, '')

    def convertToDest(self, path):
        """Gets source path to destination file path."""
        return path.replace(self.src_basedir, self.dest_basedir)

    def generateTestDir(self, path):
        """Generates a test directory based on expected url path"""
        m = hashlib.md5()        
        m.update(path)
        return m.hexdigest()

    def getTestDir(self):
        """Gets the output directory for unit test files."""
        return self.test_basedir    

    def toWebFriendlyFilename(self, filename):
        """Renames source filename to a more web friendly filename.

        Separates camel case with dash. Replaces underscores with dash.
        Also makes filename all lowercase.

        Returns:
            Web friendly filename
        """
        web_url = []
        last_was_lower = None
        for c in filename:
            is_lower = c.islower()
            if c == '.':
                break
            elif not is_lower and last_was_lower:
                web_url.append('-')
                web_url.append(c.lower())
            elif c == '_':
                web_url.append('-')
            else:
                web_url.append(c.lower())
            last_was_lower = is_lower
        web_url.append(ExtKey.HTML)
        return ''.join(web_url)


class DirWalker(object):
    """Recursively walk through directory and process files/dirs.

    Files/dirs are processed using Handler classes.
    """

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
    """Ignores directories with specified prefix.

    Currently only one prefix can be used, which is 
    good enough for now.
    """

    def __init__(self, prefix):
        self.prefix = prefix

    def compatible(self, handle, handle_path):
        if isdir(handle_path):
            return handle.startswith(self.prefix)
        return False

    def process(self, filename, src_dir, handler_meta):
        pass


class CopyHandler(object):
    """Copies files in whitelist to output directory"""

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
    """Parses Java example files and create HTML page.

    Attributes:
        cache: Metadata extracted from Java files.
    """    
    def __init__(self, exercise_folder, template, template_vars, test_file_suffix):
        self.exercise_folder = exercise_folder
        self.template = template
        self.template_vars = template_vars
        self.test_file_suffix = test_file_suffix
        self.cache = []

    def compatible(self, handle, handle_path):
        """Checks if handle is a Java exercise folder

        Args:
            handle: Name of the file/directory
            handle_path: File path

        Returns:
            Whether folder contains Java exercise files
        """
        filename = ''.join([handle, ExtKey.JAVA])
        return (isdir(handle_path) and 
                handle_path.startswith(self.exercise_folder) and 
                exists(join(handle_path, filename)))

    def process(self, handle, src_dir, handler_meta):
        """Extracts metadata from Java exercise files and creates HTML file

        Args:
            filename: Name of file
            src_dir: Directory of source file
            handler_meta: Instance of HandlerMeta class
        """
        filename = ''.join([handle, ExtKey.JAVA])
        output_name = handler_meta.toWebFriendlyFilename(filename)
        dest_dir = handler_meta.convertToDest(src_dir)
        dest_url = handler_meta.convertToDestUrl(src_dir)
        src_path = join(src_dir, handle, filename)
        dest_path = join(dest_dir, output_name)
        dest_url_path = join(dest_url, output_name)

        testfile = ''.join([handle, self.test_file_suffix, ExtKey.JAVA])
        test_dir = join(handler_meta.getTestDir(), handler_meta.generateTestDir(dest_url_path))
        
        if not exists(test_dir):
            makedirs(test_dir)

        if not exists(dest_dir):
            makedirs(dest_dir)

        code, meta = parseCodeFile(src_path)
        code = ''.join(code)

        meta[MetaKey.CLASSNAME] = filename[:-len(ExtKey.JAVA)]
        meta[MetaKey.INSTRUCTIONS] = markdown.markdown(meta[MetaKey.INSTRUCTIONS])

        if MetaKey.TYPE not in meta:
            meta[MetaKey.TYPE] = MetaKey.TYPE_DEFAULT

        if meta[MetaKey.TYPE] == MetaKey.TYPE_DEFAULT:
            meta[MetaKey.CODE] = code.strip()
        else:
            with open(join(test_dir, filename), 'w') as fw:
                fw.write(code)

        for key in self.template_vars:
            meta[key] = self.template_vars[key]

        with open(dest_path, 'w') as f:
            f.write(self.template.render(meta))

        with open(join(src_dir, handle, testfile), 'r') as fr:
            with open(join(test_dir, testfile), 'w') as fw:
                fw.write(fr.read())


class JavaExampleHandler(object):
    """Parses Java example files and create HTML page.

    Attributes:
        cache: Metadata extracted from Java files.
    """

    def __init__(self, template, template_vars):
        self.template = template
        self.template_vars = template_vars
        self.cache = []

    def compatible(self, handle, handle_path):
        """Checks for Java file by extension

        Args:
            handle: Name of the file/directory
            handle_path: File path

        Returns:
            Whether file is Java or not
        """                    
        ext = None
        if isfile(handle_path):
            filename, ext = splitext(handle)
        return ext == ExtKey.JAVA

    def process(self, filename, src_dir, handler_meta):
        """Extracts metadata from Java example file and creates HTML file

        Args:
            filename: Name of file
            src_dir: Directory of source file
            handler_meta: Instance of HandlerMeta class
        """          
        output_name = handler_meta.toWebFriendlyFilename(filename)
        dest_dir = handler_meta.convertToDest(src_dir)
        dest_url = handler_meta.convertToDestUrl(src_dir)
        src_path = join(src_dir, filename)
        dest_path = join(dest_dir, output_name)
        dest_url_path = join(dest_url, output_name)

        code, meta = parseCodeFile(src_path)
        code = ''.join(code)
        meta[MetaKey.CODE] = code.strip()
        meta[MetaKey.EXERCISES] = markdown.markdown(meta[MetaKey.EXERCISES])

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
    """Parses C++ files and create HTML page.

    Attributes:
        cache: Metadata extracted from C++ files.
    """

    def __init__(self, template, template_vars):
        self.template = template
        self.template_vars = template_vars
        self.cache = []        

    def compatible(self, handle, handle_path):
        """Checks for C++ file by extension

        Args:
            handle: Name of the file/directory
            handle_path: File path

        Returns:
            Whether file is C++ or not
        """
        ext = None
        if isfile(handle_path):
            filename, ext = splitext(handle)
        return ext == ExtKey.CPP

    def process(self, filename, src_dir, handler_meta):
        """Extracts metadata from C++ file and create HTML file

        Args:
            filename: Name of file
            src_dir: Directory of source file
            handler_meta: Instance of HandlerMeta class
        """        
        output_name = handler_meta.toWebFriendlyFilename(filename)
        dest_dir = handler_meta.convertToDest(src_dir)
        dest_url = handler_meta.convertToDestUrl(src_dir)
        src_path = join(src_dir, filename)
        dest_path = join(dest_dir, output_name)
        dest_url_path = join(dest_url, output_name)

        code, meta = parseCodeFile(src_path)
        code = ''.join(code)
        meta[MetaKey.CODE] = code.strip()
        meta[MetaKey.EXERCISES] = markdown.markdown(meta[MetaKey.EXERCISES])

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
    """Writes search index in JSON format.

    Uses metadata extracted by the handler classes to
    create the search index. Currently used with 
    Twitter typeahead library.
    """

    def write(self, data, dest_dir, file):
        """Writes data in JSON format."""
        if not exists(dest_dir):
            makedirs(dest_dir)

        dest_path = join(dest_dir, file)        
        with open(dest_path, 'w') as f:
            f.write(json.dumps(data))        



def main():
    """Typical setup for build script."""

    # Load Build Settings
    settings = None
    with open('build_settings.yaml', 'r') as f:
        settings = load(f.read())

    # Init Jinja2 Template Env
    env = Environment(loader=PackageLoader(__name__, settings['templates-dir']))
    examples_tpl = env.get_template(settings['examples-tpl'])
    exercises_tpl = env.get_template(settings['exercises-tpl'])

    # Init File/Dir Handlers
    handler_meta = HandlerMeta(
        settings['src-dir'], settings['site-dir'], settings['test-dir'])

    skipdir_handler = SkipDirHandler(settings['ignore-dirs'])

    java_exercise_handler = JavaExerciseHandler(
        settings['exercises-dir'],
        exercises_tpl,
        settings['java-exercise-handler'],
        settings['test-file-suffix'])
    
    copy_handler = CopyHandler(settings['copy-files'])

    java_example_handler = JavaExampleHandler(
        examples_tpl,
        settings['java-example-handler'])

    cpp_example_handler = CPPExampleHandler(
        examples_tpl,
        settings['cpp-example-handler'])

    # Handlers tell directory what to do
    dirwalker = DirWalker([
        skipdir_handler, 
        java_exercise_handler, 
        copy_handler, 
        java_example_handler, 
        cpp_example_handler])
    dirwalker.walk(handler_meta)

    # Writes file meta captured from handlers to build search index
    search_data_writer = SearchDataWriterJSON()
    search_data_writer.write(
        java_example_handler.cache, 
        join(settings['site-dir'], settings['data-dir']),
        settings['java-example-search'])
    search_data_writer.write(
        cpp_example_handler.cache, 
        join(settings['site-dir'], settings['data-dir']),
        settings['cpp-example-search'])


if __name__ == '__main__':
    main()
