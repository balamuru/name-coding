package com.vgb.namecoding.service;

import com.vgb.namecoding.service.scoring.NameScoringService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class NameScoringServiceTest {

    @Autowired
    private NameScoringService nameScoringService;


    @Test
    public void testScoreName1() {
        Assert.assertEquals(40, nameScoringService.score("LINDA"));
    }

    @Test
    public void testScoreName2() {
        Assert.assertEquals(40, nameScoringService.score("linda"));
    }

    @Test
    public void testScoreName3() {
        Assert.assertEquals(40, nameScoringService.score("LinDa"));
    }

    @Test
    public void testScoreNameWithNonAlphanumerics() {
        Assert.assertEquals(-18, nameScoringService.score("#LinD#a@"));
    }

    @Test
    public void testScoreNameWithEmptyString() {
        Assert.assertEquals(0, nameScoringService.score(""));
    }

}
