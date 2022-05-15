<%@page import="com.Notification"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>notifications Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/notifications.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Items Management V10.1</h1>
<form id="formNotification" name="formNotification">
Header:
<input id="header" name="header" type="text"
class="form-control form-control-sm">
<br> Description:
<input id="description" name="description" type="text"
class="form-control form-control-sm">

<br>
<input id="btnSave" name="btnSave" type="button" value="Save"
class="btn btn-primary">
<input type="hidden" id="hidNotificationIDSave"
name="hidNotificationIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id="divItemsGrid">
<%
Notification notificationObj = new Notification();
out.print(notificationObj.readNotifications());
%>
</div>
</div> </div> </div>
</body>
</html>