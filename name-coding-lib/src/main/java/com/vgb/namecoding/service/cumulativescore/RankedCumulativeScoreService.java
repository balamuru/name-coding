package com.vgb.namecoding.service.cumulativescore;

import com.vgb.namecoding.service.reader.ReaderService;
import com.vgb.namecoding.service.scoring.NameScoringService;
import com.vgb.namecoding.service.sorting.SortingService;

import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;


public class RankedCumulativeScoreService implements CumulativeScoreService{


    private ReaderService readerService;


    private SortingService<String> sortingService;


    private NameScoringService nameScoringService;


    public RankedCumulativeScoreService(ReaderService readerService, SortingService<String> sortingService, NameScoringService nameScoringService) {
        this.readerService = readerService;
        this.sortingService = sortingService;
        this.nameScoringService = nameScoringService;

    }

    @Override
    public long compute(URL url) throws Exception {
        final AtomicLong index = new AtomicLong(0);
        final AtomicLong totalScore = new AtomicLong(0);

        sortingService.sort(readerService.read(url)).iterator().forEachRemaining(name -> {
            {
                final long sortedNameScore = index.incrementAndGet() * nameScoringService.score(name);
                totalScore.addAndGet(sortedNameScore);
           }
        });
        System.err.println("Result: " + totalScore);
        return totalScore.get();
    }

}
