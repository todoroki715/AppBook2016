package jp.co.trans.tech.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.trans.tech.formbean.ErrorFormBean;
import jp.co.trans.tech.formbean.LoginTopFormBean;

/*@LoginTopServletクラス
 * ログイントップ画面へのディスパッチ処理を行うクラス
 *
 */
public class LoginTopServlet extends HttpServlet{

	/*@void doGet(HttpServletRequest, HttpServletResponse)
	 * get要求でアクセスされた場合の処理
	 * post要求と同じ処理をするためdoPostに送り一括処理をする
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{
		HttpSession session = request.getSession(true);
		session.invalidate();
		this.doPost(request, response);
		return;
	}


	/*@void doPost(HttpServletRequest, HttpServletResponse)
	 * post要求でアクセスされた場合の処理
	 * セッションを取りLoginTopFormBeanオブジェクトを所得する
	 * 	--できなかった場合インスタンス生成を行う
	 * その後セッションに格納してログイン画面へディスパッチ処理を行う
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, ServletException{

		HttpSession session = request.getSession(true);
		try{
			LoginTopFormBean LoginForm = (LoginTopFormBean) session.getAttribute("loginTopForm");
			if(LoginForm == null){
				LoginForm = new LoginTopFormBean();
			}
			LoginForm.setErrorMsg("");
			session.setAttribute("loginTopForm", LoginForm);
			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/loginTop.jsp");
			dispatch.forward(request, response);
		}


		/*@例外処理
		 * ErrorFormBeanインスタンスを生成し例外のメッセージを設定する
		 * その後生成したインスタンスをセッションに格納する
		 * 最後にエラー画面へディスパッチ処理を行う
		 */
		catch (Exception e) {
			ErrorFormBean ErrorForm = new ErrorFormBean();
			ErrorForm.setErrorMsg(e.getMessage());
			session.setAttribute("errorForm", ErrorForm);
			RequestDispatcher dispatch = request.getRequestDispatcher("./WEB-INF/jsp/error1.jsp");
			dispatch.forward(request, response);
		}
		return;

	}

}
