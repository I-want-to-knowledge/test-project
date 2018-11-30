package com.geo.source.testmain.publictest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 
 *
 * @author YanZhen
 * 2018-05-23 11:23:03
 * ClassPathResourcesTest
 */
public class ClassPathResourcesTest {
	public static void main(String[] args) {
		name1();
	}

	/**
	 * 测试文件读取
	 *
	 * 2018-05-23 11:23:48 void
	 */
	private static void name1() {
		Resource resource = new ClassPathResource("test.properties");
		String str = null;
		try {
			InputStream inputStream = resource.getInputStream();
			str = IOUtils.toString(inputStream, "utf-8");
			String[] split = str.split("\r\n|\r|\n");
			System.out.println(split[0]);
			System.out.println(split[1]);
			Map<String, String> params = propertiesFile(split);
			System.out.println(params.get("test.data2"));
			
			ClassPathResource res = new ClassPathResource("test.properties");
			Properties pro = new Properties();
			pro.load(res.getInputStream());
			System.out.println(pro.getProperty("test.data1"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析properties文件
	 *
	 * 2018-05-23 16:53:03
	 * @param split
	 * @return Map<String,String>
	 */
	private static Map<String, String> propertiesFile(String[] split) {
		Map<String, String> map = new HashMap<String, String>();
		for (String str : split) {
			if (str.contains("=") || str.contains(":")) {
				String[] split2 = str.split("=|:");
				map.put(split2[0], split2[1]);
			}
		}
		return map;
	}
}

