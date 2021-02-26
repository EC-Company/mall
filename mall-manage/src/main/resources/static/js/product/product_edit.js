layui.use(['upload', 'layedit', 'form'], function () {
    var $ = layui.jquery, upload = layui.upload, layedit = layui.layedit, form = layui.form;


    //拖拽上传
    upload.render({
        elem: '#test10'
        , url: 'http://localhost:8001/file/upload' //改成您自己的上传接口
        , done: function (res) {
            layer.msg('上传成功');
            layui.$('#uploadDemoView').removeClass('layui-hide').find('img').attr('src', res.data.src);
            $('#pic_icon').addClass("layui-hide");
            $('#productImage').val(res.data.src);
        }
    });

    layedit.set({
        uploadImage: {
            url: 'http://localhost:8001/file/upload'
        }
    });
    var editIndex = layedit.build('details'); //建立编辑器

    //自定义验证规则
    form.verify({
        name: function (value) {
            if (value.length < 5) {
                return '至少得5个字符啊';
            }
        }
        , stock: function (value) {
            if (value <= 0) {
                return '商品库存必须大于0';
            }
        }
        , score: function (value) {
            if (value <= 0) {
                return '商品所需积分必须大于0';
            }
        }
        , details: function (value) {
            layedit.sync(editIndex);
        }
    });

    //监听提交
    form.on('submit(productForm)', function (data) {

        if (!data.field.image) {
            layer.msg("请上传商品图片");
            return;
        }

        var details = layedit.getContent(editIndex);
        if (!details) {
            layer.msg("请编辑商品详情");
            return;
        }
        data.field.details = details;

        $.post('updateProduct', data.field, function (d) {
            layer.msg(d.msg);
            if (d.code == 0) {
                window.location.href = "product";
            }
        });
        return false;
    });


})