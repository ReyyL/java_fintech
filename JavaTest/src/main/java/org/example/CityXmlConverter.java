package org.example;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CityXmlConverter {
    private static final Logger logger = LoggerFactory.getLogger(CityXmlConverter.class);
    private static final XmlMapper XML_MAPPER = new XmlMapper();

    public String toXML(City city) {
        try {
            logger.info("started xml serialization for slug={}", city.getSlug());
            String xml = XML_MAPPER.writeValueAsString(city);
            logger.info("xml serialized successfully");
            return xml;
        } catch (JsonProcessingException e) {
            logger.error("couldn't create xml", e);
            throw new CityProcessingException("Failed to serialize city to XML", e);
        }
    }
}
