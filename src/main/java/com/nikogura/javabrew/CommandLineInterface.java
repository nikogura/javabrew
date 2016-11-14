package com.nikogura.javabrew;

import com.beust.jcommander.JCommander;
import com.nikogura.javabrew.command.*;
import com.nikogura.javabrew.config.JavabrewConfig;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Properties;

/**
 * Created by Nik Ogura on 11/13/16.
 */
public class CommandLineInterface {
    public static Properties props;

    public static void main(String[] args) throws Exception {

        /*
            The following disables the default INFO logging at spring startup
         */

        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(ch.qos.logback.classic.Level.ERROR);

        // Load the
        ApplicationContext ctx = new AnnotationConfigApplicationContext(JavabrewConfig.class);

        props = ctx.getBean("props", Properties.class);

        CommandMain cm = new CommandMain();
        JCommander jc = new JCommander(cm);

        CommandPropDump propdump = new CommandPropDump();
        jc.addCommand("propdump", propdump);

        CommandHelp help = new CommandHelp();
        jc.addCommand("help", help, "-h", "--help");

        CommandVersion version = new CommandVersion();
        jc.addCommand("version", version, "-v", "--version");

        CommandBravo bravo = new CommandBravo();
        jc.addCommand("bravo", bravo);

        if (args == null || args.length == 0) {
            String[] newargs = {"-h"};
            jc.parse(newargs);
        } else {
            jc.parse(args);
        }

        String command = jc.getParsedCommand();

        switch (command) {
            case "help":
                jc.usage();
                return;

            case "version":
                System.out.println("Javabrew v" + props.getProperty("version"));
                return;

            case "propdump":
                dumpProperties(props);

                return;

            case "bravo":
                CommandBravoHelp bravohelp = new CommandBravoHelp();
                jc.addCommand("help", bravohelp);

                CommandBravoFoo bravofoo = new CommandBravoFoo();
                jc.addCommand("foo", bravofoo);

                CommandBravoBar bravobar = new CommandBravoBar();
                jc.addCommand("bar", bravobar);

                CommandBravoBaz bravobaz = new CommandBravoBaz();
                jc.addCommand("baz", bravobaz);

                if (bravo.commands == null) {
                    System.out.println("Bravo requires options");
                    return;
                }

                jc.parse(bravo.commands.toArray(new String[0]));
                String subcommand = jc.getParsedCommand().toLowerCase();
                System.out.println("Command: " + subcommand);
                String nameString = null;

                switch (subcommand) {
                    case "help":
                        jc.usage("bravo");
                        break;
                    case "foo":
                        System.out.println("Doing Foo");
                        break;
                    case "bar":
                        System.out.println("Doing Bar");
                        break;
                    case "baz":
                        System.out.println("Doing Baz");
                        break;
                }

                return;
        }
    }

    public static void dumpProperties(Properties props) {
        System.out.println("DoctorEvil");

        props.list(System.out);
    }
}
