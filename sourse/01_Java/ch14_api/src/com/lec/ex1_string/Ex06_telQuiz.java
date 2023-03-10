//<예제. 반복적으로 전화번호를 입력받아 다음과 같은 형식을 출력하는 반복문으로 구현하시오. 전화번호 대신 x를 입력하면 종료합니다>
/*String tel;
while(){
  // 번호(031-234-5678)입력 하면 (sc.next())
	"입력한 전화번호 : 031-234-5678
	 짝수번째 문자열  : 0 1 2 4 5 7
	문자를 꺼꾸로    : 8765-432-130
	전화번호 맨앞자리는: 031
	전화번호 뒷자리는: 5678"을 sysout
  // x(X)를 입력하면 프로그램 끝
}*/
package com.lec.ex1_string;

import java.util.Scanner;

public class Ex06_telQuiz {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("전화번호를 입력해주세요(종료는 x)");
			String tel = scanner.next();
			String Odd = "";
			String reverse = "";
			if (tel.equalsIgnoreCase("x"))
				break;
			System.out.println("입력한 전화번호 : " + tel);
			System.out.println("짝수번째 문자열 : ");
			for (int i = 0; i < tel.length(); i++) {
				if ((i % 2) == 0) {
					System.out.print(tel.charAt(i));
				}
			}
			System.out.println();
			for (int i = tel.length() - 1; i >= 0; i--) {
				reverse = reverse + tel.charAt(i);
			}
			System.out.print("문자를 거꾸로 : ");
			System.out.println(reverse);
			System.out.println("전화번호 맨 앞자리 : " + tel.substring(0, 3));
			System.out.println("전화번호 뒷자리 : " + tel.substring(tel.lastIndexOf('-') + 1));
		}
	}
}
