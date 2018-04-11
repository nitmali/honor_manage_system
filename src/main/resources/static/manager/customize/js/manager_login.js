var managerLoginApp = new Vue({
    el: '#managerLoginApp',
    data: {
        userModel: {}
    },
    methods: {
        manager_login: function () {
            if (this.userModel.userName !== undefined && this.userModel.password !== undefined) {
                this.userModel.userType = "manager";
                $.ajax({
                    url: '/api/post_manager_login',
                    type: 'POST',
                    data: JSON.stringify(managerLoginApp.userModel, null, 4),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (data) {
                        if (data.message === "login success") {
                            managerLoginApp.go_to_url("manager_index.html");
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