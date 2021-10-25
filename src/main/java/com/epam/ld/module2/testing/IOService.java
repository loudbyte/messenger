package com.epam.ld.module2.testing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * IOService class
 */
public class IOService {
    /**
     * Write data to file
     *
     * @param input data to file
     *
     * @throws IOException if writing to file failed
     */
    public void writeToFile(String input) throws IOException {
        File file = new File(Properties.getOutput());
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath())) {
            bufferedWriter.write(input);
        }
    }

    /**
     * Get data from file.
     *
     * @return returns data from file
     *
     * @throws IOException if reading from file failed
     */
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
