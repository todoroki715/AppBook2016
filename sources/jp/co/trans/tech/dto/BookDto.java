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
	public void setBookId(String Id){
		this.bookId = Id;
	}

	//図書IDを返す
	public String getBookId(){
		return this.bookId;
	}

	//図書名をセットする
	public void setBookName(String Name){
		this.bookName = Name;
	}

	//図書名を返す
	public String getBookName(){
		return this.bookName;
	}

	//URLをセットする
	public void setIntro(String intro){
		this.intro=intro;
	}

	//URLを返す
	public String getIntro(){
		return this.intro;
	}

	//貸出フラグセットする
	public void setLendFlg(int Flg){
		this.lendFlg=Flg;
	}

	//貸出フラグを返す
	public int getLendFlg(){
		return this.lendFlg;
	}
}
