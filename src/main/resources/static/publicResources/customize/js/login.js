var userLoginApp = new Vue({
    el: '#userLoginApp',
    data: {
        userModel: {}
    },
    created: function () {
        this.userModel.userType = "student";
    },
    methods: {
        checker_login: function () {
            if (this.userModel.userName !== undefined && this.userModel.password !== undefined) {
                if (this.userModel.userType !== "manager") {
                    userLoginApp.userModel.password = md5(md5(userLoginApp.userModel.password) + '8');
                    userLoginApp.userModel.password = md5(md5(userLoginApp.userModel.password) + '8');
                }
                var api = "/api/post_" + this.userModel.userType + "_login";
                $.ajax({
                    url: api,
                    type: 'POST',
                    data: JSON.stringify(userLoginApp.userModel, null, 4),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (data) {
                        var url = "../../"+ userLoginApp.userModel.userType
                            +"/pages/"+ userLoginApp.userModel.userType +"_index.html";
                        if (data.message === "login success") {
                            userLoginApp.go_to_url(url);
                        } else {
                            userLoginApp.userModel.password = '';
                            alert("账号或者密码错误");
                        }
                    },
                    error: function (XMLResponse) {
                        alert(XMLResponse.responseText);
                    }
                });
            }
        },
        go_to_url: function (url) {
            var a = $('<a href="' + url + '" >链接</a>');  //生成一个临时链接对象
            var d = a.get(0);
            var e = document.createEvent('MouseEvents');
            e.initEvent('click', true, true);  //模拟点击操作
            d.dispatchEvent(e);
            a.remove();   // 点击后移除该对象
        }
    }
});