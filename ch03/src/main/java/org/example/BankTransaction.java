package org.example;

import java.time.LocalDate;
import java.util.Objects;

public class BankTransaction {

    private final LocalDate date;
    private final double amount;
    private final String description;

    public BankTransaction(LocalDate date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "date=" + date +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, description);
        /*
        Hashcode는 객체를 식벽하는 Integer 값이다. 객체가 갖고 ㅇㅆ는 데이터를 어떤 알고리즘에 적용하여 계산된 정수 값이다.
        이를 사용하는 이유중에 하나는, 객체를 비교할 때 드는 비용을 낮추기 위해서이다.
        자바에서 2개의 객체가 같은지 비교할 때 equals()를 사용하는데 여러 객체를 비교할 때 이를 사용하면
        Integer를 비교하는 것에 비애 많은 시간이 소요된다.
        Java에서 hashcode는 Integer이며, hashcode를 이용하여 객체를 비교하면 equals()를 이용하는 것보다 시간이 단축된다.

        두 객체의 HashCode가 다르면 두 객체는 다른 것이다. 따라서 객체를 비교할 때 먼저 hashcode를 비교하면
        절대 같을 수 없는 경우를 빠른연산으로 확인 할 수 있다.
        그리고나서 hashcode가 같을 때 equals()로 두 객체가 같은지 비교할 수있다.

         */
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BankTransaction that = (BankTransaction) obj;
        return Double.compare(that.amount, this.amount) == 0 &&
                this.date.equals(that.date) &&
                this.description.equals(that.description);

    }
}
