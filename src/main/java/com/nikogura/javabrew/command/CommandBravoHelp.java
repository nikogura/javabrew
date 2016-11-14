package com.nikogura.javabrew.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.List;

/**
 * Created by nikogura on 9/24/16.
 */
@Parameters(commandDescription = "Bravo Help Message")
public class CommandBravoHelp {
    @Parameter
    public List<String> commands;
}
