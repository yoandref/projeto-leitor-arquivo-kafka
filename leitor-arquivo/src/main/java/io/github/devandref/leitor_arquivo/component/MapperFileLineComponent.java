package io.github.devandref.leitor_arquivo.component;

import io.github.devandref.leitor_arquivo.dto.FileObjectDto;
import io.github.devandref.leitor_arquivo.exception.IllegalFileExcetion;
import org.springframework.stereotype.Component;


@Component
public class MapperFileLineComponent {

    private static final int LINE_SIZE = 54;

    private static final int INICIO_CPF = 0;
    private static final int FIM_CPF = 11;
    private static final int INICIO_CARTAO = 11;
    private static final int FIM_CARTAO = 27;
    private static final int INICIO_VALOR_TRANSACAO = 27;
    private static final int FIM_VALOR_TRANSACAO = 39;
    private static final int INICIO_TIPO_MOVIMENTO = 39;
    private static final int FIM_TIPO_MOVIMENTO = 40;
    private static final int INICIO_DATA_TRANSACAO = 40;
    private static final int FIM_DATA_TRANSACAO = 54;

    public FileObjectDto returnObjectFromLine(String line) {
        if (checkFileLine(line)) {
            return this.mappingLine(line);
        }
        throw new IllegalFileExcetion("Arquivo com a formatacao incorreta!");
    }

    private Boolean checkFileLine(String line) {
        return line.length() == LINE_SIZE;
    }

    private FileObjectDto mappingLine(String line) {
        return FileObjectDto.builder()
                .documentNumber(line.substring(INICIO_CPF, FIM_CPF))
                .cardNumber(line.substring(INICIO_CARTAO, FIM_CARTAO))
                .transactionValue(line.substring(INICIO_VALOR_TRANSACAO, FIM_VALOR_TRANSACAO))
                .movimentType(line.substring(INICIO_TIPO_MOVIMENTO, FIM_TIPO_MOVIMENTO))
                .dataTransactional(line.substring(INICIO_DATA_TRANSACAO, FIM_DATA_TRANSACAO))
                .build();

    }


}
