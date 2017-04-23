// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require jquery-2.2.0.min
//= require jquery.timepicker
//= require polyfiller
//= require_self

if (typeof jQuery !== 'undefined') {
    (function ($) {
        $(document).ajaxStart(function () {
            $('#spinner').fadeIn();
        }).ajaxStop(function () {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}

function configurejQueryPlugins($) {
    $('.timepicker').timepicker({
        timeFormat: 'H:i'
    });
}

function configureCkeditor($) {
    CKEDITOR.replaceAll(function (textarea, config) {
        if (!$(textarea).hasClass('ckeditor-simple')) {
            return false;
        }
        config.contentsCss = '/assets/application.css?compile=false';
        config.toolbar = [
            ['Bold', 'Italic']
        ];
        config.removePlugins = 'elementspath,contextmenu,tabletools';
        return true;
    });
}

$(document).ready(function () {
    configurejQueryPlugins(jQuery);
    configureCkeditor(jQuery);
});

// Configure polyfills
webshims.setOptions('forms-ext', {types: 'date'});
webshims.polyfill('forms forms-ext');