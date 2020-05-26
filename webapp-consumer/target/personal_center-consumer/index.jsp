<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>homepage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        .box {
            width: 300px;
            float: left;
            margin: 0 20px 0 20px;
        }
        .box div, .box input {
            border: 1px solid;
            -moz-border-radius: 4px;
            border-radius: 4px;
            width: 100%;
            padding: 0px;
            margin: 5px;
        }
        .box div {
            border-color: grey;
            height: 300px;
            overflow: auto;
        }
        .box input {
            height: 30px;
        }
        h1 {
            margin-left: 30px;
        }
        body {
            background-color: #F0F0F0;
            font-family: "Arial";
        }
    </style>

</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="javascript:return host+':8081/home'">青年社交平台</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item  dropdown">
                    <a class="nav-link dropdown-toggle dropdown-toggle-split" href="#" id="homeDropdown" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        主页
                    </a>
                    <div class="dropdown-menu" aria-labelledby="homeDropdown">
                        <a class="dropdown-item" href="javascript:void(0)" onclick="showStation('推荐')">推荐车站</a>
                        <a class="dropdown-item" href="javascript:void(0)" onclick="showStation('音乐')">音乐路线</a>
                        <a class="dropdown-item" href="javascript:void(0)" onclick="showStation('影评')">影评路线</a>
                        <a class="dropdown-item" href="javascript:void(0)" onclick="showStation('摄影')">摄影路线</a>
                        <a class="dropdown-item" href="javascript:void(0)" onclick="showStation('其他')">其他路线</a>
                    </div>
                </li>

            </ul>
            <ul id="personal-navbar" class="navbar-nav" style="display: none">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="personalCenterDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        个人中心
                    </a>
                    <div class="dropdown-menu" aria-labelledby="personalCenterDropdown">
                        <h4 id="randomNumber" class="text-center">用户编号</h4>
                        <a class="dropdown-item" href="javascript:void(0)" onclick="rerandom()">重新生成编号</a>
                        <a class="dropdown-item" href="javascript:void(0)" onclick="createRpFrame()">设置新密码</a>
                        <a class="dropdown-item" href="javascript:void(0)" onclick="createStationFrame()">自建车站</a>
                        <a class="dropdown-item" href="javascript:void(0)" onclick="logout()">退出登陆</a>
                    </div>
                </li>
            </ul>
            <ul id="gp-navbar" class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="gpDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        留音机
                    </a>
                    <div class="dropdown-menu" aria-labelledby="gpDropdown">
                        <a class="dropdown-item" href="javascript:void(0)" onclick="GP('time')">最新留音</a>
                        <a class="dropdown-item" href="javascript:void(0)" onclick="GP('star')">热门留音</a>
                    </div>
                </li>
            </ul>
            <ul id="login-navbar" class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="loginDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        登录
                    </a>
                    <div class="dropdown-menu" aria-labelledby="loginDropdown">
                        <form class="px-4 py-3">
                            <div class="form-group">
                                <label id="random" for="userid">用户编号</label>
                                <input type="text" name="userid" class="form-control" id="userid" placeholder="PXY19003211">
                            </div>
                            <div class="form-group">
                                <label for="password">密码</label>
                                <input type="password" name="password" class="form-control" id="password" placeholder="Password">
                            </div>
                            <button type="button" id="loginButton" onclick="login()" class="btn btn-outline-success">登录</button>
                        </form>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="javascript:void(0)" onclick="createRegisterFrame()">没有账户吗？尝试注册一个</a>
                    </div>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" id="searchBar" type="search" placeholder="在这里搜索车站关键词" aria-label="Search">
                <button type="button" id="searchButton" class="btn btn-outline-success my-2 my-sm-0" onclick="searchBlurry()">搜索</button>
            </form>
        </div>
    </nav>
    <div class="container" id="context">
        <div class="jumbotron">
            <h1 class="display-4">你好!</h1>
            <p class="lead">欢迎来到青年社交平台</p>
            <hr class="my-4">
            <p>这是一个匿名社交平台，在这里你可以和别人匿名实时聊天</p>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<%--    <script src="http://cdn.jsdelivr.net/sockjs/1.0.1/sockjs.min.js"></script>--%>
    <script>
        let host = '192.168.43.67';
        let loginbar = $("#login-navbar");
        let persoanlbar = $("#personal-navbar");
        let loginUser;
        let random = $("#random");
        let personalRandom = $("#randomNumber");
        function login() {
            let userid = $("#userid").val();
            let password = $("#password").val();
            $.ajax(
                "http://"+host+":8081/user/login",
                {
                    method: "GET",
                    data: {
                        userid: userid,
                        password: password
                    },
                    success: function (result) {
                        if (result.code == 200) {
                            alert("登录成功")
                            loginUser = result.data.loginUser;
                            loginbar.hide();
                            persoanlbar.show();
                            personalRandom.text(loginUser.randomNumber);
                        }else{
                            alert("用户编号或密码错误");
                        }
                    }
                });
        }

        let p0;
        let p1;


        let rf;
        function register() {
            alert(p0.val());
            $.ajax(
                "http://"+host+":8081/user/register",
                {
                    method: "POST",
                    data: {
                        password: p0.val()
                    },
                    success: function (result) {
                        if (result.code == 200) {
                            let registerUser = result.data.registerUser;
                            alert("注册成功！用户编号为："+registerUser.randomNumber)
                            rf.hide();
                        }else{
                            alert("注册失败")
                        }
                    },
                    error: function (xhr, status, n) {
                        alert("status");

                    }
                });
        }

        function rerandom() {
            $.ajax(
                "http://"+host+":8081/user/rerandom",
                {
                    method:"PUT",
                    success: function (result) {
                        if (result.code == 200) {
                            loginUser = result = result.data.loginUser;
                            alert("申请成功："+loginUser.randomNumber);
                            personalRandom.text(loginUser.randomNumber);
                        }else{
                            loginbar.show();
                            persoanlbar.hide();
                            alert("生成失败");
                        }

                    }

            })

        }

        function logout() {
            $.ajax(
                "http://"+host+":8081/user/logout",
                {
                    method:"GET",
                    success: function (result) {
                        if (result.code === 200) {
                            alert("注销成功")
                            persoanlbar.hide();
                            loginbar.show();
                            loginUser = undefined;
                            window.location.reload();
                        }else{
                            alert("注销失败")
                        }

                    }
                }
            );
        }

        let context = $('#context');
        function createRegisterFrame() {
            rf = $('        <div id="registerFrame">\n' +
                '            <form>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="registerPass">密码</label>\n' +
                '                    <input type="password" class="form-control" id="registerPass" aria-describedby="emailHelp">\n' +
                '                </di v>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="registerPassAgain">确认密码</label>\n' +
                '                    <input type="password" class="form-control" id="registerPassAgain">\n' +
                '                    <small id="passwordhelp" class="form-text text-muted">请输入两次相同的密码</small>\n' +
                '                </div>\n' +
                '            </form>\n' +
                '            <button id="registerButton" onclick="register()" class="btn btn-outline-success">注册</button>\n' +
                '        </div>\n');
            context.empty();
            context.append(rf);
            p1 = $("#registerPassAgain");
            p0 = $("#registerPass");

        }

        function createStationFrame() {
            context.empty();
            stationFrame = $('        <div id="createStationFrame">\n' +
                '            <form>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="stationName">车站标题</label>\n' +
                '                    <input type="text" class="form-control" id="stationName" aria-describedby="emailHelp">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="stationIntroduction">车站简介</label>\n' +
                '                    <textarea class="form-control" id="stationIntroduction" rows="3"></textarea>\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="stationIntroduction">车站简介</label>\n' +
                '                </div>\n' +
                '                <div class="form-check form-check-inline">\n' +
                '                    <input class="form-check-input" name="inlineRadioOptions" type="radio" id="inlineRadio1" value="摄影">\n' +
                '                    <label class="form-check-label" for="inlineRadio1">摄影路线</label>\n' +
                '                </div>\n' +
                '                <div class="form-check form-check-inline">\n' +
                '                    <input class="form-check-input" name="inlineRadioOptions" type="radio" id="inlineRadio2" value="音乐">\n' +
                '                    <label class="form-check-label" for="inlineRadio2">音乐路线</label>\n' +
                '                </div>\n' +
                '                <div class="form-check form-check-inline">\n' +
                '                    <input class="form-check-input" name="inlineRadioOptions" type="radio" id="inlineRadio3" value="影评">\n' +
                '                    <label class="form-check-label" for="inlineRadio3">影评路线</label>\n' +
                '                </div>\n' +
                '                <div class="form-check form-check-inline">\n' +
                '                    <input class="form-check-input" name="inlineRadioOptions" type="radio"  id="inlineRadio4" value="其他">\n' +
                '                    <label class="form-check-label" for="inlineRadio4">其他路线</label>\n' +
                '                </div>\n' +
                '            </form>\n' +
                '            <button onclick="createStation()" class="btn btn-outline-success">创建</button>\n' +
                '        </div>\n')
            context.append(stationFrame)
        }
        let stationFrame;
        let inputd;
        let category;
        function createStation() {
            inputd = document.getElementsByName("inlineRadioOptions");
            for (let i = 0; i < inputd.length;i++) {
                if (inputd[i].checked===true) {
                    category = inputd[i];
                    break;
                }
            }
            alert($(category).val())
            $.ajax(
                "http://"+host+":8081/user/create_station",
                {
                    method:"POST",
                    data:{
                        stationName:$('#stationName').val(),
                        stationIntroduction:$('#stationIntroduction').val(),
                        category:$(category).val()
                    },
                    success: function (result) {
                        if (result.code === 200) {
                            alert("创建成功");
                            stationFrame.hide();
                        }else{
                            alert("创建失败")
                        }
                    },
                    error: function (xmh, status, errorThrow) {
                        alert(status)

                    }
                }
            );
        }

        let rpFrame;

        function createRpFrame() {
            context.empty();
            rpFrame = $('        <div id="rpFrame">\n' +
                '            <form>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="oldPass">旧密码</label>\n' +
                '                    <input type="text" class="form-control" id="oldPass" aria-describedby="emailHelp">\n' +
                '                </div>\n' +
                '                <div class="form-group">\n' +
                '                    <label for="newPsss">新密码</label>\n' +
                '                    <input class="form-control" id="newPsss"></input>\n' +
                '                </div>\n' +
                '            </form>\n' +
                '            <button onclick="resetPass()" class="btn btn-outline-success">提交</button>\n' +
                '        </div>\n')
            context.append(rpFrame);

        }
        let oldPass;
        let newPass;
        function resetPass() {
            oldPass = $("#oldPass").val();
            newPass = $("#newPsss").val();
            $.ajax(
                "http://"+host+":8081/user/reset_password",
                {
                    method:"PUT",
                    data:{
                        oldPass:oldPass,
                        newPass: newPass
                    },
                    success: function (result) {
                        if (result.code === 200) {
                            alert("修改成功：old:"+oldPass+"new:"+newPass);
                            rpFrame.hide();
                        }else if (result.code===400){
                            alert(result.message);
                        }
                    },
                    error: function (xmh, status, errorThrow) {
                        alert(status)
                    }
                }
            );
        }

    </script>
<script>
    var print;
    let chatFrame;
    function createChatFrame(obj) {
        if (loginUser === undefined) {
            alert("您还没有登录，登陆后才能访问车站");
            return;
        }
        context.empty();
        if (sockjs != undefined) {
            sockjs.close();
        }
        chatFrame = $('' +
            '<h2 class="text-left">当前在线人数：<p id="'+$(obj).attr("id")+'onlineCount"></p></h2>' +
            '<h3 class="text-left">车站标题：<p>'+$(obj).find("h5").text()+'</p></h3>' +
            '<h4 class="text-left">车站简介：<P>'+$(obj).find("p").text()+'</p></h4>' +
            '<div id="first" class="box">\n' +
            '    <div></div>\n' +
            '    <form><input autocomplete="off" value="Type here..."></input>' +
            '<button class="btn btn-outline-primary" id="chatSubmit">发送</button> </form>\n' +
            '</div>');
        context.append(chatFrame);
        $('#first input').focus();
        var chatSubmit = $('#chatSubmit');
        var div;
        var inp;
        var form;
        div = $('#first div');
        inp = $('#first input');
        form = $('#first form');
        print = function (m, p) {
            p = (p === undefined) ? '' : JSON.stringify(p);
            div.append($("<code>").text(m + ' ' + p));
            div.append($("<br>"));
            div.scrollTop(div.scrollTop() + 10000);
        };
        chatSubmit.click(function() {
            sockjs.send(inp.val());
            inp.val('');
            return false;
        });
        var sockjs_url = 'ws://' + host + ':8081/ws?uid=' + loginUser.randomNumber + '&sid=' + $(obj).attr('id');
        sockjs = new WebSocket(sockjs_url);
        sockjs.onopen = function () {
            print('[' + loginUser.randomNumber + ']进入房间');
        };
        sockjs.onmessage = function (e) {
            let msgObject = JSON.parse(e.data);
            print('['+msgObject.userId+'] ：', msgObject.msg);
            $('#' + $(obj).attr("id") + 'onlineCount').text(msgObject.count);
        };
        sockjs.onclose = function () {
            print('服务器断开连接');
        };

        function checkChat(olddata, newdata) {
            if (olddata === newdata) {
                alert("没得");
            }

        }
    }
    let sockjs;
    let stationPage;
    let stationList;
    function showStation(key) {
        $.ajax(
            "http://"+host+":8081/home/search_station",
            {
                method:"GET",
                data:{
                    category:key
                },
                success: function (result) {
                    if (result.code === 200) {
                        stationList = result.data.stationList;
                        bindStation(stationList);
                    }else{
                        alert("此路线暂时没有车站哦，你可以尝试在个人中心->自建车站中去创建一个~");
                    }
                },
                error: function (xmh, status, errorThrow) {
                    alert("服务器繁忙");
                }
            }
        )
    }

    function bindStation(stationList) {
        context.empty();
        stationPage = $('<div id="stationPage"></div>');
        stationPage.empty();
        let listGroup = $('<div class="list-group"></div>');
        for (let i = 0; i < stationList.length; i++) {
            let stationId = stationList[i].id;
            let stationName = stationList[i].stationName;
            let stationIntroduction = stationList[i].stationIntroduction;
            let category = stationList[i].category;
            let createTime = new Date(stationList[i].createTime).toString();
            let listNode =$('' +
                '<a href="javascript:void(0)" id="'+stationId+'" onclick="createChatFrame(this)" class="list-group-item list-group-item-action">\n' +
                '    <div class="d-flex w-100 justify-content-between">\n' +
                '      <h5 class="mb-1">'+stationName+'</h5>\n' +
                '      <small>'+createTime+'</small>\n' +
                '    </div>\n' +
                '    <p class="mb-1">'+stationIntroduction+'</p>\n' +
                '    <small>'+category+'</small>\n' +
                '  </a>' +
                '')
            listGroup.append(listNode);
        }
        stationPage.append(listGroup);
        context.append(stationPage);
    }

    function searchBlurry() {
        let searchBar = $("#searchBar");
        $.ajax(
            "http://" + host + ":8081/home/search/" + searchBar.val(),
            {
                method: "GET",
                success: function (result) {
                    if (result.code === 200) {
                        stationList = result.data.stationList;
                        bindStation(stationList);
                    } else {
                        alert("您搜索的内容一点都没有");
                    }
                },
                error: function (xmh, status, errorThrow) {
                    alert("服务器繁忙");
                }
            }
        );
    }
</script>
    <script>
        function GP(key) {
            $.ajax(
                "http://" + host + ":8081/gramophone/search/" + key,
                {
                    method: "GET",
                    success: function (result) {
                        if (result.code === 200) {
                            //渲染
                            let gpList = $(result.data.gramophone);
                            if (key === "time") {
                                showGp(gpList);
                            }else
                            {
                                showGpStar(gpList);
                            }
                        }else{
                            //不想渲染 提示没有一条
                            alert("留音机里似乎还没有留音存在")
                        }
                    },
                    error: function (xhr, status, o) {
                        alert("服务器繁忙");
                    }
                }
            );

        }

        function showGpStar(gpList) {
            context.empty();
            let gpPage = $('<div id="gpPage"></div>');
            gpPage.empty();
            let listGroup = $('<div class="list-group"></div>');
            for (let i = gpList.length-1; i >= 0; i--) {
                let gpId = gpList[i].id;
                let gpContent = gpList[i].content;
                let gpTime = new Date(gpList[i].time).toString();
                let gpStar = gpList[i].star;
                let listNode =$('' +
                    '<a href="javascript:void(0)" id="'+gpId+'" class="list-group-item list-group-item-action">\n' +
                    '    <div class="d-flex w-100 justify-content-between">\n' +
                    '      <h5 class="mb-1">'+gpContent+'</h5>\n' +
                    '      <small>'+gpTime+'</small>\n' +
                    '    </div>\n' +
                    '    <small>点赞数：'+gpStar+'</small>\n' +
                    '  </a>' +
                    '')
                listGroup.append(listNode);
            }
            gpPage.append(listGroup);
            context.append(gpPage);
        }
        function showGp(gpList) {
            context.empty();
            let gpPage = $('<div id="gpPage"></div>');
            gpPage.empty();
            let listGroup = $('<div class="list-group"></div>');
            for (let i = gpList.length-1; i >=0 ; i--) {
                let gpId = gpList[i].id;
                let gpContent = gpList[i].content;
                let gpTime = new Date(gpList[i].time).toString();
                let gpStar = gpList[i].star;
                let listNode =$('' +
                    '<a href="javascript:void(0)" id="'+gpId+'" class="list-group-item list-group-item-action">\n' +
                    '    <div class="d-flex w-100 justify-content-between">\n' +
                    '      <h5 class="mb-1">'+gpContent+'</h5>\n' +
                    '      <small>'+gpTime+'</small>\n' +
                    '    </div>\n' +
                    '    <small>点赞数：'+gpStar+'</small>\n' +
                    '  </a>' +
                    '')
                listGroup.append(listNode);
            }
            gpPage.append(listGroup);
            context.append(gpPage);
        }
    </script>
</body>
</html>