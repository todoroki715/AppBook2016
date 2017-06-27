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
import jp.co.trans.tech.utilities.Construct;

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
		HttpSession session = request.getSession(false);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try{
			//セッションタイムアウト感知
			if(!request.isRequestedSessionIdValid()){

				//今後セッションを利用するため生成する
				session = request.getSession(true);

				throw new Exception(Construct.SESSION_TIMEOUT);
			}

			//もしログイン情報の初期化があればログイン画面へ戻る
			if(session.getAttribute("GREETING_NAME") == null){
				session.invalidate();
				RequestDispatcher dispatch = request.getRequestDispatcher("./login.do");
				dispatch.forward(request, response);
				return;
			}

			//フォームをセッションから取る
			LenBookListFormBean LenBookListForm = (LenBookListFormBean) session.getAttribute("lenBookForm");

			String view = request.getParameter(Construct.VIEW_MODE);

			//もしセッション中にフォームがなければ生成する
			if(view.equals(Construct.MODE_MENU)){
				LenBookListForm = new LenBookListFormBean();
				//セッションにフォームを保存
				session.setAttribute("lenBookForm", LenBookListForm);

			}else if(view.equals(Construct.MODE_LEN)){
				LenBookListForm.setBookName(request.getParameter("bookName"));
				LenBookListForm.setAccountName(request.getParameter("accountName"));
			}

			//エラーメッセージ初期化
			LenBookListForm.setErrorMsg("");

			//インスタンスを生成
			LenBookService Service = new LenBookService();

			LenBookListForm.setList(Service.doSelectLenBook(LenBookListForm.getBookName(), LenBookListForm.getAccountName()));

			if(LenBookListForm.getList().size() == 0){
				LenBookListForm.setErrorMsg("該当する図書はありません。");
			}


			//貸出画面にディスパッチ
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
