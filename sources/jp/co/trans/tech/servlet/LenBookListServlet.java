package jp.co.trans.tech.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.trans.tech.formbean.ErrorFormBean;
import jp.co.trans.tech.formbean.LenBookListFormBean;
import jp.co.trans.tech.service.LenBookService;

/*@LenBookList
 * 図書のデータをリスト化する
 * 図書のデータを検索する
 */

public class LenBookListServlet extends HttpServlet{
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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, ServletException{

		//セッションを取る
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try{
			//フォームをセッションから取る
			LenBookListFormBean LenBookListForm = (LenBookListFormBean) session.getAttribute("lenBookForm");

			String view = request.getParameter("vmode");

			//もしセッション中にフォームがなければ生成する
			if(view.equals("MENU")){
				LenBookListForm = new LenBookListFormBean();
				//セッションにフォームを保存
				session.setAttribute("lenBookForm", LenBookListForm);

			}else if(view.equals("LENBOOK")){
				LenBookListForm.setbookName(request.getParameter("bookName"));
				LenBookListForm.setaccountName(request.getParameter("accountName"));
			}
			LenBookListForm.seterrorMsg("");
			LenBookService Service = new LenBookService();
			LenBookListForm.setList(Service.doSelectLenBook(LenBookListForm.getbookName(), LenBookListForm.getaccountName()));

			if(LenBookListForm.getList().size() == 0){
				LenBookListForm.seterrorMsg("該当する図書はありません。");
			}


			//パスワード変更画面にディスパッチ
			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/lenBookList.jsp");
			dispatch.forward(request, response);

		/*@例外処理
		 * ErrorFormBeanインスタンスを生成し例外のメッセージを設定する
		 * その後生成したインスタンスをセッションに格納する
		 * 最後にエラー画面へディスパッチ処理を行う
		 */
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
