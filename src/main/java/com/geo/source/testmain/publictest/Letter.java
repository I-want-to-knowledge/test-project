package com.geo.source.testmain.publictest;

/**
 * 字母对象
 *
 * @author YanZhen 2018-10-22 14:20:11 Letter
 */
public class Letter {

	private String a;
	private String b;
	private String c;
	private String d;
	private Letter2 letter2;

	public Letter2 initLetter2(String e, String f) {
		return new Letter2(e, f);
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return d;
	}

	public void setD(String d) {
		this.d = d;
	}

	public Letter2 getLetter2() {
		return letter2;
	}

	public void setLetter2(Letter2 letter2) {
		this.letter2 = letter2;
	}

	@Override
	public String toString() {
		return "{'a':" + a + ", 'b':" + b + ", 'c':" + c + ", 'd':" + d + "}";
	}

	public class Letter2 {
		private String e;
		private String f;

		private Letter2(String e, String f) {
			this.e = e;
			this.f = f;
		}

		public String getE() {
			return e;
		}

		public String getF() {
			return f;
		}
	}

}
