var changeManagerPasswordApp = new Vue({
    el: "#changeManagerPasswordApp",
    data: {
        changePassword: {}
    },
    methods: {
        change_password: function () {
            if (this.verification_password) {
                $.ajax({
                    url: '/api/manager/change_managerPassword',
                    type: 'POST',
                    data: JSON.stringify(changeManagerPasswordApp.changePassword, null, 4),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (data) {
                        if (data.message === "change password success") {
                            alert("修改成功");
                            changeManagerPasswordApp.changePassword = '';
                            changeManagerPasswordApp.newPasswordAgain = '';
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
            changeManagerPasswordApp.changePassword = '';
            changeManagerPasswordApp.newPasswordAgain = '';
        }
    }
});