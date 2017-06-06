<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.LoginTopFormBean" scope = "session" id = "loginTopForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>メニュー画面</title>
</head>
<body>
<input type="hidden" name="vmode" value="MENU"/>
<input class="lenretBT" type="submit" name="lenretBT" value="貸出・返却" onclick='' />
<input class="lenhisBT" type="submit" name="lenhisBT" value="貸出履歴" onclick='' />
</body>
</html>