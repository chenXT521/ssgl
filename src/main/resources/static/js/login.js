$(function() {

});

function getFormData(eId) {
    var inData={};
    $("#" + eId).find("input").each(function() {
        if ($(this).attr("real-value") != null) {
            inData[$(this).attr("name")] = $(this).attr("real-value").trim();
        } else {
            inData[$(this).attr("name")] = $(this).val().trim();
        }
    });

    $("#" + eId).find("select").each(function() {
        inData[$(this).attr("name")] = $(this).val();
    });
    $("#" + eId).find("textarea").each(function() {
        inData[$(this).attr("name")] = $(this).val().trim();
    });
    return inData;
};

$("#login").on("click",function () {
    var map = getFormData("loginForm");
    var depId = map.depid;
    var userName = map.userName;
    var password = map.password;
    if(!depId){
        var text = "请选择用户部门";
        $("#warmText").text(text);
        $("#loginWarm").show();
        return;
    }
    if(!userName){
        var text = "请输入账户名";
        $("#warmText").text(text);
        $("#loginWarm").show();
        return;
    }
    if(!password){
        var text = "请输入密码";
        $("#warmText").text(text);
        $("#loginWarm").show();
        return;
    }

})