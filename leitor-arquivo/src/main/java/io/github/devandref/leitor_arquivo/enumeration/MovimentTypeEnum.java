package io.github.devandref.leitor_arquivo.enumeration;

import lombok.Getter;

@Getter
public enum MovimentTypeEnum {

    ADESAO("A"),
    MIGRACAO("M"),
    RECARGA("R");

    private String id;

    MovimentTypeEnum(String id) {
        this.id = id;
    }

}
