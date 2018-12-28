package com.geo.source.testmain.publictest;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * 文件
 *
 * @author YanZhen
 * 2018-11-22 15:04:20
 * FileTest
 */
public class FileTest {

	public static void main(String[] args) {
		File file = new File("src/main/resources/test.xml");
		System.out.println(file.getName());
		System.out.println(file.getParent());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			System.out.println(doc.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}   
	}
}
