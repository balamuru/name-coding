package com.vgb.namecoding.service.cumulativescore;

import com.vgb.namecoding.service.reader.ReaderService;
import com.vgb.namecoding.service.scoring.NameScoringService;
import com.vgb.namecoding.service.sorting.SortingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This implementation of @{@link FileScoreService} works by multiplying the rank of
 * a name with the score of the name itself
 */
public class RankedFileScoreService implements FileScoreService {

    private static Logger logger = LoggerFactory.getLogger(RankedFileScoreService.class);

    private ReaderService readerService;
    private SortingService<String> sortingService;
    private NameScoringService nameScoringService;

    /**
     * Constructor
     * @param readerService reader service
     * @param sortingService sorting service
     * @param nameScoringService scoring service
     */
    public RankedFileScoreService(ReaderService readerService, SortingService<String> sortingService, NameScoringService nameScoringService) {
        this.readerService = readerService;
        this.sortingService = sortingService;
        this.nameScoringService = nameScoringService;

    }

    @Override
    public long compute(URL url) throws Exception {
        final AtomicLong index = new AtomicLong(0);
        final AtomicLong totalScore = new AtomicLong(0);
        //1. read data
        //2. sort data

        logger.info("Launching computation");
        sortingService.sort(readerService.read(url)).iterator().forEachRemaining(name -> {
            {
                if (logger.isDebugEnabled()) {
                    logger.debug("Going to score word: " + name);
                }

                //for each item of the sorted dataset, .. calculate its sorted score
                final long sortedNameScore = index.incrementAndGet() * nameScoringService.score(name);
                //accumulate the total score
                totalScore.addAndGet(sortedNameScore);
           }
        });

        return totalScore.get();
    }

}
