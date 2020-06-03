package com.vgb.namecoding.service.reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 * Reads information from a file, splits based on a configurable pattern
 * and the outputs a sorted collection
 */
public class DelimitedSortingReaderService implements ReaderService {

    private static Logger logger = LoggerFactory.getLogger(DelimitedSortingReaderService.class);
    private Pattern pattern;


    /**
     * Constructor
     * @param pattern delimiter pattern
     */
    public DelimitedSortingReaderService(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public Collection<String> read(URL url) throws IOException, URISyntaxException {
        logger.info("Reading file from " + url);
        //read file into a single line String
        final String nameString = Files.readString(Paths.get(url.toURI()));
        logger.info("Data read complete from " + url);
        //define a collector backed by a sorted data structure
        final Collector<String, ?, Collection<String>> nameSetCollector = Collectors.toCollection(TreeSet::new);
        //transform the line of text into a list of names using the pattern
        return Arrays.stream(pattern.split(nameString)).map(s1 -> {
            //trim outside the quotes and inside the quotes too
            final String t1 = s1.trim();
            return t1.substring(1, t1.length() - 1).trim();
        }).filter(s -> !s.trim().isEmpty()).collect(nameSetCollector);

    }

}
