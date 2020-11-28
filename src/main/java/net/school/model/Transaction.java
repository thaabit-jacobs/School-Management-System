package net.school.model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private String transactionType;
    private double transactionAmount;
    private String transactionStatus;
    private LocalDateTime dateCreated;
    private int studentId;

    public Transaction(){

    }

    public Transaction(int id, String transactionType, double transactionAmount, String transactionStatus, LocalDateTime dateCreated, int studentId) {
        if (id < 0)
            throw new IllegalArgumentException("invalid id supplied " + id);
        this.id = id;

        this.transactionType = transactionType;

        this.transactionAmount = transactionAmount;

        this.transactionStatus = transactionStatus;

        if(dateCreated == null)
            throw new IllegalArgumentException("invalid dateCreated supplied");
        this.dateCreated = dateCreated;

        if (studentId < 0)
            throw new IllegalArgumentException("invalid accountId supplied " + studentId);
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public Transaction setId(int id){
        if (id < 0)
            throw new IllegalArgumentException("invalid id supplied");
        this.id = id;

        return this;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Transaction setTransactionType(String transactioType){
        if(transactioType == null || transactioType.trim().length() == 0)
            throw new IllegalArgumentException("invalid transactioType supplied");
        this.transactionType = transactioType;

        return this;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public Transaction setTransactionAmount(double transactionAmount){
        this.transactionAmount = transactionAmount;

        return this;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public Transaction setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;

        return this;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public Transaction setDateCreated(LocalDateTime dateCreated){
        if(dateCreated == null)
            throw new IllegalArgumentException("invalid dateCreated supplied");
        this.dateCreated = dateCreated;

        return this;
    }

    public int getStudentId() {
        return studentId;
    }

    public Transaction setStudentId(int accountId){
        if (accountId < 0)
            throw new IllegalArgumentException("invalid accountId supplied");
        this.studentId = accountId;

        return this;
    }

    public Transaction build(){
        return new Transaction(id, transactionType, transactionAmount, transactionStatus, dateCreated, studentId);
    }

    @Override
    public String toString() {
        return "Transaction{ \n" +
                " id=" + id +
                ", \n transactioType='" + transactionType + '\'' +
                ", \n transactionAmount=" + transactionAmount +
                ", \n isSuccess=" + transactionStatus +
                ", \n dateCreated=" + dateCreated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (! (o instanceof  Transaction))
            return false;

        Transaction transaction = (Transaction)o;
        return this.id == transaction.id && this.dateCreated == transaction.dateCreated;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
