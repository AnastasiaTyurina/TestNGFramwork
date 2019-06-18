package com.syntax.practice;

import org.testng.annotations.Test;

public class UserDirPath {

	@Test
	public void printPath() {
		System.out.println(System.getProperty("user.dir"));
		String path=System.getProperty("user.dir"); //C:\Users\anast\eclipse-workspace\TestNGFramework
		String fullPath=path+"/target/screenshots/fileName.png";
	}
}
