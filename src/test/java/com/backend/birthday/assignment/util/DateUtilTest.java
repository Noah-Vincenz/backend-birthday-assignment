package com.backend.birthday.assignment.util;

import java.time.LocalDate;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilTest {

    private static final LocalDate noLeapYearDate = LocalDate.of(2022, 2, 28);

    @ParameterizedTest
    @MethodSource("sameDayOfYearParams")
    void test_sameDayOfYear(LocalDate firstDateInput, LocalDate secondDateInput, boolean expectedResult) {
        assertEquals(expectedResult, DateUtil.sameDayOfYear(firstDateInput, secondDateInput));
    }

    @ParameterizedTest
    @MethodSource("sameBirthdayParams")
    void test_sameBirthday(LocalDate todayInput, LocalDate birthdayInput, boolean expectedResult) {
        assertEquals(expectedResult, DateUtil.sameBirthday(todayInput, birthdayInput));
    }

    private static Stream<Arguments> sameDayOfYearParams() {
        return Stream.concat(commonArguments(), Stream.of(
                Arguments.of(noLeapYearDate, LocalDate.of(1988, 2, 29), false) // only birthday is leap year, today is 28.02 and birthday is 29.02
        ));
    }

    private static Stream<Arguments> sameBirthdayParams() {
        return Stream.concat(commonArguments(), Stream.of(
                Arguments.of(noLeapYearDate, LocalDate.of(1988, 2, 29), true) // only birthday is leap year, today is 28.02 and birthday is 29.02
        ));
    }

    private static Stream<Arguments> commonArguments() {
        return Stream.of(
                // no leap years
                Arguments.of(noLeapYearDate, noLeapYearDate, true), // same date
                Arguments.of(noLeapYearDate.minusDays(1), noLeapYearDate, false), // one day difference
                Arguments.of(noLeapYearDate.plusDays(1), noLeapYearDate, false), // one day difference
                Arguments.of(noLeapYearDate, noLeapYearDate.minusMonths(1), false), // one month difference
                Arguments.of(noLeapYearDate, noLeapYearDate.plusMonths(1), false), // one month difference
                Arguments.of(noLeapYearDate, noLeapYearDate.minusYears(1), true), // one year difference
                Arguments.of(noLeapYearDate, noLeapYearDate.plusYears(1), true), // one year difference
                // two leap years
                Arguments.of(LocalDate.of(1992, 2, 29), LocalDate.of(1988, 2, 29), true), // two leap years, both dates are 29.02
                Arguments.of(LocalDate.of(1992, 2, 28), LocalDate.of(1988, 2, 28), true), // two leap years, both dates are 28.02
                Arguments.of(LocalDate.of(1992, 2, 28), LocalDate.of(1988, 2, 29), false), // two leap years, today is 28.02 and birthday is 29.02
                Arguments.of(LocalDate.of(1992, 2, 29), LocalDate.of(1988, 2, 28), false), // two leap years, today is 29.02 and birthday is 28.02
                // birthday is during leap year
                Arguments.of(noLeapYearDate, LocalDate.of(1988, 2, 28), true), // birthday is leap year, today is 28.02 and birthday is 28.02
                // today is during leap year
                Arguments.of(LocalDate.of(1988, 2, 28), noLeapYearDate, true), // today is leap year, today is 28.02 and birthday is 28.02
                Arguments.of(LocalDate.of(1988, 2, 29), noLeapYearDate, false), // today is leap year, today is 29.02 and birthday is 28.02
                Arguments.of(LocalDate.of(1988, 5, 8), LocalDate.of(1989, 5, 8), true), // today is leap year, today is 08.05 and birthday is 08.05
                Arguments.of(LocalDate.of(1988, 2, 29), LocalDate.of(1989, 2, 28), false) // today is leap year, today is 29.02 and birthday is 28.02
        );
    }
}
