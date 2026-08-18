package org.example;

import org.example.LinkedListHW2.CustomLinkedList;
import org.example.ParserHW1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Stream;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final List<String> CITY_NAMES = List.of("city", "city-error");

    static void main() {

        // hw1
        /*var loader = new CityJsonLoader();
        var xmlConverter = new CityXmlConverter();
        var xmlWriter = new XmlFileWriter();

        for (String name : CITY_NAMES) {
            processCity(name, loader, xmlConverter, xmlWriter);
        }*/

        // hw2
        var customLinkedList = new CustomLinkedList<>();
        customLinkedList.add(null);
        customLinkedList.add(123);
        customLinkedList.add(1234);
        customLinkedList.addAll(List.of(1, 2, 3));
        System.out.println(customLinkedList.size()); // 6

        CustomLinkedList<String> stream = Stream.of("yo", "2", "312").reduce(
                new CustomLinkedList<>(),
                (list, element) -> {
                    list.add(element);
                    return list;
                },
                (_, _) -> null
        );
        stream.get(0); // yo
        stream.get(1); // 2
        stream.get(2); // 312
    }

    // hw1
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
