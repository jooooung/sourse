package com.lec.ex4_object;

public class Ex04_hashCode {
	public static void main(String[] args) {
		String str1 = "Hello";
		String str2 = new String("Hello");  //새로운 메모리 공간에 생성
		if(str1 == str2) {
			System.out.println("주소가 같음(같은 곳을 참조한다)");
		} else {
			System.out.println("주소가 다름(다른 곳을 참조한다)");
		}
		Card card = new Card('♠', 2);
		System.out.println(card.hashCode());
		System.out.println(str1.hashCode());
		System.out.println(str2.hashCode());
	}
}
