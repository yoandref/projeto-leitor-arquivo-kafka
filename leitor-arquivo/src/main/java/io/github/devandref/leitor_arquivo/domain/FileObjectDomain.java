package io.github.devandref.leitor_arquivo.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class FileObjectDomain {

    private String documentNumber;
    private String cardNumber;
    private BigDecimal transactionValue;
    private String movimentType;
    private LocalDateTime dataTransactional;


}
