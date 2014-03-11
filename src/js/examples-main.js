function init_editor(options) {
    var labels = {
        reset: "Reset",
        edit: "Make Editable",
        run: "Run",
        compiling: "Compiling your code...",
        server_error: 'Sorry we encountered a server error.'
    };

    var editor = ace.edit("editor");
    editor.setTheme(options.theme);
    editor.getSession().setMode(options.mode);
    editor.setReadOnly(true);

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
        if (editor.getReadOnly()) {
            setTimeout(function() {
                onCompile(masterOutputEl.innerHTML);

            }, options.delay);
        } else {
            var postData = {
                className: options.className,
                code: editor.getValue()
            };
            $.post(options.endpoint, postData, function(data) {
                onCompile(data);
            })
            .fail(function() {
                onCompile(labels.server_error)
            })
        }
    });

    resetButtonEl.addEventListener('click', function() {
        outputEl.style.display ='none';
        outputEl.innerHTML = '';
        if (editor.getReadOnly()) {
            resetButtonEl.innerHTML = labels.reset;
            editor.setReadOnly(false);
        } else {
            resetButtonEl.innerHTML = labels.edit;
            editor.setValue(masterContentEl.childNodes[0].nodeValue);
            editor.setReadOnly(true);
        }
    });
}