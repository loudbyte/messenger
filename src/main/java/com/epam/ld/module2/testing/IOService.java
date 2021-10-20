package com.epam.ld.module2.testing;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class IOService {

    public void writeToFile(String input) throws IOException {
        File file = new File(Properties.getOutput());
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath())) {
            bufferedWriter.write(input);
        }
    }

    public List<String> getInputFromFile() throws IOException {
        List<String> result;
        InputStream inputStream = null;
        try {
            File file = new File(Properties.getInput());
            inputStream = new FileInputStream(file);
            result = readFromInputStream(inputStream);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private List<String> readFromInputStream(InputStream inputStream) throws IOException {
        List<String> result;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            result = br.lines().collect(Collectors.toList());
        }
        return result;
    }
}
