package jp.co.trans.tech.formbean;

public class ChangePassFormBean {

	//エラーメッセージ
	private String errorMsg = "";

	//アカウントID
	private String accountId = "";

	//エラーメッセージをセットする
	public void seterrorMsg(String Msg){
		this.errorMsg = Msg;
	}

	//セットされたエラーメッセージを返す
	public String geterrorMsg(){
		return this.errorMsg;
	}

	//アカウントIDをセットする
	public void setaccountId(String Id){
		this.accountId = Id;
	}

	//セットされたアカウントIDを返す
	public String getaccountId(){
		return this.accountId;
	}


}
