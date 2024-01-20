package com.backend.birthday.assignment.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import com.backend.birthday.assignment.exception.CustomRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static com.backend.birthday.assignment.util.Constants.DEFAULT_JSON_RESOURCE_FILE_NAME;

public final class FileReader {

    private static final ObjectMapper mapper = new ObjectMapper();

    private FileReader() {
        throw new IllegalStateException("Utility class");
    }

    // TODO:
    //  create Person class and return List<Person>
    //  use input stream that returns List<List<String>> (or read by line)
    //  use map list<String> check if size = 3, if not then log error and return null, else return new Person(list.get(0), list.get(1), new LocalDate(list.get(2), DateTimeFormatter))
    //  filter (item -> item != null)
    //  collect(Collectors.toList)
    public static List<List<String>> readFileFromResources(String path) {
        try {
            if (path != null) {
                return mapper.readValue(Paths.get(new File(path).toURI()).toFile(), List.class);
            } else { // no local file path given, using default path
                URL defaultResource = FileReader.class.getClassLoader().getResource(DEFAULT_JSON_RESOURCE_FILE_NAME);
                if (defaultResource == null) {
                    String message = "Could not locate default json resource file with name " + DEFAULT_JSON_RESOURCE_FILE_NAME;
                    System.out.println(message);
                    throw new CustomRuntimeException(message, "Birthday.filereader.default.path.invalid");
                }
                if (defaultResource.toString().contains("SNAPSHOT.jar")) { // we are running packaged jar, need to update path
                    String jarPath = defaultResource.getPath();
                    // resources in target folder are contained inside /target/classes/
                    URI uri = URI.create(jarPath.split("/target/")[0] + "/target/classes/" + DEFAULT_JSON_RESOURCE_FILE_NAME);
                    defaultResource = new File(uri).toURI().toURL();
                }
                // convert JSON file to list using Jackson
                return mapper.readValue(Paths.get(defaultResource.toURI()).toFile(), List.class);
            }
        } catch (URISyntaxException e) {
            String message = "Could not create URI from default json resource file URL";
            System.out.println(message);
            throw new CustomRuntimeException(message, "Birthday.filereader.uri.syntax");
        } catch (IOException e) {
            String message = "Could not use Jackson to read and map the file inputs into a Java List object";
            System.out.println(message);
            throw new CustomRuntimeException(message, "Birthday.filereader.jackson.mapper.error");
        }
    }
}
