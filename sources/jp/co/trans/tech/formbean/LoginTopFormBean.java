package jp.co.trans.tech.formbean;

/* @LoginTopFormBeanクラス
 * セッション中のデータを保持するためのクラス
 *
 */

public class LoginTopFormBean {
	//エラーメッセージ
	private String errorMsg = "";

	//アカウントID
	private String accountId = "";

	//アカウント名
	private String accountName = "";

	//管理者フラグ
	private int masterFlg = 0;


	//エラーメッセージをセットする
	public void setErrorMsg(String errorMsg){
		this.errorMsg = errorMsg;
	}

	//アカウント名をセットする
	public void setAccountName(String accountName){
		this.accountName = accountName;
	}

	//アカウントIDをセットする
	public void setAccountId(String accountId){
		this.accountId = accountId;
	}

	//管理者フラグをセットする
	public void setMasterFlg(int Flg){
		this.masterFlg = Flg;
	}

	//エラーメッセージを返す
	public String getErrorMsg(){
		return this.errorMsg;
	}

	//アカウント名を返す
	public String getAccountName(){
		return this.accountName;
	}

	//アカウントIDを返す
	public String getAccountId(){
		return this.accountId;
	}

	//管理者フラグを返す
	public int getMasterFlg(){
		return this.masterFlg;
	}
}
