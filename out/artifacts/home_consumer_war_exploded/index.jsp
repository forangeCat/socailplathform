<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>homepage</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="localhost:8080/home">青年社交平台</a>
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
                        <a class="dropdown-item" href="#">推荐车站</a>
                        <a class="dropdown-item" href="#">音乐路线</a>
                        <a class="dropdown-item" href="#">影评路线</a>
                        <a class="dropdown-item" href="#">摄影路线</a>
                        <a class="dropdown-item" href="#">其他路线</a>
                    </div>
                </li>

            </ul>
            <ul class="navbar-nav">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="personalCenterDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        个人中心
                    </a>
                    <div class="dropdown-menu" aria-labelledby="personalCenterDropdown">
                        <h4 class="text-center">用户编号</h4>
                        <a class="dropdown-item" href="#">重新生成编号</a>
                        <a class="dropdown-item" href="#">设置新密码</a>
                        <a class="dropdown-item" href="#">我的留音机</a>
                        <a class="dropdown-item" href="#">自建车站</a>
                        <a class="dropdown-item" href="#">退出登陆</a>
                    </div>
                </li>
            </ul>
            <ul class="navbar-nav">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="loginDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        登录
                    </a>
                    <div class="dropdown-menu" aria-labelledby="loginDropdown">
                        <form action="localhost:8081/personal/user/login"class="px-4 py-3">
                            <div class="form-group">
                                <label for="exampleDropdownFormEmail1">用户编号</label>
                                <input type="text" name="userid" class="form-control" id="exampleDropdownFormEmail1" placeholder="PXY19003211">
                            </div>
                            <div class="form-group">
                                <label for="exampleDropdownFormPassword1">密码</label>
                                <input type="password" name="password" class="form-control" id="exampleDropdownFormPassword1" placeholder="Password">
                            </div>

                            <button type="submit" class="btn btn-outline-success">登录</button>
                        </form>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">没有账户吗？尝试注册一个</a>
                    </div>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="在这里搜索车站关键词" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
            </form>
        </div>
    </nav>

</body>
</html>