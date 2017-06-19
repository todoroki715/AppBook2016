<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.LoginTopFormBean" scope = "session" id = "loginTopForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/menu.css" type="text/css">
<title>メニュー画面</title>
</head>
<script type="text/javascript">
function lenret(){
	document.form.action="./lenBookList.do";
	document.form.method="post";
}

function lenhis(){
	document.form.action="./hisBookList.do";
	document.form.method="post";
}
</script>

<body>
<form name="form">
<div class="title"><h1><u>図書管理システムメニュー&nbsp;&nbsp;&nbsp;</u></h1></div>
	<div class="menuform">
		<table class="menu">
			<tr>
				<th style="text-align: left">貸出・返却</th>
			</tr>
			<tr class="manu_border">
				<th>貸し出しと返却ができます</th>
				<th><input class="BT" type="submit" name="lenretBT" value="貸出・返却" onclick='lenret()' /></th>
			</tr>
			<tr>
				<th colspan="2"><hr size="1px" color="#000000"></th>
			</tr>
			<%if(loginTopForm.getMasterFlg() == 1) {%>
			<tr>
				<th style="text-align: left">貸出履歴参照</th>
			</tr>
			<tr class="manu_border">
				<th>貸出状況を確認できます</th>
				<th><input class="BT" type="submit" name="lenhisBT" value="貸出履歴" onclick='lenhis()' /></th>
			</tr>
			<tr>
				<th colspan="2"><hr size="1px" color="#000000"></th>
			</tr>
			<%} %>
		</table>
		<input type="hidden" name="vmode" value="MENU"/>
	</div>
</form>
</body>
</html>