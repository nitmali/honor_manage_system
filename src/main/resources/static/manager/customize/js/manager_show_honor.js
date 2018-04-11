$(document).ready(function () {
    getDataTable();
});

var table;

var honorModalApp = new Vue({
    el: '#honorModalApp',
    data: {
        honorInfo: ''
    },
    methods: {
        get_honorInfo_id: function (id) {
            $.get("/api/manager/get_honorInfo_id",
                {
                    id: id
                },
                function (honorInfo) {
                    honorModalApp.honorInfo = honorInfo;
                });
        },
        delete_honorInfo: function (honorInfo) {
            $.ajax({
                url: '/api/manager/delete_honorInfo',
                type: 'POST',
                data: JSON.stringify(honorModalApp.honorInfo, null, 4),
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.message === "delete honorInfo success") {
                        alert("删除成功");
                        location.reload();
                    }
                },
                error: function (XMLResponse) {
                    alert(XMLResponse.responseText);
                }
            });
        }
        ,
        update_honorInfo: function (honorInfo) {
            $.ajax({
                url: '/api/manager/update_honorInfo',
                type: 'POST',
                data: JSON.stringify(honorModalApp.honorInfo, null, 4),
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.message === "update honorInfo success") {
                        alert("修改成功");
                        location.reload();
                    }
                },
                error: function (XMLResponse) {
                    alert(XMLResponse.responseText);
                }
            });
        }
    }
});

    function getDataTable() {
    table = $('#show_honor_table').DataTable(
        {
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            responsive: true,
            bAutoWidth: true,
            processing: true,
            aLengthMenu: [5, 10, 25, 50], //更改显示记录数选项
            ajax: {
                url: "/api/manager/get_honorInfo_all",
                type: 'GET',
                dataSrc: ""
            },
            columns: [
                {data: "id"},
                {data: "level"},
                {data: "kind"},
                {data: "name"},
                {data: "rank"},
                {data: "year"},
                {data: "status"}
            ],
            columnDefs: []
        });

    $('#show_honor_table tbody').on('click', 'tr', function () {
        // console.log(table.row(this).data());
        var honor = table.row(this).data();
        honorModalApp.get_honorInfo_id(honor.id)
        $("#honorModalApp").modal();
    });
}

