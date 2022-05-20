package com.example.spring.study.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor   // 생성자 추가
@ToString// DB가 해당 객체를 인식가능
@NoArgsConstructor // 디폴트 생성자 추가
@Getter
public class Article {

    @Id  // 대표값 지정
    @GeneratedValue   // 1,2,3,  .... 자동 생성 어노테이션
    private Long id;

    @Column
    private String title;

    @Column
    private String content;


}
