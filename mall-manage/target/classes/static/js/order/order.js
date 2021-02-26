layui.use(['table', 'form'], function () {
    var table = layui.table, form = layui.form, $ = layui.$;
    //第一个实例
    table.render({
        elem: '#demo'
        , id: 'orderTable'
        , url: 'queryOrderList' //数据接口
        , page: true //开启分页
        , limit: 5
        , limits: [5, 10, 15, 20]
        , cols: [[ //表头
            {type: 'numbers', title: '编号', align: "center", fixed: 'left'}
            , {field: 'orderNo', title: '订单号', align: "center"}
            , {field: 'productName', title: '商品名', align: "center"}
            , {field: 'username', title: '兑换用户', align: "center"}
            , {field: 'consignee', title: '收货人', align: "center"}
            , {field: 'phone', title: '收货电话', align: "center"}
            , {field: 'address', title: '收获地址', align: "center"}
            , {field: 'createTime', title: '创建时间', align: "center"}
            , {toolbar: '#barDemo', title: '操作', align: "center", fixed: 'right'}
        ]]
    });

    //监听工具条
    table.on('tool(orderTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        if (layEvent === 'send') { //查看
            // layer.msg("修改：" + data.orderNo)
            $.post('updateOrderStatus', {orderId: data.id}, function (d) {
                if (d.code == 0) {
                    reloadTable();
                }
                layer.msg(d.msg);
            });
        }
    });

    function reloadTable(d, i) {
        var params = {
            where: d //设定异步数据接口的额外参数
        }
        if (i) {
            params.page = {curr: 1}
        }
        //表格重载
        table.reload('orderTable', params);
    }

    //监听提交
    form.on('submit(orderSearch)', function (data) {
        reloadTable(data.field, 1);
        return false;
    });


});