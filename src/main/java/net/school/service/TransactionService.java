package net.school.service;

import net.school.doa.student.StudentDoa;
import net.school.doa.student.StudentDoaImpl;
import net.school.doa.transacs.TransactionDoa;
import net.school.doa.transacs.TransactionDoaImpl;
import net.school.model.Student;
import net.school.model.Transaction;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TransactionService {
    private final TransactionDoa transactionDoa = new TransactionDoaImpl();
    private final static TransactionService instance = new TransactionService();

    private TransactionService(){}

    public static synchronized TransactionService getInstance(){
        return instance;
    }

    public synchronized boolean insertTransaction(Transaction transaction){
        return transactionDoa.insertTransaction(transaction);
    }

    public synchronized Transaction selectTransaction(int id){
        return transactionDoa.selectTransaction(id);
    }

    public synchronized List<Transaction> selectAllTransactions(){
        return transactionDoa.selectAllTransactions();
    }

    public synchronized List<Transaction> selectAllTransactionsForStudents(int id){
        return transactionDoa.selectAllTransactionsForStudent(id);
    }

    public synchronized boolean deleteAllTransactionsForStudent(int id){
        return transactionDoa.deleteAllTransactionsForStudent(id);
    }

    public int getUniqueId(){
        List<Transaction> transactions = selectAllTransactions();
        Comparator<Transaction> byId = (a, b) -> a.getId() - b.getId();
        Collections.sort(transactions, byId);

        if(transactions.size()==0)
            return 1;

        int uniqueId = transactions.get(transactions.size() - 1).getId() + 1;

        return uniqueId;
    }
}
