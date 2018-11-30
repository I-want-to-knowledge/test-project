package com.geo.source.testmain.publictest;

import com.geo.source.testmain.publictest.Letter.Letter2;

public class TestClass {

	public static void main(String[] args) {
		Letter letter = new Letter();
		Letter2 initLetter2 = letter.initLetter2("5", "6");
		System.out.println(initLetter2.getE());
		System.out.println(initLetter2.getF());
		
	}
}
