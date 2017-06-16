package jp.co.trans.tech.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.trans.tech.dto.RetBookDto;
import jp.co.trans.tech.formbean.ErrorFormBean;
import jp.co.trans.tech.formbean.LoginTopFormBean;
import jp.co.trans.tech.formbean.RetInputFormBean;
import jp.co.trans.tech.service.RetInputUpdService;
import jp.co.trans.tech.utilities.Utilities;

/*@RetInputServletクラス
 * 貸出・返却画面で選択した図書の情報を所得する
 * その後返却画面へディスパッチする
 */
public class RetInputServlet extends HttpServlet{
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
	 * その後、パスワード変更画面に移行する
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, ServletException{

		//セッションを取る
		HttpSession session = request.getSession();

		try{
			//インスタンスを生成
			RetInputFormBean RetInputForm = new RetInputFormBean();

			//セッションにフォームを保存
			session.setAttribute("retInputForm", RetInputForm);

			//ログイン画面のフォームを取る
			LoginTopFormBean LoginTopForm = (LoginTopFormBean) session.getAttribute("loginTopForm");

			//ログインフォームからアカウントIDとアカウント名を所得する
			RetInputForm.setreturnAccountId(LoginTopForm.getaccountId());
			RetInputForm.setreturnAccountName(LoginTopForm.getaccountName());

			//ボタンからデータ所得
			String bookId = request.getParameter("bookIdR");


			RetInputUpdService Service = new RetInputUpdService();
			RetBookDto Dto = new RetBookDto();

			//返却済みか確認
			Dto = Service.doSelectBookHistory(bookId);
			if(Dto == null){
				RetInputForm.seterrorMsg("既に返却済みの可能性があります。再度、図書貸出・返却画面でご確認してください。");

			}else{
				//所得できたらデータ保存
				RetInputForm.setbookId(Dto.getbookId());
				RetInputForm.setbookName(Dto.getbookName());
				RetInputForm.setlendId(Dto.getlendId());
				RetInputForm.setlendAccountId(Dto.getlendAccountId());
				RetInputForm.setlendAccountName(Dto.getlendAccountName());
				RetInputForm.setlendDate(Dto.getlendDateDisp());
				RetInputForm.setreturnYDate(Dto.getreturnYDateDisp());
			}

			//現在の日付をセット
			RetInputForm.setreturnDate(Utilities.getRealTimeStr("yyyy/MM/dd"));


			//パスワード変更画面にディスパッチ
			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/retInputBook.jsp");
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
