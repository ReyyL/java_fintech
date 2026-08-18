package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Data
class Coords {
    private Double lat;
    private Double lon;
}

@Data
class City {
    private String slug;
    private Coords coords;
}

public class CityJsonLoader {
    private static final Logger logger = LoggerFactory.getLogger(CityJsonLoader.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public City load(String name) {
        String resourceName = name + ".json";
        logger.debug("looking for json file {}", resourceName);

        try (InputStream inputStream =
                     getClass().getClassLoader().getResourceAsStream(resourceName)) {

            if (inputStream == null) {
                logger.warn("json file {} not found", resourceName);
                throw new CityProcessingException(
                        "Resource not found: " + resourceName,
                        new FileNotFoundException(resourceName));
            }

            logger.info("started json parsing");
            City city = OBJECT_MAPPER.readValue(inputStream, City.class);
            logger.info("json parsed successfully: {}", city.getSlug());
            return city;

        } catch (IOException e) {
            logger.error("couldn't parse json {}", resourceName, e);
            throw new CityProcessingException("Failed to parse " + resourceName, e);
        }
    }
}


