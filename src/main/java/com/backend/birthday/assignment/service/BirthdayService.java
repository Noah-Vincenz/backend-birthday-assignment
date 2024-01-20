package com.backend.birthday.assignment.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.backend.birthday.assignment.util.DateUtil;

import static com.backend.birthday.assignment.util.Constants.DATE_TIME_PATTERN;

public class BirthdayService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    public void printTodaysBirthdays(String filePath) {
        LocalDate today = LocalDate.now();
        List<List<String>> listOfPeople = FileReader.readFileFromResources(filePath);
        listOfPeople.forEach(person -> {
            String birthdayStr = person.get(2); // assumption is that the birthday comes as 3 element in the list TODO: remove this and move error handling to where we create Person object
            LocalDate birthday = LocalDate.parse(birthdayStr, formatter);
            if (DateUtil.sameBirthday(today, birthday)) System.out.println(person);
        });
    }
}
