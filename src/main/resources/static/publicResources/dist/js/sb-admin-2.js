/*!
 * Start Bootstrap - SB Admin 2 v3.3.7+1 (#template-overviews/sb-admin-2)
 * Copyright 2013-2016 Start Bootstrap
 * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap/blob/gh-pages/LICENSE)
 */
$(function() {
    $('#side-menu').metisMenu();
    addFooter();
});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        var topOffset = 50;
        var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    // var element = $('ul.nav a').filter(function() {
    //     return this.href == url;
    // }).addClass('active').parent().parent().addClass('in').parent();
    var element = $('ul.nav a').filter(function() {
        return this.href == url;
    }).addClass('active').parent();

    while (true) {
        if (element.is('li')) {
            element = element.parent().addClass('in').parent();
        } else {
            break;
        }
    }
});

function addFooter() {
    var footer = "<footer>\n" +
        "    <div class=\"container\">\n" +
        "        <div class=\"row\">\n" +
        "            <div class=\"col-sm-12 col-md-4 col-md-offset-4 col-lg-4 col-lg-offset-4\" " +
        "                  style='text-align: center'>\n" +
        "                <span>2018 © 高校荣誉管理平台 by ting</a></span> |\n" +
        "                <span><a href=\"mailto:nitmali@126.com\">联系管理员</a></span>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "</footer>";
    $('body').append(footer);
}

