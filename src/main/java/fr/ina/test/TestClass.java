package fr.ina.test;

import org.apache.commons.cli.*;

public class TestClass {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption(Option.builder("h").longOpt("host").hasArg().argName("host name").desc("Host IP address").build());
        options.addOption(Option.builder("p").longOpt("port").hasArg().argName("port number").desc("Host port number").type(Integer.class).build());
        options.addOption(Option.builder("?").longOpt("help").desc("Print usage information").build());
        CommandLine cmdline;
        try {
            CommandLineParser parser = new DefaultParser();
            cmdline = parser.parse(options, args);

            if (cmdline.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("testing argments project", options);
                return;
            }

            if (!cmdline.hasOption("host")) {
                System.err.println("Host must be specified with the 'host' option");
                return;
            }
            if (!cmdline.hasOption("port")) {
                System.err.println("Port number must be specified with the 'port' option");
                return;
            }

        } catch (ParseException exp) {
            System.err.println("Parsing command line failed.  Reason: " + exp.getMessage());
            return;
        }

        String host = cmdline.getOptionValue("host");
        Integer port = Integer.valueOf(cmdline.getOptionValue("port"));

        System.out.println("Received host: " + host);
        System.out.println("Received port: " + port);
    }
}
