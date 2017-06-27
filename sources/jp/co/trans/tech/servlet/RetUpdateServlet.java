package jp.co.trans.tech.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.trans.tech.formbean.ErrorFormBean;
import jp.co.trans.tech.formbean.RetInputFormBean;
import jp.co.trans.tech.service.RetInputUpdService;

/*@RetUpdateServletクラス
 * 貸出中の図書に対して返却・削除・更新処理を行う
 * その後完了画面へディスパッチする
 */

public class RetUpdateServlet extends HttpServlet{
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
	 * もし取れなければインスタンスを生成する
	 * その後、完了画面に移行する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, ServletException{

		//セッションを取る
		HttpSession session = request.getSession(false);

		try{

			//もしログイン情報の初期化があればログイン画面へ戻る
			if(session.getAttribute("GREETING_NAME") == null){
				session.invalidate();
				RequestDispatcher dispatch = request.getRequestDispatcher("./login.do");
				dispatch.forward(request, response);
			}

			//フォームをセッションから取る
			RetInputFormBean RetInputForm = (RetInputFormBean) session.getAttribute("retInputForm");

			//エラーメッセージを初期化
			RetInputForm.setErrorMsg("");

			RetInputUpdService Service = new RetInputUpdService();
			int num;

			//貸出件数検索
			num = Service.doSelectLenBookConf(RetInputForm.getLendId());

			//貸出状態でなければ返却済みと判断
			if(num == 0){
				RetInputForm.setErrorMsg("既に返却済みの図書の可能性があります。図書貸出・返却画面にて再度ご確認ください。");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/retInputBook.jsp");
				dispatch.forward(request, response);
				return;
			}

			boolean bool;

			//図書の返却・削除・更新を行う
			bool = Service.doRetUpdateBook(RetInputForm.getLendId(), RetInputForm.getReturnAccountId());

			//失敗すればエラーメッセージを出す
			if(bool == false){
				RetInputForm.setErrorMsg("返却ができませんでした。お手数ですが、図書貸出・返却画面でご確認の上、再度、返却を行ってください。");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/retInputBook.jsp");
				dispatch.forward(request, response);
				return;
			}

			//完了画面にディスパッチ
			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/regComp.jsp");
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
