package com.nikogura.javabrew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

/**
 * Created by Nik Ogura on 11/13/16.
 */
@SpringBootApplication
public class JavabrewController {
    @Autowired
    Properties props;
}
