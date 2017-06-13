<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.LenBookListFormBean" scope = "session" id = "lenBookForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>図書貸出・返却</title>
</head>
<body>
<input style="width:150px" type="text" name="bookName" maxlength='200'/>
<input style="width:150px" type="text" name="accountName" maxlength='30'/>
<input class="botton" type="submit" name="searchBT" value="検索" onclick='search()'/>
<input type="hidden" name="vmode" value="LENBOOK"/>
<%if(lenBookForm.getList().size() != 0){ %>
	<table>
	<tr>
		<th>図書ID</th>
		<th>図書名</th>
		<th>利用者ID</th>
		<th>利用者名</th>
		<th>貸出日</th>
		<th>返却予定日</th>
		<th>貸出・返却</th>
	</tr>
	<%for(int i = 0; i < lenBookForm.getList().size(); i++){ %>
	<tr>
		<th><jsp:getProperty property="lenBookForm.getList().get(<%= i %>).getbookId()" name="lenBookForm"/></th>
		<th><jsp:getProperty property="lenBookForm.getList().get(<%= i %>).getbookName()" name="lenBookForm"/></th>
		<th><jsp:getProperty property="lenBookForm.getList().get(<%= i %>).getlendaccountId()" name="lenBookForm"/></th>
		<th><jsp:getProperty property="lenBookForm.getList().get(<%= i %>).getaccountName()" name="lenBookForm"/></th>
		<th><jsp:getProperty property="lenBookForm.getList().get(<%= i %>).getlendDateDisp()" name="lenBookForm"/></th>
		<th><jsp:getProperty property="lenBookForm.getList().get(<%= i %>).getreturnYDateDisp()" name="lenBookForm"/></th>
		<%if(lenBookForm.getList().get(i).getaccountName() == ""){ %>
			<th><input type="button" name="bookIdL" value="lenBookForm.getList().get(<%= i %>).getbookId()" onclick='len()'/></th>
		<%} else{ %>
			<th><input type="button" name="bookIdR" value="lenBookForm.getList().get(<%= i %>).lendId()" onclick='ret()'/></th>
		<%} %>
	</tr>
	<%} %>
	</table>
<%} %>
<input class="botton" type="submit" name="backBT" value="戻る" onclick='back()'/>
</body>
</html>