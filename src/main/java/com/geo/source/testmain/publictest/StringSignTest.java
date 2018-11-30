package com.geo.source.testmain.publictest;

public class StringSignTest {

	public static void main(String[] args) {
		// X-Sign
		System.out.println(getSign());
	}

	public static String getSign() {
		long timeStamp = System.currentTimeMillis();
		String originStamp = String.valueOf(timeStamp);
		String handledStamp;
		char c = originStamp.charAt(originStamp.length() - 1);
		if (String.valueOf(c).equals("0")) {
			StringBuilder sb = new StringBuilder(originStamp);
			sb.deleteCharAt(sb.length() - 1);
			sb.append("1");
			handledStamp = sb.toString();
		} else {
			handledStamp = originStamp;
		}
		String reverse = new StringBuilder(handledStamp).reverse().toString();
		long reverseStamp = Long.parseLong(reverse);
		long result = reverseStamp ^ 10079;
		return String.valueOf(result);
	}
}
