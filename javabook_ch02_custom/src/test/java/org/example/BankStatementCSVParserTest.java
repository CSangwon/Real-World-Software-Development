package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankStatementCSVParserTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        final String line = "30-01-2017,-50,Tesco";

        final BankTransaction result = statementParser.parseFrom(line);

        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30),
                -50, "Tesco");
        final double toterance = 0.0d;

        Assertions.assertEquals(expected.getDate(), result.getDate());
        Assertions.assertEquals(expected.getAmount(), result.getAmount());
        Assertions.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void betweenAnyPeriodGetData() throws Exception {
        final List<String> lines = new ArrayList<>();
        lines.add("30-01-2017,-100,Deliveroo");
        lines.add("30-01-2017,-50,Tesco");
        lines.add("01-02-2017,6000,Salary");
        lines.add("02-02-2017,2000,Royalties");

        final String start = "01-01-2017";
        final String end = "01-02-2017";

        final List<BankTransaction> result = statementParser.parseLineFrom(lines, start, end);
        System.out.println(result);
        Assertions.assertEquals(result.size(), 2);
    }


}