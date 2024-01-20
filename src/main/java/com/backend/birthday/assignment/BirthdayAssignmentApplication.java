package com.backend.birthday.assignment;

import com.backend.birthday.assignment.service.BirthdayService;

public class BirthdayAssignmentApplication {

    public static void main (String[] args) {
        BirthdayService service = new BirthdayService();
        String filePath = null;
        if (args.length != 0) {
            filePath = args[0];
        }
        service.printTodaysBirthdays(filePath);
    }
}
