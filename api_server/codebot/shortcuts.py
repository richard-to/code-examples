from urlparse import urlparse
from flask import escape

def run_code(service, request):
    classname = request.form['classname']
    if not service.is_valid_classname(classname):
        return escape("Invalid file name.")
    
    code = request.form['code']
    urlparts = urlparse(request.environ.get('HTTP_REFERER'))
    workspace = service.create_workspace(classname, code, urlparts[2])
    result = service.run(workspace)
    return escape(result)