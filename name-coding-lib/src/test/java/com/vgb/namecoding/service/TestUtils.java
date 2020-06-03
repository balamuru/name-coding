package com.vgb.namecoding.service;

import java.io.File;
import java.nio.file.Paths;

public class TestUtils {

    /**
     * Fetch a file from the resources directory
     * @param fileName file name
     * @return file object
     */
    public static File getResourceFile(String fileName) {
        return Paths.get("src", "test", "resources", fileName).toFile();
    }
}
