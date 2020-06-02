package com.vgb;

import com.vgb.namecoding.service.reader.CSVReaderService;
import com.vgb.namecoding.service.reader.ReaderService;
import com.vgb.namecoding.service.scoring.FirstNameScoringService;
import com.vgb.namecoding.service.scoring.NameScoringService;
import com.vgb.namecoding.service.sorting.SortingService;
import com.vgb.namecoding.service.sorting.TreeMapSortingService;

import java.util.regex.Pattern;

public class AppConfiguration {

    public Pattern nameSplitterPattern() {
        return Pattern.compile(",");
    }

    public ReaderService readerService(Pattern pattern) {
        return new CSVReaderService(pattern);
    }

    public SortingService<String> sortingService() {
        return new TreeMapSortingService();
    }

    public NameScoringService nameScoringService() {
        return new FirstNameScoringService();
    }


}
