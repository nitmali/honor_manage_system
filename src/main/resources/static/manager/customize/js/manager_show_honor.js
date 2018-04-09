$(document).ready(function () {
    getDataTable();
});

var table;

var honorModalApp = new Vue({
    el: '#honorModalApp',
    data: {
        honorInfo: '',
        post_honorInfo: ''
    },
    methods: {
        get_honorInfo_one: function (id) {
            $.get("/get_honorInfo_id",
                {
                    id: id
                },
                function (honorInfo) {
                    honorModalApp.honorInfo = honorInfo;
                    if (honorModalApp.honorInfo.level === "COLLEGE") {
                        honorModalApp.honorInfo.level = "院级";
                    }
                    if (honorModalApp.honorInfo.level === "SCHOOL") {
                        honorModalApp.honorInfo.level = "校级";
                    }
                    if (honorModalApp.honorInfo.level === "MUNICIPAL") {
                        honorModalApp.honorInfo.level = "市级";
                    }
                    if (honorModalApp.honorInfo.level === "省级") {
                        honorModalApp.honorInfo.level = "PROVINCIAL";
                    }
                    if (honorModalApp.honorInfo.level === "NATIONAL") {
                        honorModalApp.honorInfo.level = "国家级";
                    }

                    if (honorModalApp.honorInfo.kind === "ACADEMIC_RESEARCH") {
                        honorModalApp.honorInfo.kind = "学术研究";
                    }
                    if (honorModalApp.honorInfo.kind === "DISCIPLINE_COMPETITION") {
                        honorModalApp.honorInfo.kind = "学科竞赛";
                    }
                    if (honorModalApp.honorInfo.kind === "INNOVATION_ENTREPRENEURSHIP") {
                        honorModalApp.honorInfo.kind = "创新创业";
                    }
                    if (honorModalApp.honorInfo.kind === "考级考证") {
                        honorModalApp.honorInfo.kind = "EXAMINATIONS";
                    }

                    if (honorModalApp.honorInfo.rank === "OTHER") {
                        honorModalApp.honorInfo.rank = "其他奖项";
                    }
                    if (honorModalApp.honorInfo.rank === "FIRST") {
                        honorModalApp.honorInfo.rank = "一等奖";
                    }
                    if (honorModalApp.honorInfo.rank === "SECOND") {
                        honorModalApp.honorInfo.rank = "二等奖";
                    }
                    if (honorModalApp.honorInfo.rank === "THIRD") {
                        honorModalApp.honorInfo.rank = "三等奖";
                    }

                    if (honorModalApp.honorInfo.status === "EFFECTIVE") {
                        honorModalApp.honorInfo.status = "有效";
                    }
                    if (honorModalApp.honorInfo.status === "INVALID") {
                        honorModalApp.honorInfo.status = "失效";
                    }
                });
        },
        delete_honorInfo: function (honorInfo) {

            $.ajax({
                url: '/delete_honorInfo',
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


            honorModalApp.post_honorInfo = honorModalApp.honorInfo;
            if (honorModalApp.honorInfo.level === "院级") {
                honorModalApp.post_honorInfo.level = "COLLEGE";
            }
            if (honorModalApp.honorInfo.level === "校级") {
                honorModalApp.post_honorInfo.level = "SCHOOL";
            }
            if (honorModalApp.honorInfo.level === "市级") {
                honorModalApp.post_honorInfo.level = "MUNICIPAL";
            }
            if (honorModalApp.honorInfo.level === "省级") {
                honorModalApp.post_honorInfo.level = "PROVINCIAL";
            }
            if (honorModalApp.honorInfo.level === "国家级") {
                honorModalApp.post_honorInfo.level = "NATIONAL";
            }

            if (honorModalApp.honorInfo.kind === "学术研究") {
                honorModalApp.post_honorInfo.kind = "ACADEMIC_RESEARCH";
            }
            if (honorModalApp.honorInfo.kind === "学科竞赛") {
                honorModalApp.post_honorInfo.kind = "DISCIPLINE_COMPETITION";
            }
            if (honorModalApp.honorInfo.kind === "创新创业") {
                honorModalApp.post_honorInfo.kind = "INNOVATION_ENTREPRENEURSHIP";
            }
            if (honorModalApp.honorInfo.kind === "EXAMINATIONS") {
                honorModalApp.post_honorInfo.kind = "考级考证";
            }

            if (honorModalApp.honorInfo.rank === "其他奖项") {
                honorModalApp.post_honorInfo.rank = "OTHER";
            }
            if (honorModalApp.honorInfo.rank === "一等奖") {
                honorModalApp.post_honorInfo.rank = "FIRST";
            }
            if (honorModalApp.honorInfo.rank === "二等奖") {
                honorModalApp.post_honorInfo.rank = "SECOND";
            }
            if (honorModalApp.honorInfo.rank === "三等奖") {
                honorModalApp.post_honorInfo.rank = "THIRD";
            }

            if (honorModalApp.honorInfo.status === "有效") {
                honorModalApp.post_honorInfo.status = "EFFECTIVE";
            }
            if (honorModalApp.honorInfo.status === "失效") {
                honorModalApp.post_honorInfo.status = "INVALID";
            }
            
            $.ajax({
                url: '/update_honorInfo',
                type: 'POST',
                data: JSON.stringify(honorModalApp.post_honorInfo, null, 4),
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
                url: "/get_honorInfo_all",
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
            columnDefs: [
                {
                    targets: 1,
                    render: function (value) {
                        if (value === "COLLEGE") {
                            return "院级";
                        }
                        if (value === "SCHOOL") {
                            return "校级";
                        }
                        if (value === "MUNICIPAL") {
                            return "市级";
                        }
                        if (value === "PROVINCIAL") {
                            return "省级";
                        }
                        if (value === "NATIONAL") {
                            return "国家级";
                        }
                    }
                },
                {
                    targets: 2,
                    render: function (value) {
                        if (value === "ACADEMIC_RESEARCH") {
                            return "学术研究";
                        }
                        if (value === "DISCIPLINE_COMPETITION") {
                            return "学科竞赛";
                        }
                        if (value === "INNOVATION_ENTREPRENEURSHIP") {
                            return "创新创业";
                        }
                        if (value === "EXAMINATIONS") {
                            return "考级考证";
                        }
                    }
                },
                {
                    targets: 4,
                    render: function (value) {
                        if (value === "OTHER") {
                            return "其他奖项";
                        }
                        if (value === "FIRST") {
                            return "一等奖";
                        }
                        if (value === "SECOND") {
                            return "二等奖";
                        }
                        if (value === "THIRD") {
                            return "三等奖";
                        }
                    }
                },
                {
                    targets: 6,
                    render: function (value) {
                        if (value === "EFFECTIVE") {
                            return "有效";
                        }
                        if (value === "INVALID") {
                            return "失效";
                        }
                    }
                }
            ]
        });

    $('#show_honor_table tbody').on('click', 'tr', function () {
        // console.log(table.row(this).data());
        var honor = table.row(this).data();
        honorModalApp.get_honorInfo_one(honor.id)
        $("#honorModalApp").modal();
    });
}

