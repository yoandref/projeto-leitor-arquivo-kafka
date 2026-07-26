package io.github.devandref.leitor_arquivo.component;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class ReadFileComponent {

    public List<String> readFile(String pathFile, String fileName) {
        Path path = Paths.get(pathFile.concat(fileName));
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
