package com.vgb.namecoding.service;

import com.vgb.namecoding.service.cumulativescore.FileScoreService;
import com.vgb.namecoding.service.cumulativescore.RankedFileScoreService;
import com.vgb.namecoding.service.reader.DelimitedReaderService;
import com.vgb.namecoding.service.reader.ReaderService;
import com.vgb.namecoding.service.scoring.FirstNameScoringService;
import com.vgb.namecoding.service.scoring.NameScoringService;
import com.vgb.namecoding.service.sorting.SortingService;
import com.vgb.namecoding.service.sorting.TreeSetSortingService;
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
        return new DelimitedReaderService(pattern);
    }

    @Bean
    public SortingService<String> sortingService() {
        return new TreeSetSortingService();
    }

    @Bean
    public NameScoringService nameScoringService() {
        return new FirstNameScoringService();
    }

    @Bean
    public FileScoreService cumulativeScoreService(ReaderService readerService, SortingService<String> sortingService, NameScoringService nameScoringService) {
        return new RankedFileScoreService(readerService, sortingService,  nameScoringService);
    }

}
