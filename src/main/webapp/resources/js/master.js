var readyState = function(callback)
{
    var body = document.body;

    if (body && body.readyState == 'loaded')
    {
        callback();
    }
    else
    {
        if (window.addEventListener)
        {
            window.addEventListener('load', callback, false);
        }
        else
        {
            window.attachEvent('onload', callback);
        }
    }
}

readyState(function()
{
    /**
     * Scroll Page
     */
    function scrollPage(page)
    {
        $('#navigation a[data-nav="scroll"]').removeClass('active');

        $('#navigation a[href="#/' + page + '"]').addClass('active');

        scroll = false;

        $('html, body').animate({scrollTop: $('#' + page).offset().top}, 800, function() {
            scroll = true;
        });
    }

    /**
     * Popover
     */
    $("a[data-toggle=popover]")
            .popover()
            .click(function(e) {
        e.preventDefault()
    });

});