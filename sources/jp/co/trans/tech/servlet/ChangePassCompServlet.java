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

			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/changePassComp.jsp");
			dispatch.forward(request, response);
		}catch (Exception e) {
			ErrorFormBean ErrorForm = new ErrorFormBean();
			session.setAttribute("errorForm", ErrorForm);
			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/error1.jsp");
			dispatch.forward(request, response);
		}


	}
}
