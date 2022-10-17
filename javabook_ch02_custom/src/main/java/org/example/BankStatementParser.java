package org.example;

import java.util.List;

public interface BankStatementParser {
    BankTransaction parseFrom(String line);
    BankTransaction parseFrom(String line, String startDate, String endDate);

    List<BankTransaction> parseLineFrom(List<String> lines);
    List<BankTransaction> parseLineFrom(List<String> lines, String startDate, String endDate);
}
