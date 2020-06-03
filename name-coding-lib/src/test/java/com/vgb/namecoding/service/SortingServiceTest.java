package com.vgb.namecoding.service;

import com.vgb.namecoding.service.sorting.SortingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class SortingServiceTest {

    @Autowired
    private SortingService<String> sortingService;

    @Test
    public void testSorting() {
        final List<String> unsorted = Arrays.asList("MARY", "PATRICIA", "LINDA", "BARBARA", "VINCENZO", "SHON", "LYNWOOD", "JERE", "HAI");
        final Collection<String> sorted = new ArrayList<>(sortingService.sort(unsorted));
        Assert.assertArrayEquals(new String[]{"BARBARA", "HAI", "JERE", "LINDA", "LYNWOOD", "MARY", "PATRICIA", "SHON", "VINCENZO"}, sorted.toArray());
    }

}
