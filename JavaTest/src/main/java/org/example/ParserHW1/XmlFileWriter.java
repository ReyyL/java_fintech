package org.example.ParserHW1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class XmlFileWriter {
    private static final Logger logger = LoggerFactory.getLogger(XmlFileWriter.class);

    public void save(String xml, String fileName) {
        var directory = Path.of("output");
        Path path = directory.resolve(fileName + ".xml");

        try {
            Files.createDirectories(directory);
            Files.writeString(path, xml);
            logger.info("xml file {} saved successfully", path);
        } catch (IOException e) {
            logger.error("couldn't save xml file {}", path, e);
            throw new CityProcessingException("Failed to save " + path, e);
        }
    }
}