<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.ChangePassFormBean" scope = "session" id = "ChangePassForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		ログインID<input type="text" name="accountId" maxlength='6'/>
		パスワード<input type="password" name="pass" maxlength='20'/>
		新しいパスワード<input type="password" name="changePass" maxlength='20'/>
		確認パスワード<input type="password" name="confPass" maxlength='20'/>

		<input type="hidden" name="vmode" value="PASS"/>

		パスワード変更<input type="submit" name="changeBT" value="パスワード変更" onclick='change()' />
		戻る<input type="submit" name="backBT" value="戻る" onclick='back()' />
	</form>
</body>
</html>