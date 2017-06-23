<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.LoginTopFormBean" scope = "session" id = "loginTopForm"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/Login.css" type="text/css">
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
<jsp:include page="header.jsp" />
	<form name='form'>
		<div class="loginform">
			<h3>ログインID/パスワードを入力してログインしてください。</h3>
			<%if(loginTopForm.getErrorMsg().equals("") == false){ %>
			<font color="ff0000">*<%=loginTopForm.getErrorMsg() %></font>
			<%} %>
			<table class="input">
				<tr>
					<th>ログインID&nbsp;:&nbsp;</th>
					<th><input style="width:100px" type="text" name="accountId" maxlength='6'/></th>
				</tr>
				<tr>
					<th>パスワード&nbsp;:&nbsp;</th>
					<th><input style="width:150px" type="password" name="pass" maxlength='20'/></th>
				</tr>
			</table>
			<table class="BT">
				<tr>
					<th><input class="botton" type="submit" name="loginBT" value="ログイン" onclick='login()'/></th>
					<th><input class="botton" type="button" name="clearBT" value="クリア" onclick ='IdPassclear()'/></th>
				</tr>
			</table>
			<input type="hidden" name="vmode" value="LOGIN"/>
		</div>

		<div class="pass">
			<input class="changeBT" type="submit" name="changeBT" value="パスワード変更" onclick='change()'/>
		</div>
	</form>
<jsp:include page="footer.jsp" />
</body>
</html>