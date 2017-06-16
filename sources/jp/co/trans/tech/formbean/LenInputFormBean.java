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
	public void seterrorMsg(String Msg){
		this.errorMsg=Msg;
	}

	//エラーメッセージを返す
	public String geterrorMsg(){
		return this.errorMsg;
	}

	//アカウントIDセット
	public void setaccountId(String Id){
		this.accountId=Id;
	}

	//アカウントIDを返す
	public String getaccountId(){
		return this.accountId;
	}

	//アカウント名をセットする
	public void setaccountName(String Name){
		this.accountName=Name;
	}

	//アカウント名を返す
	public String getaccountName(){
		return this.accountName;
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

	//URLをセットする
	public void setintro(String intro){
		this.intro=intro;
	}

	//URLを返す
	public String getintro(){
		return this.intro;
	}

	//返却予定日をセットする
	public void setreturnYDate(String Date){
		this.returnYDate=Date;
	}

	//返却予定を所得する
	public String getreturnYDate(){
		return this.returnYDate;
	}
}
