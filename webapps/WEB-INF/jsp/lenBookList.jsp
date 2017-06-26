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
<jsp:include page="header.jsp" />
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
<%if(lenBookForm.getErrorMsg().equals("") == false){ %>
	<div class="error"><font color="ff0000">*<%=lenBookForm.getErrorMsg() %></font></div>
<%} %>
	<% if(lenBookForm.getList() != null && lenBookForm.getList().size() != 0){ %>

	<div class="list_div">
		<table class = "list_table" border=1>
		<tr class="column">
			<th class="bookId">図書ID</th>
			<th class="bookName">図書名</th>
			<th class="lendAccountId">利用者ID</th>
			<th class="lendAccountName">利用者名</th>
			<th class="lendDate">貸出日</th>
			<th class="returnYDate">返却予定日</th>
			<th class="lenret">貸出・返却</th>
		</tr>
		<%! int i; %>
		<%for(i = 0; i < lenBookForm.getList().size(); i++){ %>
		<tr>
			<td><%= lenBookForm.getList().get(i).getBookId() %></td>
			<td><%= lenBookForm.getList().get(i).getBookName() %></td>
			<td><%= nullcheck(lenBookForm.getList().get(i).getLendAccountId()) %></td>
			<td><%= nullcheck(lenBookForm.getList().get(i).getAccountName()) %></td>
			<td><%= nullcheck(lenBookForm.getList().get(i).getLendDateDisp()) %></td>
			<td><%= nullcheck(lenBookForm.getList().get(i).getReturnYDateDisp()) %></td>
			<%if(lenBookForm.getList().get(i).getAccountName() == null || lenBookForm.getList().get(i).getAccountName().equals("") == true){ %>
				<td><button type="submit" class="listBT" name="bookIdL" value="<%= lenBookForm.getList().get(i).getBookId() %>" onclick='len()'>貸出</button></td>
			<%} else{ %>
				<td><button type="submit" class="listBT" name="bookIdR" value="<%= lenBookForm.getList().get(i).getLendId() %>" onclick='ret()'>返却</button></td>
			<%} %>
		</tr>
		<%} %>
		</table>
	<%} %>
	</div>

	<input class="backBT" type="submit" name="backBT" value="戻る" onclick='back()'/>
</form>
<jsp:include page="footer.jsp" />
</body>
</html>