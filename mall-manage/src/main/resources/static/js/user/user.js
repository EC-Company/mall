layui.use(['table', 'form'], function () {
    var table = layui.table, form = layui.form, $ = layui.$;
    //第一个实例
    table.render({
        elem: '#demo'
        , id: 'userTable'
        , url: 'queryUserList' //数据接口
        , page: true //开启分页
        , limit: 5
        , limits: [5, 10, 15, 20]
        , cols: [[ //表头
            {type: 'numbers', title: '编号', align: "center", fixed: 'left'}
            , {field: 'username', title: '用户名', align: "center"}
            , {field: 'email', title: '邮箱', align: "center"}
            , {field: 'phone', title: '手机号', align: "center"}
            , {field: 'balance', title: '积分', align: "center"}
            , {field: 'createTime', title: '创建时间', align: "center"}
            , {toolbar: '#barDemo', title: '操作', align: "center"}
        ]]
    });

    //监听工具条
    table.on('tool(userTable)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        if (layEvent === 'detail') { //查看
            layer.msg("查看：" + data.username)
        } else if (layEvent === 'dongjie') { //编辑
            // layer.msg("冻结：" + data.username);
            updateUserStatus({id: data.id, status: 1});
        } else if (layEvent === 'jiedong') {
            // layer.msg("解冻：" + data.username);
            updateUserStatus({id: data.id, status: 0});
        }
    });

    function updateUserStatus(d) {
        $.post("updateUserStatus", d, function (res) {
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
        table.reload('userTable', params);
    }

    //监听提交
    form.on('submit(userSearch)', function (data) {
        reloadTable(data.field, 1);
        return false;
    });


});