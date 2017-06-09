<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean class="jp.co.trans.tech.formbean.ErrorFormBean" scope="session" id="errorForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>エラー</title>
</head>
<body>
<h1>処理中に、エラーが発生しました。</h1>
<h1>システム管理者に連絡してください</h1>
${errorForm.getErrorMsg() };

</body>
</html>