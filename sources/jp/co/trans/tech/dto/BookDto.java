package jp.co.trans.tech.dto;


/*@BookDtoクラス
 * データベースの情報を保持する
 */
public class BookDto {

	//図書ID
	private String bookId="";

	//図書名
	private String bookName="";

	//URL
	private String intro="";

	//貸出フラグ
	private int lendFlg=0;

	//図書IDをセットする
	public void setbookId(String Id){
		this.bookId = Id;
	}

	//図書IDを返す
	public String getbookId(){
		return this.bookId;
	}

	//図書名をセットする
	public void setbookName(String Name){
		this.bookName = Name;
	}

	//図書名を返す
	public String getbookName(){
		return this.bookName;
	}

	//URLをセットする
	public void setintro(String intro){
		this.intro=intro;
	}

	//URLを返す
	public String getintro(){
		return this.intro;
	}

	//貸出フラグセットする
	public void setlendFlg(int Flg){
		this.lendFlg=Flg;
	}

	//貸出フラグを返す
	public int getlendFlg(){
		return this.lendFlg;
	}
}
