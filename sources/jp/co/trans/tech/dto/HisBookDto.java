package jp.co.trans.tech.dto;

import java.util.Date;

import jp.co.trans.tech.utilities.Utilities;

public class HisBookDto {
	//図書ID
	private String bookId;

	//図書名
	private String bookName;

	//貸出ID
	private int lendId;

	//貸出アカウントID
	private String lendAccountId;

	//貸出アカウント名
	private String lendAccountName;

	//返却アカウントID
	private String returnAccountId;

	//返却アカウント名
	private String returnAccountName;

	//貸出日
	private Date lendDate;

	//返却日
	private Date returnDate;

	//貸出日(文字列型)
	private String lendDateDisp;

	//返却日(文字列型)
	private String returnDateDisp;

	//図書ID返却
	public String getBookId() {
		return bookId;
	}

	//図書IDセット
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	//図書名所得
	public String getBookName() {
		return bookName;
	}

	//図書名セット
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	//貸出ID所得
	public int getLendId() {
		return lendId;
	}

	//貸出IDセット
	public void setLendId(int lendId) {
		this.lendId = lendId;
	}

	//貸出アカウントID所得
	public String getLendAccountId() {
		return lendAccountId;
	}

	//貸出アカウントIDセット
	public void setLendAccountId(String lendAccountId) {
		this.lendAccountId = lendAccountId;
	}

	//貸出アカウント名所得
	public String getLendAccountName() {
		return lendAccountName;
	}

	//貸出アカウント名セット
	public void setLendAccountName(String lendAccountName) {
		this.lendAccountName = lendAccountName;
	}

	//返却アカウントID所得
	public String getReturnAccountId() {
		return returnAccountId;
	}

	//返却アカウントIDセット
	public void setReturnAccountId(String returnAccountId) {
		this.returnAccountId = returnAccountId;
	}

	//返却アカウント名所得
	public String getReturnAccountName() {
		return returnAccountName;
	}

	//返却アカウント名セット
	public void setReturnAccountName(String returnAccountName) {
		this.returnAccountName = returnAccountName;
	}

	//貸出日所得
	public Date getLendDate() {
		return lendDate;
	}

	//貸出日セット
	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}

	//返却日所得
	public Date getReturnDate() {
		return returnDate;
	}

	//返却日セット
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	//貸出日所得(文字列)
	public String getLendDateDisp() {
		this.lendDateDisp = Utilities.getDateStr(this.lendDate, "yyyy/MM/dd");
		return lendDateDisp;
	}

	//返却日所得(文字列)
	public String getReturnDateDisp() {
		this.returnDateDisp = Utilities.getDateStr(this.returnDate, "yyyy/MM/dd");
		return returnDateDisp;
	}


}
