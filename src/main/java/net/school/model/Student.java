package net.school.model;

public class Student extends Academic {
    private double feesBalance;

    public double getFeesBalance() {
        return feesBalance;
    }

    public Person setFeesBalance(double feesBalance) {
        this.feesBalance = feesBalance;
        return this;
    }
}
