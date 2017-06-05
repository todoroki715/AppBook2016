<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.LoginTopFormBean" scope = "session" id = "loginTopForm"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ログイントップ</title>
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
		ログインID/パスワードを入力してログインしてください。
		ログインID<input type="text" name="accountId" maxlength='6'/>
		パスワード<input type="password" name="pass" maxlength='20'/>
		<input type="hidden" name="vmode" value="LOGIN"/>
		<input type="submit" name="loginBT" value="ログイン" onclick='login()'/>
		<input type="button" name="clearBT" value="クリア" onclick='IdPassclear()'/>
		<input type="submit" name="changeBT" value="パスワード変更" onclick='change()' />
	</form>
</body>
</html>