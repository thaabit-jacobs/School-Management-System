package net.school.service;

import net.school.doa.accountant.AccountantDoa;
import net.school.doa.accountant.AccountantDoaImpl;
import net.school.doa.admin.AdminDoa;
import net.school.doa.admin.AdminDoaImpl;
import net.school.model.Accountant;
import net.school.model.Admin;

import java.util.Collections;
import java.util.List;

public class AccountantService {
    private final AccountantDoa accountantDoa = new AccountantDoaImpl();
    private final static AccountantService instance = new AccountantService();

    private AccountantService(){}

    public static synchronized AccountantService getInstance(){
        return instance;
    }

    public synchronized boolean insertAccountant(Accountant accountant){
        return accountantDoa.insertAccountant(accountant);
    }

    public synchronized Accountant selectAccountant(int id){
        return accountantDoa.selectAccountant(id);
    }

    public synchronized List<Accountant> selectAllAccountants(){
        return accountantDoa.selectAllAccountants();
    }

    public synchronized boolean deleteAccountant(int id){
        return accountantDoa.deleteAccountant(id);
    }


    public synchronized int getUniquerId(){
        List<Accountant> accountants = accountantDoa.selectAllAccountants();
        Collections.sort(accountants);

        if(accountants.size()==0)
            return 1;

        int uniqueId = accountants.get(accountants.size() - 1).getId() + 1;

        return uniqueId;
    }
}
