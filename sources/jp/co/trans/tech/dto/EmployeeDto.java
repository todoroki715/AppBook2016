package jp.co.trans.tech.dto;

public class EmployeeDto {
	private String accountId;
	private String accountName;
	private int lendFlg;
	private int masterFlg;


	public void setaccountId(String accountId){
		this.accountId = accountId;
	}
	public void setaccountName(String accountName){
		this.accountName = accountName;
	}
	public void setlendFlg(int Flg){
		this.lendFlg = Flg;
	}
	public void setmasterFlg(int Flg){
		this.masterFlg = Flg;
	}

	public String getaccountName(){
		return this.accountName;
	}
	public String getaccountId(){
		return this.accountId;
	}
	public int getlendFlg(){
		return this.lendFlg;
	}
	public int getmasterFlg(){
		return this.masterFlg;
	}

}
