<%@ page import="com.shetech.phonebook.page.BeanExample" %><%--
  Created by IntelliJ IDEA.
  User: C323928
  Date: 12/05/2022
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ciclo For</title>
</head>
<body>
<h1>PAGINA CICLO FOR</h1>
<ul>
    <%
        BeanExample bean = new BeanExample();
    %>
    <%=bean.getCount()%>
    <%
        for (int i = 0; i < 100; i++) {
    %>

    <li><%=i%>
    </li>
    <%
        }
    %>
</ul>
</body>
</html>
