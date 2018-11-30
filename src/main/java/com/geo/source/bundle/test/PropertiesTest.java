package com.geo.source.bundle.test;

import java.util.ResourceBundle;

public class PropertiesTest {
	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("test");
		System.out.println(bundle.getStringArray("stor"));
	}
}

