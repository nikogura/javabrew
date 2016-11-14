package com.nikogura.javabrew.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.List;

/**
 * Created by nikogura on 9/24/16.
 */
@Parameters(commandDescription = "Bar Operations of Bravo")
public class CommandBravoBar {
    @Parameter(description = "Dotted name-like string of the resource operated upon")
    public List<String> nameString;
}
