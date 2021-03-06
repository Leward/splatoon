// This is a manifest file that'll be compiled into application.js.
//
// Any JavaScript file within this directory can be referenced here using a relative path.
//
// You're free to add application-wide JavaScript to this file, but it's generally better
// to create separate JavaScript files as needed.
//
//= require timepicker/jquery.timepicker
//= require readmore
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
    if(window.CKEDITOR == undefined) {
        console.warn("CKEDITOR is not enabled on this page");
        return;
    }
    CKEDITOR.replaceAll(function (textarea, config) {
        var isCkeditorSimple = $(textarea).hasClass('ckeditor-simple');
        var isCkeditorFull = $(textarea).hasClass('ckeditor-full');
        if (!isCkeditorSimple && !isCkeditorFull) {
            return false;
        }

        config.contentsCss = '/assets/application.css';
        if(isCkeditorSimple) {
            config.toolbar = [
                ['Bold', 'Italic']
            ];
            config.removePlugins = 'elementspath,contextmenu,tabletools';
        } else if(isCkeditorFull) {
            // config.toolbar = [
            //     { name: 'editing', items: [ 'Undo', 'Redo' ] },
            //     { name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
            //     { name: 'insert', items: [ 'Image', 'Table', 'HorizontalRule', 'SpecialChar', 'Embed' ] },
            //     { name: 'document', items: [ 'Maximize', '-', 'Source' ] },
            //     '/',
            //     { name: 'basicstyles', items: [ 'Bold', 'Italic', 'Underline', 'Strike', '-', 'RemoveFormat' ] },
            //     { name: 'align', items: [ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ] },
            //     { name: 'paragraph', items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote' ] },
            //     '/',
            //     { name: 'styles', items: [ 'Styles', 'Format' ] },
            //     { name: 'colors', items: [ 'TextColor', 'BGColor' ] }
            //
            // ];
            config.extraPlugins = 'uploadimage,embed,justify,colorbutton,table,tabletools';
            config.uploadUrl = '/upload';
            //config.embed_provider = '//noembed.com/embed?url={url}&callback={callback}';
            config.embed_provider = 'https://noembed.com/embed?callback={callback}&url={url}';
        }
        return true;
    });
}

function configureReadMore($) {
    $('.read-more').readmore({
        speed: 75,
        collapsedHeight: 230,
        moreLink: '<p class="text-right"><a href="#">Lire plus</a></p>',
        lessLink: '<p class="text-right"><a href="#">Lire moins</a></p>'
    });
}

function configureTeamPerformancesChart() {
    if(window.Chart === undefined) {
        console.warn("Chart.js is not enabled on this page");
        return;
    }
    var ctx = document.getElementById("team-performances-canvas").getContext("2d");
    if(!ctx) {
        return;
    }
    new Chart(ctx, {
        type: 'bar',
        data: window.teamChartData,
        options: {
            title: {
                display: false
            },
            tooltips: {
                mode: 'index',
                intersect: false
            },
            legend: {
                display: false
            },
            maintainAspectRatio: false,
            scales: {
                xAxes: [{
                    stacked: true
                }],
                yAxes: [{
                    stacked: true
                }]
            }
        }
    });
}

function configureLinkToTwitchStream($) {
    $('.streams .stream-switch a').on('click', function () {
        var link = $(this).attr('href');
        $('.twitch-stream').html('<iframe ' +
            ' src="'+ link +'" ' +
            ' frameborder="0" allowfullscreen="true" scrolling="no" ' +
            ' "></iframe>');
        return false;
    });
}

$(document).ready(function () {
    configurejQueryPlugins(jQuery);
    configureCkeditor(jQuery);
    configureReadMore(jQuery);
    configureTeamPerformancesChart();
    configureLinkToTwitchStream(jQuery);
});

// Configure polyfills
webshims.setOptions('forms-ext', {types: 'date'});
webshims.polyfill('forms forms-ext');