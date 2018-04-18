$().ready(function () {
    $.ajax({
        url: "/api/manager/count_reportRecord_status",
        type: "get",
        dataType: "json",
        data: {},
        success: function (data) {
            Morris.Donut({
                element: 'morris-donut-chart',
                data: [{
                    label: "未审核",
                    value: data.WAITING_REVIEW
                }, {
                    label: "审核中",
                    value: data.FIRST_REVIEW
                }, {
                    label: "通过审核",
                    value: data.ALREADY_REVIEW
                }],
                resize: true
            });
        },
        error: function () {
            alert("System Error!");
        }
    });

    $.get('/api/manager/count_reportRecord_Kind_level',
        function (data) {
            Morris.Bar({
                element: 'morris-bar-chart',
                data: [{
                    y: '国家级',
                    a: data[0].ACADEMIC_RESEARCH,
                    b: data[0].DISCIPLINE_COMPETITION,
                    c: data[0].INNOVATION_ENTREPRENEURSHIP,
                    d: data[0].EXAMINATIONS
                }, {
                    y: '省级',
                    a: data[1].ACADEMIC_RESEARCH,
                    b: data[1].DISCIPLINE_COMPETITION,
                    c: data[1].INNOVATION_ENTREPRENEURSHIP,
                    d: data[1].EXAMINATIONS
                }, {
                    y: '市级',
                    a: data[2].ACADEMIC_RESEARCH,
                    b: data[2].DISCIPLINE_COMPETITION,
                    c: data[2].INNOVATION_ENTREPRENEURSHIP,
                    d: data[2].EXAMINATIONS
                }, {
                    y: '校级',
                    a: data[3].ACADEMIC_RESEARCH,
                    b: data[3].DISCIPLINE_COMPETITION,
                    c: data[3].INNOVATION_ENTREPRENEURSHIP,
                    d: data[3].EXAMINATIONS
                }, {
                    y: '院级',
                    a: data[4].ACADEMIC_RESEARCH,
                    b: data[4].DISCIPLINE_COMPETITION,
                    c: data[4].INNOVATION_ENTREPRENEURSHIP,
                    d: data[4].EXAMINATIONS
                }],
                xkey: 'y',
                ykeys: ['a', 'b', 'c', 'd'],
                labels: ['学术研究', '学科竞赛', '创新创业', '考级考证'],
                hideHover: 'auto',
                resize: true
            });
        });




});
