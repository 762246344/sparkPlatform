/**
 * Created by pc on 2017/4/30.
 */

    //左侧列表
    //			$(".content-left ul li a").click(function(){
    //				$(this).parent().children().toggleClass("active");
    //			});
$(".header ul li a").click(function () {
    $(this).addClass("active").siblings(".active").removeClass("active");
//        $(this).addClass("disabled").siblings(".disabled").removeClass("disabled");
//        var id = $(this).attr("href");
//        $(id).addClass("active").siblings(".active").removeClass("active");
});
$(document).ready(function () {
    $("#sql-select").addClass("active");
//        $("a :frist").addClass("active");
//        $("[href='#sql-select']").addClass("disabled");
    $("[href='#sql-select']").addClass("active");
});

$('#upload').submit(function (event) {
    event.preventDefault();
    var form = $(this);
    var formData = new FormData();
    formData.append("file",$("#fileField")[0].files[0]);
    //普通表单
    $.ajax({
        type: form.attr('method'),
        url: form.attr('action'),
        data: formData,
        processData: false,
        contentType: false
        //enctype:"multipart/form-data"
    }).success(function () {
        console.log("success");
        //成功提交
    }).fail(function (jqXHR, textStatus, errorThrown) {
        //错误信息
    });
});
function exec1(){
    var exec = new Object();
    exec.code = code_text1.value;
    exec.kind = 'spark';
    console.log(JSON.stringify(exec));
    $.ajax({
        contentType: "application/json; charset=utf-8",
        type: "POST",
        crossDomain: true,
        //dataType: "json",
        url: "http://platform.spark.com:8080/session/exec",
        data: JSON.stringify(exec),
        success: function (data) {
            console.log(data.data);
            res1.value = data.data;
        }
    });
}
function exec2() {
    var exec = new Object();
    exec.code = code_text.value;
    exec.kind = 'spark';
    console.log(JSON.stringify(exec));
    $.ajax({
        contentType: "application/json; charset=utf-8",
        type: "POST",
        crossDomain: true,
        //dataType: "json",
        url: "http://platform.spark.com:8080/session/exec",
        data: JSON.stringify(exec),
        success: function (data) {
            console.log(data.data);
            res.value = data.data;
        }
    });
}
function clear1(){
    code_text.value="";
    res.value="";
    code_text1.value="";
    res1.value="";
}
function jump_webUI(){
   this.href="a.html";
}
function jump_log(){
    this.href="b.html";
}
function sql_select(){
    $(".content-left1").addClass("inactive");
    $(".content-left").removeClass("inactive");
    $(".content-right1").addClass("inactive");
    $(".content-right").removeClass("inactive");
    $(".sql_select").removeClass("inactive").siblings().addClass("inactive");
}
function web_shell(){
    $(".content-left1").addClass("inactive");
    $(".content-left").removeClass("inactive");
    $(".content-right1").addClass("inactive");
    $(".content-right").removeClass("inactive");
    $(".web_shell").removeClass("inactive").siblings().addClass("inactive");
}
function mission_submit(){
    $(".content-left1").removeClass("inactive");
    $(".content-left").addClass("inactive");
    $(".content-right1").removeClass("inactive");
    $(".content-right").addClass("inactive");
    //$(".mission_submit").removeClass("inactive").siblings().addClass("inactive");
}
function show1(btn){
    $(".pro").slideToggle(1000);
    if(btn.innerHTML=="收起"){
        btn.innerHTML="配置任务";
    }else{
        btn.innerHTML="收起";
    }
}
function sel(value){
    if(value=="Python"){
        $(".lei").addClass("inactive");
    }else{
        $(".lei").removeClass("inactive");
    }
}