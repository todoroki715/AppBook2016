package jp.co.trans.tech.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

import jp.co.trans.tech.dto.EmployeeDto;

public class EmployeeService {
	private Connection con = null;
	private Statement state = null;

	public int doSelectCount(String accountId, String pass) throws SQLException, NamingException{
		ResultSet rs = null;
		try{
			Conection ();
			state = con.createStatement();
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("COUNT(*) cnt ");
			sb.append("FROM ");
			sb.append("EMP_MST EM ");
			sb.append("WHERE ");
			sb.append("EM.ACCOUNT_ID = '"+accountId+"' AND ");
			sb.append("EM.PASS = '"+pass+"' AND ");
			sb.append(" EM.LEND_FLG = 0");

			rs = state.executeQuery(sb.toString());
			int count = 0;
			while(rs.next()){
				count++;
			}
			return count;
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			closeSet(rs);
		}
		return 0;
	}
	public EmployeeDto doSelectPrimay(String accountId) throws SQLException, NamingException{
		EmployeeDto Employee = new EmployeeDto();
		ResultSet rs = null;
		try{
			Conection ();
			state = con.createStatement();
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

			rs = state.executeQuery(sb.toString());

			rs.next();
			Employee.setaccountId(rs.getString("ACCOUNT_ID"));
			Employee.setaccountName(rs.getString("ACCOUNT_NAME"));
			Employee.setlendFlg(rs.getInt("LEND_FLG"));
			Employee.setmasterFlg(rs.getInt("MASTER_FLG"));
			return Employee;
		}catch (Exception e) {
			// TODO: handle exception
		}finally{

			closeSet(rs);
		}
		return Employee;
	}
	private void closeSet(ResultSet rs) throws SQLException {
		// TODO 自動生成されたメソッド・スタブ

		//resultSet,state,conを終了（クローズ）する。
		if(rs.isClosed() == false){
			rs.close();
		}
		if(state.isClosed() == false){
			state.close();
		}

		if(con.isClosed() == false){
			con.close();
		}
	}
	private void Conection () throws SQLException, NamingException{
		try{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.6.86:1521:ORCL","tctuser","Tct01234");

		}catch (Exception e) {
			// TODO: handle exception
		}
		return;

	}
}
