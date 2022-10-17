package org.example;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount(){
        double total = 0;
        for (final BankTransaction bankTransaction : bankTransactions) {
            total += bankTransaction.getAmount();
        }

        return total;
    }

    public double calculateTotalInMonth(final Month month) {
        double total = 0;

        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                total += bankTransaction.getAmount();
            }
        }

        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;

        for (final BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDescription().equals(category)) {
                total += bankTransaction.getAmount();
            }
        }

        return total;
    }

    public int[] calculateAllMonthCount(){
        int[] result = new int[12];

        for (final BankTransaction bankTransaction : bankTransactions) {
            result[bankTransaction.getDate().getMonth().getValue() - 1] += 1;
        }
        return result;
    }

    public Map<String, Integer> calculateAllCategoryCount(){
        Map<String, Integer> result = new HashMap<>();

        for (final BankTransaction bankTransaction : bankTransactions) {
            String description = bankTransaction.getDescription();
            if (result.containsKey(description)) {
                result.put(description, result.get(description) + 1);
            } else {
                result.put(description, 1);
            }
        }

        return result;
    }



    public List<BankTransaction> calculateMaxAmountForPeriod() {
        List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (result.isEmpty()) {
                result.add(bankTransaction);
            }

            if (result.get(0).getAmount() < bankTransaction.getAmount()){
                result.clear();
                result.add(bankTransaction);
            } else if (result.get(0).getAmount() == bankTransaction.getAmount()){
                result.add(bankTransaction);
            }

        }
        return result;
    }

    public List<BankTransaction> calculateMinAmountForPeriod() {
        List<BankTransaction> result = new ArrayList<>();
        for (final BankTransaction bankTransaction : bankTransactions) {
            if (result.isEmpty()) {
                result.add(bankTransaction);
            }

            if (result.get(0).getAmount() > bankTransaction.getAmount()){
                result.clear();
                result.add(bankTransaction);
            } else if (result.get(0).getAmount() == bankTransaction.getAmount()){
                result.add(bankTransaction);
            }

        }
        return result;
    }


}
