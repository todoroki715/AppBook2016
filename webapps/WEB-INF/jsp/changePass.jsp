<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.ChangePassFormBean" scope = "session" id = "changePassForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/changePass.css" type="text/css">
<title>パスワード変更画面</title>
</head>
<script type="text/javascript">
	function change(){
		document.form.action = "./changepasscomp.do";
		document.form.method = "post";
	}
	function back(){
		document.form.action = "./login.do";
		document.form.method = "post";
	}

</script>
<body>
	<form name="form">
	<div class="title"><h1><u>パスワード変更&nbsp;&nbsp;&nbsp;</u></h1></div>

		<div class="changeform">

			<div class="error"><font color="ff0000"><jsp:getProperty property="errorMsg" name="changePassForm"/></font></div>

			<table class="pass">
			<tr>
				<th>ログインID：</th>
				<th><input style="width:100px" type="text" name="accountId" maxlength='6'/></th>
			</tr>
			<tr>
				<th>パスワード：</th>
				<th><input style="width:150px" type="password" name="pass" maxlength='20'/></th>
			</tr>
			<tr>
				<th>新しいパスワード：</th>
				<th><input type="password" name="changePass" maxlength='20'/></th>
			</tr>
			<tr>
				<th>確認パスワード：</th>
				<th><input type="password" name="comfPass" maxlength='20'/></th>
			</tr>
			</table>
			<input type="hidden" name="vmode" value="PASS"/>
			<input class="changeBT" type="submit" name="changeBT" value="変更" onclick='change()' />
		</div>
		<input class="backBT" type="submit" name="backBT" value="戻る" onclick='back()' />
	</form>
</body>
</html>