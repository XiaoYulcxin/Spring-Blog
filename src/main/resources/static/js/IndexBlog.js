$('.menu.toggle').click(function () {
    $('.m-item').toggleClass('m-moblie-hide');
});
$('#payButton').popup({
    popup: $('.payQ.popup'),
    on: 'click',
    position: 'bottom center'
});
tocbot.init({
    // Where to render the table of contents.
    tocSelector: '.js-toc',
    // Where to grab the headings to build the table of contents.
    contentSelector: '.js-toc-content',
    // Which headings to grab inside of the contentSelector element.
    headingSelector: 'h1, h2, h3',
    // For headings inside relative or absolute positioned containers within content.
    hasInnerContainers: true,
});
$('.toc.button').popup({
    popup: $('.toc-container.popup'),
    on: 'click',
    position: 'left center'
});
$('#wexinButton').popup({
    popup: $('.wechatQR.popup'),
    on: 'click',
    position: 'left center'
})
// 平滑滚动效果：回顶部
$('#toTop-Button').click(function () {
    $(window).scrollTo(0, 500)
});
// 平滑滚动效果：跳到留言部分
$('#commandButton').click(function () {
    $(window).scrollTo('#command', 500)
})
var waypoint = new Waypoint({
    element: document.getElementById('blog-content'),
    handler: function (direction) {
        // 控制右边固定快捷导航栏显示/隐藏
        if(direction == 'down'){
            $('#nav-fixed').show(500)
        }else{
            $('#nav-fixed').hide(500)
        }
        // notify('中文网页重设与排版')
    }
})
