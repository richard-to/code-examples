(function(window, $) {
    var SiteService = {};


    var enableMenus = function() {
        $('#menu-button').click(function(e) {
            $('#menu-container').addClass('show');
            $(this).addClass('nav-selected');
            e.stopPropagation();
        });

        $('#main-container, .nav-container').click(function() {
            $('#menu-container').removeClass('show');
            $('#menu-button').removeClass('nav-selected');            
        });

        $('#self-test-button').click(function() {
            if ($('#exercises-container').hasClass('show')) {
                $('#self-test-button').removeClass('nav-selected');
                $('#exercises-container').removeClass('show');
            } else {
                $('#self-test-button').addClass('nav-selected');
                $('#exercises-container').addClass('show');                
            }
        });
    };
    SiteService.enableMenus = enableMenus;


    var enableSearch = function(dataUrl) {
        $.getJSON(dataUrl, function(data) {
            var searchEngine = new Bloodhound({
                datumTokenizer: function(datum) {
                    var keywords = datum.title + " " + datum.tags;
                    return Bloodhound.tokenizers.whitespace(keywords); 
                },
                queryTokenizer: Bloodhound.tokenizers.whitespace,
                local: data
            });
             
            searchEngine.initialize();
             
            $('.search-input').typeahead(null, {
                name: 'states',
                displayKey: 'title',
                source: searchEngine.ttAdapter()
            }).on('typeahead:selected', function (object, datum) {
                window.location.href = datum.url;
            });
        });

    };
    SiteService.enableSearch = enableSearch;


    window.SiteService = SiteService;
})(window, jQuery);