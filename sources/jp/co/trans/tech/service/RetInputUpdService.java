package jp.co.trans.tech.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jp.co.trans.tech.dto.RetBookDto;

/*@RetInputUpdServiceクラス
 * 返却処理のため、データベースアクセス等を行う
 */

public class RetInputUpdService {
	public RetBookDto doSelectBookHistory(String lendId)
		throws SQLException, NamingException{

		RetBookDto Dto = null;

		Connection con = null;
		Statement state = null;
		ResultSet rs = null;

		try{
			//コネクション生成
			con = Conection();

			//ステートメント生成
			state = con.createStatement();

			//SQL文作成
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("BM.BOOK_ID, ");
			sb.append("BM.BOOK_NAME, ");
			sb.append("RH.LEND_ID, ");
			sb.append("RH.LEND_ACCOUNT_ID, ");
			sb.append("RH.LEND_DATE, ");
			sb.append("EM.ACCOUNT_NAME, ");
			sb.append("RH.RETURN_Y_DATE ");
			sb.append("FROM ");
			sb.append("REN_HIS RH, ");
			sb.append("BOK_MST BM, ");
			sb.append("EMP_MST EM ");
			sb.append("WHERE ");
			sb.append("RH.LEND_ID="+lendId+" AND ");
			sb.append("RH.BOOK_ID=BM.BOOK_ID AND ");
			sb.append("RH.LEND_ACCOUNT_ID=EM.ACCOUNT_ID ");

			//SQL実行
			rs = state.executeQuery(sb.toString());

			Dto = new RetBookDto();

			//データ所得
			while(rs.next()){
				Dto.setbookId(rs.getString("BOOK_ID"));
				Dto.setbookName(rs.getString("BOOK_NAME"));
				Dto.setlendId(rs.getInt("LEND_ID"));
				Dto.setlendAccountId(rs.getString("LEND_ACCOUNT_ID"));
				Dto.setlendAccountName(rs.getString("ACCOUNT_NAME"));
				Dto.setlendDate(rs.getDate("LEND_DATE"));
				Dto.setreturnYDate(rs.getDate("RETURN_Y_DATE"));
			}

		}finally{
			closeSet(con, state, rs);
		}
		return Dto;
	}

	public int doSelectLenBookConf(int lendId)
			throws SQLException, NamingException{
		int count = 0;

		Connection con = null;
		Statement state = null;
		ResultSet rs = null;

		try{
			//コネクション所得
			con = Conection();

			//ステートメント生成
			state = con.createStatement();

			//SQL文作成
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("count(*) cnt ");
			sb.append("FROM ");
			sb.append("REN_TRN ");
			sb.append("WHERE ");
			sb.append("LEND_ID = "+lendId);

			//SQL実行
			rs =  state.executeQuery(sb.toString());

			while(rs.next()){
				count = rs.getInt("cnt");
			}

		}finally{
			closeSet(con, state, rs);
		}
		return count;
	}

	public boolean doRetUpdateBook(int lendId, String accountId)
			throws SQLException, NamingException{
		Connection con = null;
		Statement state = null;
		try{
			con = Conection();
			state = con.createStatement();
			//SQL文作成
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE ");
			sb.append("FROM ");
			sb.append("REN_TRN ");
			sb.append("WHERE ");
			sb.append("LEND_ID = "+lendId);
			int ret;
			ret = state.executeUpdate(sb.toString());
			if(ret == 0){
				con.rollback();
				return false;
			}
			if(state != null && state.isClosed() == false){
				state.close();
			}

			sb = new StringBuilder();
			sb.append("UPDATE REN_HIS ");
			sb.append("SET ");
			sb.append("RETURN_DATE = TO_DATE(TO_CHAR(SYSDATE,'yyyy/MM/dd'),'yyyy/MM/dd'), ");
			sb.append("RETURN_ID = '"+accountId+"', ");
			sb.append("UPDATE_DATE = SYSDATE ");
			sb.append("WHERE ");
			sb.append("LEND_ID = "+lendId);
			state = con.createStatement();
			ret = state.executeUpdate(sb.toString());
			if(ret == 0){
				con.rollback();
				return false;
			}
			//成功していればコミットする
			con.commit();
		}catch (SQLException e) {
			con.rollback();
			throw e;
		}
		finally{
			closeSet(con, state);
		}
		return true;
	}

	private void closeSet(Connection con, Statement state) throws SQLException {

		//state,conを終了する。
		if(state != null && state.isClosed() == false){
			state.close();
		}

		if(con != null && con.isClosed() == false){
			con.close();
		}
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