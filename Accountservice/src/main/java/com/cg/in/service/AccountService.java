package com.cg.in.service;

import java.util.List;

import com.cg.in.entities.Account;
import com.cg.in.exception.AccountException;

public interface AccountService {

	public void deposit(int accountNum,double amount) throws AccountException;
	
	public void withdraw(int accountNum,double Wamount) throws AccountException;
	
	public void transfer(int faccountNum,int taccountNum,double tbalance) throws AccountException;
	
	public Account getAccountById(int accountNum);
	
	public void deleteAccountById(int accountNum);
	
	public Account createAccount(Account account) ;
	
	public void updateAccount(Account updatedAccount);
	
	public List<Account> getAllAccounts();
	
}
