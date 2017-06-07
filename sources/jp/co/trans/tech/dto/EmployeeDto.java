package jp.co.trans.tech.dto;


/* @LoginTopFormBeanクラス
 * セッション中のデータを保持するためのクラス
 *
 */

public class EmployeeDto {
	//アカウントID
	private String accountId;

	//アカウント名
	private String accountName;

	//貸出フラグ
	private int lendFlg;

	//管理者フラグ
	private int masterFlg;


	//アカウントIDをセットする
	public void setaccountId(String accountId){
		this.accountId = accountId;
	}

	//アカウント名をセットする
	public void setaccountName(String accountName){
		this.accountName = accountName;
	}

	//貸出フラグをセットする
	public void setlendFlg(int Flg){
		this.lendFlg = Flg;
	}

	//管理者フラグをセットする
	public void setmasterFlg(int Flg){
		this.masterFlg = Flg;
	}

	//アカウント名を返す
	public String getaccountName(){
		return this.accountName;
	}

	//アカウントIDを返す
	public String getaccountId(){
		return this.accountId;
	}

	//貸出フラグを返す
	public int getlendFlg(){
		return this.lendFlg;
	}

	//管理者フラグを返す
	public int getmasterFlg(){
		return this.masterFlg;
	}

}
