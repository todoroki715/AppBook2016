package jp.co.trans.tech.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.trans.tech.formbean.ErrorFormBean;
import jp.co.trans.tech.formbean.HisBookListFormBean;
import jp.co.trans.tech.service.HisBookService;
import jp.co.trans.tech.utilities.Construct;

public class HisBookListServlet extends HttpServlet{

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
	 * セッションを取りHisBookListFormBeanオブジェクトを所得する
	 * もし取れなければインスタンスを生成する
	 * その後、貸出履歴画面に移行する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, ServletException{

		//セッションを取る
		HttpSession session = request.getSession(false);

		//文字化け防止に文字コード変換
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		try{
			//もしログイン情報の初期化があればログイン画面へ戻る
			if(session.getAttribute("GREETING_NAME") == null){
				session.invalidate();
				RequestDispatcher dispatch = request.getRequestDispatcher("./login.do");
				dispatch.forward(request, response);
			}

			//フォームをセッションから取る
			HisBookListFormBean hisBookListForm = (HisBookListFormBean) session.getAttribute("hisBookForm");

			//viewモード所得
			String view = request.getParameter(Construct.VIEW_MODE);

			//viewモード確認
			if(view.equals(Construct.MODE_MENU)){
				hisBookListForm = new HisBookListFormBean();

				//セッションにフォームを保存
				session.setAttribute("hisBookForm", hisBookListForm);

			}else if(view.equals(Construct.MODE_HISTORY)){
				hisBookListForm.setBookName(request.getParameter("bookName"));
				hisBookListForm.setAccountName(request.getParameter("accountName"));
			}

			HisBookService Service = new HisBookService();

			//データベースから貸出履歴の情報をリスト化する
			hisBookListForm.setHisBookList(Service.doSelectHisBook(hisBookListForm.getBookName(), hisBookListForm.getAccountName()) );

			//エラーメッセージ初期化
			hisBookListForm.setErrorMsg("");

			//もし貸出履歴の情報を読み込めなければエラーメッセージを出す
			if(hisBookListForm.getHisBookList().size() == 0){
				hisBookListForm.setErrorMsg("該当する貸出履歴はありません。");
			}

			//図書貸出履歴画面にディスパッチ
			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/hisBookList.jsp");
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
