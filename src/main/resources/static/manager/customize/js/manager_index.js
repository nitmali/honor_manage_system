$().ready(function () {
    $.ajax({
        url: "/api/manager/count_reportRecord",
        type: "get",
        dataType: "json",
        data: {
        },
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
                    label: "已审核",
                    value: data.ALREADY_REVIEW
                }],
                resize: true
            });
        },
        error: function () {
            alert("System Error!");
        }
    });

    // $.get('/countCarLogs', function (data) {
    //     Morris.Bar({
    //         element: 'morris-bar-chart',
    //         data: data,
    //         xkey: 'licensePlate',
    //         ykeys: ['count'],
    //         labels: ['数量'],
    //         resize: true
    //     });
    // });

    Morris.Bar({
        element: 'morris-bar-chart',
        data: [{
            y: '2006',
            a: 100,
            b: 90
        }, {
            y: '2007',
            a: 75,
            b: 65
        }, {
            y: '2008',
            a: 50,
            b: 40
        }, {
            y: '2009',
            a: 75,
            b: 65
        }, {
            y: '2010',
            a: 50,
            b: 40
        }, {
            y: '2011',
            a: 75,
            b: 65
        }, {
            y: '2012',
            a: 100,
            b: 90
        }],
        xkey: 'y',
        ykeys: ['a', 'b'],
        labels: ['Series A', 'Series B'],
        hideHover: 'auto',
        resize: true
    });


});
