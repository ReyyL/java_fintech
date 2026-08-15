package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final List<String> CITY_NAMES = List.of("city", "city-error");

    static void main() {
        var loader = new CityJsonLoader();
        var xmlConverter = new CityXmlConverter();
        var xmlWriter = new XmlFileWriter(Path.of("output"));

        for (String name : CITY_NAMES) {
            processCity(name, loader, xmlConverter, xmlWriter);
        }
    }

    private static void processCity(String name,
                                    CityJsonLoader loader,
                                    CityXmlConverter xmlConverter,
                                    XmlFileWriter xmlWriter) {
        try {
            City city = loader.load(name);
            String xml = xmlConverter.toXML(city);
            xmlWriter.save(xml, name);
        } catch (CityProcessingException e) {
            logger.warn("processing aborted for '{}', city data unavailable: {}", name, e.getMessage());
        }
    }
}
