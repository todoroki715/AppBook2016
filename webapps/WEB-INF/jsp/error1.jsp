<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="jp.co.trans.tech.formbean.ErrorFormBean" scope="session" id="errorForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./css/error.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>エラー</title>
</head>
<script type="text/javascript">
	function back(){
		document.form.action = "./login.do";
		document.form.method = "get";
	}

</script>
<body>
<jsp:include page="header.jsp" />
<div class="syserror">
<h1>処理中に、エラーが発生しました。</h1>
<h1>システム管理者に連絡してください</h1>
<h2>${errorForm.getErrorMsg() };</h2>
</div>
<form name="form">
<input class="backBT" type="submit" name="backBT" value="戻る" onclick='back()' />
</form>
<jsp:include page="footer.jsp" />
</body>
</html>