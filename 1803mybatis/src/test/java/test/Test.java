package test;

import java.io.UnsupportedEncodingException;

public class Test {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str1 = "Java";

		
		byte[] bys1 = str1.getBytes("Unicode");
	
		

		System.out.println(bys1.length);

		
		System.out.println("-----str1 = Java;-------");
		for(int i=0;i<=bys1.length-1;i++) {
			
			System.out.print("\t"+bys1[i]);
		}

		
	}
}
