package com.example.testproject;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties//안드로이드와 파이어베이스를 맵핑할 때 번지수가 맞는거만 넣어준다는 의미의 어노테이션
public class User {
    public String name; //이름
    public String email; //이메일
    public String age; //나이

    public User(){} //생성자

    //값을 추가 할때 쓰는 함수 MainActivity 에서 함수로 사용
    public User(String name, String email, String age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
    //getter, setter 설정
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getAge() {
        return age;
    }
}






