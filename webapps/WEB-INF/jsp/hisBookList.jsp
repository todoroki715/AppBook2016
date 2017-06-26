<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class = "jp.co.trans.tech.formbean.HisBookListFormBean" scope = "session" id = "hisBookForm"/>
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
<link rel="stylesheet" href="./css/lenBookList.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>図書貸出履歴画面</title>
</head>
<script type="text/javascript">
	function search(){
		document.form.action = "./hisBookList.do";
		document.form.method = "post";
	}
	function back(){
		document.form.action = "./menu.do";
		document.form.method = "post";
	}
</script>
<body>
<jsp:include page="header.jsp" />
<form name="form">
<input type="hidden" name="vmode" value="HISBOOK"/>
<div class="title"><h1><u>図書貸出履歴&nbsp;&nbsp;&nbsp;</u></h1></div>
<div class="inputform">
	<table class="formtable">
	<tr>
		<td>図書名：</td>
		<td><input style="width:320px;" maxlength='200' type="text" name="bookName"/></td>
	</tr>
	<tr>
		<td>氏名：</td>
		<td><input style="width:180px;" maxlength='30' type="text" name="accountName"/></td>
	</tr>
	</table>
	<input class="searchBT" type="submit" name="searchBT" value="検索" onclick='search()'/>

</div>
<%if(hisBookForm.getErrorMsg().equals("") == false){ %>
	<div class="error">
	<font color="ff0000">*<%=hisBookForm.getErrorMsg() %></font>
	</div>
<%} %>

<%if(hisBookForm.getHisBookList().size() != 0){ %>
<div class="list_div">
	<table class = "list_table" border=1>
	<tr class="column">
		<th class="bookId">図書ID</th>
		<th class="bookName">図書名</th>
		<th class="lendDate">貸出日</th>
		<th class="returnDate">返却日</th>
		<th class="lendAccountName">利用者名</th>
		<th class="returnAccountName">返却者名</th>
	</tr>
	<%! int i; %>
	<%for(i = 0; i < hisBookForm.getHisBookList().size(); i++){ %>
	<tr>
		<td><%=hisBookForm.getHisBookList().get(i).getBookId() %></td>
		<td><%=hisBookForm.getHisBookList().get(i).getBookName() %></td>
		<td><%=hisBookForm.getHisBookList().get(i).getLendDateDisp() %></td>
		<td><%=hisBookForm.getHisBookList().get(i).getReturnDateDisp() %></td>
		<td><%=hisBookForm.getHisBookList().get(i).getLendAccountName() %></td>
		<td><%=nullcheck(hisBookForm.getHisBookList().get(i).getReturnAccountName()) %></td>
	</tr>
	<%} %>
	</table>

</div>
<%} %>
<div class = "back"><input class="backBT" type="submit" name="backBT" value="戻る" onclick='back()' /></div>
</form>
<jsp:include page="footer.jsp" />
</body>
</html>