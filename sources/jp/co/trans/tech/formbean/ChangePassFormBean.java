package jp.co.trans.tech.formbean;

public class ChangePassFormBean {

	//エラーメッセージ
	private String errorMsg = "";

	//アカウントID
	private String accountId = "";

	//エラーメッセージをセットする
	public void setErrorMsg(String Msg){
		this.errorMsg = Msg;
	}

	//セットされたエラーメッセージを返す
	public String getErrorMsg(){
		return this.errorMsg;
	}

	//アカウントIDをセットする
	public void setAccountId(String Id){
		this.accountId = Id;
	}

	//セットされたアカウントIDを返す
	public String getAccountId(){
		return this.accountId;
	}


}
