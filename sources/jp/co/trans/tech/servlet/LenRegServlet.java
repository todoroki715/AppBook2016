package jp.co.trans.tech.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.trans.tech.formbean.ErrorFormBean;
import jp.co.trans.tech.formbean.LenInputFormBean;
import jp.co.trans.tech.service.LenRegService;
import jp.co.trans.tech.utilities.Utilities;

/*@LenRegServletクラス
 * 該当図書の貸出処理を行う
 * その後完了画面へディスパッチする
 */
public class LenRegServlet extends HttpServlet{
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
			LenInputFormBean LenInputForm = (LenInputFormBean) session.getAttribute("lenInputForm");

			//返却予定日を所得する
			String returnYDate = request.getParameter("returnYDate");
			//エラーメッセージ初期化
			LenInputForm.seterrorMsg("");

			//入力確認
			if(Utilities.checkIndispensable(returnYDate) == false){
				LenInputForm.seterrorMsg("返却予定日が未設定です。");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/lenInputBook.jsp");
				dispatch.forward(request, response);
				return;
			}

			//妥当性チェック
			if(Utilities.checkDateValid(returnYDate, "yyyy/MM/dd") == false){
				LenInputForm.seterrorMsg("返却予定日の入力に誤りがあります。");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/lenInputBook.jsp");
				dispatch.forward(request, response);
				return;
			}

			LenRegService RegService = new LenRegService();

			int count;
			//貸出されているか確認
			count = RegService.doSelectLenBookConf(LenInputForm.getbookId());

			//されていればエラーメッセージを表記して画面に戻る
			if(count >= 1){
				LenInputForm.seterrorMsg("既に貸し出されている図書の可能性があります。図書貸出・返却画面にて再度ご確認ください。");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/lenInputBook.jsp");
				dispatch.forward(request, response);
				return;
			}

			boolean bool;
			//貸出登録処理,貸出できればtrueが返る
			bool = RegService.doInsertLenBook(LenInputForm.getaccountId(),LenInputForm.getbookId(),returnYDate);

			if(bool == false){
				LenInputForm.seterrorMsg("貸出できませんでした。お手数ですが、図書貸出・返却画面でご確認の上。再度貸し出しを行ってください。");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/lenInputBook.jsp");
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
