var checkerLoginApp = new Vue({
    el: '#checkerLoginApp',
    data: {
        userModel: {}
    },
    methods: {
        checker_login: function () {
            if (this.userModel.userName !== undefined && this.userModel.password !== undefined) {
                this.userModel.userType = "checker";
                $.ajax({
                    url: '/api/post_checker_login',
                    type: 'POST',
                    data: JSON.stringify(checkerLoginApp.userModel, null, 4),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (data) {
                        if (data.message === "login success") {
                            checkerLoginApp.go_to_url("checker_index.html");
                        } else {
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