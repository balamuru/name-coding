package com.vgb.namecoding.cli;

import com.vgb.namecoding.service.cumulativescore.FileScoreService;
import com.vgb.namecoding.service.cumulativescore.RankedFileScoreService;
import com.vgb.namecoding.service.reader.DelimitedSortingReaderService;
import com.vgb.namecoding.service.reader.ReaderService;
import com.vgb.namecoding.service.scoring.FirstNameScoringService;
import com.vgb.namecoding.service.scoring.NameScoringService;
import com.vgb.namecoding.service.sorting.PassThroughSortingService;
import com.vgb.namecoding.service.sorting.SortingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Pattern;

/**
 * Configuration class
 */
@Configuration
public class AppConfiguration {

    @Bean
    public Pattern nameSplitterPattern() {
        return Pattern.compile(",");
    }

    @Bean
    public ReaderService readerService(Pattern pattern) {
        return new DelimitedSortingReaderService(pattern);
    }

    @Bean
    public SortingService<String> sortingService() {
        return new PassThroughSortingService();
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
