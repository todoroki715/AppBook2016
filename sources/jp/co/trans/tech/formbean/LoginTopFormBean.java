package jp.co.trans.tech.formbean;

public class LoginTopFormBean {
	private String errorMsg = "";
	private String accountId = "";
	private String accountName = "";
	private int masterFlg = 0;

	public void seterrorMsg(String errorMsg){
		this.errorMsg = errorMsg;
	}
	public void setaccountName(String accountName){
		this.accountName = accountName;
	}
	public void setaccountId(String accountId){
		this.accountId = accountId;
	}
	public void setmasterFlg(int Flg){
		this.masterFlg = Flg;
	}

	public String geterrorMsg(){
		return this.errorMsg;
	}
	public String getaccountName(){
		return this.accountName;
	}
	public String getaccountId(){
		return this.accountId;
	}
	public int getmasterFlg(){
		return this.masterFlg;
	}
}
