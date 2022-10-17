package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainApplication {
    public static void main(final String... args) throws IOException {

        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer(args[0]);
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        bankStatementAnalyzer.analyze(bankStatementParser);
        bankStatementAnalyzer.analyze(args[1], args[2], bankStatementParser);
    }
}
