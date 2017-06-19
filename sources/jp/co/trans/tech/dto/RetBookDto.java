package jp.co.trans.tech.dto;

import java.util.Date;

import jp.co.trans.tech.utilities.Utilities;

/*@RetBookDtoクラス
 * データベースの情報を保持する
 */
public class RetBookDto {

	//貸出ID
	private int lendId;

	//貸出をしたアカウントID
	private String lendAccountId = "";

	//貸出をしたアカウント名
	private String lendAccountName = "";

	//貸出した日付
	private Date lendDate;

	//図書ID
	private String bookId = "";

	//図書名
	private String bookName = "";

	//返却予定日
	private Date returnYDate;

	//貸出日の文字列
	private String lendDateDisp;

	//返却予定日の文字列
	private String returnYDateDisp;


	//貸出IDをセット
	public void setLendId(int Id){
		this.lendId = Id;
	}

	//貸出IDを返す
	public int getLendId(){
		return this.lendId;
	}

	//貸出をしたアカウントIDをセットする
	public void setLendAccountId(String Id){
		this.lendAccountId=Id;
	}

	//貸出をしたアカウントIDを返す
	public String getLendAccountId(){
		return this.lendAccountId;
	}

	//貸出をしたアカウント名をセットする
	public void setLendAccountName(String Name){
		this.lendAccountName=Name;
	}

	//貸出をしたアカウント名を返す
	public String getLendAccountName(){
		return this.lendAccountName;
	}

	//貸出日をセットする
	public void setLendDate(Date date){
		this.lendDate=date;
	}

	//貸出日を返す
	public Date getLendDate(){
		return this.lendDate;
	}

	//図書IDをセットする
	public void setBookId(String Id){
		this.bookId=Id;
	}

	//図書IDを返す
	public String getBookId(){
		return this.bookId;
	}

	//図書名をセットする
	public void setBookName(String Name){
		this.bookName=Name;
	}

	//図書名を返す
	public String getBookName(){
		return this.bookName;
	}

	//返却予定日をセットする
	public void setReturnYDate(Date date){
		this.returnYDate=date;
	}

	//返却予定日を返す
	public Date getReturnYDate(){
		return this.returnYDate;
	}

	//貸出日を文字列で返す
	public String getLendDateDisp(){
		this.lendDateDisp = Utilities.getDateStr(this.lendDate, "yyyy/MM/dd");
		return this.lendDateDisp;
	}

	//返却予定日を文字列で返す
	public String getReturnYDateDisp(){
		this.returnYDateDisp = Utilities.getDateStr(this.returnYDate, "yyyy/MM/dd");
		return this.returnYDateDisp;
	}

}
