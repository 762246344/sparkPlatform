/**
 * Created by pc on 2017/4/30.
 */


var itv;

//导航栏
$(document).ready(function () {
    $("#sql-select").addClass("active");
    $("[href='#sql-select']").addClass("active");
});

$(".header ul li a").click(function () {
    $(this).addClass("active").siblings(".active").removeClass("active");
    if ($(this).html() == "Spark-Job" && $(this).attr('class') == "active") {
        itv = setInterval(get_job_info, 3000);
    } else {
        clearInterval(itv);
    }
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
    exec.code = code_text_shell.value;
    if($("#lan").val()=="scala"){
        exec.kind = 'spark';
    }else {
        exec.kind = 'pyspark';
    }
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
//上传文件
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
//清空
function clear_txt() {
    code_text_sql.value = "";
    res_sql.value = "";
    code_text_shell.value = "";
    res_shell.value = "";
}
//跳转
function jump_webUI() {
    var type = $("#lan").val().toLowerCase();
    $.ajax({
        type: "GET",
        crossDomain: true,
        url: sparkApiUrl + "/session/getUrl?type=" + type,
        success: function (data) {
            if (data.errno == "0") {
                window.open(data.data.sparkUiUrl);
            } else {
                alert("获取url失败" + data.errmsg);
            }
        },
        fail: function (data) {
            alert("服务异常");
        }
    });
}
//跳转
function jump_log() {
    var type = $("#lan").val().toLowerCase();
    $.ajax({
        type: "GET",
        crossDomain: true,
        url: sparkApiUrl + "/session/getUrl?type=" + type,
        success: function (data) {
            if (data.errno == "0") {
                window.open(data.data.driverLogUrl);
            } else {
                alert("获取url失败" + data.errmsg);
            }
        },
        fail: function (data) {
            alert("服务异常");
        }
    });
}
//导航切换
function sql_select() {
    $(".content-left1").addClass("inactive");
    $(".content-left").removeClass("inactive");
    $(".content-right1").addClass("inactive");
    $(".content-right").removeClass("inactive");
    $(".sql_select").removeClass("inactive").siblings().addClass("inactive");
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
}
//获取列表
get_table();
function get_table() {
    $.ajax({
        type: "GET",
        crossDomain: true,
        url: sparkApiUrl + "/sql/allTable",
        success: function (data) {
            if (data.errno == "0") {
                $("#table_ul").empty();
                for (i in data.data) {
                    var li = "<li oncontextmenu=right_click('" + data.data[i] + "')>" + data.data[i] + "</li>";
                    $("#table_ul").append(li);
                }
            } else {
                alert("获取表失败" + data.errmsg);
            }
        },
        fail: function (data) {
            alert("服务异常");
        }
    });
}
get_files();
function get_files() {
    $.ajax({
        type: "GET",
        crossDomain: true,
        url: sparkApiUrl + "/hdfs/getFile",
        success: function (data) {
            if (data.errno == "0") {
                $("#exec_files").empty();
                $("#job_file").empty();
                for (i in data.data) {
                    var li = "<li>" + data.data[i].fileName + "<span onclick=\"del(\'" + data.data[i].filePath + "\')\"><img src='img/delete.png'></span></li>";
                    $("#exec_files").append(li);
                    var option = "<option value=" + data.data[i].filePath + ">" + data.data[i].fileName + "</option>"
                    $("#job_file").append(option);
                }
            } else {
                alert("获取执行文件列表失败" + data.errmsg);
            }
        },
        fail: function (data) {
            alert("服务异常");
        }
    });
}
//配置任务，类名切换
function sel(value) {
    if (value == "Python") {
        $("#job_class_name").attr("disabled", true);
    } else {
        $("#job_class_name").removeAttr("disabled");
    }
}
//右键菜单
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
//删除操作，刷新列表
function del(file_path) {
    if (confirm("确定删除吗？")) {
        var request_json = {};
        request_json.path = file_path;
        console.log(JSON.stringify(request_json));
        $.ajax({
            contentType: "application/json; charset=utf-8",
            type: "POST",
            crossDomain: true,
            //dataType: "json",
            url: sparkApiUrl + "/hdfs/delete",
            data: JSON.stringify(request_json),
            success: function (data) {
                if (data.errno == "0") {
                    alert("删除成功");
                    get_files();
                } else {
                    alert("删除失败：" + data.errmsg);
                }
            },
            fail: function (data) {
                alert("服务异常");
            }
        });
    }
}

function get_job_info() {
    $.ajax({
        type: "GET",
        crossDomain: true,
        url: sparkApiUrl + "/getJobInfo",
        success: function (data) {
            if (data.errno == "0") {
                $("#job_table tbody").empty();
                var row;
                var tbody = "";
                for (var i = 0; i < data.data.length; i++) {
                    row = "<tr>";
                    row += "<td>" + data.data[i].name + "</td>";
                    row += "<td>" + data.data[i].type + "</td>";
                    row += "<td>" + data.data[i].startTime + "</td>";
                    row += "<td>" + data.data[i].stopTime + "</td>";
                    row += "<td>" + data.data[i].state + "</td>";
                    if (data.data[i].yarnUrl == null || data.data[i].yarnUrl == "") {
                        row += "<td><a href='javascript:void(0);'>详情</td>";
                    } else {
                        row += "<td><a target='_blank' href='" + data.data[i].yarnUrl + "'>详情</td>";
                    }
                    row += "</tr>";
                    tbody = row + tbody;
                }
                $("#job_table tbody").html(tbody);
            } else {
                alert("获取任务列表失败：" + data.errmsg);
            }
        },
        fail: function (data) {
            alert("服务异常");
        }
    });
}

//提交任务
function submit_job(btn) {
    var request_json = {};
    request_json.type = $("#job_type").val();
    request_json.file = $("#job_file").val();
    if ($("#job_name").val() != "") {
        request_json.name = $("#job_name").val();
    }
    if ($("#job_args").val() != "") {
        request_json.args = $("#job_args").val().split(" ");
    }
    if ($("#job_type").val() != "Python") {
        request_json.className = $("#job_class_name").val();
    }
    console.log(JSON.stringify(request_json));
    $.ajax({
        contentType: "application/json; charset=utf-8",
        type: "POST",
        crossDomain: true,
        //dataType: "json",
        url: sparkApiUrl + "/batch",
        data: JSON.stringify(request_json),
        success: function (data) {
            if (data.errno == "0") {
                alert("提交成功");
            } else {
                alert("提交任务失败：" + data.errmsg);
            }
        },
        fail: function (data) {
            alert("服务异常");
        }
    });
}
