from flask import Flask

from .core import DockerProcFactory, WorkspaceFactory
from .services import init_services

import codebot.api as api

def create_app(static_folder):
    app = Flask(__name__, instance_relative_config=True, static_url_path='', static_folder=static_folder)
    app.config.from_object('codebot.default_settings')
    app.config.from_pyfile('settings.cfg', silent=True)

    docker_proc_factory = DockerProcFactory()
    docker_proc_factory.init_app(app)
    
    workspace_factory = WorkspaceFactory()
    workspace_factory.init_app(app)

    init_services(workspace_factory, docker_proc_factory)
    
    app.register_blueprint(api.app, url_prefix='/api')
    
    return app
