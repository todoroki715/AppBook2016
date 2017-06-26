<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./css/regComp.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>完了画面</title>
</head>
<script type="text/javascript">
function back(){
	document.form.action = "./lenBookList.do";
	document.form.method = "post";
}

</script>
<body>
<jsp:include page="header.jsp" />
<form name="form">
<div class="title"><h1><label>処理が正常に完了しました。</label></h1></div>

<input class="backBT" type="submit" name="lenretBT" value="図書貸出・返却画面に戻る" onclick='back()' />
<input type="hidden" name="vmode" value="COMP"/>
</form>
<jsp:include page="footer.jsp" />
</body>
</html>