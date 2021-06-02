package com.kh.spring.menu.model.vo;



//빌더 패턴을 뜯어보기 위해 만듦



public class Car {
    // final 키워드를 써서 생성자를 통한 입력을 강요함
    private final String id, name;
    // 클래스 안에 스태틱 형태의 내부 클래스(inner class) 생성
    protected static class Builder {
        private String id;
        private String name;
        // id 입력값 받음: 리턴 타입을 Builder 타입으로 한 다음 this를 리턴
        public Builder id(String value) {
            id = value;
            return this;
        }
        // name 입력값 받음: 리턴 타입을 Builder 타입으로 한 다음 this를 리턴
        public Builder name(String value) {
            name = value;
            return this;
        }
        // 마지막에 build() 메소드를 실행하면 this가 리턴되도록 함
        public Car build() {
            return new Car(this);
        }
    }
    // 생성자를 private로 함
    // 외부에서는 접근할 수 없고 위의 Builder 클래스에서는 사용 가능
    private Car(Builder builder) {
        id = builder.id;
        name = builder.name;
    }
    // 빌더 소환: 외부에서 Car.builder() 형태로 접근 가능하게 스태틱 메소드로 
    public static Builder builder() {
        return new Builder();
    }
    // Getter
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}