package io.github.devandref.leitor_arquivo.service.impl;

import io.github.devandref.leitor_arquivo.component.MapperFileLineComponent;
import io.github.devandref.leitor_arquivo.component.ReadFileComponent;
import io.github.devandref.leitor_arquivo.dto.FileObjectDto;
import io.github.devandref.leitor_arquivo.service.ProcessFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessFileServiceImpl implements ProcessFileService {

    @Autowired
    private MapperFileLineComponent mapperFileLineComponent;

    @Autowired
    private ReadFileComponent readFileComponent;

    @Override
    @Scheduled(cron = "0 */1 * * * *")
    public void processFile() {
        List<String> lines = readFileComponent.readFile("/home/qwerty/Documentos/", "transacoes_10k.txt");
        for (String line : lines) {
            FileObjectDto fileObjectDto = mapperFileLineComponent.returnObjectFromLine(line);
            System.out.println(fileObjectDto);
        }

    }
}
