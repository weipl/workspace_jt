<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
	<h1>用户列表</h1>
	<table border="1">
	<tr align="center"><td><b>编号</b></td><td><b>姓名</b></td><td><b>出生年月</b></td><td><b>地址</b></td></tr>
	<c:forEach items="${userList}" var="u">
	<tr>
		<td>${u.id}</td>
		<td>${u.name}</td>
		<td>${u.birthday}</td>
		<td>${u.address}</td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>