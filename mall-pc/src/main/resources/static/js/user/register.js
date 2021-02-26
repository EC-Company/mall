layui.use(['form', 'jquery', 'layer'], function () {
    var form = layui.form;
    var $ = layui.jquery;
    var layer = layui.layer;
    //添加表单失焦事件
    //验证表单
    $('#user').blur(function () {
        var user = $(this).val();
        //alert(user);
        if (user.length < 6) {
            $('#wr').removeAttr('hidden');
            $('#ri').attr('hidden', 'hidden');
            layer.msg('密码长度不能小于6位数');
            return;
        }
        $.ajax({
            url: 'verifyUserName',
            type: 'post',
            dataType: 'json',
            data: {
                username: user
            },
            //验证用户名是否可用
            success: function (data) {
                console.log(data, data.code);
                if (data.code == 0) {
                    $('#ri').removeAttr('hidden');
                    $('#wr').attr('hidden', 'hidden');
                } else {
                    $('#wr').removeAttr('hidden');
                    $('#ri').attr('hidden', 'hidden');
                    layer.msg(data.msg);
                }
            }
        });
    });

    // you code ...
    // 为密码添加正则验证
    $('#pwd').blur(function () {
        var reg = /^[\w]{6,12}$/;
        if (!($('#pwd').val().match(reg))) {
            //layer.msg('请输入合法密码');
            $('#pwr').removeAttr('hidden');
            $('#pri').attr('hidden', 'hidden');
            layer.msg('请输入合法密码');
        } else {
            $('#pri').removeAttr('hidden');
            $('#pwr').attr('hidden', 'hidden');
        }
    });

    //验证两次密码是否一致
    $('#rpwd').blur(function () {
        if ($('#pwd').val() != $('#rpwd').val()) {
            $('#rpwr').removeAttr('hidden');
            $('#rpri').attr('hidden', 'hidden');
            layer.msg('两次输入密码不一致!');
        } else {
            $('#rpri').removeAttr('hidden');
            $('#rpwr').attr('hidden', 'hidden');
        }
    });

    //
    //添加表单监听事件,提交注册信息
    form.on('submit(sub)', function (data) {
        console.log(data.field);
        $.ajax({
            url: 'register',
            type: 'post',
            dataType: 'json',
            data: data.field,
            success: function (data) {
                if (data.code == 0) {
                    layer.msg('注册成功');
                    //跳转到登录页面
                    location.href = "login.html";
                } else {
                    layer.msg(data.msg);
                }
            }
        })
        //防止页面跳转
        return false;
    });
});