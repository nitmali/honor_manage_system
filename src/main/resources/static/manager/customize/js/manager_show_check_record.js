$(document).ready(function () {
    getDataTable();
});

var table;

var checkRecordModalApp = new Vue({
    el: '#checkRecordModalApp',
    data: {
        checkRecord: ''
    },
    methods: {
        get_checkRecord_id: function (id) {
            $.get("/api/manager_checker/get_checkRecord_id",
                {
                    id: id
                },
                function (checkRecord) {
                    checkRecordModalApp.checkRecord = checkRecord;
                    $.cookie("reportRecordId",
                        checkRecordModalApp.checkRecord.reportRecordRecordId,{expires: 7,path: '/'});
                });
        },
        close_model: function () {
            $('#checkRecordModalApp').modal('hide');
        }
    }
});

function getDataTable() {

    table = $('#manager_show_record_table').DataTable(
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
            serverSide:true,
            aLengthMenu: [5, 10, 25, 50], //更改显示记录数选项
            ajax: {
                url: "/api/manager_checker/get_checkRecord_all",
                type: 'POST',
                dataType: "JSON"
            },
            columns: [
                {data: "id"},
                {data: "studentInfoName"},
                {data: ""},
                {data: "honorInfoLevel"},
                {data: "honorInfoName"},
                {data: "honorInfoYear"},
                {data: "checkerInfoName"},
                {data: "checkTime"},
                {data: "status"}
            ],
            columnDefs: [
                {
                    targets: 2,
                    render: function (data, type, row, meta) {
                        return row.studentInfoMajor
                            + row.studentInfoGrade.slice(0,2)
                            + row.studentInfoClass.slice(0,1);
                    }
                }
            ]
        });

    $('#manager_show_record_table tbody').on('click', 'tr', function () {
        // console.log(table.row(this).data());
        var checkRecord = table.row(this).data();
        checkRecordModalApp.get_checkRecord_id(checkRecord.id);
        $("#checkRecordModalApp").modal();
    });
}

