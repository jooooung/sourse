package com.lec.ex1_runnable;

public class Ex02_TargetExTestMain {
	public static void main(String[] args) {
		Runnable target01 = new TargetEx01();
		Runnable target02 = new TargetEx02();
		//A라는 이름의 쓰레드 생성 - 안녕하세요 10번 수행할 쓰레드
		Thread threadA = new Thread(target01);
		threadA.setName("A");
		//이름 자동생성 쓰레드 생성
		Thread threadB = new Thread(target02);
		threadA.start();
		threadB.start();
		for (int i = 0; i < 10 ; i++) {
			System.out.println("나는" + Thread.currentThread().getName() + "의 i = " + i);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {}
		}
	}
}
