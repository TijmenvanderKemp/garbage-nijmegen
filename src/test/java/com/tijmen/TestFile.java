package com.tijmen;

import java.io.InputStream;

public final class TestFile {
    private TestFile() {
    }

    public static InputStream get(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }
}
