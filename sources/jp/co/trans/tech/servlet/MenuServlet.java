package jp.co.trans.tech.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.trans.tech.dto.EmployeeDto;
import jp.co.trans.tech.formbean.ErrorFormBean;
import jp.co.trans.tech.formbean.LoginTopFormBean;
import jp.co.trans.tech.service.EmployeeService;
import jp.co.trans.tech.utilities.Utilities_common;
public class MenuServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{

			RequestDispatcher dispatch = request.getRequestDispatcher("./login.do");
			dispatch.forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
			    throws IOException, ServletException{

			HttpSession session = request.getSession();

			try{
				LoginTopFormBean LoginTopForm = (LoginTopFormBean) session.getAttribute("loginTopForm");
				String accountId = request.getParameter("accountId");
				String pass = request.getParameter("pass");
				LoginTopForm.setaccountId(accountId);
				if(Utilities_common.checkIndispensable(accountId) == false){
					RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/menu.jsp");
					dispatch.forward(request, response);
				}
				/*
				if(Utilities_common.checkNumeric(accountId)){

				}*/
				EmployeeService Employee = new EmployeeService();
				int num = Employee.doSelectCount(accountId,pass);
				if(num == 0){
					RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/loginTop.jsp");
					dispatch.forward(request, response);

				}
				EmployeeDto Dto = Employee.doSelectPrimay(accountId);
				LoginTopForm.setaccountId(Dto.getaccountId());
				LoginTopForm.setaccountName(Dto.getaccountName());
				LoginTopForm.setmasterFlg(Dto.getmasterFlg());
				session.setAttribute("GREETING_NAME","こんにちは！" + LoginTopForm.getaccountName() + "さん");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/menu.jsp");
				dispatch.forward(request, response);
			}catch (Exception e) {
				ErrorFormBean ErrorForm = new ErrorFormBean();
				session.setAttribute("errorForm", ErrorForm);
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/error1.jsp");
				dispatch.forward(request, response);
			}


		}


}