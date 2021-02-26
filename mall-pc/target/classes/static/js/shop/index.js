layui.use(['laypage', 'jquery', 'laytpl'], function () {
    var laypage = layui.laypage, $ = layui.jquery, laytpl = layui.laytpl;

    $('#seachBtn').on('click', function () {
        var name = $('#seachKey').val();

        // 查询商品列表
    });

    //加载商品列表
    loadProductList();

    function loadProductList(d) {
        $.ajax({
            url: 'queryProductList'
            , async: false
            , data: d
            , success: function (res) {
                console.log(res);
                //渲染商品列表
                renderHtml(res.data);
            }
        });
    }

    //模板
    function renderHtml(d) {
        var getTpl = demo.innerHTML, view = document.getElementById('productBox');
        laytpl(getTpl).render(d, function (html) {
            view.innerHTML = html;
        });
    }


});