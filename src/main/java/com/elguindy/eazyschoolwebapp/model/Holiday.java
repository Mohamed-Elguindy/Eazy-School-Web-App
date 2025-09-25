package com.elguindy.eazyschoolwebapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Type;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Holiday {
    String reason;
    String day;
    Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }
}
