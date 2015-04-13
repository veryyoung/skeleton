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
    <title>首页</title>
    <link href="${pageContext.request.contextPath}/assets/css/pages/index.css" rel="stylesheet"/>
    <%@include file="common/head.jspf" %>
</head>
<body>
<%@include file="common/topNav.jspf" %>
<table class="table table-bordered table-hover">
    <caption>用户管理</caption>
    <thead>
    <tr>
        <th>名称</th>
        <th>城市</th>
        <th>密码</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Tanmay</td>
        <td>Bangalore</td>
        <td>560001</td>
    </tr>
    <tr>
        <td>Sachin</td>
        <td>Mumbai</td>
        <td>400003</td>
    </tr>
    <tr>
        <td>Uma</td>
        <td>Pune</td>
        <td>411027</td>
    </tr>
    </tbody>
</table>
${users}
<%@include file="common/footer.jspf" %>
</body>


</html>
