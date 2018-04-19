$(document).ready(function () {
    getDataTable();
});

function getDataTable() {
    table = $('#student_show_record_table').DataTable(
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
                url: "/api/student/get_reportRecord_studentInfo",
                type: 'GET',
                dataSrc: ""
            },
            columns: [
                {data: "id"},
                {data: "honorInfoLevel"},
                {data: "honorInfoKind"},
                {data: "honorInfoName"},
                {data: "honorInfoRank"},
                {data: "honorInfoYear"},
                {data: "recordTime"},
                {data: "status"}
            ],
            columnDefs: []
        });
}

