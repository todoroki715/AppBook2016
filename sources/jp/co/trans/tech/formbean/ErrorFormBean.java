package jp.co.trans.tech.formbean;

public class ErrorFormBean {
	private String message = "";

	public void setErrorMsg(String message){
		this.message = message;
	}

	public String getErrorMsg(){
		return this.message;
	}

}
