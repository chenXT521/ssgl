$(function() {
    $(window).keydown(function(event){
        if(event.keyCode==13){
            $("#login").trigger("click");
        }
    });
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
    var depId = map.depId;
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
    password = $.md5(password);
    map.password = password;
    var data = JSON.stringify(map);
    $.ajax({
        url : 'toLogin',
        type : "post",
        dataType : "json",
        contentType : "application/json",
        data : data,
        success : function(res) {
            if(res.isSuccess){
                window.location.href="/login/toIndex";
            }else{
                var text = res.returnValue;
                $("#warmText").text(text);
                $("#loginWarm").show();
                return;
            }
        }
    });
})