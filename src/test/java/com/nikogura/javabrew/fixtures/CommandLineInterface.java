package com.nikogura.javabrew.fixtures;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikogura on 10/21/16.
 */
public class CommandLineInterface {
    public static List<String> getHelpArgs() {
        List<String> args = new ArrayList<>();

        args.add("-h");
        args.add("--help");
        args.add("help");

        return args;
    }
}
