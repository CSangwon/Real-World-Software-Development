package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;


public class BankStatementAnalyzer {

    private static final String RESOURCES = "src/main/resources/";
    private final Path path;
    private List<String> lines;

    public BankStatementAnalyzer(String fileName) throws IOException {
        this.path = Paths.get(RESOURCES + fileName);
        this.lines = Files.readAllLines(path);
    }

    public void analyze(final BankStatementParser bankStatementParser) throws IOException {

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLineFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
    }

    public void analyze(final String startDate, final String endDate, final BankStatementParser bankStatementParser) throws IOException {

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLineFrom(lines, startDate, endDate);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummaryPeriod(bankStatementProcessor, startDate, endDate);

        collectSummaryHistogram(bankStatementProcessor);
    }
    private static void collectSummary(BankStatementProcessor bankStatementProcessor) {
        System.out.println("The total for all transactions is " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("The total for transactions in January is " +
                bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("The total for transactions in February is " +
                bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));

        System.out.println("The total salary received is " +
                bankStatementProcessor.calculateTotalForCategory("Salary"));
        System.out.println("======================================================");
    }

    private static void collectSummaryPeriod(BankStatementProcessor bankStatementProcessor,
                                             String startDate, String endDate) {
        System.out.println("Between " + startDate + " And " + endDate + " Max Amount Data : "+
                bankStatementProcessor.calculateMaxAmountForPeriod());

        System.out.println("Between " + startDate + " And " + endDate + " Min Amount Data : "+
                bankStatementProcessor.calculateMinAmountForPeriod());

        System.out.println("======================================================");
    }

    private static void collectSummaryHistogram(BankStatementProcessor bankStatementProcessor) {
        int[] calculateAllMonthCount = bankStatementProcessor.calculateAllMonthCount();
        for (int i = 0 ; i < calculateAllMonthCount.length; i ++){
            String month = new DateFormatSymbols().getMonths()[i];
            System.out.print(month + " : ");
            for (int j = 0 ; j < calculateAllMonthCount[i]; j ++ ){
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
        Map<String, Integer> calculateAllCategoryCount = bankStatementProcessor.calculateAllCategoryCount();
        for(String keySet : calculateAllCategoryCount.keySet()){
            System.out.println(keySet + " : " + calculateAllCategoryCount.get(keySet));
        }
    }
}