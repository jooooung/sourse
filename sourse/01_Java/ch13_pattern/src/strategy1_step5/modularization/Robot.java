package strategy1_step5.modularization;

import strategy1_step4.interfaces.FlyImpl;
import strategy1_step4.interfaces.KnifeImpl;
import strategy1_step4.interfaces.MissileImpl;

public class Robot {
	private FlyImpl fly;
	private MissileImpl missile;
	private KnifeImpl knife;

	public void actionWalk() {
		System.out.println("걸을 수 있습니다");
	}

	public void actionRun() {
		System.out.println("뛸 수 있습니다");
	}

	public void shape() {
		String className = getClass().getName(); // 클래스 이름
		int idx = className.lastIndexOf("."); // idx 는 클래스 이름의 .이다
		String name = className.substring(idx + 1); // name 은 클래스 이름의 . +1 부터이다
		System.out.println(name + "의 외형은 팔, 다리, 몸통, 머리가 있습니다");
	}

	public void actionFly() {
		fly.fly();
	}

	public void actionMissile() {
		missile.missile();
	}

	public void actionKnife() {
		knife.knife();
	}

	public void setFly(FlyImpl fly) {
		this.fly = fly;
	}

	public void setMissile(MissileImpl missile) {
		this.missile = missile;
	}

	public void setKnife(KnifeImpl knife) {
		this.knife = knife;
	}
}
