package com.vgb.namecoding.cli;

import com.vgb.namecoding.service.cumulativescore.CumulativeScoreService;
import com.vgb.namecoding.service.cumulativescore.RankedCumulativeScoreService;
import com.vgb.namecoding.service.reader.DelimitedReaderService;
import com.vgb.namecoding.service.reader.ReaderService;
import com.vgb.namecoding.service.scoring.FirstNameScoringService;
import com.vgb.namecoding.service.scoring.NameScoringService;
import com.vgb.namecoding.service.sorting.SortingService;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

public class AppConfigurationTest {
    AppConfiguration appConfiguration = new AppConfiguration();

    @Test
    public void testNameSplitterPattern() throws Exception {
        Pattern result = appConfiguration.nameSplitterPattern();
        Assert.assertArrayEquals(new String[] {"a","b", "cd"}, result.split("a,b,cd"));
    }

    @Test
    public void testReaderService() throws Exception {
        Assert.assertNotNull(appConfiguration.readerService(null));
    }

    @Test
    public void testSortingService() throws Exception {
        Assert.assertNotNull(appConfiguration.sortingService());
    }

    @Test
    public void testNameScoringService() throws Exception {
        Assert.assertNotNull(appConfiguration.nameScoringService());
    }

    @Test
    public void testCumulativeScoreService() throws Exception {
        Assert.assertNotNull(appConfiguration.cumulativeScoreService(new DelimitedReaderService(null), null, new FirstNameScoringService()));
    }
}
