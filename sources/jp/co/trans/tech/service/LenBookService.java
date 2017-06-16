package jp.co.trans.tech.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jp.co.trans.tech.dto.LenBookDto;
import jp.co.trans.tech.utilities.Utilities;


/*@LenBookServiceクラス
 * データベースに接続し、データを所得する
 */
public class LenBookService {

	//エスケープ文字設定
	public final String escape_word = ";";



	public List<LenBookDto> doSelectLenBook(String bookName, String accountName)
			throws SQLException, NamingException{
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		List<LenBookDto> Form = new ArrayList<LenBookDto>();
		try{

			//シングルクォーテーションの変換
			bookName = bookName.replace("\'","\'\'");
			accountName = accountName.replace("\'","\'\'");

			boolean escape_flag_book = false;
			boolean escape_flag_account = false;
			//ワイルドカードの判定・変換
			if(bookName.matches(".*%.*") || bookName.matches(".*％.*")
					|| bookName.matches(".*_.*") || bookName.matches(".*＿.*")){
				escape_flag_book = true;
				bookName = bookName.replace("%",escape_word+"%");
				bookName = bookName.replace("％",escape_word+"％");
				bookName = bookName.replace("_",escape_word+"_");
				bookName = bookName.replace("＿",escape_word+"＿");
			}
			if(accountName.matches(".*%.*") || accountName.matches(".*％.*")
					|| accountName.matches(".*_.*") || accountName.matches(".*＿.*")){
				escape_flag_account = true;
				accountName = accountName.replace("%",escape_word+"%");
				accountName = accountName.replace("％",escape_word+"％");
				accountName = accountName.replace("_",escape_word+"_");
				accountName = accountName.replace("＿",escape_word+"＿");
			}

			con = Conection();
			state = con.createStatement();

			//SQL文を生成
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("BOK_MST.BOOK_ID AS BOOK_ID, ");
			sb.append("BOK_MST.BOOK_NAME AS BOOK_NAME, ");
			sb.append("REN_TRN.LEND_ID AS LEND_ID, ");
			sb.append("REN_TRN.LEND_ACCOUNT_ID AS LEND_ACCOUNT_ID, ");
			sb.append("EMP_MST.ACCOUNT_NAME AS ACCOUNT_NAME, ");
			sb.append("REN_TRN.LEND_DATE AS LEND_DATE, ");
			sb.append("REN_TRN.RETURN_Y_DATE AS RETURN_Y_DATE, ");
			sb.append("REN_TRN.UPDATE_DATE AS UPDATE_DATE ");
			sb.append("FROM ");
			sb.append("BOK_MST, ");
			sb.append("EMP_MST, ");
			sb.append("REN_TRN ");
			sb.append("WHERE ");
			sb.append("BOK_MST.LEND_FLG = 0 AND ");
			sb.append("BOK_MST.BOOK_ID = REN_TRN.BOOK_ID(+) AND ");
			sb.append("REN_TRN.LEND_ACCOUNT_ID = EMP_MST.ACCOUNT_ID(+) ");

			//空欄かどうか判定する
			if(Utilities.checkIndispensable(bookName)){
				sb.append("AND BOK_MST.BOOK_NAME LIKE '%"+bookName+"%' ");

				//エスケープ文字が入っているかどうか
				if(escape_flag_book == true){
					sb.append("ESCAPE '"+escape_word+"' ");
				}
			}
			if(Utilities.checkIndispensable(accountName)){
				sb.append("AND EMP_MST.ACCOUNT_NAME LIKE '%"+accountName+"%' ");
				if(escape_flag_account == true){
					sb.append("ESCAPE '"+escape_word+"' ");
				}

			}
			sb.append("ORDER BY BOK_MST.BOOK_ID");

			//SQL実行
			rs = state.executeQuery(sb.toString());
			int count = 0;

			//要素があればリストに追加する
			while(rs.next()){
				Form.add(new LenBookDto());
				Form.get(count).setbookId(rs.getString("BOOK_ID"));
				Form.get(count).setbookName(rs.getString("BOOK_NAME"));
				Form.get(count).setlendId(rs.getInt("LEND_ID"));
				Form.get(count).setlendAccountId(rs.getString("LEND_ACCOUNT_ID"));
				Form.get(count).setaccountName(rs.getString("ACCOUNT_NAME"));
				Form.get(count).setlendDate(rs.getDate("LEND_DATE"));
				Form.get(count).setreturnYDate(rs.getDate("RETURN_Y_DATE"));
				Form.get(count).setupdateDate(rs.getDate("UPDATE_DATE"));
				count++;
			}


		}finally{
			closeSet(con, state, rs);
		}

		return Form;
	}

	/*@void closeSet(Connection, Statement, ResultSet)
	 * Connection, Statement, ResultSetを受け取る
	 * もし開いていれば終了処理を行う
	 */
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
