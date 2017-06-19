package jp.co.trans.tech.formbean;

/*@LenInputFormBeanクラス
 * 画面で使用する変数をセットするクラス
 */

public class LenInputFormBean {
	//エラーメッセージ
	private String errorMsg = "";

	//アカウントID
	private String accountId = "";

	//アカウント名
	private String accountName = "";

	//図書ID
	private String bookId = "";

	//図書名
	private String bookName = "";

	//URL
	private String intro = "";

	//返却予定日
	private String returnYDate = "";


	//エラーメッセージセット
	public void setErrorMsg(String Msg){
		this.errorMsg=Msg;
	}

	//エラーメッセージを返す
	public String getErrorMsg(){
		return this.errorMsg;
	}

	//アカウントIDセット
	public void setAccountId(String Id){
		this.accountId=Id;
	}

	//アカウントIDを返す
	public String getAccountId(){
		return this.accountId;
	}

	//アカウント名をセットする
	public void setAccountName(String Name){
		this.accountName=Name;
	}

	//アカウント名を返す
	public String getAccountName(){
		return this.accountName;
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

	//URLをセットする
	public void setIntro(String intro){
		this.intro=intro;
	}

	//URLを返す
	public String getIntro(){
		return this.intro;
	}

	//返却予定日をセットする
	public void setReturnYDate(String Date){
		this.returnYDate=Date;
	}

	//返却予定を所得する
	public String getReturnYDate(){
		return this.returnYDate;
	}
}
