package kh.java.polymorphism.animal;

public class AnimalMain {
	public static void main(String[] args) {
		AnimalMain am = new AnimalMain();
		am.test8();
	}

	/**
	 * 자식객체를 부모타입의 참조변수에 담아 제어할 수 있다. 단, 자식 클래스에 선언된 자원은 사용 할 수 없다.
	 */
	public void test1() {
		Cat cat = new Cat();
		cat.say();
		cat.punch();

		Animal animal1 = cat;
		System.out.println(cat == animal1); // 주소값 비교 true

		animal1.say();
//		animal1.punch(); //부모타입 변수에 담으면 자식클래스에만 선언된 필드, 메서드를 사용불가 하다.

		Object obj1 = cat;
//		obj1.say();
//		obj1.punch(); //이것도 마찬가지
		System.out.println(obj1.toString()); // Object에 선언되어있는 것만 가능

		// 다시 자식타입의 변수에 담으면 자식타입의 자원 사용 가능
		Animal animal2 = (Animal) obj1;
		animal2.say();

		Cat cat2 = (Cat) animal2;
		cat2.punch();

		((Animal) obj1).say();
	}

	/**
	 * 형변환 upCasting = 부모타입으로 형변환. 자동(암묵적)형변환 downCasting = 자식타입으로 형변환. 수동(명시적)형변환
	 */
	public void test2() {
		Animal animal = new Dog(); // UpCasting
		Dog dog = (Dog) animal;
	}

	/**
	 * 부모타입의 배열에 자식객체를 담을 수 있다.
	 */
	public void test3() {
		Animal[] arr = new Animal[3];

		arr[0] = new Cat();
		arr[1] = new Dog();
//		arr[2] = new Animal();

		System.out.println(arr[0] instanceof Cat); // true
		System.out.println(arr[0] instanceof Dog); // false
		System.out.println(arr[0] instanceof Animal); // true
		System.out.println(arr[0] instanceof Object); // true

		// Animal anm1 = new Dog();
		for (Animal anm1 : arr) {
			anm1.say();

			// cat 객체인 경우
			if (anm1 instanceof Cat) {
				((Cat) anm1).punch();
			} else if (anm1 instanceof Dog) {
				((Dog) anm1).kick();
			}
		}
	}

	/**
	 * 매개변수 선언부에서 다형성 활용하기
	 */
	public void test4() {
		Cat cat = new Cat();
		Dog dog = new Dog();

		attack(cat);
		attack(dog);
	}

	public void attack(Animal animal) {

	}

	public void attack(Cat cat) {
		cat.punch();
	}

	public void attack(Dog dog) {
		dog.kick();
	}

	/**
	 * 리턴값을 다형성 이용해서 처리하기
	 */
	public void test5() {
		Animal cat = makeCat();
		Animal dog = makeDog();

		Animal[] arr = new Animal[100];
		for (int i = 0; i < arr.length; i++) {
			if (i % 2 == 0) {
				arr[i] = makeDog();
			} else
				arr[i] = makeCat();
		}
		for (Animal a : arr) {
			if (a instanceof Cat) {
				((Cat) a).punch();
			} else if (a instanceof Dog) {
				((Dog) a).kick();
			}
		}
		// 위의 조건문대신 메서드 오버로딩을 활용해도 좋다
		for (Animal a : arr) {
			a.attack();
		}

	}

	public Dog makeDog() {
		return new Dog();
	}

	public Cat makeCat() {
		return new Cat();
	}

	/**
	 * 동적바인딩 1. 상속 & 부모클래스의 메소드 오버라이딩 2. 자식객체를 부모타입으로 제어, 해당 메소드 호출 자동으로 자식타입의 재작성한
	 * 메서드가 호출된다. 다운캐스팅 없이 자식 메서드 사용가능
	 * 
	 * 정적 바인딩 동일한 타입의 메서드를 호출한다.
	 */
	public void test6() {
		Animal a1 = new Cat();
		Animal a2 = new Dog();

		a1.say();
		a2.say();

		// 다형성을 이용한 attack
		Animal[] arr = new Animal[100];
		for (int i = 0; i < arr.length; i++) {
			if (i % 2 == 0) {
				arr[i] = makeDog();
			} else
				arr[i] = makeCat();
		}

		// 실행
		for (Animal anm : arr) {
			anm.attack();// 동적바인딩 자식클래스의 오버라이드한 메서드가 호출된다
		}
	}

	/**
	 * 추상클래스 Animal
	 */
	public void test7() {
//		Animal a = new Animal();// Cannot instantiate the type Animal
		Animal a = new Cat();
		Animal b = new Dog();
		Animal c = new Snake();

		// 추상클래스 Animal을 상속한 자식 클래스는
		// 무조건 추상메서드 attack구현을 보장받는다.
		a.attack();
		b.attack();
		c.attack();
	}

	/**
	 * 인터페이스는 객체화 할 수 없다. 인터페이스 역시 부모타입으로 다형성을 적용 할 수 있다.
	 */
	public void test8() {
//		Runnable r = new Runnable();  //Cannot instantiate the type Runnable
		Runnable r1 = new Dog();
		Runnable r2 = new Cat();

		r1.run();
		r2.run();

		((Dog) r1).say();
		((Dog) r1).attack();
		((Dog) r1).kick();

//		r1.say(); //Animal의 재작성메서드 say
//		r1.attack(); //Animal의 재작성메서드 attack
//		r1.kick(); //Dog의 kick 위세개는 어느것도 사용 불가능하다

		// 상수
		System.out.println(Runnable.LEGS);

		Bitable b1 = new Dog();
		Bitable b2 = new Cat();
		b1.bite("멍멍~");
		b2.bite("왜애애애앵~");

		System.out.println("-------------------");

		Dog d1 = (Dog) b1;
		d1.bite("멍멍~");
		d1.kick();
		d1.attack();
		d1.say();

		System.out.println("-------------------");

		Animal a1 = d1;
		a1.attack();
		a1.say();
	}

}