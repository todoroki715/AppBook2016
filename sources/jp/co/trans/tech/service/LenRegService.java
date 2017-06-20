package jp.co.trans.tech.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jp.co.trans.tech.dto.BookDto;

/*@LenRegServiceクラス
 * 貸出処理のため、データベースにアクセスする
 */
public class LenRegService {


	/*@BookDto doSelectBookPrimary(String)
	 * 該当する図書IDの情報を取り出すメソッド
	 */
	public BookDto doSelectBookPrimary(String bookId)
			throws SQLException, NamingException{
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		BookDto Dto = null;
		try{

			con = Conection();
			state = con.createStatement();
			//SQL文作成
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("BOOK_ID, ");
			sb.append("BOOK_NAME, ");
			sb.append("INTRO, ");
			sb.append("LEND_FLG ");
			sb.append("FROM ");
			sb.append("BOK_MST ");
			sb.append("WHERE ");
			sb.append("BOOK_ID='"+bookId+"'");

			//SQLを実行する
			rs = state.executeQuery(sb.toString());

			Dto = new BookDto();
			//データがあれば変数に格納する
			while(rs.next()){
				Dto.setBookId(rs.getString("Book_Id"));
				Dto.setBookName(rs.getString("Book_Name"));
				Dto.setIntro(rs.getString("INTRO"));
				Dto.setLendFlg(rs.getInt("LEND_FLG"));
			}

			if(Dto.getBookId().equals("") == true){
				Dto = null;
			}
		}finally{
			closeSet(con, state, rs);
		}
		return Dto;
	}

	/*@int doSelectLenBookConf(String)
	 * 該当する図書IDが存在するか確認するメソッド
	 */
	public int doSelectLenBookConf(String bookId)
			throws SQLException, NamingException{
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		int count = 0;
		try{

			//コネクションを所得
			con = Conection();

			//ステートメント生成
			state = con.createStatement();

			//SQL文作成
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("count(*)cnt ");
			sb.append("FROM ");
			sb.append("REN_TRN ");
			sb.append("WHERE ");
			sb.append("BOOK_ID='"+bookId+"'");

			//SQL実行
			rs = state.executeQuery(sb.toString());

			//データ所得
			while(rs.next()){
				count = rs.getInt("cnt");
			}

		}finally{
			closeSet(con, state, rs);
		}
		return count;
	}

	/*@boolean doInsertLenBook(String, String, String)
	 * 入力した情報に基づいて貸出処理を行う
	 * 成功すればtrue,失敗すればfalseを返す
	 */

	public boolean doInsertLenBook(String accountId, String bookId, String returnYDate)
			throws SQLException, NamingException{
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		int lendId = 0;
		try{
			con = Conection();
			state = con.createStatement();
			//SQL文作成
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("(MAX(CAST(LEND_ID AS NUMBER)) + 1) LEND_ID ");
			sb.append("FROM ");
			sb.append("REN_HIS ");

			//SQL実行
			rs = state.executeQuery(sb.toString());


			//データ所得
			while(rs.next()){
				lendId = rs.getInt("LEND_ID");
			}

			//再度ステートメントを生成するので一端閉じる
			if(state != null && state.isClosed() == false){
				state.close();
			}

			//ステートメントを生成
			state = con.createStatement();

			//SQL文作成
			sb = new StringBuilder();
			sb.append("INSERT ");
			sb.append("INTO REN_TRN( ");
			sb.append("LEND_ID, ");
			sb.append("LEND_ACCOUNT_ID, ");
			sb.append("BOOK_ID, ");
			sb.append("LEND_DATE, ");
			sb.append("RETURN_Y_DATE, ");
			sb.append("UPDATE_DATE ");
			sb.append(") ");
			sb.append("VALUES( ");
			sb.append(lendId+", ");
			sb.append("'"+accountId+"', ");
			sb.append("'"+bookId+"', ");
			sb.append("TO_DATE(TO_CHAR(SYSDATE,'yyyy/MM/dd'),'yyyy/MM/dd'), ");
			sb.append("TO_DATE('"+returnYDate+"','yyyy/MM/dd'), ");
			sb.append("SYSDATE ");
			sb.append(")");

			int num;

			//アップデート処理
			num = state.executeUpdate(sb.toString());

			//アップデートできなければロールバック
			if(num <= 0){
				con.rollback();
				return false;
			}

			//再度ステートメントを生成するので一端閉じる
			if(state != null && state.isClosed() == false){
				state.close();
			}

			//ステートメント生成
			state = con.createStatement();

			//SQL文作成
			sb = new StringBuilder();
			sb.append("INSERT ");
			sb.append("INTO REN_HIS( ");
			sb.append("LEND_ID, ");
			sb.append("LEND_ACCOUNT_ID, ");
			sb.append("BOOK_ID, ");
			sb.append("LEND_DATE, ");
			sb.append("RETURN_Y_DATE, ");
			sb.append("UPDATE_DATE ");
			sb.append(") ");
			sb.append("VALUES( ");
			sb.append(lendId+", ");
			sb.append("'"+accountId+"', ");
			sb.append("'"+bookId+"', ");
			sb.append("TO_DATE(TO_CHAR(SYSDATE,'yyyy/MM/dd'),'yyyy/MM/dd'), ");
			sb.append("TO_DATE('"+returnYDate+"','yyyy/MM/dd'), ");
			sb.append("SYSDATE ");
			sb.append(")");

			//アップデート処理
			num = state.executeUpdate(sb.toString());

			//アップデートできなければロールバック
			if(num <= 0){
				con.rollback();
				return false;
			}

			//成功していればコミットする
			con.commit();

		}catch (SQLException e) {
			//エラーがあればロールバックして、exceptionをスローする
			con.rollback();
			throw e;
		}finally{
			closeSet(con, state, rs);
		}
		return true;
	}

	private void closeSet(Connection con, Statement state, ResultSet rs) throws SQLException {

		//rs,state,conを終了する。
		if(rs != null && rs.isClosed() == false){
			rs.close();
		}
		if(state != null && state.isClosed() == false){
			state.close();
		}

		if(con != null && con.isClosed() == false){
			con.close();
		}
	}

	/*@Connection Conection ()
	 * コネクション接続関数
	 */
	private Connection Conection () throws SQLException, NamingException{

		InitialContext context = new InitialContext();
		DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/OracleC");
		Connection con = ds.getConnection();


		//自動コミット解除
		con.setAutoCommit(false);

		return con;


	}
}
