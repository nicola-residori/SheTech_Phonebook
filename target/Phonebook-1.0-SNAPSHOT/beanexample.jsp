<%--
  Created by IntelliJ IDEA.
  User: C323928
  Date: 12/05/2022
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:useBean id="bean" class="com.shetech.phonebook.page.BeanExample" scope="application"/>

<%
    bean.setCognome("Paperino");
%>

<h1>cognome:</h1>
<p>
    <%= bean.getCognome()%>
</p>

<h1>nome:</h1>
<jsp:setProperty name="bean" property="nome" value="Nome"/>
<p>
    <jsp:getProperty name="bean" property="nome"/>
</p>

<h1>eta:</h1>
<p>
    <jsp:getProperty name="bean" property="eta"/>
</p>

<h1>contatore:</h1>
<p>
    <jsp:getProperty name="bean" property="count"/>
</p>

</body>
</html>
