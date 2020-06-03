package com.vgb.namecoding.service;

import com.vgb.namecoding.service.cumulativescore.FileScoreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.nio.file.NoSuchFileException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class FileScoreServiceTest {
    @Autowired
    FileScoreService fileScoreService;

    @Test
    public void testFileScoreWithSmallFile() throws Exception {
        Assert.assertEquals(3194L, fileScoreService.compute(TestUtils.getResourceFile("sample.txt").toURI().toURL()));
    }

    @Test
    public void testFileScoreWithLargeFile() throws Exception {
        Assert.assertEquals(871198282L, fileScoreService.compute(TestUtils.getResourceFile("sample-large.txt").toURI().toURL()));
    }

    @Test(expected = NoSuchFileException.class)
    public void testFileScoreWithInvalidFile() throws Exception {
        fileScoreService.compute(TestUtils.getResourceFile("sample-invalid.txt").toURI().toURL());
    }


}
