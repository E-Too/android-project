package com.example.too.paperchaser;

/**
 * Created by too on 11/23/17.
 */

public class Transaction {
    private String transactionId;
    private String transactionAmount;
    private String transactionDate;

    public Transaction(){

    }

    public Transaction(String transactionId,String transactionDate, String transactionAmount ) {
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
