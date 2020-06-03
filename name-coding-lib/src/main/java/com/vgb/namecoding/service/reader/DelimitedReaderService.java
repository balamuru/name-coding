package com.vgb.namecoding.service.reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Reads information from a file, splits based on a configuratble pattern
 * and the outputs an unsorted collection
 */
public class DelimitedReaderService implements ReaderService {

    private Pattern pattern;


    /**
     * Constructor
     * @param pattern delimiter pattern
     */
    public DelimitedReaderService(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public Collection<String> read(URL url) throws IOException, URISyntaxException {
        //read file into a single line String
        final String nameString = Files.readString(Paths.get(url.toURI()));

        //transform the line of text into a list of names using the pattern
        return Arrays.stream(pattern.split(nameString)).map(s1 -> s1.substring(1, s1.length() - 1)).collect(Collectors.toList());
    }

}
