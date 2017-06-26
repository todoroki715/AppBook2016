<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/changePassComp.css" type="text/css">
<title>パスワード変更完了</title>
</head>

<script type="text/javascript">
	function logintop(){
		document.form.action = "./login.do";
		document.form.method = "post";
	}
</script>

<body>
<jsp:include page="header.jsp" />
<form class="form">
	<h1 class="message1">パスワードの変更が正常に完了しました。</h1>
	<h2 class="message2">ログイントップ画面より新しいパスワードでログインできます。</h2>
	<input class="BT" type="submit" name="loginBT" value="ログイントップに戻る" onclick='logintop()'/>
</form>
<jsp:include page="footer.jsp" />
</body>
</html>