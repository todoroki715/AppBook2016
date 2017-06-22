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

import jp.co.trans.tech.dto.HisBookDto;
import jp.co.trans.tech.utilities.Construct;
import jp.co.trans.tech.utilities.Utilities;

public class HisBookService {

	/*@List<HisBookDto> doSelectHisBook(String, String)
	 *
	 */
	public List<HisBookDto> doSelectHisBook(String bookName, String accountName)
			throws SQLException, NamingException{

		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		List<HisBookDto> Dto = new ArrayList<HisBookDto>();

		try{
			//コネクション所得
			con = Conection();

			//ステートメント生成
			state = con.createStatement();

			//SQL文作成
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("BM.BOOK_ID,");
			sb.append("BM.BOOK_NAME,");
			sb.append("RH.LEND_ID,");
			sb.append("RH.LEND_ACCOUNT_ID,");
			sb.append("EM_L.ACCOUNT_NAME LEND_NAME,");
			sb.append("RH.RETURN_ID, ");
			sb.append("EM_R.ACCOUNT_NAME RETURN_NAME, ");
			sb.append("RH.LEND_DATE, ");
			sb.append("RH.RETURN_DATE ");
			sb.append("FROM ");
			sb.append("REN_HIS RH,");
			sb.append("BOK_MST BM,");
			sb.append("EMP_MST EM_L,");
			sb.append("EMP_MST EM_R ");
			sb.append("WHERE ");

			//空欄かどうか判定する
			if(Utilities.checkIndispensable(bookName)){

				boolean escape_flag = false;

				//シングルクォーテーションの変換
				//もし対処しなければSQLのエラーが発生してシステムエラーとなる
				bookName = bookName.replace("\'","\'\'");

				//ワイルドカードの判定・変換
				if(bookName.matches(".*%.*") || bookName.matches(".*％.*")
						|| bookName.matches(".*_.*") || bookName.matches(".*＿.*")){
					escape_flag = true;
					bookName = bookName.replace("%",Construct.ESCAPE+"%");
					bookName = bookName.replace("％",Construct.ESCAPE+"％");
					bookName = bookName.replace("_",Construct.ESCAPE+"_");
					bookName = bookName.replace("＿",Construct.ESCAPE+"＿");
				}
				sb.append("BM.BOOK_NAME LIKE '%"+bookName+"%' ");

				//エスケープ文字が入っているかどうか
				if(escape_flag == true){
					sb.append("ESCAPE '"+Construct.ESCAPE+"' ");
				}
				sb.append("AND ");
			}

			if(Utilities.checkIndispensable(accountName)){
				boolean escape_flag = false;

				//シングルクォーテーションの変換
				//もし対処しなければSQLのエラーが発生してシステムエラーとなる
				accountName = accountName.replace("\'","\'\'");

				//ワイルドカードの判定・変換
				if(accountName.matches(".*%.*") || accountName.matches(".*％.*")
						|| accountName.matches(".*_.*") || accountName.matches(".*＿.*")){
					escape_flag = true;
					accountName = accountName.replace("%",Construct.ESCAPE+"%");
					accountName = accountName.replace("％",Construct.ESCAPE+"％");
					accountName = accountName.replace("_",Construct.ESCAPE+"_");
					accountName = accountName.replace("＿",Construct.ESCAPE+"＿");
				}
				sb.append("(EM_L.ACCOUNT_NAME LIKE '%"+accountName+"%' ");

				//エスケープ文字が入っているかどうか
				if(escape_flag == true){
					sb.append("ESCAPE '"+Construct.ESCAPE+"' ");
				}

				sb.append("OR EM_R.ACCOUNT_NAME LIKE '%"+accountName+"%' ");

				//エスケープ文字が入っているかどうか
				if(escape_flag == true){
					sb.append("ESCAPE '"+Construct.ESCAPE+"' ");
				}

				sb.append(") AND ");

			}

			sb.append("RH.BOOK_ID = BM.BOOK_ID AND ");
			sb.append("RH.LEND_ACCOUNT_ID = EM_L.ACCOUNT_ID AND ");
			sb.append("RH.RETURN_ID = EM_R.ACCOUNT_ID(+) ");
			sb.append("ORDER BY ");
			sb.append("RH.LEND_ID DESC");

			//SQL実行
			rs =  state.executeQuery(sb.toString());


			//SQLを実行して得られたデータをリストに保存する
			for(int i = 0; rs.next(); i++){
				Dto.add(new HisBookDto());
				Dto.get(i).setBookId(rs.getString("BOOK_ID"));
				Dto.get(i).setBookName(rs.getString("BOOK_NAME"));
				Dto.get(i).setLendId(rs.getInt("LEND_ID"));
				Dto.get(i).setLendAccountId(rs.getString("LEND_ACCOUNT_ID"));
				Dto.get(i).setLendAccountName(rs.getString("LEND_NAME"));
				Dto.get(i).setReturnAccountId(rs.getString("RETURN_ID"));
				Dto.get(i).setReturnAccountName(rs.getString("RETURN_NAME"));
				Dto.get(i).setLendDate(rs.getDate("LEND_DATE"));
				Dto.get(i).setReturnDate(rs.getDate("RETURN_DATE"));
			}
		}finally{
			closeSet(con, state, rs);
		}
		return Dto;
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
