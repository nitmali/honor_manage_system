var changeCheckerPasswordApp = new Vue({
    el: "#changeCheckerPasswordApp",
    data: {
        changePassword: {},
        newPasswordAgain:''
    },
    methods: {
        change_password: function () {
            if (this.verification_password) {
                $.ajax({
                    url: '/api/checker/change_checkerPassword',
                    type: 'POST',
                    data: JSON.stringify(changeCheckerPasswordApp.changePassword, null, 4),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (data) {
                        if (data.message === "change password success") {
                            alert("修改成功");
                            changeCheckerPasswordApp.reset_data();
                        }else if(data.message === "old password error"){
                            alert("原密码错误");
                        }
                    },
                    error: function (XMLResponse) {
                        alert(XMLResponse.responseText);
                    }
                });
            }
        },
        verification_password: function () {
            if (this.changePassword.newPassword === this.newPasswordAgain) {
                return true;
            }
        },
        reset_data:function () {
            changeCheckerPasswordApp.changePassword = {};
            changeCheckerPasswordApp.newPasswordAgain = '';
        }
    }
});