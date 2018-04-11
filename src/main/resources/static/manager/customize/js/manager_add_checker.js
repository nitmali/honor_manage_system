var password_flag;

var addCheckerApp = new Vue({
    el: '#addCheckerApp',
    data: {
        checkerInfo: {},
        passwordAgain: '',
        flag: ''
    },
    methods: {
        add_checkerInfo: function (checkerInfo) {
            if (this.checkerInfo.name !== undefined
                && this.checkerInfo.password !== undefined
                && this.checkerInfo.phone !== undefined
                && this.checkerInfo.username !== undefined
                && this.passwordAgain !== undefined
                && this.checkerInfo.authority !== undefined) {
                if (addCheckerApp.verification_phone() && addCheckerApp.verification_password()
                    && addCheckerApp.verification_username()) {
                    if (addCheckerApp.checkerInfo) {
                        addCheckerApp.checkerInfo.password = md5(md5(addCheckerApp.checkerInfo.password) + '8');
                        addCheckerApp.checkerInfo.password = md5(md5(addCheckerApp.checkerInfo.password) + '8');
                        $.ajax({
                            url: '/api/manager/add_checkerInfo',
                            type: 'POST',
                            data: JSON.stringify(addCheckerApp.checkerInfo, null, 4),
                            contentType: "application/json",
                            dataType: "json",
                            success: function (data) {
                                if (data.message === "add checkerInfo success") {
                                    alert("添加成功");
                                    addCheckerApp.reset_data();
                                }
                            },
                            error: function (XMLResponse) {
                                alert(XMLResponse.responseText);
                            }
                        });
                    } else {
                        alert("用户名已存在");
                    }
                }
            } else {
                alert("请填写完整信息");
            }
        },
        verification_password: function () {
            if (this.checkerInfo.password !== this.passwordAgain
                && this.checkerInfo.password !== '' && this.passwordAgain !== '') {
                alert("两次密码输入不一致!");
            } else if (this.checkerInfo.password.length < 6 || this.checkerInfo.password.length > 16) {
                alert("请输入6~16位密码");
            } else {
                return true;
            }
        },
        verification_phone: function () {
            if (this.checkerInfo.phone.length !== 11 && this.checkerInfo.phone.length !== 6
                && this.checkerInfo.phone !== '') {
                alert("请填写有效手机号码");
            } else {
                return true;
            }
        },
        verification_username: function () {
            addCheckerApp.get_username();
            if (this.checkerInfo.username.length < 3) {
                alert("请输入不小于3位数的用户名");
            } else {
                return true;
            }
        },
        get_username: function () {
            $.get("/api/manager/get_checkerInfo_username",
                {
                    username: addCheckerApp.checkerInfo.username
                },
                function (data) {
                    if (data !== '') {
                        alert("用户名已存在");
                        addCheckerApp.flag = false;
                    } else {
                        addCheckerApp.flag = true;
                    }
                });
        },
        reset_data: function () {
            this.checkerInfo = {};
            this.passwordAgain = '';
        }
    }
});