var changeStudentPasswordApp = new Vue({
    el: "#changeStudentPasswordApp",
    data: {
        changePassword: {},
        newPasswordAgain:''
    },
    methods: {
        change_password: function () {
            if (this.verification_password) {

                changeStudentPasswordApp.changePassword.newPassword
                    = md5(md5(changeStudentPasswordApp.changePassword.newPassword) + '8');
                changeStudentPasswordApp.changePassword.newPassword
                    = md5(md5(changeStudentPasswordApp.changePassword.newPassword) + '8');


                changeStudentPasswordApp.changePassword.oldPassword
                    = md5(md5(changeStudentPasswordApp.changePassword.oldPassword) + '8');
                changeStudentPasswordApp.changePassword.oldPassword
                    = md5(md5(changeStudentPasswordApp.changePassword.oldPassword) + '8');

                $.ajax({
                    url: '/api/student/change_studentPassword',
                    type: 'POST',
                    data: JSON.stringify(changeStudentPasswordApp.changePassword, null, 4),
                    contentType: "application/json",
                    dataType: "json",
                    success: function (data) {
                        if (data.message === "change password success") {
                            alert("修改成功");
                            changeStudentPasswordApp.reset_data();
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
            changeStudentPasswordApp.changePassword = {};
            changeStudentPasswordApp.newPasswordAgain = '';
        }
    }
});