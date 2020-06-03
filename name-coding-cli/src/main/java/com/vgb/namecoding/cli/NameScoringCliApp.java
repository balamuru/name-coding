package com.vgb.namecoding.cli;

import com.vgb.namecoding.service.cumulativescore.CumulativeScoreService;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.MalformedURLException;


/**
 * CLI Application
 */
@Component
public class NameScoringCliApp {

    @Autowired
    private CumulativeScoreService scoreService;

    /**
     * Constructor
     * @param scoreService score service
     */
    public NameScoringCliApp(CumulativeScoreService scoreService) {
        this.scoreService = scoreService;
    }

    /**
     * Main method
     * @param args command line args
     * @throws Exception if something unexpected goes wrong
     */
    public static void main(String[] args) throws Exception {
        final Options options = prepareOptions();
        final HelpFormatter helpFormatter = new HelpFormatter();
        try {
            final ApplicationContext ctx = new AnnotationConfigApplicationContext("com.vgb");
            final CommandLine cmd = new DefaultParser().parse(options, args);
            if (!cmd.hasOption("file")) {
                throw new MissingArgumentException("no value supplied for file path");
            }

            //invoke the score calculation
            final long score = ctx.getBean(NameScoringCliApp.class).scoreService.compute(new File(cmd.getOptionValue("file")).toURI().toURL());
            System.out.println("Input file: " + cmd.getOptionValue("file"));
            System.out.println("Total score: " + score);

        } catch (ParseException | MalformedURLException e) {
            printHelp(options, helpFormatter);
        }

    }

    private static void printHelp(Options options, HelpFormatter helpFormatter) {
        helpFormatter.printHelp("java -jar <cli-jar-name>.jar", options);
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
