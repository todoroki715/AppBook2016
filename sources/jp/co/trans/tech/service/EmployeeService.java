package jp.co.trans.tech.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jp.co.trans.tech.dto.EmployeeDto;
/*@EmployeeServiceクラス
 * データベースへのアクセスを提供するクラス
 * データベースにアクセスして該当データを取り出したり更新を行う
 *
 */
public class EmployeeService {


	/*@int doSelectCount(String, String)
	 * IDとパスワードを受け取る
	 * データベースに接続してIDとパスワードに一致する人物を探す
	 * 終了するとID、パスワードに一致した人物を返す
	 */
	public int doSelectCount(String accountId, String pass) throws SQLException, NamingException{
		ResultSet rs = null;
		Connection con = null;
		Statement state = null;
		int count = 0;
		try{
			//コネクションを所得しステートメントも所得する
			con = Conection ();
			state = con.createStatement();

			//SQL文作成
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("COUNT(*) cnt ");
			sb.append("FROM ");
			sb.append("EMP_MST EM ");
			sb.append("WHERE ");
			sb.append("EM.ACCOUNT_ID = '"+accountId+"' AND ");
			sb.append("EM.PASS = '"+pass+"' AND ");
			sb.append(" EM.LEND_FLG = 0");

			//SQLを実行しその結果を保存する
			rs = state.executeQuery(sb.toString());

			//データが取れているか確認しあれば保存する
			while(rs.next()){
				count = rs.getInt("cnt");
			}

		}finally{
			closeSet(con, state, rs);
		}
		return count;
	}

	/*@EmployeeDto doSelectPrimay(String)
	 * IDを受け取る
	 * データベースに接続してIDに該当するデータを所得する
	 * 返り値は該当した人物のデータ
	 */
	public EmployeeDto doSelectPrimay(String accountId) throws SQLException, NamingException{
		EmployeeDto Employee = new EmployeeDto();
		Connection con = null;
		ResultSet rs = null;
		Statement state = null;
		try{
			//コネクションを所得しステートメントも所得する
			con = Conection ();
			state = con.createStatement();

			//SQL文作成
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("ACCOUNT_ID, ");
			sb.append("ACCOUNT_NAME, ");
			sb.append("LEND_FLG, ");
			sb.append("MASTER_FLG ");
			sb.append("FROM ");
			sb.append("EMP_MST ");
			sb.append("WHERE ");
			sb.append("ACCOUNT_ID = '"+accountId+"'");

			//SQLを実行しその結果を保存する
			rs = state.executeQuery(sb.toString());

			//データがあるか確認して、あればアカウントのデータを所得する
			if(rs.next() == true){
				Employee.setaccountId(rs.getString("ACCOUNT_ID"));
				Employee.setaccountName(rs.getString("ACCOUNT_NAME"));
				Employee.setlendFlg(rs.getInt("LEND_FLG"));
				Employee.setmasterFlg(rs.getInt("MASTER_FLG"));
			}else{
				Employee.setaccountId(null);
				Employee.setaccountName(null);
				Employee.setlendFlg(0);
				Employee.setmasterFlg(0);
			}

		}finally{

			closeSet(con, state, rs);
		}
		return Employee;
	}


	/*@boolean doUpdatePass(String, String)
	 * IDとパスワードを受け取る
	 * データベースに接続してIDに該当するデータを更新する
	 * 返り値は更新に成功したかどうか
	 */
	public boolean doUpdatePass(String accountId, String pass) throws SQLException, NamingException{
		ResultSet rs = null;
		Connection con = null;
		Statement state = null;
		try{
			//コネクションを所得しステートメントも所得する
			con = Conection ();
			state = con.createStatement();

			//SQL文作成
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE EMP_MST ");
			sb.append("SET ");
			sb.append("PASS = '"+pass+"', ");
			sb.append("UPDATE_DATE = SYSDATE ");
			sb.append("WHERE ");
			//sb.append("0!=0");
			sb.append("ACCOUNT_ID = '"+accountId+"'");

			//SQLを実行しその結果を保存する
			//executeUpdateは更新した行番号を返り値とする
			int num;
			num = state.executeUpdate(sb.toString());

			//更新失敗すればfalseを返す
			if(num == 0){
				return false;
			}

			//成功していればコミットする
			con.commit();

			return true;

		}catch (SQLException e) {

			//エラーがあればロールバックして、exceptionをスローする
			con.rollback();
			throw e;
		}
		finally{
			closeSet(con, state, rs);
		}
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
		//DataSource ds = (DataSource)context.lookup("");
		Connection con = ds.getConnection();


		//自動コミット解除
		con.setAutoCommit(false);

		return con;


	}
}
