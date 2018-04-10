var addHonorApp = new Vue({
    el: '#addHonorApp',
    data: {
        honorInfo: {}
    },
    methods: {
        add_honorInfo: function (honorInfo) {
            if (this.honorInfo.level !== undefined
                && this.honorInfo.kind !== undefined
                && this.honorInfo.name !== undefined
                && this.honorInfo.rank !== undefined
                && this.honorInfo.year !== undefined
                && this.honorInfo.status !== undefined) {
                $.ajax({
                    url: '/api/manager/add_honorInfo',
                    type: 'POST',
                    data: JSON.stringify(addHonorApp.honorInfo, null, 4),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (data) {
                        if (data.message === "add honorInfo success") {
                            alert("添加成功");
                            location.reload();
                        }
                    },
                    error: function (XMLResponse) {
                        alert(XMLResponse.responseText);
                    }
                });
            } else {
                alert("请填写完整信息");
            }
        },
        reset_data: function () {
            this.honorInfo = {};
        }
    }
});