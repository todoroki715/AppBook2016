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
	public void setErrorMsg(String Msg){
		this.errorMsg=Msg;
	}

	//エラーメッセージを返す
	public String getErrorMsg(){
		return this.errorMsg;
	}

	//貸出IDをセットする
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
	public void setLendDate(String date){
		this.lendDate=date;
	}

	//貸出日を返す
	public String getLendDate(){
		return this.lendDate;
	}

	//返却する人のアカウントIDをセットする
	public void setReturnAccountId(String Id){
		this.returnAccountId=Id;
	}

	//返却する人のアカウントIDを返す
	public String getReturnAccountId(){
		return this.returnAccountId;
	}

	//返却する人のアカウント名をセットする
	public void setReturnAccountName(String Name){
		this.returnAccountName=Name;
	}

	//返却する人のアカウント名を返す
	public String getReturnAccountName(){
		return this.returnAccountName;
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
	public void setReturnYDate(String date){
		this.returnYDate=date;
	}

	//返却予定日を返す
	public String getReturnYDate(){
		return this.returnYDate;
	}

	//返却日をセットする
	public void setReturnDate(String Date){
		this.returnDate=Date;
	}

	//返却日を返す
	public String getReturnDate(){
		return this.returnDate;
	}
}
