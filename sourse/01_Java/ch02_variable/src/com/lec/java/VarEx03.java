package com.lec.java;

//변수 선언 방법
public class VarEx03 {
	public static void main(String[] args) {
		// 1. 자료형 변수명; - 변수 선언과 할당을 분리
		int num1; // 변수 선언 (할당하지 않으면 출력 불가)
		num1 = 10; // 할당

		// 2. 자료형 변수명 = 값; - 변수 선언과 동시에 초기화
		int num2 = 20;
		System.out.printf("num1=%d, num2=%d\n", num1, num2); // \n은 여백

		// 3. 자료형 변수1, 변수2,...;  - 동일 자료형의 변수 다수 선언
		int num3, num4;
		num3 = 30; num4 = 40;
		System.out.printf("num3=%d, num4=%d\n", num3, num4);
		
		// 4. 자료형 변수1=값1, 변수2=값2,...;  -동일자료형 변수를 다수선언과 초기화
		int num5=50, num6=60;
		System.out.printf("num5=%d, num6=%d\n", num5, num6);
	}

}
