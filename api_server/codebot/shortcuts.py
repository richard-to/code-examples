from flask import escape

def run_code(service, request):
    classname = request.form['classname']
    if not service.is_valid_classname(classname):
        return escape("Invalid file name.")
    
    code = request.form['code']        
    workspace = service.create_workspace(classname, code)
    result = service.run(workspace)
    return escape(result)