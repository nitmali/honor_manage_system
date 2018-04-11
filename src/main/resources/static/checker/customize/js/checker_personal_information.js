var checkerModalApp = new Vue({
    el: '#checkerModalApp',
    data: {
        checkerInfo: ''
    },
    methods: {
        change_information: function (checkerInfo) {
            $.ajax({
                url: '/api/manager/update_checkerInfo',
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
        },
        reset_data:function () {
            this.checkerInfo = {};
        }
    }
});

