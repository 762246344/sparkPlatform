/**
 * Created by pc on 2017/4/30.
 */

get_table();
get_files();
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
    $("[href='#sql-select']").addClass("active");
});

$('#upload').submit(function (event) {
    event.preventDefault();
    var form = $(this);
    var formData = new FormData();
    formData.append("file", $("#fileField")[0].files[0]);
    //普通表单
    $.ajax({
        type: form.attr('method'),
        url: form.attr('action'),
        data: formData,
        processData: false,
        contentType: false
        //enctype:"multipart/form-data"
    }).success(function () {
        if (data.errno == "0") {
            alert("上传成功");
        } else {
            alert("上传失败");
        }
        console.log("success");
        //成功提交
    }).fail(function (jqXHR, textStatus, errorThrown) {
        //错误信息
    });
});

//sql 执行
function exec_sql() {
    var exec = {};
    exec.sql = code_text_sql.value;
    $.ajax({
        contentType: "application/json; charset=utf-8",
        type: "POST",
        crossDomain: true,
        //dataType: "json",
        url: sparkApiUrl + "/sql/query",
        data: JSON.stringify(exec),
        success: function (data) {
            if (data.errno == "0") {
                var row = "<tr>";
                for (i in data.data[0]) {
                    row += "<td>" + data.data[0][i] + "</td>";
                }
                row += "</tr>";
                $("#res_table thead").html(row);
                var tbody = "";
                for (var i = 1; i < data.data.length; i++) {
                    row = "<tr>";
                    for (j in data.data[i]) {
                        row += "<td>" + data.data[i][j] + "</td>";
                    }
                    row += "</tr>";
                    tbody += row;
                }
                $("#res_table tbody").html(tbody);
            } else {
                alert("执行sql语句失败：" + data.errmsg);
            }
        },
        fail: function (data) {
            alert("服务异常");
        }
    });
}
//web-shell  执行
function exec_shell() {
    var exec = {};
    exec.code = code_text.value;
    exec.kind = 'spark';
    console.log(JSON.stringify(exec));
    $.ajax({
        contentType: "application/json; charset=utf-8",
        type: "POST",
        crossDomain: true,
        //dataType: "json",
        url: sparkApiUrl + "/session/exec",
        data: JSON.stringify(exec),
        success: function (data) {
            if (data.errno == "0") {
                res_shell.value = data.data;
            } else {
                alert("执行shell语句失败：" + data.errmsg);
            }
        },
        fail: function (data) {
            alert("服务异常");
        }
    });
}
//清空
function clear_sql() {
    code_text.value = "";
    res.value = "";
    code_text1.value = "";
    res1.value = "";
}
function jump_webUI() {
    this.href = "a.html";
}
function jump_log() {
    this.href = "b.html";
}
function sql_select() {
    $(".content-left1").addClass("inactive");
    $(".content-left").removeClass("inactive");
    $(".content-right1").addClass("inactive");
    $(".content-right").removeClass("inactive");
    $(".sql_select").removeClass("inactive").siblings().addClass("inactive");
}
//获取列表
function get_table() {
    $.ajax({
        type: "GET",
        crossDomain: true,
        url: sparkApiUrl + "/sql/allTable",
        success: function (data) {
            if (data.errno == "0") {
                for (i in data.data) {
                    var li = "<li oncontextmenu=right_click('" + data.data[i] + "')>" + data.data[i] + "</li>";
                    $("#table_ul").append(li);
                }
            } else {
                alert("获取表失败"+data.errmsg);
            }
        },
        fail: function (data) {
            alert("服务异常");
        }
    });
}
function get_files() {
    $.ajax({
        type: "GET",
        crossDomain: true,
        url: sparkApiUrl + "/hdfs/getFile",
        success: function (data) {
            if (data.errno == "0") {
                $("#exec_files").empty();
                for (i in data.data) {
                    var li = "<li>" + data.data[i].fileName + "<span><img src='img/delete.png'></span></li>";
                    $("#exec_files").append(li);
                }
            } else {
                alert("获取执行文件列表失败"+data.errmsg);
            }
        },
        fail: function (data) {
            alert("服务异常");
        }
    });
}
function web_shell() {
    $(".content-left1").addClass("inactive");
    $(".content-left").removeClass("inactive");
    $(".content-right1").addClass("inactive");
    $(".content-right").removeClass("inactive");
    $(".web_shell").removeClass("inactive").siblings().addClass("inactive");
}
function mission_submit() {
    $(".content-left1").removeClass("inactive");
    $(".content-left").addClass("inactive");
    $(".content-right1").removeClass("inactive");
    $(".content-right").addClass("inactive");
    //$(".mission_submit").removeClass("inactive").siblings().addClass("inactive");
}

//配置任务
//function show1(btn) {
//    $(".pro").slideToggle(1000);
//    if (btn.innerHTML == "收起") {
//        btn.innerHTML = "配置任务";
//    } else {
//        btn.innerHTML = "收起";
//    }
//}

function sel(value) {
    if (value == "Python") {
        $("#class_name").attr("disabled", true);
    } else {
        $("#class_name").removeAttr("disabled");
    }
}

function right_click(table) {
    $("#menu_right").css("left", window.event.clientX);
    $("#menu_right").css("top", window.event.clientY);
    $("#menu_right").css("display", "block");
    $("#menu_right ul li:eq(0)").click(function () {
        $("#code_text_sql").html("desc " + table);
        $("#exec_sql_btn").click();
    });
    $("#menu_right ul li:eq(1)").click(function () {
        $("#code_text_sql").html("select count(*) from " + table);
        $("#exec_sql_btn").click();
    });
    $("#menu_right ul li:eq(2)").click(function () {
        $("#code_text_sql").html("select * from " + table + " limit 10");
        $("#exec_sql_btn").click();
    });
    $("#menu_right ul li:eq(3)").click(function () {
        $("#code_text_sql").html("select * from " + table + " limit 20");
        $("#exec_sql_btn").click();
    });
    $("#menu_right ul li:eq(4)").click(function () {
        $("#code_text_sql").html("select * from " + table + " limit 30");
        $("#exec_sql_btn").click();
    });
    event.returnValue = false;
    document.onclick = function () {
        $("#menu_right").css("display", "none");
    }
}

function del(){
    //TODO 记得修改
    $(this).parent().remove();
}