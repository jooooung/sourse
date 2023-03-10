//  매개변수 없는 생성자 함수는 없음
//2차원
package com.lec.ex03_point;

public class Point {
	private int x;
	private int y;
	public Point() {
		System.out.println("매개변수 없는 Point(부모클래스(Super클래스, 상위클래스)) 생성자");
	}
	public Point(int x, int y) {
		System.out.println("매개변수 있는 Point 생성자");
		this.x = x;
		this.y = y;
	}
	public String infoPrint() {
		return "좌표 x = " + x + ", y = " + y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
