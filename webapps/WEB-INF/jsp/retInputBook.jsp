<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.RetInputFormBean" scope = "session" id = "retInputForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./css/retInputBook.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>図書返却画面</title>
</head>
<script type="text/javascript">
function ret(){
	document.form.action="./retUpdBook.do";
	document.form.method="post";
}

function back(){
	document.form.action="./lenBookList.do";
	document.form.method="post";
}
</script>
<body>
<div class="title"><h1><u>図書返却</u></h1></div>
<form name="form">
	<div class="error"><font color="ff0000"><jsp:getProperty property="errorMsg" name="retInputForm"/></font></div>
	<table class="formtable" border=1>
		<tr>
		<td style="width:180px;">利用者氏名(社員番号)</td>
		<td style="width:670px;"><%=retInputForm.getLendAccountName() %>(<%=retInputForm.getLendAccountId() %>)</td>
		</tr>
		<tr>
		<td>返却者氏名(社員番号)</td>
		<td><%=retInputForm.getReturnAccountName() %>(<%=retInputForm.getReturnAccountId() %>)</td>
		</tr>
		<tr>
		<td>図書名</td>
		<td><%=retInputForm.getBookName() %></td>
		</tr>
		<tr>
		<td>貸出日</td>
		<td><%=retInputForm.getLendDate() %></td>
		</tr>
		<tr>
		<td>返却予定日</td>
		<td><%=retInputForm.getReturnYDate() %></td>
		</tr>
		<tr>
		<td>返却日</td>
		<td><input style="width:100px" type="text" name="returnDate" value="<%=retInputForm.getReturnDate()%>" disabled="disabled" />
		<font color="#ff0000" size=1>*本日の日付が自動で返却日になります</font>
		</td>
		</tr>
	</table>
	<input type="hidden" name="vmode" value="RETINPUT"/>
<div class="ret"><input class="retBT" type="submit" name="retBT" value="返却" onclick='ret()' /></div>
<div class="back"><input class="backBT" type="submit" name="backBT" value="戻る" onclick='back()' /></div>
</form>
</body>
</html>