// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.2.0.min
//= require jquery.timepicker
//= require webshim-1.16.0/polyfiller
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ajaxStart(function() {
            $('#spinner').fadeIn();
        }).ajaxStop(function() {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}

function configurejQueryPlugins($) {
    $('.timepicker').timepicker({
        timeFormat: 'H:i'
    });
}

$(document).ready(function () {
    configurejQueryPlugins(jQuery);
});

// Configure polyfills
webshims.setOptions('forms-ext', {types: 'date'});
webshims.polyfill('forms forms-ext');