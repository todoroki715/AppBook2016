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
import jp.co.trans.tech.utilities.Utilities;

/*@MenuServletクラス
 * ログイン機能や情報所得を実施する
 * メニュー画面へのディスパッチ処理も行う
 */

public class MenuServlet extends HttpServlet{

	private final int idHigh = 6;
	private final int passLow = 3;
	private final int passHigh = 20;
	/*@void doGet(HttpServletRequest, HttpServletResponse)
	 * get要求でアクセスされた場合の処理
	 * 承認しないためログイン画面にディスパッチする
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{

		RequestDispatcher dispatch = request.getRequestDispatcher("./login.do");
		dispatch.forward(request, response);
		return;
	}


	/*@void doPost(HttpServletRequest, HttpServletResponse)
	 * post要求でアクセスされた場合の処理
	 * セッションを取りセッションからLoginTopFormBeanオブジェクトを所得する
	 * 所得したオブジェクトからアカウントIDとパスワードを所得する
	 * その後チェック処理を通る
	 * チェック処理を通った後にIDとパスワードをデータベースから検索
	 * 存在した場合ログイン処理を続けてメニュー画面に移行する
	 *
	 * チェックで異常があったり
	 * 			IDやパスワードに異常があればエラーを出す。
	 * そしてログイン画面に移行する
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, ServletException{

		HttpSession session = request.getSession();

		try{

			LoginTopFormBean LoginTopForm = (LoginTopFormBean) session.getAttribute("loginTopForm");

			//所得したパラメータからIDとパスワードを所得する
			String accountId = request.getParameter("accountId");
			String pass = request.getParameter("pass");
			String view = request.getParameter("vmode");

			if(view.equals("LENBOOK")){
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/menu.jsp");
				dispatch.forward(request, response);
				return;
			}

			//IDが設定されているか確認
			if(Utilities.checkIndispensable(accountId) == false){
				LoginTopForm.setErrorMsg("ログインIDが未設定です");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/loginTop.jsp");
				dispatch.forward(request, response);
				return;
			}

			//IDが数値かどうか確認する
			if(Utilities.checkNumeric(accountId) == false){
				LoginTopForm.setErrorMsg("ログインIDは半角数字で設定してください");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/loginTop.jsp");
				dispatch.forward(request, response);
				return;
			}

			//IDが6桁であるか確認する
			if(Utilities.checkLength(accountId, idHigh) == false){
				LoginTopForm.setErrorMsg("ログインIDは、"+idHigh+"桁で設定してください");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/loginTop.jsp");
				dispatch.forward(request, response);
				return;
			}

			//パスワードが設定されているか確認
			if(Utilities.checkIndispensable(pass) == false){
				LoginTopForm.setErrorMsg("パスワードが未設定です");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/loginTop.jsp");
				dispatch.forward(request, response);
				return;
			}

			//パスワードが半角英数字か確認する
			if(Utilities.checkAlphanumeric(pass) == false){
				LoginTopForm.setErrorMsg("パスワードは半角英数字で設定してください");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/loginTop.jsp");
				dispatch.forward(request, response);
				return;
			}

			//パスワードが3文字以上20文字以下か確認する
			if(Utilities.checkLengthLowHigh(pass, passLow, passHigh) == false){
				LoginTopForm.setErrorMsg("パスワードは"+passLow+"～"+passHigh+"桁の範囲で設定してください");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/loginTop.jsp");
				dispatch.forward(request, response);
				return;
			}

			//データ保存用にインスタンス生成
			EmployeeService Employee = new EmployeeService();

			//データが存在するか確認
			int num = Employee.doSelectCount(accountId,pass);

			//所得できなかったらエラーを出してディスパッチ処理
			if(num == 0){
				LoginTopForm.setErrorMsg("ログインID、パスワードに誤りがあるか、利用できないアカウントです");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/loginTop.jsp");
				dispatch.forward(request, response);
				return;
			}

			//ログイン情報所得
			EmployeeDto Dto = Employee.doSelectPrimay(accountId);

			if(Dto.getaccountId() == null){
				LoginTopForm.setErrorMsg("ログイン者の情報を確認できませんでした");
				RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/loginTop.jsp");
				dispatch.forward(request, response);
				return;
			}

			//ログイン情報保存
			LoginTopForm.setAccountId(Dto.getaccountId());
			LoginTopForm.setAccountName(Dto.getaccountName());
			LoginTopForm.setMasterFlg(Dto.getmasterFlg());

			//ログイン挨拶文字列格納
			session.setAttribute("GREETING_NAME","こんにちは！" + LoginTopForm.getAccountName() + "さん");

			//ディスパッチ処理（メニュー画面に移行）
			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/menu.jsp");
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