package com.vgb.namecoding.service.cumulativescore;

import com.vgb.namecoding.service.reader.ReaderService;
import com.vgb.namecoding.service.scoring.NameScoringService;
import com.vgb.namecoding.service.sorting.SortingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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
        //perform the following in a single expression
        //1. read data
        //2. sort data
        logger.info("Launching computation");
        final AtomicInteger idx = new AtomicInteger(0);
        return sortingService.sort(readerService.read(url)).stream()
                .map(s -> new SortedNameData(s, idx.incrementAndGet()))
                .map(sortedNameData -> Long.valueOf(sortedNameData.getRank() * nameScoringService.score(sortedNameData.getWord())))
                .collect(Collectors.summingLong(Long::longValue));
    }

    private static  class SortedNameData {
        private final String word;
        private final int rank;

        public SortedNameData(String word, int rank) {
            this.word = word;
            this.rank = rank;
        }

        public String getWord() {
            return word;
        }

        public int getRank() {
            return rank;
        }
    }
}
