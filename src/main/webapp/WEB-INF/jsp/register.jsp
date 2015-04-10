<%--
  Created by IntelliJ IDEA.
  User: veryyoung
  Date: 2015/3/3
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <%@include file="common/head.jspf" %>
</head>
<body>
<%@include file="common/topNav.jspf" %>
<div>
    <form class="form-horizontal" role="form" action="/register" method="POST">

        <div class="form-group">
            <label for="txt_loginname" class="col-sm-3 control-label">注册名</label>

            <div class="col-sm-2">
                <input type="text" class="form-control" id="txt_loginname" name="userName"
                       placeholder="用户名"/>
            </div>
        </div>

        <div class="form-group">
            <label for="txt_password" class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;码</label>

            <div class="col-sm-2">
                <input type="password" class="form-control" id="txt_password" name="password"
                       placeholder="密码"/>
            </div>
        </div>


        <div class="form-group">
            <div class="col-sm-offset-5 col-sm-10">
                <button type="submit" class="btn btn-info" id="login-submit">注册
                </button>
                &nbsp;
                <button type="reset" class="btn btn-warning">重置</button>
            </div>
        </div>


    </form>
</div>
<%@include file="common/footer.jspf" %>

</body>
</html>
