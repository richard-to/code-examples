from flask import Blueprint, escape, request

from ..shortcuts import run_code
from ..services import java_example, java_exercise, java_stub_exercise, cpp_example

bp = Blueprint('api', __name__)


@bp.route('/compile/java', methods=['POST'])
def compile_java():
    return run_code(java_example, request)


@bp.route('/compile/java/exercise', methods=['POST'])
def compile_java_exercise():
    if request.form['type'] == 'stub':
        exercise_service = java_stub_exercise
    else:
        exercise_service = java_exercise
    return run_code(exercise_service, request)   


@bp.route('/compile/cpp', methods=['POST'])
def compile_cpp():
    return run_code(cpp_example, request)