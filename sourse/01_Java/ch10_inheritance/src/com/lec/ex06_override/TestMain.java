package com.lec.ex06_override;

public class TestMain {
	public static void main(String[] args) {
		Parent pObj1 = new Parent();
		Parent pObj2 = new Parent(10);
		System.out.println("pObj1과pObj2가 같은지 ?" + pObj1.equals(pObj2));
		pObj1.method1();
		pObj2.method2();
		System.out.println("----------------------------------");
		Child cObj1 = new Child(); //Parent() -> Child() 실행  
		Child cObj2 = new Child(2);  //Parent() -> Child(2)  실행
		cObj1.method1();
		cObj2.method2();
		cObj1.method3();
		System.out.println("cObj1과 cObj2가 같은지?" + cObj1.equals(pObj1)); //Child의 equals
		System.out.println("----------------------------------");
//		Child obj = new Child();
		Parent obj = new Child();
		obj.method1();
		obj.method2();
//		obj.method3();   obj 변수의 타입 때문에 실행 불가
//		((Child)obj).method3(); // 객체의 명시적인 형변환
		if(obj instanceof Child) {  //객체의 명시적인 형변환일 경우 instanceof 사용
			((Child)obj).method3();
		}else {
			System.out.println("형변환 불가");
		}
	}
}
