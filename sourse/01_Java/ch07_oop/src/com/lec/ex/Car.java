// 클래스 : 속성(데이터)과 메소트
// Car mycar = new Car();   mycar라는 객체를 만듬
// mycar.park()      mycar
package com.lec.ex;

public class Car {
	private String color;		//class 내에서만 쓸 수 있는 변수 선언
	private int    cc;
	private int    speed;
	public void park() {          // void는 리턴을 안 할 때 사용
		speed = 0;
		System.out.println(color + "색 차 주차한다. 지금 속도 : " + speed);
	}
	public void drive() {
		speed = 60;
		System.out.println(color + "색 차 운전한다. 지금 속도 : " + speed);
	}
	public void race() {
		speed = 120;
		System.out.println(color + "색 차 레이싱한다. 지금 속도 : " + speed);
	}
	//getter & setter (color, cc, speed)
	//Car car = new Car(); car,getColor()
	public String getColor() {   			//get번수 선언시 변수의 시작은 대문자 
		return color;       				//get 시 리턴 
	}
	public void setColor(String color) {		//set변수는 리턴이 없으므로 void 사용
		this.color = color;		//this.color : 내 객체 안의 color		
	}
	public int getCc() {
		return cc;
	}
	public void setCc(int cc) {
		this.cc = cc;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}