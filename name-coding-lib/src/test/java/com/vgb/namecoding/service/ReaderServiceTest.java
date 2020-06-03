package com.vgb.namecoding.service;

import com.vgb.namecoding.service.reader.ReaderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.nio.file.NoSuchFileException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class ReaderServiceTest {

    @Autowired
    private ReaderService readerService;

    @Test
    public void readData() throws Exception {
        final List<String> names = readerService.read(TestUtils.getResourceFile("sample.txt").toURI().toURL());
        Assert.assertEquals(9, names.size());
        Assert.assertArrayEquals(new String[]{"MARY", "PATRICIA", "LINDA", "BARBARA", "VINCENZO", "SHON", "LYNWOOD", "JERE", "HAI"}, names.toArray());
    }

    @Test
    public void readLargeData() throws Exception {
        final List<String> names = readerService.read(TestUtils.getResourceFile("sample-large.txt").toURI().toURL());
        Assert.assertEquals(5163, names.size());
    }

    @Test
    public void readSloppyData() throws Exception {
        final List<String> names = readerService.read(TestUtils.getResourceFile("sample-sloppy.txt").toURI().toURL());
        Assert.assertEquals(9, names.size());
        Assert.assertArrayEquals(new String[]{"MARY", "PATRICIA", "LINDA", "BARBARA", "VINCENZO", "SHON", "LYNWOOD", "JERE", "HAI"}, names.toArray());
    }


    @Test(expected = NoSuchFileException.class)
    public void readInvalidFile() throws Exception {
        readerService.read(TestUtils.getResourceFile("i-dont-exist.txt").toURI().toURL());
    }
}
