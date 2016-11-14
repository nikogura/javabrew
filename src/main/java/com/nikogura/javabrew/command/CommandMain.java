package com.nikogura.javabrew.command;

import com.beust.jcommander.Parameter;

/**
 * Created by nikogura on 9/23/16.
 */
public class CommandMain {
    @Parameter(names = "-d", description = "Debug mode")
    public boolean debug = false;

}
