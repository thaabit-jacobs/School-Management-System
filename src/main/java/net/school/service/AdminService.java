package net.school.service;

import net.school.doa.admin.AdminDoa;
import net.school.doa.admin.AdminDoaImpl;
import net.school.model.Admin;

import java.util.Collections;
import java.util.List;

public class AdminService {

    private final AdminDoa adminDoa = new AdminDoaImpl();
    private final static AdminService instance = new AdminService();

    private AdminService(){}

    public static synchronized AdminService getInstance(){
        return instance;
    }

    public synchronized boolean insertAdmin(Admin admin){
        return adminDoa.insertAdmin(admin);
    }

    public synchronized Admin selectAdmin(int id){
        return adminDoa.selectAdmin(id);
    }

    public synchronized List<Admin> selectAllAdmins(){
        return adminDoa.selectAllAdmins();
    }

    public synchronized  boolean updateAdmin(Admin admin){
        return adminDoa.updateAdmin(admin);
    }

    public synchronized boolean deleteAdmin(int id){
        return adminDoa.deleteAdmin(id);
    }

    public synchronized boolean deleteAllAdmins(){
        return adminDoa.deleteAllAdmins();
    }

    public synchronized int getUniquerId(){
        List<Admin> admins = adminDoa.selectAllAdmins();
        Collections.sort(admins);

        int uniqueId = admins.get(admins.size() - 1).getId() + 1;

        return uniqueId;
    }
}
