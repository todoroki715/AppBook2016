package jp.co.trans.tech.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.trans.tech.formbean.ChangePassFormBean;
import jp.co.trans.tech.formbean.ErrorFormBean;
import jp.co.trans.tech.service.EmployeeService;
import jp.co.trans.tech.utilities.Utilities;

public class ChangePassCompServlet extends HttpServlet{
	final int idHigh = 6;
	final int passLow = 3;
	final int passHigh = 20;
	/*@void doGet(HttpServletRequest, HttpServletResponse)
	 * get要求でアクセスされた場合の処理
	 * get要求は承認しないためログイン画面に飛ばす
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{

		RequestDispatcher dispatch = request.getRequestDispatcher("./login.do");
		dispatch.forward(request, response);
	}

	/*@void doPost(HttpServletRequest, HttpServletResponse)
	 * post要求でアクセスされた場合の処理
	 * セッションを取りChangePassFormBeanオブジェクトを所得する
	 * リクエストから入力されたパラメータを変数に保存する
	 * それらのチェック処理を行い、異常があれば変更画面にエラーメッセージを出す
	 * 異常がなければデータベースに接続して更新処理
	 * その後、パスワード変更完了画面に移行する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, ServletException{

		HttpSession session = request.getSession();

		try{
			ChangePassFormBean changePassForm = (ChangePassFormBean) session.getAttribute("changePassForm");

			//リクエストからパラメータを取る
			String accountId = request.getParameter("accountId");
			String pass = request.getParameter("pass");
			String changePass = request.getParameter("changePass");
			String comfPass = request.getParameter("comfPass");

			//IDが設定されているか確認
			if(Utilities.checkIndispensable(accountId) == false){
				changePassForm.seterrorMsg("ログインIDが未設定です");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
				dispatch.forward(request, response);
				return;
			}

			//IDが数値かどうか確認する
			if(Utilities.checkNumeric(accountId) == false){
				changePassForm.seterrorMsg("ログインIDは半角数字で設定してください");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
				dispatch.forward(request, response);
				return;
			}

			//IDが6桁であるか確認する
			if(Utilities.checkLength(accountId, idHigh) == false){
				changePassForm.seterrorMsg("ログインIDは、6桁で設定してください");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
				dispatch.forward(request, response);
				return;
			}

			//パスワードが設定されているか確認
			if(Utilities.checkIndispensable(pass) == false){
				changePassForm.seterrorMsg("パスワードが未設定です");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
				dispatch.forward(request, response);
				return;
			}

			//パスワードが半角英数字か確認する
			if(Utilities.checkAlphanumeric(pass) == false){
				changePassForm.seterrorMsg("パスワードは半角英数字で設定してください");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
				dispatch.forward(request, response);
				return;
			}

			//パスワードが3文字以上20文字以下か確認する
			if(Utilities.checkLengthLowHigh(pass, passLow, passHigh) == false){
				changePassForm.seterrorMsg("パスワードは3～20桁の範囲で設定してください");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
				dispatch.forward(request, response);
				return;
			}

			//変更後パスワードが設定されているか確認
			if(Utilities.checkIndispensable(changePass) == false){
				changePassForm.seterrorMsg("新しいパスワードが未設定です");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
				dispatch.forward(request, response);
				return;
			}

			//変更後パスワードが半角英数字か確認する
			if(Utilities.checkAlphanumeric(changePass) == false){
				changePassForm.seterrorMsg("新しいパスワードは半角英数字で設定してください");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
				dispatch.forward(request, response);
				return;
			}

			//変更後パスワードが3文字以上20文字以下か確認する
			if(Utilities.checkLengthLowHigh(changePass, 3, 20) == false){
				changePassForm.seterrorMsg("新しいパスワードは3～20桁の範囲で設定してください");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
				dispatch.forward(request, response);
				return;
			}

			//新しいパスワードと確認用パスワードが一致しているか確認する
			if(changePass.equals(comfPass) == false){
				changePassForm.seterrorMsg("確認パスワードが新しいパスワードと一致しません");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
				dispatch.forward(request, response);
				return;
			}

			EmployeeService Service = new EmployeeService();

			int num = Service.doSelectCount(accountId, pass);
			if(num == 0){
				changePassForm.seterrorMsg("ログインＩＤ、パスワードに誤りがあるか、利用できないアカウントです");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
				dispatch.forward(request, response);
				return;
			}

			//更新処理を行い、成功したかどうかを返す
			boolean Success = Service.doUpdatePass(accountId, changePass);
			if(Success == false){
				changePassForm.seterrorMsg("パスワードの変更ができませんでした。お手数ですが、再度実施してください。");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
				dispatch.forward(request, response);
				return;
			}

			//更新完了画面にディスパッチする
			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePassComp.jsp");
			dispatch.forward(request, response);


		}catch (Exception e) {
			ErrorFormBean ErrorForm = new ErrorFormBean();
			ErrorForm.setErrorMsg(e.getMessage());
			session.setAttribute("errorForm", ErrorForm);
			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/error1.jsp");
			dispatch.forward(request, response);
		}
		return;
	}
}
