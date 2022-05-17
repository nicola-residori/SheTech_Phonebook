<%@ page import="com.shetech.phonebook.controller.PhonebookPage" %>
<%@ page import="com.shetech.phonebook.domain.Contact" %><%--
  Created by IntelliJ IDEA.
  User: C323928
  Date: 12/05/2022
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rubrica telefonica</title>

    <!-- caller -->
    <jsp:useBean id="phonebookCaller" class="com.shetech.phonebook.service.PhonebookCaller" scope="application"/>
    <jsp:useBean id="phonebookCallerMock" class="com.shetech.phonebook.service.PhonebookCallerMock"
                 scope="application"/>

    <!-- controller -->
    <jsp:useBean id="controller" class="com.shetech.phonebook.controller.PhonebookPage" scope="page"/>
    <%
        controller.setPhonebookCaller(phonebookCaller);
        controller.setPhonebookCallerMock(phonebookCallerMock);
    %>

    <!-- check command -->
    <%
        String command = request.getParameter("command");

        if ("AGGIUNGI".equalsIgnoreCase(command) || "AGGIORNA".equalsIgnoreCase(command)) {
            Contact contact = new Contact();
            contact.setPhone(request.getParameter("phone"));
            contact.setName(request.getParameter("name"));
            contact.setSurname(request.getParameter("surname"));

            String age = request.getParameter("age");
            if (age != null && !age.isEmpty()) {
                contact.setAge(Integer.parseInt(age));
            }
            contact.setFemale("female".equalsIgnoreCase(request.getParameter("sex")));

            if ("AGGIUNGI".equalsIgnoreCase(command)) {
                controller.add(contact);
            } else {
                controller.update(contact.getPhone(), contact);
            }
        } else if ("ELIMINA".equalsIgnoreCase(command)) {
            String phone = request.getParameter("phone");
            controller.remove(phone);
        }
    %>
</head>
<body>

<!-- form inserimento -->
<div style="border: solid 1pt; margin: 10px; padding: 10px;width: 700px;">
    <form name="formInserimento" method="post">
        <table>
            <tr>
                <td>Telefono:</td>
                <td><input type="text" name="phone" value=""/></td>
            </tr>
            <tr>
                <td>Nome:</td>
                <td><input type="text" name="name" value=""/></td>
            </tr>
            <tr>
                <td>Cognome:</td>
                <td><input type="text" name="surname" value=""/></td>
            </tr>
            <tr>
                <td>Eta:</td>
                <td><input type="text" name="age" value=""/></td>
            </tr>
            <tr>
                <td>Sesso:</td>
                <td>
                    <input type="radio" name="sex" value="male">UOMO</input>
                    <input type="radio" name="sex" value="female">DONNA</input>
                </td>
            </tr>
        </table>
        <input type="submit" name="command" value="AGGIUNGI"/>
        <input type="submit" name="command" value="ELIMINA"/>
        <input type="submit" name="command" value="AGGIORNA"/>
        <input type="submit" name="command" value="RICARICA"/>
    </form>
</div>

<!-- lista contatti -->
<div style="border: solid 1pt; margin: 10px; padding: 10px;width: 700px;">
    <table>
        <tr>
            <th>Telefono</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Eta</th>
            <th>Sesso</th>
        </tr>

        <%
            for (Contact c : controller.getContacts()) {
        %>
        <tr>
            <td><%=c.getPhone()%>
            </td>
            <td><%=c.getName()%>
            </td>
            <td><%=c.getSurname()%>
            </td>
            <td><%=c.getAge()%>
            </td>
            <td><%=c.isFemale() ? "DONNA" : "UOMO"%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>

</body>
</html>
