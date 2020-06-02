package com.vgb;

import com.vgb.namecoding.service.cumulativescore.CumulativeScoreService;
import com.vgb.namecoding.service.cumulativescore.RankedCumulativeScoreService;
import com.vgb.namecoding.service.reader.CSVReaderService;
import com.vgb.namecoding.service.reader.ReaderService;
import com.vgb.namecoding.service.scoring.NameScoringService;
import com.vgb.namecoding.service.sorting.SortingService;
import org.apache.commons.cli.*;

import java.io.File;
import java.net.MalformedURLException;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        final Options options = prepareOptions();
        final HelpFormatter helpFormatter = new HelpFormatter();
        final CommandLineParser parser = new DefaultParser();
        try {
            System.out.println("Hello World!");
            CommandLine cmd = parser.parse(options, args);


            if (!cmd.hasOption("file")) {
                throw new MissingArgumentException("no value supplied for file path");
            }

//            ReaderService readerService, SortingService<String> sortingService, NameScoringService nameScoringService
//            ReaderService readerService = new CSVReaderService(Pattern.compile());
            SortingService<String> sortingService;
            NameScoringService nameScoringService;

            AppConfiguration configuration = new AppConfiguration();
            CumulativeScoreService scoreService = new RankedCumulativeScoreService(configuration.readerService(configuration.nameSplitterPattern()), configuration.sortingService(), configuration.nameScoringService());
            final long score = scoreService.compute(new File(cmd.getOptionValue("file")).toURI().toURL());


        } catch (ParseException | MalformedURLException e) {
            printHelp(options, helpFormatter);
            System.exit(1);
        }

    }

    private static void printHelp(Options options, HelpFormatter helpFormatter) {
        helpFormatter.printHelp("java -jar App.jar", options);
    }


    private static Options prepareOptions() {
        Options options = new Options();
        // Required options
        Option fileOption = Option.builder()
                .required()
                .longOpt("file")
                .desc("fully qualified path to the names input file")
                .hasArg()
                .build();

        options.addOption(fileOption);

        return options;
    }

}
