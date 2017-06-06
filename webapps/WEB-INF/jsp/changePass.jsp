<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.ChangePassFormBean" scope = "session" id = "ChangePassForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>パスワード変更画面</title>
<style>
div.changeform{
	background:#ffffff;
	width: 500px;
	padding: 50px;
	text-align: center;
	border: 1px solid #000000;
	margin:30px auto;
}
table.pass{
	text-align: left;
	width: 600px;
	margin-bottom:10px;
}
input.changeBT{
	margin-left:400px;
}
input.backBT{
	margin-left:10px;
}
</style>
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
		<div class="changeform">
			<h2>パスワード変更</h2>
			<table class="pass">
			<tr>
				<th>ログインID</th>
				<th><input type="text" name="accountId" maxlength='6'/></th>
			</tr>
			<tr>
				<th>パスワード</th>
				<th><input type="password" name="pass" maxlength='20'/></th>
			</tr>
			<tr>
				<th>新しいパスワード</th>
				<th><input type="password" name="changePass" maxlength='20'/></th>
			</tr>
			<tr>
				<th>確認パスワード</th>
				<th><input type="password" name="confPass" maxlength='20'/></th>
			</tr>
			</table>
			<input type="hidden" name="vmode" value="PASS"/>
			<input class="changeBT" type="submit" name="changeBT" value="パスワード変更" onclick='change()' />
		</div>
		<input class="backBT" type="submit" name="backBT" value="戻る" onclick='back()' />
	</form>
</body>
</html>