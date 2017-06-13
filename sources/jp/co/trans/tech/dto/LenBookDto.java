package jp.co.trans.tech.dto;

import java.util.Date;

import jp.co.trans.tech.utilities.Utilities;



public class LenBookDto {

	private String bookId;
	private String bookName;
	private int lendId;
	private String lendAccountId;
	private String accountName;
	private Date lendDate;
	private Date returnYDate;
	private Date updateDate;
	private String lendDateDisp;
	private String returnYDateDisp;

	public void setbookId(String Id){
		this.bookId = Id;
	}
	public String getbookId(){
		return this.bookId;
	}

	public void setbookName(String Name){
		this.bookName = Name;
	}
	public String getbookName(){
		return this.bookName;
	}

	public void setlendId(int Id){
		this.lendId = Id;
	}
	public int getlendId(){
		return this.lendId;
	}

	public void setlendAccountId(String Id){
		this.lendAccountId = Id;
	}
	public String getlendAccountId(){
		return this.lendAccountId;
	}

	public void setaccountName(String Name){
		this.accountName = Name;
	}
	public String getaccountName(){
		return this.accountName;
	}

	public void setlendDate(Date date){
		this.lendDate = date;
	}
	public Date getlendDate(){
		return this.lendDate;
	}

	public void setreturnYDate(Date date){
		this.returnYDate = date;
	}
	public Date getreturnYDate(){
		return this.returnYDate;
	}

	public void setupdateDate(Date date){
		this.updateDate = date;
	}
	public Date getupdateDate(){
		return this.updateDate;
	}

	public String getlendDateDisp(){
		this.lendDateDisp = Utilities.getDataStr(this.lendDate, "yyyy/MM/dd");
		return this.lendDateDisp;
	}
	public String getreturnYDateDisp(){
		this.returnYDateDisp = Utilities.getDataStr(this.returnYDate, "yyyy/MM/dd");
		return this.returnYDateDisp;
	}
}
