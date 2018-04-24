$(document).ready(function () {
    $("#annexImage").attr("src","/api/checker_manager/get_annex?id="+$.cookie("reportRecordId"));
});