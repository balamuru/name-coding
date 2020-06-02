package com.vgb.namecoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class NameReaderServiceImpl implements NameReaderService {

    @Autowired
    @Qualifier("nameSplitterPattern")
    private Pattern pattern;


    public NameReaderServiceImpl(Pattern pattern) {
        this.pattern = pattern;
    }


    @Override
    public Collection<String> read(URL url) throws IOException, URISyntaxException {
//        final SortedSet<String> set = new TreeSet<>();
        final String nameString = Files.readString(Paths.get(url.toURI()));

        //transform the line into a list of names
        return Arrays.stream(pattern.split(nameString)).map(s1 -> s1.substring(1, s1.length() - 1)).collect(Collectors.toList());

    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        final NameSortingService<String> nameSortingService = new TreeMapNameSortingService();
        final NameReaderService nameReaderService = new NameReaderServiceImpl(Pattern.compile(","));
        Collection<String> sortedNames = nameReaderService.read(new File("/home/vinayb/Downloads/sample-large.txt").toURI().toURL());

        NameScoringService nameScoringService = new FirstNameScoringService();
        AtomicLong index = new AtomicLong(0);
        AtomicLong totalScore = new AtomicLong(0);

        sortedNames.iterator().forEachRemaining(name -> {
            {

                long cur = index.incrementAndGet();
                int individualScore = nameScoringService.score(name);
                long sortedScore = cur * individualScore;

                totalScore.addAndGet(sortedScore);

//                System.err.println("name : " + name + " - idx " + cur + ", ind-score: " + individualScore + ", sorted-score: " + sortedScore + ", totalScore: " + totalScore);


            }
        });
        System.err.println("Result: " + totalScore);
    }
}
