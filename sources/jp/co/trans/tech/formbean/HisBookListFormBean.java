package jp.co.trans.tech.formbean;

import java.util.List;

import jp.co.trans.tech.dto.HisBookDto;

public class HisBookListFormBean {

	//エラーメッセージ
	private String errorMsg = "";

	//図書名
	private String bookName = "";

	//アカウント名
	private String accountName = "";

	//図書履歴リスト
	private List<HisBookDto> hisBookList;

	//エラーメッセージ所得
	public String getErrorMsg() {
		return errorMsg;
	}

	//エラーメッセージセット
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	//図書名所得
	public String getBookName() {
		return bookName;
	}

	//図書名セット
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	//アカウント名所得
	public String getAccountName() {
		return accountName;
	}

	//アカウント名セット
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	//図書履歴リスト所得
	public List<HisBookDto> getHisBookList() {
		return hisBookList;
	}

	//図書履歴リストセット
	public void setHisBookList(List<HisBookDto> hisBookList) {
		this.hisBookList = hisBookList;
	}
}
