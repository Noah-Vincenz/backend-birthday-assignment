package com.backend.birthday.assignment.service;

import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.backend.birthday.assignment.exception.CustomRuntimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileReaderTest {

    @Test
    void test_readFileFromResources_without_path() {
        List<List<String>> result = FileReader.readFileFromResources(null);
        assertEquals(5, result.size());
        assertTrue(result.containsAll(List.of(List.of("Doe", "John", "1982/10/08"),
                                              List.of("Wayne", "Bruce", "1965/01/30"),
                                              List.of("Gaga", "Lady", "1986/03/28"),
                                              List.of("Curry", "Mark", "1988/02/29"),
                                              List.of("Curry", "Marvin", "1988/01/05"))));
    }

    @Test
    void test_readFileFromResources_with_wrong_path() {
        CustomRuntimeException exceptionThrown = assertThrows(CustomRuntimeException.class, () -> FileReader.readFileFromResources("wrong_sample_input"));
        assertEquals("Could not use Jackson to read and map the file inputs into a Java List object", exceptionThrown.getMessage());
    }

    @Test
    void test_readFileFromResources_with_path() {
        URL resource = FileReader.class.getClassLoader().getResource("another_sample_input.json");
        assert resource != null;
        List<List<String>> result = FileReader.readFileFromResources(resource.getPath());
        assertEquals(5, result.size());
        assertTrue(result.containsAll(List.of(List.of("2Doe", "John", "1982/10/08"),
                                              List.of("2Wayne", "Bruce", "1965/01/30"),
                                              List.of("2Gaga", "Lady", "1986/03/28"),
                                              List.of("2Curry", "Mark", "1988/02/29"),
                                              List.of("2Curry", "Marvin", "1988/01/05"))));
    }
}
