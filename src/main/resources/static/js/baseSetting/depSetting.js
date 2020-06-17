$(function () {
    getDeps();
})

function getDeps() {
    $.ajax({
        type:"post",
        url:"getDeps",
        data:{},
        success:function(data) {
            var html = "";
            for (var i = 0;i<data.length;i++){
                html+="<tr>";
                html+="<td>"+(i+1)+"</td>";
                html+="<td>"+data[i].depName+"</td>";
                html+="<td><u style='color: #ec971f' onclick='editDep(\""+data[i].depID+"\",\""+data[i].depName+"\",\""+data[i].state+"\")'>编辑</u>&nbsp;&nbsp;<u style='color: #FD482C' onclick='removeDep(\""+data[i].depID+"\",\""+data[i].depName+"\")'>删除</u></td>";
                html+="</tr>";
            }
            if(html == ""){
                html+="<tr><td rowspan='3'>暂无数据</td></tr>";
            }
            $("#tbody").html(html);
        }
    });
}

function editDep(depID,depName,state) {
    $("#depModal").modal("show");
    $("#modalName").html(depName);
    $("#modalState").val("edit");
    $("#depId").val(depID);
    $("#depName").val(depName);
    set_select_checked("depState", state);
}
function removeDep(depID,depName) {
    layer.confirm('删除'+depName+",将删除所有关联数据，是否确认？", {
        btn: ['确认','取消'] //按钮
    }, function(){
        $.ajax({
            type: "post",
            url: "removeDepById",
            data: {"depId":depID},
            async:false,
            success: function (data) {
                getDeps();
                layer.msg('删除成功', {icon: 1});
            }
        });
        /*layer.msg('的确很重要', {icon: 1});*/
    }, function(){
        layer.close(layer.index);
    });
}

function set_select_checked(selectId, checkValue){
    var select = document.getElementById(selectId);
    for (var i = 0; i < select.options.length; i++){
        if (select.options[i].value === checkValue){
            select.options[i].selected = true;
            break;
        }
    }
}
$("#addDep").on("click",function () {
    $("#depModal").modal("show");
    $("#modalName").html("添加部门");
    $("#depId").val("");
    $("#depName").val("");
    $("#modalState").val("add");
    set_select_checked("depState", 1);
});

$("#confim").on("click",function () {
    var modalState =  $("#modalState").val();
    if(modalState == "add"){
        var depName =  $("#depName").val().trim();
        var depState = $("#depState").val();
        if(!depName){
            layer.alert("请填写部门名称",{
                icon: 2,
                skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
            });
            return;
        }
        $.ajax({
            type: "post",
            url: "addDep",
            data: {"depName": depName,"depState":depState},
            async: false,
            success: function (res) {
                var success = res.success;
                var msg = res.msg;
                if(success){
                    layer.msg(msg,{icon:1});
                    getDeps();
                    $("#depModal").modal("hide");
                }else{
                    layer.msg(msg,{icon:2});
                }

            }
        });
    }else if(modalState == "edit"){
        var depId = $("#depId").val();
        if(!depId){
            layer.alert("未检测到这个部门的信息，无法修改",{
                icon: 2,
                skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
            });
            return;
        }
        var depName =  $("#depName").val().trim();
        var depState = $("#depState").val();
        if(!depName){
            layer.alert("部门名称不能为空",{
                icon: 2,
                skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
            });
            return;
        }
        $.ajax({
            type: "post",
            url: "editDep",
            data: {"depId":depId,"depName": depName, "depState": depState},
            async: false,
            success: function (res) {
                var success = res.success;
                var msg = res.msg;
                if(success){
                    layer.msg(msg,{icon:1});
                    getDeps();
                    $("#depModal").modal("hide");
                }else{
                    layer.msg(msg,{icon:2});
                }
            }
        });

    }
})
