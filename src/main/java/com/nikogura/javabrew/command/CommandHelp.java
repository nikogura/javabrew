package com.nikogura.javabrew.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.List;

/**
 * Created by nikogura on 9/23/16.
 */
@Parameters(commandDescription = "Displays this message")
public class CommandHelp {
    @Parameter
    public List<String> commands;

}
