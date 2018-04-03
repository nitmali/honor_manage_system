$(document).ready(function () {
    getDataTable();
});

var table;

var checkerModalApp = new Vue({
    el: '#checkerModalApp',
    data: {
        checkerInfo: ''
    },
    methods: {
        get_checkerInfo_one: function (id) {
            $.get("/get_checkerInfo_one",
                {
                    id: id
                },
                function (checkerInfoOne) {
                    checkerModalApp.checkerInfo = checkerInfoOne;
                    checkerModalApp.checkerInfo.password = '';
                });
        },
        delete_checkerInfo: function (checkerInfo) {

            $.ajax({
                url: '/delete_checkerInfo',
                type: 'POST',
                data: JSON.stringify(checkerModalApp.checkerInfo, null, 4),
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.message === "delete checkerInfo success") {
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
        update_checkerInfo: function (checkerInfo) {

            $.ajax({
                url: '/update_checkerInfo',
                type: 'POST',
                data: JSON.stringify(checkerModalApp.checkerInfo, null, 4),
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.message === "update checkerInfo success") {
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
    table = $('#manager_show_checker_table').DataTable(
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
                url: "/get_checkerInfo_all",
                type: 'GET',
                dataSrc: ""
            },
            columns: [
                {data: "id"},
                {data: "username"},
                {data: "name"},
                {data: "phone"},
                {data: "authority"}
            ],
            columnDefs: []
        });

    $('#manager_show_checker_table tbody').on('click', 'tr', function () {
        var checker = table.row(this).data();
        checkerModalApp.get_checkerInfo_one(checker.id);
        $('#checkerModalApp').modal();
    });
}

