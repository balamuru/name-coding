package com.vgb.namecoding.service;

import com.vgb.namecoding.service.namescoring.FirstNameScoringService;
import com.vgb.namecoding.service.namescoring.NameScoringService;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortedNamesProvider implements NamesProvider{
    private Pattern pattern;

    public SortedNamesProvider(Pattern pattern) {
        this.pattern = pattern;
    }

    public SortedNamesProvider() {
        this(Pattern.compile(","));
    }

    public Set<String> init(URL url) throws IOException, URISyntaxException {
        final SortedSet<String> set = new TreeSet<>();
        final String nameString = Files.readString(Paths.get(url.toURI()));

        //transform the line into a list of names
        final List<String> nameList = Arrays.stream(pattern.split(nameString)).map(s1 -> s1.substring(1, s1.length() - 1)).collect(Collectors.toList());

        //add to a sorted data structure. Names will be inserted in sorted order
        nameList.forEach(s1 -> set.add(s1));

        return Collections.unmodifiableSet(set);

    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        SortedNamesProvider nameSortingService = new SortedNamesProvider();


//        Paths.get("https://gist.githubusercontent.com/balamuru/53914d825e14a77286a5224e0dc27568/raw/cd91126cbb5b67992011c2ee9628bbbed0b18ada/gistfile1.txt")

//        https://gist.githubusercontent.com/balamuru/53914d825e14a77286a5224e0dc27568/raw/cd91126cbb5b67992011c2ee9628bbbed0b18ada/gistfile1.txt
        Set<String> sortedNames = nameSortingService.init(new File("/home/vinayb/Downloads/sample-large.txt").toURI().toURL());
//        nameSortingService.init(new URL("https://gist.githubusercontent.com/balamuru/53914d825e14a77286a5224e0dc27568/raw/cd91126cbb5b67992011c2ee9628bbbed0b18ada/gistfile1.txt"));



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
