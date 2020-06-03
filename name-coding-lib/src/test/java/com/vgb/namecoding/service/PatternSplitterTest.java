package com.vgb.namecoding.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.regex.Pattern;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class PatternSplitterTest {

    @Autowired
    Pattern delimiterPattern;

    @Test
    public void testSplitString1() {
        Assert.assertArrayEquals(new String[] {"a","b","c"}, delimiterPattern.split("a,b,c"));
    }

    @Test
    public void testSplitString2() {
        String[] foo = delimiterPattern.split("a,b,,c");
        Assert.assertArrayEquals(new String[] {"a","b","","c"}, delimiterPattern.split("a,b,,c"));
    }

    @Test
    public void testSplitString3() {
        Assert.assertArrayEquals(new String[] {"\"a\"","\"b\"","\"c\""}, delimiterPattern.split("\"a\",\"b\",\"c\""));
    }

    @Test
    public void testSplitStringWithNopatternMatch1() {
        Assert.assertArrayEquals(new String[] {""}, delimiterPattern.split(""));
    }

    @Test
    public void testSplitStringWithNopatternMatch2() {
        Assert.assertArrayEquals(new String[] {"asd j"}, delimiterPattern.split("asd j"));
    }

    @Test(expected = NullPointerException.class)
    public void testSplitStringWithNopatternMatch3() {
        delimiterPattern.split(null);
    }
}
