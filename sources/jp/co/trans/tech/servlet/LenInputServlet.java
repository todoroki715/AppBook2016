package jp.co.trans.tech.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.trans.tech.dto.BookDto;
import jp.co.trans.tech.formbean.ErrorFormBean;
import jp.co.trans.tech.formbean.LenInputFormBean;
import jp.co.trans.tech.formbean.LoginTopFormBean;
import jp.co.trans.tech.service.LenRegService;
import jp.co.trans.tech.utilities.Utilities;


/*@LenInputSevletクラス
 * 貸出処理に関するServlet
 * 貸出・返却画面にて選択した図書情報を所得し、
 * 貸出入力画面へと移行するための処理を行う
 */
public class LenInputServlet extends HttpServlet{
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
			//フォームをセッションから取る
			LenInputFormBean LenInputForm = new LenInputFormBean();;

			//セッションにフォームを保存
			session.setAttribute("lenInputForm", LenInputForm);

			//ログイン画面のフォームを取る
			LoginTopFormBean LoginTopForm = (LoginTopFormBean) session.getAttribute("loginTopForm");

			//ログインフォームからアカウントIDと名前を取り出す
			LenInputForm.setaccountId(LoginTopForm.getaccountId());
			LenInputForm.setaccountName(LoginTopForm.getaccountName());

			//クリックされたボタンからID情報を取り出す
			String bookId = request.getParameter("bookIdL");


			LenRegService RegService = new LenRegService();
			BookDto Dto = new BookDto();


			//本に関するデータを取り出す
			Dto = RegService.doSelectBookPrimary(bookId);
			if(Dto == null){
				LenInputForm.seterrorMsg("該当と所がありません。再度、図書貸出返却画面で確認してください。");
			}else{

				//取り出したデータを保存
				LenInputForm.setbookId(Dto.getbookId());
				LenInputForm.setbookName(Dto.getbookName());
				LenInputForm.setintro(Dto.getintro());

				//貸出フラグが立っていればエラーメッセージを出す
				if(Dto.getlendFlg() == 1){
					LenInputForm.seterrorMsg("貸出停止の図書です。再度。図書貸出返却画面で確認してください。");
				}
			}


			LenInputForm.setreturnYDate(Utilities.getRealTimeStr("yyyy/MM/dd"));

			//貸出画面にディスパッチ
			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/lenInputBook.jsp");
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
