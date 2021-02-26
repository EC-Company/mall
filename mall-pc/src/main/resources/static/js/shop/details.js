layui.use(['form', 'jquery'], function () {
    var $ = layui.jquery;


    function openBox() {
        var index = layer.open({
            title: '请填写收获信息'
            , type: 1,
            area: ['680px', '350px']
            , skin: 'layui-layer-demo', //样式类名
            closeBtn: 0, //不显示关闭按钮
            anim: 2,
            // shadeClose: true, //开启遮罩关闭
            content: $('#addressBox')
            , btn: ['确定', '取消']
            , yes: function () {
                // layer.msg(1);
                submit();
                //关闭当前弹出层
                layer.close(index);
                // clearAddreeBox();
            }
            , btn2: function () {
                // layer.msg(2);
                clearAddreeBox();
            }
        });
    }

    function submit() {
        var params = {
            productId: productId,
            consignee: $('#consignee').val(),
            phone: $('#phone').val(),
            address: $('#address').val()
        }
        // console.log(params);

        $.post('insertOrder', params, function (res) {
            if (res.code == 0) {
                layer.msg('兑换成功');
                //设置2秒后跳转主页
                setTimeout(function () {
                    window.location.href = 'index.html';
                }, 2000);
            } else {
                layer.msg(res.msg);
            }
        });


    }

    function clearAddreeBox() {
        $('#consignee').val('');
        $('#phone').val('');
        $('#address').val('');
    }


    $("#ljdh").on("click", function () {
        openBox();
    });


});