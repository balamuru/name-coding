package com.vgb.namecoding.service;

import com.vgb.namecoding.service.cumulativescore.FileScoreService;
import com.vgb.namecoding.service.cumulativescore.RankedFileScoreService;
import com.vgb.namecoding.service.reader.DelimitedSortingReaderService;
import com.vgb.namecoding.service.reader.ReaderService;
import com.vgb.namecoding.service.scoring.FirstNameScoringService;
import com.vgb.namecoding.service.scoring.NameScoringService;
import com.vgb.namecoding.service.sorting.PassThroughSortingService;
import com.vgb.namecoding.service.sorting.SortingService;
import com.vgb.namecoding.service.sorting.TreeSetSortingService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

/**
 * Test Configuration class
 */
@Configuration
public class TestConfiguration {

    @Bean
    public Pattern nameSplitterPattern() {
        return Pattern.compile(",");
    }

    @Bean
    public ReaderService readerService(Pattern pattern) {
        return new DelimitedSortingReaderService(pattern);
    }

    @Bean("passThroughSortingService")
    public SortingService<String> passThroughSortingService() {
        return new PassThroughSortingService();
    }

    @Bean("treeSetSortingService")
    public SortingService<String> treeSetSortingService() {
        return new TreeSetSortingService();
    }

    @Bean
    public NameScoringService nameScoringService() {
        return new FirstNameScoringService();
    }

    @Bean
    public FileScoreService cumulativeScoreService(ReaderService readerService, @Qualifier("passThroughSortingService") SortingService<String> sortingService, NameScoringService nameScoringService) {
        return new RankedFileScoreService(readerService, sortingService,  nameScoringService);
    }

}
