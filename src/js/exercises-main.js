function init_editor(options) {
    var labels = {
        reset: "Reset",
        run: "Check your answer",
        compiling: "Compiling and testing your code...",
        server_error: 'Sorry we encountered a server error.'
    };

    var editor = ace.edit("editor");
    editor.setTheme(options.theme);
    editor.getSession().setMode(options.mode);

    document.getElementById('editor').style.fontSize='16px';
    document.getElementById('editor').style.lineHeight='1.5';

    var masterContentEl = document.getElementById('master-content');
    var masterOutputEl = document.getElementById('master-output');
    var outputEl = document.getElementById('output');
    var runButtonEl = document.getElementById('run-button');
    var resetButtonEl = document.getElementById('reset-button');

    runButtonEl.addEventListener('click', function() {
        var onCompile = function(output) {
            outputEl.style.display = 'block';
            outputEl.innerHTML = output;
            runButtonEl.disabled = false;
            resetButtonEl.disabled = false;
            runButtonEl.innerHTML = labels.run;
        };

        runButtonEl.disabled = true;
        resetButtonEl.disabled = true;
        runButtonEl.innerHTML = labels.compiling;

        var postData = {
            classname: options.classname,
            code: editor.getValue(),
            type: options.type
        };

        $.post(options.endpoint, postData, function(data) {
            onCompile(data);
        })
        .fail(function() {
            onCompile(labels.server_error)
        })
    });

    resetButtonEl.addEventListener('click', function() {
        outputEl.style.display ='none';
        outputEl.innerHTML = '';
        editor.setValue(masterContentEl.innerHTML);
    });
}