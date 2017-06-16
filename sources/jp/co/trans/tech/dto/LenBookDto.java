package jp.co.trans.tech.dto;

import java.util.Date;

import jp.co.trans.tech.utilities.Utilities;


/*@LenBookDtoクラス
 * データベースの情報を保持する
 */
public class LenBookDto {

	//図書ID
	private String bookId;

	//図書名
	private String bookName;

	//貸出ID
	private int lendId;

	//貸出をしたアカウントID
	private String lendAccountId;

	//アカウント名
	private String accountName;

	//貸出日
	private Date lendDate;

	//返却予定日
	private Date returnYDate;

	//更新日
	private Date updateDate;

	//貸出日表記用変数
	private String lendDateDisp;

	//返却予定日表記用変数
	private String returnYDateDisp;


	//図書IDをセットする
	public void setbookId(String Id){
		this.bookId = Id;
	}

	//図書IDを返す
	public String getbookId(){
		return this.bookId;
	}

	//図書名をセットする
	public void setbookName(String Name){
		this.bookName = Name;
	}

	//図書名を返す
	public String getbookName(){
		return this.bookName;
	}

	//貸出IDをセットする
	public void setlendId(int Id){
		this.lendId = Id;
	}

	//貸出IDを返す
	public int getlendId(){
		return this.lendId;
	}

	//貸出をしたアカウントIDをセットする
	public void setlendAccountId(String Id){
		this.lendAccountId = Id;
	}

	//貸出をしたアカウントIDを返す
	public String getlendAccountId(){
		return this.lendAccountId;
	}

	//アカウント名をセットする
	public void setaccountName(String Name){
		this.accountName = Name;
	}

	//アカウント名を返す
	public String getaccountName(){
		return this.accountName;
	}

	//貸出日をセットする
	public void setlendDate(Date date){
		this.lendDate = date;
	}

	//貸出日を返す
	public Date getlendDate(){
		return this.lendDate;
	}

	//返却予定日をセットする
	public void setreturnYDate(Date date){
		this.returnYDate = date;
	}

	//返却予定日を返す
	public Date getreturnYDate(){
		return this.returnYDate;
	}

	//更新日をセットする
	public void setupdateDate(Date date){
		this.updateDate = date;
	}

	//更新日を返す
	public Date getupdateDate(){
		return this.updateDate;
	}

	//貸出日を文字列にして返す
	public String getlendDateDisp(){
		this.lendDateDisp = Utilities.getDateStr(this.lendDate, "yyyy/MM/dd");
		return this.lendDateDisp;
	}

	//返却予定日を文字列にして返す
	public String getreturnYDateDisp(){
		this.returnYDateDisp = Utilities.getDateStr(this.returnYDate, "yyyy/MM/dd");
		return this.returnYDateDisp;
	}
}
