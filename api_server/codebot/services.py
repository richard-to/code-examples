import core

java_example = core.JaveExampleService()
java_exercise = core.JaveExerciseService()
java_stub_exercise = core.JaveStubExerciseService()
cpp_example = core.CppExampleService()

_services = [
    java_example,
    java_exercise,
    java_stub_exercise,
    cpp_example
]

def init_services(workspace_factory, docker_proc_factory):
    for service in _services:
        service.init(workspace_factory, docker_proc_factory)