package jp.co.trans.tech.utilities;

import org.apache.commons.lang3.StringUtils;

/*@Utilities_commonクラス
 * 主にチェック処理等を行いチェック結果を返却する
 */

public class Utilities {


	/* @boolean checkIndispensable(String)
	 * 文字列を判定する
	 * 未入力やスペースのみであるとfalseを返す
	 */
	public static boolean checkIndispensable(String str){
		if(StringUtils.isBlank(str)){
			return false;
		}
		return true;
	}


	/* @boolean checkNumeric(String)
	 * 文字列が数値かどうか判定する
	 * 数値以外ならfalseを返す
	 */
	public static boolean checkNumeric(String str){
		if(StringUtils.isNumeric(str) == false){
			return false;
		}
		return true;
	}


	/* @boolean checkLength(String)
	 * 文字列の長さを判定する
	 * 6文字以外だとfalseを返す
	 */
	public static boolean checkLength(String str){
		if(str.length() != 6){
			return false;
		}

		return true;
	}


	/* @boolean CheckAlphanumeric(String)
	 * 文字列が半角英数字か判定する
	 * 半角英数字以外だとfalseを返す
	 */
	public static boolean checkAlphanumeric(String str){
		if(StringUtils.isAlphanumeric(str) == false){
			return false;
		}
		return true;

	}


	/* @boolean CheckLengthLowHigh(String, int, int)
	 * 文字列が半角英数字か判定する
	 * 文字列としての長さとバイトの長さの差から判定する
	 * 半角英数字以外だとfalseを返す
	 */
	public static boolean checkLengthLowHigh(String str, int Low, int High){
		if(str.length() < Low || str.length() > High){
			return false;
		}

		return true;
	}

}
