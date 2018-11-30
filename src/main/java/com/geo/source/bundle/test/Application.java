package com.geo.source.bundle.test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ResourceBundle类读取指定文件
 *
 * @author YanZhen
 * 2018-08-15 17:12:08
 * Application
 */
public class Application {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		// en_US
		System.out.println("Current Locale: " + Locale.getDefault());
		ResourceBundle mybundle = ResourceBundle.getBundle("MyLabels");

		// read MyLabels_en_US.properties
		System.out.println("Say how are you in US English: " + mybundle.getString("how_are_you"));

		Locale.setDefault(new Locale("ms", "MY"));

		// read MyLabels_ms_MY.properties
		System.out.println("Current Locale: " + Locale.getDefault());
		mybundle = ResourceBundle.getBundle("MyLabels");
		System.out.println("Say how are you in Malaysian Malaya language: " + mybundle.getString("how_are_you"));

	}

}
