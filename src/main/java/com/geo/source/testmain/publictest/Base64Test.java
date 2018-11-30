package com.geo.source.testmain.publictest;

import java.util.Base64;

/**
 * 编码解码
 *
 * @author YanZhen 2018-11-22 16:10:01 Base64Test
 */
public class Base64Test {
	public static void main(String[] args) {
		final Base64.Decoder decoder = Base64.getDecoder();
		final Base64.Encoder encoder = Base64.getEncoder();
		final String text = "字串文字";
		try {
			byte[] textByte = text.getBytes("UTF-8");
			//编码
			final String encodedText = encoder.encodeToString(textByte);
			System.out.println(encodedText);
			//解码
			System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
			
			// aGFvNzA3Nzg5|bG92ZVhJQU83MDc3ODk=
			System.out.println("解码 aGFvNzA3Nzg5 : "+decoder.decode("aGFvNzA3Nzg5"));
			System.out.println("解码 bG92ZVhJQU83MDc3ODk= :"+decoder.decode("bG92ZVhJQU83MDc3ODk="));
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
//		final Base64.Decoder decoder = Base64.getDecoder();
//		final Base64.Encoder encoder = Base64.getEncoder();
//		final String text = "字串文字";
//		final byte[] textByte = text.getBytes("UTF-8");
//		//编码
//		final String encodedText = encoder.encodeToString(textByte);
//		System.out.println(encodedText);
//		//解码
//		System.out.println(new String(decoder.decode(encodedText), "UTF-8"));

	}
}
