package jp.co.trans.tech.formbean;

import java.util.List;

import jp.co.trans.tech.dto.LenBookDto;

public class LenBookListFormBean {

	private String errorMsg = "";
	private String bookName = "";
	private String accountName = "";
	private List<LenBookDto> lenBookList;

	//エラーメッセージをセットする
	public void seterrorMsg(String errorMsg){
		this.errorMsg = errorMsg;
	}

	public String geterrorMsg(){
		return this.errorMsg;
	}

	public void setbookName(String Name){
		this.bookName = Name;
	}

	public String getbookName(){
		return this.bookName;
	}
	public void setaccountName(String Name){
		this.accountName = Name;
	}

	public String getaccountName(){
		return this.accountName;
	}
	public void setList(List<LenBookDto> list){
		this.lenBookList = list;
	}

	public List<LenBookDto> getList(){
		return this.lenBookList;
	}

}
