package com.elguindy.eazyschoolwebapp.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class contact {
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
}
