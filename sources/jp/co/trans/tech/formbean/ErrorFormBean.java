package jp.co.trans.tech.formbean;


/* @ErrorFormBeanクラス
 * エラーメッセージを保持し、エラー画面で出すためのクラス
 */
public class ErrorFormBean {

	//メッセージ
	private String message = "";

	//エラーメッセージをセットする
	public void setErrorMsg(String message){
		this.message = message;
	}

	//セットされたエラーメッセージを返す
	public String getErrorMsg(){
		return this.message;
	}

}
