package jp.co.trans.tech.formbean;

/*@RetInputFormBeanクラス
 * 画面で使用する変数をセットするクラス
 */

public class RetInputFormBean {

	//エラーメッセージ
	private String errorMsg = "";

	//貸出ID
	private int lendId;

	//貸出をしたアカウントID
	private String lendAccountId = "";

	//貸出をしたアカウント名
	private String lendAccountName = "";

	//貸出日
	private String lendDate = "";

	//返却する人のアカウントID
	private String returnAccountId = "";

	//返却する人のアカウント名
	private String returnAccountName = "";

	//図書ID
	private String bookId = "";

	//図書名
	private String bookName = "";

	//返却予定日
	private String returnYDate = "";

	//返却日
	private String returnDate = "";

	//エラーメッセージをセットする
	public void seterrorMsg(String Msg){
		this.errorMsg=Msg;
	}

	//エラーメッセージを返す
	public String geterrorMsg(){
		return this.errorMsg;
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
		this.lendAccountId=Id;
	}

	//貸出をしたアカウントIDを返す
	public String getlendAccountId(){
		return this.lendAccountId;
	}

	//貸出をしたアカウント名をセットする
	public void setlendAccountName(String Name){
		this.lendAccountName=Name;
	}

	//貸出をしたアカウント名を返す
	public String getlendAccountName(){
		return this.lendAccountName;
	}

	//貸出日をセットする
	public void setlendDate(String date){
		this.lendDate=date;
	}

	//貸出日を返す
	public String getlendDate(){
		return this.lendDate;
	}

	//返却する人のアカウントIDをセットする
	public void setreturnAccountId(String Id){
		this.returnAccountId=Id;
	}

	//返却する人のアカウントIDを返す
	public String getreturnAccountId(){
		return this.returnAccountId;
	}

	//返却する人のアカウント名をセットする
	public void setreturnAccountName(String Name){
		this.returnAccountName=Name;
	}

	//返却する人のアカウント名を返す
	public String getreturnAccountName(){
		return this.returnAccountName;
	}

	//図書IDをセットする
	public void setbookId(String Id){
		this.bookId=Id;
	}

	//図書IDを返す
	public String getbookId(){
		return this.bookId;
	}

	//図書名をセットする
	public void setbookName(String Name){
		this.bookName=Name;
	}

	//図書名を返す
	public String getbookName(){
		return this.bookName;
	}

	//返却予定日をセットする
	public void setreturnYDate(String date){
		this.returnYDate=date;
	}

	//返却予定日を返す
	public String getreturnYDate(){
		return this.returnYDate;
	}

	//返却日をセットする
	public void setreturnDate(String Date){
		this.returnDate=Date;
	}

	//返却日を返す
	public String getreturnDate(){
		return this.returnDate;
	}
}
