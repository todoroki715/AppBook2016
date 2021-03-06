package jp.co.trans.tech.formbean;

import java.util.List;

import jp.co.trans.tech.dto.LenBookDto;
import jp.co.trans.tech.utilities.Utilities;

/*@LenBookListFormBean
 * 画面で使用する変数をセットするクラス
 */
public class LenBookListFormBean {

	//エラーメッセージ
	private String errorMsg = "";

	//図書名
	private String bookName = "";

	//アカウント名
	private String accountName = "";

	//図書情報リスト
	private List<LenBookDto> lenBookList;

	//エラーメッセージをセットする
	public void setErrorMsg(String errorMsg){
		this.errorMsg = errorMsg;
	}

	//エラーメッセージを返す
	public String getErrorMsg(){
		return this.errorMsg;
	}

	//図書名をセットする
	public void setBookName(String Name){

		//入力されていなければ初期化
		if(Utilities.checkIndispensable(Name) == false){
			Name="";
		}
		this.bookName = Name;
	}

	//図書名を返す
	public String getBookName(){
		return this.bookName;
	}

	//アカウント名をセットする
	public void setAccountName(String Name){

		//入力されていなければ初期化
		if(Utilities.checkIndispensable(Name) == false){
			Name="";
		}
		this.accountName = Name;
	}

	//アカウント名を返す
	public String getAccountName(){
		return this.accountName;
	}

	//図書情報リストをセットする
	public void setList(List<LenBookDto> list){
		this.lenBookList = list;
	}

	//図書情報リストを返す
	public List<LenBookDto> getList(){
		return this.lenBookList;
	}

}
