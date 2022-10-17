package org.example;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser{

    private final static DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyy");

    @Override
    public BankTransaction parseFrom(final String line) {
        final String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final Double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    @Override
    public BankTransaction parseFrom(final String line, final String startDate, final String endDate) {
        final String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final LocalDate start = LocalDate.parse(startDate, DATE_PATTERN);
        final LocalDate end = LocalDate.parse(endDate, DATE_PATTERN);

        if (date.isAfter(start) && date.isBefore(end)) {
            final Double amount = Double.parseDouble(columns[1]);
            final String description = columns[2];
            return new BankTransaction(date, amount, description);
        }
        return null;
    }

    @Override
    public List<BankTransaction> parseLineFrom(final List<String> lines) {
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for (final String line : lines) {
            bankTransactions.add(parseFrom(line));
        }
        return bankTransactions;
    }

    @Override
    public List<BankTransaction> parseLineFrom(final List<String> lines, final String startDate, final String endDate) {
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for (final String line : lines) {
            BankTransaction bankTransaction = parseFrom(line, startDate, endDate);
            if (bankTransaction != null){
                bankTransactions.add(bankTransaction);
            }
        }
        return bankTransactions;
    }
}