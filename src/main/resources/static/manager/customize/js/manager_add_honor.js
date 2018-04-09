var addHonorApp = new Vue({
    el: '#addHonorApp',
    data: {
        honorInfo: {},
        post_honorInfo:''
    },
    methods: {
        add_honorInfo: function (honorInfo) {
            if (this.honorInfo.level !== undefined
                && this.honorInfo.kind !== undefined
                && this.honorInfo.name !== undefined
                && this.honorInfo.rank !== undefined
                && this.honorInfo.year !== undefined
                && this.honorInfo.status !== undefined) {

                addHonorApp.post_honorInfo = addHonorApp.honorInfo;
                if (addHonorApp.honorInfo.level === "院级") {
                    addHonorApp.post_honorInfo.level = "COLLEGE";
                }
                if (addHonorApp.honorInfo.level === "校级") {
                    addHonorApp.post_honorInfo.level = "SCHOOL";
                }
                if (addHonorApp.honorInfo.level === "市级") {
                    addHonorApp.post_honorInfo.level = "MUNICIPAL";
                }
                if (addHonorApp.honorInfo.level === "省级") {
                    addHonorApp.post_honorInfo.level = "PROVINCIAL";
                }
                if (addHonorApp.honorInfo.level === "国家级") {
                    addHonorApp.post_honorInfo.level = "NATIONAL";
                }

                if (addHonorApp.honorInfo.kind === "学术研究") {
                    addHonorApp.post_honorInfo.kind = "ACADEMIC_RESEARCH";
                }
                if (addHonorApp.honorInfo.kind === "学科竞赛") {
                    addHonorApp.post_honorInfo.kind = "DISCIPLINE_COMPETITION";
                }
                if (addHonorApp.honorInfo.kind === "创新创业") {
                    addHonorApp.post_honorInfo.kind = "INNOVATION_ENTREPRENEURSHIP";
                }
                if (addHonorApp.honorInfo.kind === "EXAMINATIONS") {
                    addHonorApp.post_honorInfo.kind = "考级考证";
                }

                if (addHonorApp.honorInfo.rank === "其他奖项") {
                    addHonorApp.post_honorInfo.rank = "OTHER";
                }
                if (addHonorApp.honorInfo.rank === "一等奖") {
                    addHonorApp.post_honorInfo.rank = "FIRST";
                }
                if (addHonorApp.honorInfo.rank === "二等奖") {
                    addHonorApp.post_honorInfo.rank = "SECOND";
                }
                if (addHonorApp.honorInfo.rank === "三等奖") {
                    addHonorApp.post_honorInfo.rank = "THIRD";
                }

                if (addHonorApp.honorInfo.status === "有效") {
                    addHonorApp.post_honorInfo.status = "EFFECTIVE";
                }
                if (addHonorApp.honorInfo.status === "失效") {
                    addHonorApp.post_honorInfo.status = "INVALID";
                }
                
                $.ajax({
                    url: '/add_honorInfo',
                    type: 'POST',
                    data: JSON.stringify(addHonorApp.post_honorInfo, null, 4),
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