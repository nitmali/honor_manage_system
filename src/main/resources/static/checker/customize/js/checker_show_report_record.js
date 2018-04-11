$(document).ready(function () {
    getDataTable();
});

var table;

var reportRecordModalApp = new Vue({
    el: '#reportRecordModalApp',
    data: {
        reportRecord: ''
    },
    methods: {
        get_reportRecord_id: function (id) {
            $.get("/api/manager_checker/get_reportRecord_id",
                {
                    id: id
                },
                function (reportRecord) {
                    reportRecordModalApp.reportRecord = reportRecord;
                });
        }
        ,
        check_reportRecord: function (status) {
            reportRecordModalApp.reportRecord.status = status;
            $.ajax({
                url: '/api/checker/check_reportRecord',
                type: 'POST',
                data: JSON.stringify(reportRecordModalApp.reportRecord, null, 4),
                contentType: "application/json",
                dataType: "json",
                success: function (data) {
                    if (data.message === "check reportRecord success") {
                        alert("审核成功");
                        location.reload();
                    }
                },
                error: function (XMLResponse) {
                    alert(XMLResponse.responseText);
                }
            });
        },
        check_reportRecord_PASS:function () {
            reportRecordModalApp.check_reportRecord("PASS");
        },
        check_reportRecord_NOT_PASS:function () {
            reportRecordModalApp.check_reportRecord("NOT_PASS");
        }
    }
});

function getDataTable() {
    table = $('#checker_show_record_table').DataTable(
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
                url: "/api/manager_checker/get_reportRecord_all",
                type: 'GET',
                dataSrc: ""
            },
            columns: [
                {data: "id"},
                {data: "studentInfoNumber"},
                {data: "studentInfoName"},
                {data: "studentInfoSex"},
                {data: "studentInfoCollege"},
                // {data: "studentInfoGrade"},
                // {data: "studentInfoMajor"},
                // {data: "studentInfoClass"},
                {data: ""},
                {data: "honorInfoName"},
                // {data: "honorInfoLevel"},
                // {data: "honorInfoKind"},
                // {data: "honorInfoRank"},
                // {data: "honorInfoYear"},
                // {data: "recordTime"},
                // {data: "annex"},
                {data: "status"}
            ],
            columnDefs: [
                {
                    targets: 5,
                    render: function (data, type, row, meta) {
                        return row.studentInfoMajor
                            + row.studentInfoGrade.slice(0,2)
                            + row.studentInfoClass.slice(0,1);
                    }
                }
            ]
        });

    $('#checker_show_record_table tbody').on('click', 'tr', function () {
        // console.log(table.row(this).data());
        var reportRecord = table.row(this).data();
        reportRecordModalApp.get_reportRecord_id(reportRecord.id);
        $("#reportRecordModalApp").modal();
    });
}

