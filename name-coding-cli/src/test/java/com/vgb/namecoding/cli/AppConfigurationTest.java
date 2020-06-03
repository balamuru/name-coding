package com.vgb.namecoding.cli;

import com.vgb.namecoding.service.reader.DelimitedSortingReaderService;
import com.vgb.namecoding.service.scoring.FirstNameScoringService;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

public class AppConfigurationTest {
    AppConfiguration appConfiguration = new AppConfiguration();

    @Test
    public void testNameSplitterPattern()  {
        Pattern result = appConfiguration.nameSplitterPattern();
        Assert.assertArrayEquals(new String[] {"a","b", "cd"}, result.split("a,b,cd"));
    }

    @Test
    public void testReaderService()  {
        Assert.assertNotNull(appConfiguration.readerService(null));
    }

    @Test
    public void testSortingService()  {
        Assert.assertNotNull(appConfiguration.sortingService());
    }

    @Test
    public void testNameScoringService()  {
        Assert.assertNotNull(appConfiguration.nameScoringService());
    }

    @Test
    public void testCumulativeScoreService()  {
        Assert.assertNotNull(appConfiguration.cumulativeScoreService(new DelimitedSortingReaderService(null), null, new FirstNameScoringService()));
    }
}
