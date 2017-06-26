<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<link rel="stylesheet" href="./css/headerfooter.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ヘッダー</title>
</head>
<body>
<form action = "./logout.do" method = "post">
<div class="header">
	<div class="header_left">
	<img class="logo" alt="" src="./image/image2.png" align="left"/>
	</div>
	<div class="header_left">
	<h1 class="titlelogo"><label>TCT Library</label></h1>
	</div>
	<div class="header_logout">
	<table class="login">
		<tbody>
		<%if(session.getAttribute("GREETING_NAME") != null){%>
		<tr class="cell"><td>
		<%String Msg = (String)session.getAttribute("GREETING_NAME");%>
		<%=Msg %>
		</td></tr>
		<tr class="cell"><td>
		<input class="logoutBT" type="submit" name="changeBT" value="ログアウト" onclick='change()'/>
		</td></tr>
		<%} %>
		</tbody>
	</table>
	</div>
</div>
</form>
</body>
</html>