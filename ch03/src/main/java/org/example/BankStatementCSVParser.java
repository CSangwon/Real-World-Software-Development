package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser{

    /**
     * 예외처리
     * 1. 데이터를 적절하게 파싱하지 못한다면?
     * 2. 입출금 내역을 포함하는 CSV파일을 읽을 수 없다면?
     * 3. 응용프로그램을 실행하는 하드웨어에 램이나 저장공간이 부족하다면?
     */

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
    public List<BankTransaction> parseLineFrom(final List<String> lines) {
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for (final String line : lines) {
            bankTransactions.add(parseFrom(line));
        }
        return bankTransactions;
    }
}