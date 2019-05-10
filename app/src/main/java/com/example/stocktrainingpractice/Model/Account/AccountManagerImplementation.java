package com.example.stocktrainingpractice.Model.Account;

import com.example.stocktrainingpractice.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class AccountManagerImplementation implements AccountManager {
    private static AccountManagerImplementation INSTANCE;

    private AccountManagerImplementation(){}

    public static AccountManagerImplementation getInstance(){
        if (INSTANCE == null){
            synchronized (AccountManagerImplementation.class){
                if(INSTANCE == null){
                    INSTANCE = new AccountManagerImplementation();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void createNew(Account account) {
        writeAccToFile(account);
    }


    @Override
    public void updateSettings(String accName, Double transactionFee, Boolean dayTradeRestriction) {
        Account acc = readAccFromFile(accName);
        if(transactionFee != null){
            acc.setTransactionFee(transactionFee);
        }
        if(dayTradeRestriction != null) {
            acc.setDayTradeRestriction(dayTradeRestriction);
        }
        writeAccToFile(acc);
    }


    @Override
    public void updateStock(String accName, Double cashAmt, Double equityAmt, List<Stock> stockList) {
        Account acc = readAccFromFile(accName);
        if(cashAmt != null){
            acc.setCashAmt(cashAmt);
        }
        if(equityAmt != null){
            acc.setEquityAmt(equityAmt);
        }
        if(stockList != null){
            acc.setStockList(stockList);
        }
        writeAccToFile(acc);
    }


    @Override
    public void deleteAll() {
        File folder = new File(Util.FILE_DIR);
        File[] listOfFiles = folder.listFiles();
        for (File file: listOfFiles){
            file.delete();
        }
    }


    @Override
    public void delete(String accName) {
        File file = new File(Util.FILE_DIR + accName);
        file.delete();
    }


    @Override
    public List<Account> getAllAccount() {
        List<Account> accountList = new ArrayList<>();
        File folder = new File(Util.FILE_DIR);
        File[] listOfFiles = folder.listFiles();
        for (File file: listOfFiles){
            accountList.add(readAccFromFile(file));
        }
        return accountList;
    }


    @Override
    public List<Stock> getStockList(String accName) {
        Account acc = readAccFromFile(accName);
        return acc.getStockList();
    }


    private Account readAccFromFile(String accName){
        try {
            FileInputStream fileIn = new FileInputStream(Util.FILE_DIR + accName);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Account account = ((Account)objectIn.readObject());
            fileIn.close();
            objectIn.close();
            return account;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private Account readAccFromFile(File file){
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Account account = ((Account)objectIn.readObject());
            fileIn.close();
            objectIn.close();
            return account;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private void writeAccToFile(Account account){
        try {
            FileOutputStream fileOut = new FileOutputStream(Util.FILE_DIR + account.getAccName());
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(account);
            fileOut.close();
            objectOut.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
