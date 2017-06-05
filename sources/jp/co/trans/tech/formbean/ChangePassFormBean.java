package jp.co.trans.tech.formbean;

public class ChangePassFormBean {
	private String errorMsg = "";
	private String accountId = "";

	public void seterrorMsg(String Msg){
		this.errorMsg = Msg;
	}

	public String geterrorMsg(){
		return this.errorMsg;
	}

	public void setaccountId(String Id){
		this.accountId = Id;
	}

	public String getaccountId(){
		return this.accountId;
	}


}
