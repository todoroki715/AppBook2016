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
<div>
<img  height="40" width="90" alt="" src="./image/image2.png" align="left"/>
</div>
	<table class="login" align="right">
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
</form>
</body>
</html>