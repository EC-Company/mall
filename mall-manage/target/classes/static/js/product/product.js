layui.use(['table', 'form'], function () {
    var table = layui.table, form = layui.form, $ = layui.$;
    //第一个实例
    table.render({
        elem: '#demo'
        , id: 'productTable'
        , url: 'queryProductList' //数据接口
        , page: true //开启分页
        , limit: 5
        , limits: [5, 10, 15, 20]
        , cols: [[ //表头
            {type: 'numbers', title: '编号', align: "center", fixed: 'left'}
            , {field: 'name', title: '商品名', align: "center"}
            , {
                field: 'image', title: '商品图片', align: "center", templet: function (d) {
                    return '<img height="100%" src="' + d.image + '"/>'
                }
            }
            , {field: 'stock', title: '库存', align: "center"}
            , {field: 'score', title: '积分', align: "center"}
            , {field: 'createTime', title: '创建时间', align: "center"}
            , {toolbar: '#barDemo', title: '操作', align: "center"}
        ]]
    });

    //监听工具条
    table.on('tool(productTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        if (layEvent === 'update') { //查看
            // layer.msg("修改：" + data.name)
            window.location.href = "editProduct.html?id=" + data.id;
        } else if (layEvent === 'up') {
            // layer.msg("冻结：" + data.username);
            updateProductStatus({id: data.id, status: 1});
        } else if (layEvent === 'down') {
            // layer.msg("解冻：" + data.username);
            updateProductStatus({id: data.id, status: 0});
        } else if (layEvent === 'delete') {
            $.post("deleteProduct", {id: data.id}, function (res) {
                if (res.code == 0) {
                    reloadTable();
                }
                layer.msg(res.msg);
            });
        }
    });

    function updateProductStatus(d) {
        $.post("updateProductStatus", d, function (res) {
            if (res.code == 0) {
                reloadTable();
            }
            layer.msg(res.msg);
        });
    }

    function reloadTable(d, i) {
        var params = {
            where: d //设定异步数据接口的额外参数
        }
        if (i) {
            params.page = {curr: 1}
        }
        //表格重载
        table.reload('productTable', params);
    }

    //监听提交
    form.on('submit(productSearch)', function (data) {
        reloadTable(data.field, 1);
        return false;
    });


});