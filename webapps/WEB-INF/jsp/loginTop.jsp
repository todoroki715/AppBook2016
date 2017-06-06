<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.LoginTopFormBean" scope = "session" id = "loginTopForm"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイントップ</title>
<style>
div.loginform{
	background:#ffffff;
	width: 500px;
	padding: 50px;
	text-align: center;
	border: 1px solid #000000;
	margin:30px auto;
}
div.pass{
	width: 500px;
	text-align: right;
	margin:10px auto;
}
table.input{
	text-align: left;
	width: 600px;
	margin-bottom:10px;
}
table.BT{
	margin-left:400px;
}
</style>

</head>

<script type="text/javascript">

	function IdPassclear(){
		document.form.accountId.value="";
		document.form.pass.value="";

	}

	function login(){
		document.form.action = "./menu.do";
		document.form.method = "post";

	}

	function change(){
		document.form.action = "./changepass.do";
		document.form.method = "post";
	}
</script>

<body>
	<form name='form'>
		<div align="center" class="loginform">
			<h3>ログインID/パスワードを入力してログインしてください。</h3>
			<table class="input">
				<tr>
					<th>ログインID:</th>
					<th><input type="text" name="accountId" maxlength='6'/></th>
				</tr>
				<tr>
					<th>パスワード:</th>
					<th><input type="password" name="pass" maxlength='20'/></th>
				</tr>
			</table>
			<table class="BT">
				<tr>
					<th><input type="submit" name="loginBT" value="ログイン" onclick='login()'/></th>
					<th><input type="button" name="clearBT" value="クリア" onclick ='IdPassclear()'/></th>
				</tr>
			</table>
			<input type="hidden" name="vmode" value="LOGIN"/>
		</div>

		<div align="center" class="pass">
			<input type="submit" name="changeBT" value="パスワード変更" onclick='change()'/>
		</div>
	</form>
</body>
</html>