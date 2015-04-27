<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>首页-${appName}</title>
    <%@include file="common/head.jspf" %>
</head>
<body>
<%@include file="common/topNav.jspf" %>
<div class="margin-top-100"></div>
<table class="table">
    <thead>
    <thead>
    <tr>
        <th>用户名</th>
        <th>密码</th>
        <th>注册时间</th>
        <th>是否为管理员</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.userName}</td>
            <td><sk:stringMask beforeMask="${user.password}"/></td>
            <td><fmt:formatDate value="${user.createTime}" pattern="yyyy年MM月dd日"/></td>
            <td><c:choose>
                <c:when test="${user.admin}">
                    是
                </c:when>
                <c:otherwise>
                    否
                </c:otherwise>
            </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
    </tbody>
</table>
<div class="margin-bottom-100"></div>
<%@include file="common/footer.jspf" %>
</body>


</html>
