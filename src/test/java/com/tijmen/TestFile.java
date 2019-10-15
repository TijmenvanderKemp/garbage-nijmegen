package com.tijmen;

import java.io.InputStream;

public class TestFile {
    public static InputStream get(String path) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
    }
}
