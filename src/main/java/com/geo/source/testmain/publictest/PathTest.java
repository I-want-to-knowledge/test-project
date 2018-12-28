package com.geo.source.testmain.publictest;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 路径获取
 *
 * @author YanZhen 2018-11-26 10:33:14 PathTest
 */
public class PathTest {

	public static void main(String[] args) {
		PathTest pathTest = new PathTest();
		pathTest.showURL();
	}

	private void showURL() {
		// 第一种：获取类加载的根路径 
		// D:\Users\Administrator\workspace\test-project\target\classes
		File f = new File(this.getClass().getResource("/").getPath());
		System.out.println(f);

		// 获取当前类的所在工程路径; 如果不加“/” 获取当前类的加载目录 
		// D:\Users\Administrator\workspace\test-project\target\classes\com\geo\source\testmain\publictest
		File f2 = new File(this.getClass().getResource("").getPath());
		System.out.println(f2);

		// 第二种：获取项目路径 
		// D:\Users\Administrator\workspace\test-project
		File directory = new File("");// 参数为空
		String courseFile;
		try {
			courseFile = directory.getCanonicalPath();
			System.out.println(courseFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 第三种： file:/D:/Users/Administrator/workspace/test-project/target/classes/
		URL xmlpath = this.getClass().getClassLoader().getResource("");
		System.out.println(xmlpath);

		// 第四种： D:\Users\Administrator\workspace\test-project
		System.out.println(System.getProperty("user.dir"));
		/*
		 * 结果： C:\Documents and Settings\Administrator\workspace\projectName
		 * 获取当前工程路径
		 */

		// 第五种： 获取所有的类路径 包括jar包的路径
		// D:\Users\Administrator\workspace\test-project\target\classes;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\storm\storm-core\1.2.2\storm-core-1.2.2.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\esotericsoftware\kryo\3.0.3\kryo-3.0.3.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\esotericsoftware\reflectasm\1.10.1\reflectasm-1.10.1.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\ow2\asm\asm\5.0.3\asm-5.0.3.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\esotericsoftware\minlog\1.3.0\minlog-1.3.0.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\objenesis\objenesis\2.1\objenesis-2.1.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\clojure\clojure\1.7.0\clojure-1.7.0.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\ring-cors\ring-cors\0.1.5\ring-cors-0.1.5.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\lmax\disruptor\3.3.2\disruptor-3.3.2.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\logging\log4j\log4j-api\2.8.2\log4j-api-2.8.2.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\logging\log4j\log4j-core\2.8.2\log4j-core-2.8.2.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\logging\log4j\log4j-slf4j-impl\2.8.2\log4j-slf4j-impl-2.8.2.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\slf4j\log4j-over-slf4j\1.6.6\log4j-over-slf4j-1.6.6.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\io\dropwizard\metrics\metrics-core\3.1.0\metrics-core-3.1.0.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\io\dropwizard\metrics\metrics-graphite\3.1.0\metrics-graphite-3.1.0.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\io\dropwizard\metrics\metrics-ganglia\3.1.0\metrics-ganglia-3.1.0.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\info\ganglia\gmetric4j\gmetric4j\1.0.7\gmetric4j-1.0.7.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\javax\servlet\servlet-api\2.5\servlet-api-2.5.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\commons-io\commons-io\2.6\commons-io-2.6.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\curator\curator-framework\4.0.1\curator-framework-4.0.1.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\curator\curator-client\4.0.1\curator-client-4.0.1.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\zookeeper\zookeeper\3.5.3-beta\zookeeper-3.5.3-beta.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\kafka\kafka-clients\2.0.0\kafka-clients-2.0.0.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\lz4\lz4-java\1.4.1\lz4-java-1.4.1.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\xerial\snappy\snappy-java\1.1.7.1\snappy-java-1.1.7.1.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\spring-core\5.0.8.RELEASE\spring-core-5.0.8.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\spring-jcl\5.0.8.RELEASE\spring-jcl-5.0.8.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\slf4j\slf4j-log4j12\1.7.5\slf4j-log4j12-1.7.5.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\commons-cli\commons-cli\1.2\commons-cli-1.2.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\log4j\log4j\1.2.17\log4j-1.2.17.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\io\netty\netty\3.10.5.Final\netty-3.10.5.Final.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\storm\storm-kafka-client\1.2.2\storm-kafka-client-1.2.2.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\commons-lang\commons-lang\2.5\commons-lang-2.5.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\fasterxml\jackson\core\jackson-databind\2.9.4\jackson-databind-2.9.4.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\fasterxml\jackson\core\jackson-core\2.9.4\jackson-core-2.9.4.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\google\guava\guava\16.0.1\guava-16.0.1.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\alibaba\fastjson\1.2.39\fastjson-1.2.39.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\io\netty\netty-all\5.0.0.Alpha2\netty-all-5.0.0.Alpha2.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\boot\spring-boot-starter-web\2.0.4.RELEASE\spring-boot-starter-web-2.0.4.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\boot\spring-boot-starter\2.0.4.RELEASE\spring-boot-starter-2.0.4.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\boot\spring-boot\2.0.4.RELEASE\spring-boot-2.0.4.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\boot\spring-boot-autoconfigure\2.0.4.RELEASE\spring-boot-autoconfigure-2.0.4.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\boot\spring-boot-starter-logging\2.0.4.RELEASE\spring-boot-starter-logging-2.0.4.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\logging\log4j\log4j-to-slf4j\2.10.0\log4j-to-slf4j-2.10.0.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\slf4j\jul-to-slf4j\1.7.25\jul-to-slf4j-1.7.25.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\javax\annotation\javax.annotation-api\1.3.2\javax.annotation-api-1.3.2.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\yaml\snakeyaml\1.19\snakeyaml-1.19.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\boot\spring-boot-starter-json\2.0.4.RELEASE\spring-boot-starter-json-2.0.4.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.9.6\jackson-datatype-jdk8-2.9.6.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.9.6\jackson-datatype-jsr310-2.9.6.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.9.6\jackson-module-parameter-names-2.9.6.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\boot\spring-boot-starter-tomcat\2.0.4.RELEASE\spring-boot-starter-tomcat-2.0.4.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\tomcat\embed\tomcat-embed-core\8.5.32\tomcat-embed-core-8.5.32.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\tomcat\embed\tomcat-embed-el\8.5.32\tomcat-embed-el-8.5.32.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\apache\tomcat\embed\tomcat-embed-websocket\8.5.32\tomcat-embed-websocket-8.5.32.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\hibernate\validator\hibernate-validator\6.0.11.Final\hibernate-validator-6.0.11.Final.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\javax\validation\validation-api\2.0.1.Final\validation-api-2.0.1.Final.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\jboss\logging\jboss-logging\3.3.2.Final\jboss-logging-3.3.2.Final.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\com\fasterxml\classmate\1.3.4\classmate-1.3.4.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\spring-web\5.0.8.RELEASE\spring-web-5.0.8.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\spring-beans\5.0.8.RELEASE\spring-beans-5.0.8.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\spring-webmvc\5.0.8.RELEASE\spring-webmvc-5.0.8.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\spring-aop\5.0.8.RELEASE\spring-aop-5.0.8.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\spring-context\5.0.8.RELEASE\spring-context-5.0.8.RELEASE.jar;D:\Program Files (x86)\Apache\Maven\maven-repository\org\springframework\spring-expression\5.0.8.RELEASE\spring-expression-5.0.8.RELEASE.jar
		System.out.println(System.getProperty("java.class.path"));
	}
}
