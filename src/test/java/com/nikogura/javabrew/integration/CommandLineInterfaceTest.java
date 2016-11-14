package com.nikogura.javabrew.integration;

import com.nikogura.javabrew.CommandLineInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;
import static org.testng.Assert.*;

/**
 * Created by Nik Ogura on 11/13/16.
 */
public class CommandLineInterfaceTest extends AbstractTestNGSpringContextTests {
    private static Logger logger = LoggerFactory.getLogger(CommandLineInterfaceTest.class);

    private final ByteArrayOutputStream stdOut = new ByteArrayOutputStream();
    private final ByteArrayOutputStream stdErr = new ByteArrayOutputStream();
    private PrintStream oldOut = null;
    private PrintStream oldErr = null;



    @BeforeClass
    public void setup() {
        oldOut = System.out;
        oldErr = System.err;

        System.setOut(new PrintStream(stdOut));
        System.setErr(new PrintStream(stdErr));

    }

    @AfterClass
    public void teardown() {
        System.setOut(oldOut);
        System.setErr(oldErr);

    }

    @Test
    public void testVersion() {
        String[] args = {"-v"};

        // This is sufficient to test that the CLI responds to the -v flag
        Pattern semVersion = Pattern.compile("Javabrew v.*");

        // This would be more proper, but until the code is built, there isn't a real version
        // Pattern semVersion = Pattern.compile("\\d+\\.\\d+\\.\\d+");

        try {
            CommandLineInterface.main(args);

            String output = stdOut.toString();

            assertTrue(semVersion.matcher(output).find(), "-v outputs something that looks like a version. STDOUT: " + output);

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

    }
    @Test
    public void testHelp() {
        Pattern usage = Pattern.compile("Usage: .*");

        for (String arg : com.nikogura.javabrew.fixtures.CommandLineInterface.getHelpArgs()) {

            String[] args = {arg};

            try {
                CommandLineInterface.main(args);
                String output = stdOut.toString();

                assertTrue(usage.matcher(output).find(), "Help output generates usage.  STDOUT: " + output);

            } catch (Exception e) {
                e.printStackTrace();
                fail();
            }

        }

    }

}
