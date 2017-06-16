<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.LenBookListFormBean" scope = "session" id = "lenBookForm"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!
	public String nullcheck(String str){
	if(str == null){
		return "";
	}
	return str;
	}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/lenBookList.css" type="text/css">
<title>図書貸出・返却</title>
</head>
<script type="text/javascript">
function search(){
	document.form.action="./lenBookList.do";
	document.form.method="post";
}
function back(){
	document.form.action="./menu.do";
	document.form.method="post";
}

function len(){
	document.form.action="./lenInputBook.do";
	document.form.method="post";
}
function ret(){
	document.form.action="./retInputBook.do";
	document.form.method="post";
}
</script>
<body>
<form name="form" id="form">
<input type="hidden" name="vmode" value="LENBOOK"/>
	<div class="title"><h1><u>図書貸出・返却&nbsp;&nbsp;&nbsp;</u></h1></div>
	<div class="inputform">
		<table class="formtable">
			<tr>
			<td>図書名:</td>
			<td><input style="width:500px" type="text" name="bookName" maxlength='200'/></td>
			</tr>
			<tr>
			<td>利用者:</td>
			<td><input style="width:300px" type="text" name="accountName" maxlength='30'/></td>
			</tr>
		</table>
		<input class="searchBT" type="submit" name="searchBT" value="検索" onclick='search()'/>
	</div>

	<div class="error"><font color="ff0000"><jsp:getProperty property="errorMsg" name="lenBookForm"/></font></div>

	<% if(lenBookForm.getList() != null && lenBookForm.getList().size() != 0){ %>

	<div class="list_div">
		<table class = "list_table" border=1>
		<tr class="column">
			<th style="width:70px;">図書ID</th>
			<th style="width:720px;">図書名</th>
			<th style="width:80px;">利用者ID</th>
			<th style="width:210px;">利用者名</th>
			<th style="width:90px;">貸出日</th>
			<th style="width:100px;">返却予定日</th>
			<th style="width:90px;">貸出・返却</th>
		</tr>
		<%! int i; %>
		<%for(i = 0; i < lenBookForm.getList().size(); i++){ %>
		<tr>
			<td><%= lenBookForm.getList().get(i).getbookId() %></td>
			<td><%= lenBookForm.getList().get(i).getbookName() %></td>
			<td><%= nullcheck(lenBookForm.getList().get(i).getlendAccountId()) %></td>
			<td><%= nullcheck(lenBookForm.getList().get(i).getaccountName()) %></td>
			<td><%= nullcheck(lenBookForm.getList().get(i).getlendDateDisp()) %></td>
			<td><%= nullcheck(lenBookForm.getList().get(i).getreturnYDateDisp()) %></td>
			<%if(lenBookForm.getList().get(i).getaccountName() == null
			|| lenBookForm.getList().get(i).getaccountName().equals("") == true){ %>
				<td><button type="submit" class="listBT" name="bookIdL" value="<%= lenBookForm.getList().get(i).getbookId() %>" onclick='len()'>貸出</button></td>
			<%} else{ %>
				<td><button type="submit" class="listBT" name="bookIdR" value="<%= lenBookForm.getList().get(i).getlendId() %>" onclick='ret()'>返却</button></td>
			<%} %>
		</tr>
		<%} %>
		</table>
	<%} %>
	</div>

	<input class="backBT" type="submit" name="backBT" value="戻る" onclick='back()'/>
</form>
</body>
</html>