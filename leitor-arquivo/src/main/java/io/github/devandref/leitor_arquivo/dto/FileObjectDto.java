package io.github.devandref.leitor_arquivo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class FileObjectDto {

    private String documentNumber;
    private String cardNumber;
    private String transactionValue;
    private String movimentType;
    private String dataTransactional;


}
