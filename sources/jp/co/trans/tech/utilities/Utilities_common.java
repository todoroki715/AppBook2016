package jp.co.trans.tech.utilities;

public class Utilities_common {


	public static boolean checkIndispensable(String str){
		if(str == null || str.length() == 0){
			return false;
		}

		return true;
	}
	public static boolean checkNumeric(String str){
		if(str.length() != str.getBytes().length){
			return false;
		}

		return true;
	}
	public static boolean checkLength(String str){
		if(str.length() != 6){
			return false;
		}

		return true;
	}

}
