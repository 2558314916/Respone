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
    <script src="${pageContext.request.contextPath}/WEB-INF/static/JQuery.js"></script>
  </head>
  <body>
  <form action="login" method="post">
    username: <input type="text" name="username" placeholder="username"><br>
    password: <input type="password" name="passwd" placeholder="passwd"><br>
    vCode<input type="text" name="code"><br>
    <input type="submit" value="Submit"><br>
    <a href="out">点击下载</a>
    <%--验证码--%>
    <br>
    <a href="javascript:" onclick="changeCodeImg()">
      <img id="vimg" src="vCode"/>
    </a>
  </form>
  <br>
  <a href="redirect"> redirect </a><br>
  <a href="mime">mime</a>
  <script>
    function changeCodeImg(){
      var time = new Date().getTime();
      let vimg = document.getElementById("vimg");
      vimg.onclick = function (){
        vimg.src = "vCode?time="+time;
      }
    }
  </script>
  </body>
</html>
