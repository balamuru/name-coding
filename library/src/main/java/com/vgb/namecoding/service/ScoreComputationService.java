package com.vgb.namecoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ScoreComputationService {

    @Autowired
    private NameReaderService nameReaderService;

    @Autowired
    private NameSortingService<String> nameSortingService;

    @Autowired
    private NameScoringService nameScoringService;


    public ScoreComputationService(NameReaderService nameReaderService, NameSortingService<String> nameSortingService, NameScoringService nameScoringService) {
        this.nameReaderService = nameReaderService;
        this.nameSortingService = nameSortingService;
        this.nameScoringService = nameScoringService;

    }

    public long compute(URL url) throws Exception {
        final AtomicLong index = new AtomicLong(0);
        final AtomicLong totalScore = new AtomicLong(0);

        nameSortingService.sort(nameReaderService.read(url)).iterator().forEachRemaining(name -> {
            {
                final long sortedScore = index.incrementAndGet() * nameScoringService.score(name);
                totalScore.addAndGet(sortedScore);

//                System.err.println("name : " + name + " - idx " + cur + ", ind-score: " + individualScore + ", sorted-score: " + sortedScore + ", totalScore: " + totalScore);
            }
        });
        System.err.println("Result: " + totalScore);
        return totalScore.get();
    }

}
