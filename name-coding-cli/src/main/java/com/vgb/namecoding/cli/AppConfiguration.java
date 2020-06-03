package com.vgb.namecoding.cli;

import com.vgb.namecoding.service.cumulativescore.CumulativeScoreService;
import com.vgb.namecoding.service.cumulativescore.RankedCumulativeScoreService;
import com.vgb.namecoding.service.reader.DelimitedReaderService;
import com.vgb.namecoding.service.reader.ReaderService;
import com.vgb.namecoding.service.scoring.FirstNameScoringService;
import com.vgb.namecoding.service.scoring.NameScoringService;
import com.vgb.namecoding.service.sorting.SortingService;
import com.vgb.namecoding.service.sorting.TreeMapSortingService;
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
        return new DelimitedReaderService(pattern);
    }

    @Bean
    public SortingService<String> sortingService() {
        return new TreeMapSortingService();
    }

    @Bean
    public NameScoringService nameScoringService() {
        return new FirstNameScoringService();
    }

    @Bean
    public CumulativeScoreService cumulativeScoreService(ReaderService readerService, SortingService<String> sortingService, NameScoringService nameScoringService) {
        return new RankedCumulativeScoreService(readerService, sortingService,  nameScoringService);
    }

}
