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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{

		RequestDispatcher dispatch = request.getRequestDispatcher("./login.do");
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, ServletException{

		HttpSession session = request.getSession();

		try{
			ChangePassFormBean changePassForm = (ChangePassFormBean) session.getAttribute("changePassForm");
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
			if(Utilities.checkLength(accountId) == false){
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
			if(Utilities.checkLengthLowHigh(pass, 3, 20) == false){
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


			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePass.jsp");
			dispatch.forward(request, response);
			return;

		}catch (Exception e) {
			ErrorFormBean ErrorForm = new ErrorFormBean();
			session.setAttribute("errorForm", ErrorForm);
			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/error1.jsp");
			dispatch.forward(request, response);
		}

	}
}
