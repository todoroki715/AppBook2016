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

public class LenBookService {

	public List<LenBookDto> doSelectLenBook(String bookName, String accountName)
			throws SQLException, NamingException{
		Connection con = null;
		Statement state = null;
		ResultSet rs = null;
		List<LenBookDto> Form = new ArrayList<LenBookDto>();
		LenBookDto Dto = new LenBookDto();
		try{
			con = Conection();
			state = con.createStatement();
			StringBuilder sb = new StringBuilder();
			sb.append("Select ");
			sb.append("BOK_MST.BOOK_ID, ");
			sb.append("BOK_MST.BOOK_NAME, ");
			sb.append("REN_TRN.LEND_ID, ");
			sb.append("REN_TRN.LEND_ACCOUNT_ID, ");
			sb.append("EMP_MST.ACCOUNT_NAME, ");
			sb.append("REN_TRN.LEND_DATE, ");
			sb.append("REN_TRN.RETURN_Y_DATE, ");
			sb.append("REN_TRN.UPDATE_DATE ");
			sb.append("FROM ");
			sb.append("BOK_MST, ");
			sb.append("EMP_MST, ");
			sb.append("REN_HIS, ");
			sb.append("REN_TRN ");
			sb.append("WHERE ");
			sb.append("BOK_MST.LEND_FLG = 0");
			sb.append("AND BOK_MST.BOOK_ID = REN_TRN.BOOK_ID(+) ");
			sb.append("AND REN_TRN.LEND_ACCOUNT_ID = EMP_MST.ACCOUNT_ID(+) ");
			sb.append("AND BOK_MST.BOOK_ID = REN_TRN.BOOK_ID(+) ");
			if(Utilities.checkIndispensable(bookName)){
				sb.append("AND BOK_MST.BOOK_NAME LIKE '%"+bookName+"%' ");
			}
			if(Utilities.checkIndispensable(accountName)){
				sb.append("AND EMP_MST.ACCOUNT_0NAME LIKE '%"+accountName+"%' ");
			}
			sb.append("ORDER BY BOK_MST.BOOK_ID");
			rs = state.executeQuery(sb.toString());

			while(rs.next()){
				Dto.setbookId(rs.getString("BOOK_ID"));
				Dto.setbookName(rs.getString("BOOK_NAME"));
				Dto.setlendId(rs.getInt("LEND_ID"));
				Dto.setlendAccountId(rs.getString("LEND_ACCOUNT_ID,"));
				Dto.setaccountName(rs.getString("ACCOUNT_NAME"));
				Dto.setlendDate(rs.getDate("LEND_DATE"));
				Dto.setreturnYDate(rs.getDate("RETURN_Y_DATE"));
				Dto.setupdateDate(rs.getDate("UPDATE_DATE"));
				Form.add(Dto);
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
