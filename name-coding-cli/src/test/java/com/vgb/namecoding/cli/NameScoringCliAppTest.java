package com.vgb.namecoding.cli;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class NameScoringCliAppTest {

    private ByteArrayOutputStream byteArrayOutputStream;


    @Before
    public void setup() {
        byteArrayOutputStream = new ByteArrayOutputStream();
    }

    @After
    public void tearDown() {
        System.setOut(System.out);
    }

    @Test
    public void testMain() throws Exception {
        final File sampleFile = getResourceFile("sample.txt");
        System.setOut(new PrintStream(byteArrayOutputStream)); //set the output right before the output we want to monitor
        NameScoringCliApp.main(new String[]{"--file", sampleFile.getAbsolutePath()});
        final String actual = new String(byteArrayOutputStream.toByteArray(), Charset.defaultCharset());
        final String expected = "Input file: "+sampleFile.getAbsolutePath()+"\n" +
                "Total score: 3194";
        Assert.assertEquals(expected, actual.trim());
    }

    @Test
    public void testMainInvalidArg() throws Exception {
        System.setOut(new PrintStream(byteArrayOutputStream)); //set the output right before the output we want to monitor
        NameScoringCliApp.main(new String[]{"--abcd"});
        final String actual = new String(byteArrayOutputStream.toByteArray(), Charset.defaultCharset());
        final String expected = "usage: java -jar <cli-jar-name>.jar\n" +
        "    --file <arg>   fully qualified path to the names input file";
        Assert.assertEquals(expected, actual.trim());
    }

    @Test
    public void testMainNoArg() throws Exception {
        System.setOut(new PrintStream(byteArrayOutputStream)); //set the output right before the output we want to monitor
        NameScoringCliApp.main(new String[]{});
        final String actual = new String(byteArrayOutputStream.toByteArray(), Charset.defaultCharset());
        final String expected = "usage: java -jar <cli-jar-name>.jar\n" +
                "    --file <arg>   fully qualified path to the names input file";
        Assert.assertEquals(expected, actual.trim());
    }

    @Test(expected = NoSuchFileException.class)
    public void testMainInvalidFilePath() throws Exception {
        NameScoringCliApp.main(new String[]{"--file", "path/to/invalid/file"});
    }

    private static File getResourceFile(String fileName) {
        final File file = Paths.get("src", "test", "resources", fileName).toFile();
        return file;
    }
}

