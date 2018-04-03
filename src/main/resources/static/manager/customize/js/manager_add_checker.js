var addCheckerApp = new Vue({
    el: '#addCheckerApp',
    data: {
        checkerInfo: {},
        passwordAgain: ''
    },
    methods: {
        add_checkerInfo: function (checkerInfo) {
            if (this.checkerInfo.name !== undefined
                && this.checkerInfo.password !== undefined
                && this.checkerInfo.phone !== undefined
                && this.checkerInfo.username !== undefined
                && this.passwordAgain !== undefined) {
                if (addCheckerApp.verification_phone() && addCheckerApp.verification_password()) {
                    $.ajax({
                        url: '/add_checkerInfo',
                        type: 'POST',
                        data: JSON.stringify(addCheckerApp.checkerInfo, null, 4),
                        contentType: "application/json",
                        dataType: "json",
                        success: function (data) {
                            if (data.message === "add checkerInfo success") {
                                alert("添加成功");
                                location.reload();
                            }
                        },
                        error: function (XMLResponse) {
                            alert(XMLResponse.responseText);
                        }
                    });
                }
            } else {
                alert("请填写完整信息");
            }
        },
        verification_password: function () {
            if (this.checkerInfo.password !== this.passwordAgain
                && this.checkerInfo.password !== '' && this.passwordAgain !== '') {
                alert("两次密码输入不一致!");
            } else {
                return true;
            }
        },
        verification_phone: function () {
            if (this.checkerInfo.phone.length !== 11 && this.checkerInfo.phone.length !== 6
                && this.checkerInfo.phone !== '') {
                alert("请填写有效有机号码")
            } else {
                return true;
            }
        },
        reset_data: function () {
            this.checkerInfo = {};
            this.passwordAgain = '';
        }


    }
});