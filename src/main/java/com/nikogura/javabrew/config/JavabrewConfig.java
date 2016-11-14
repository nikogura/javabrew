package com.nikogura.javabrew.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.Properties;

/**
 * Created by nikogura on 8/29/16.
 */
@Configuration
public class JavabrewConfig {
    Logger logger = LoggerFactory.getLogger(JavabrewConfig.class);

    @Bean
    public Properties props() {
        Properties props = new Properties();
        Properties privProps = new Properties();

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        InputStream stream = null;

        try {
            stream = loader.getResourceAsStream("application.properties");
            props.load(stream);

            String privatePropsPath = props.getProperty("private.properties.path") + "/" + props.getProperty("private.properties.name");

            logger.trace("Private Props Path: "+ privatePropsPath);

            File f = new File(privatePropsPath).getAbsoluteFile();

            if (f != null) {
                FileInputStream fis = new FileInputStream(f);

                if (fis != null) {
                    privProps.load(fis);
                }
            }

        } catch (FileNotFoundException e) {
            logger.trace("No Private Properties File");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        for (Object propName: privProps.keySet()) {
            props.put(propName, privProps.get(propName));

        }

        return props;
    }
}
