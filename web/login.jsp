<%--
  Created by IntelliJ IDEA.
  User: Canic
  Date: 2020/10/16
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>登录界面</title>
    <!-引用bootstrap-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
</head>
<body style="background: #10aeb5">
<div class="modal-dialog" style="margin-top: 10%">
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        <div class="modal-content">
        <div class="modal-header">
            <h4 class="modal-title text-center">登录</h4>
        </div>

        <div class="modal-body" id="modal-body">
           <%-- 用户名--%>
            <div class="form-group" style="margin-left: 20px;margin-right: 20px">
                <input type="text" name="account" class="form-control" placeholder="请输入账号：" autocomplete="off">
            </div>
           <%-- 密码--%>
            <div class="form-group" style="margin-left: 20px;margin-right: 20px">
                <input type="password" name="password" class="form-control" placeholder="请输入密码：" autocomplete="off">
            </div>
        </div>
        <div class="modal-footer">
             <div class="form-group" style="margin-left: 20px;margin-right: 20px">
                 <button type="submit" class="btn btn-primary form-control">登录</button>
             </div>
            </div>
        </div>
        <div>
            <%--登录失败消息提示--%>
            <%--col-md-4  占了页面4/？--%>
            <c:if test="${not empty msg}">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <%--可关闭按钮--%>
                         <div class="alert alert-danger alert-dismissable">
                             <button type="button" class="close" data-dismiss="alert"><span>×</span></button>
                             ${msg}
                         </div>
                    </div>
                </div>
            </c:if>
        </div>
    </form>
</div>
<%--导入script的包--%>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>




<%--<form action="" method="post">
    <h2>登录</h2>
       账号：<input type="text" placeholder="请输入账号"><br/>
       密码：<input type="password" placeholder="请输入密码"><br/>
       <input type="submit" value="登录">
</form>--%>
</body>
</html>
