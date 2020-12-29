package com.josivaniomarinho.personapi.enums;

import lombok.Getter;

@Getter
public enum PhoneType {

    HOME("Home"),
    MOBILE("Mobile"),
    COMMERCIAL("Commercial");

    private final String description;

    //Obs: A anotação "AllArgsConstructor" não funcionou aqui;
    PhoneType(String description) {
        this.description = description;
    }
}
