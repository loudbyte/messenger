package com.epam.ld.module2.testing;

import java.util.List;

public class MockIOService extends IOService {

    private final List<String> testInput;

    public MockIOService(List<String> testInput) {
        this.testInput = testInput;
    }

    @Override
    public List<String> getInputFromFile() {
        return testInput;
    }

    @Override
    public void writeToFile(String input) {
    }
}
