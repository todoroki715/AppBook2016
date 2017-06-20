<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.LenInputFormBean" scope = "session" id = "lenInputForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="./css/lenInputBook.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>図書貸出画面</title>
</head>
<script type="text/javascript">
function len(){
	document.form.action = "./lenRegBook.do";
	document.form.method = "post";
}

function back(){
	document.form.action = "./lenBookList.do";
	document.form.method = "post";
}

</script>
<body>
<div class="title"><h1><u>図書貸出</u></h1></div>
<form name="form">
	<div class="error"><font color="ff0000"><jsp:getProperty property="errorMsg" name="lenInputForm"/></font></div>
	<table class="formtable" border=1>
		<tr>
		<td style="width:180px;">氏名(社員番号)</td>
		<td style="width:670px;"><%=lenInputForm.getAccountName() %>(<%=lenInputForm.getAccountId() %>)</td>
		</tr>
		<tr>
		<td>図書名</td>
		<td><%=lenInputForm.getBookName() %></td>
		</tr>
		<tr>
		<td>返却予定日</td>
		<td><input style="width:100px" type="text" name="returnYDate" maxlength='10' value="<%= lenInputForm.getReturnYDate()%>"/>
		<font color="ff0000" size=1>*今日以降の日付を入力してください。</font><br>
		<font size=1>YYYY/MM/DDの形式で入力してください。</font>
		</td>
		</tr>
	</table>
	<input type="hidden" name="vmode" value="LENINPUT"/>
<div class = "len"><input class="lenBT" type="submit" name="lenBT" value="貸出" onclick='len()' /></div>
<div class = "back"><input class="backBT" type="submit" name="backBT" value="戻る" onclick='back()' /></div>
</form>
</body>
</html>