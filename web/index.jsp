<%--
  Created by IntelliJ IDEA.
  User: Mucd
  Date: 2021/6/4/004
  Time: 下午 03:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="login">
    username: <input type="text" name="username" placeholder="username">
    password: <input type="password" name="passwd" placeholder="passwd">
    vCode<input type="text" name="code">
    <a href="javascript:"> <img src="vCode" alt="image" id="code_img" > </a>
    <input type="submit" value="Submit">
    <a href="out"> outputStream</a>
  </form>
  </body>
</html>
