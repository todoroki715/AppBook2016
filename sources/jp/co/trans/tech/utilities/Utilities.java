package jp.co.trans.tech.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/*@Utilities_commonクラス
 * 主にチェック処理等を行いチェック結果を返却する
 */

public class Utilities {


	public static String getDataStr(Date date ,String str){
		SimpleDateFormat SimpleDate = new SimpleDateFormat(str);
		return SimpleDate.format(date);
	}

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


	/* @boolean checkLength(String, int)
	 * 文字列の長さを判定する
	 * 文字列がint引数の長さ以外だとfalseを返す
	 */
	public static boolean checkLength(String str, int High){
		if(str.length() != High){
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
	 * 引数から最小と最大の長さを判定する
	 * 指定した長さでなければfalseを返す
	 */
	public static boolean checkLengthLowHigh(String str, int Low, int High){
		if(str.length() < Low || str.length() > High){
			return false;
		}

		return true;
	}

}
