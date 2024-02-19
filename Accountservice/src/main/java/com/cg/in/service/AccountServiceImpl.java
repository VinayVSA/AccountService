package com.cg.in.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.in.entities.Account;
import com.cg.in.exception.AccountException;
import com.cg.in.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{
	
	private final AccountRepository accountRepo;
	
	@Autowired
	public AccountServiceImpl(AccountRepository accountRepo)
	{
		this.accountRepo=accountRepo;
	}

	@Override
	public void deposit(int accountNum, double amount) throws AccountException {
		Account acc=getAccountById(accountNum);
		if(acc==null)
		{
			throw new AccountException("The account with account number : "+accountNum+" did not exist");
		}
		else
		{
			double bal=acc.getBalance()+amount;
			acc.setBalance(bal);
			accountRepo.save(acc);
		}
	}

	@Override
	public void withdraw(int accountNum, double Wamount)throws AccountException {
		Account acc=getAccountById(accountNum);
		if(acc==null)
		{
			throw new AccountException("The account with account number : "+accountNum+" did not exist");
		}
		else if(acc.getBalance()<Wamount)
		{
			throw new AccountException("Insufficient balance");
		}
		else
		{
			double bal=acc.getBalance()-Wamount;
			acc.setBalance(bal);
			accountRepo.save(acc);
		}
		
	}

	@Override
	public void transfer(int faccountNum, int taccountNum, double tbalance) throws AccountException {
		
		Account facc=getAccountById(faccountNum);
		Account tacc=getAccountById(taccountNum);
		if(facc==null )
		{
			throw new AccountException("The account with account number : "+faccountNum+" did not exist");
		}
		else if(tacc==null)
		{
			throw new AccountException("The account with account number : "+taccountNum+" did not exist");
		}
		else if(facc.getBalance()<tbalance)
		{
			throw new AccountException("Insufficient balance");
		}
		else
		{
			double fbal=facc.getBalance()-tbalance;
			facc.setBalance(fbal);
			accountRepo.save(facc);
			
			double tbal=tacc.getBalance()+tbalance;
			tacc.setBalance(tbal);
			accountRepo.save(tacc);
		}
	}

	@Override
	public Account getAccountById(int accountNum) {
		
		return accountRepo.findById(accountNum).get();
	}

	@Override
	public void deleteAccountById(int accountNum) {
		accountRepo.deleteById(accountNum);
		
	}

	@Override
	public Account createAccount(Account account) {
		
		 return accountRepo.save(account);
	}

	@Override
	public void updateAccount(Account updatedAccount) {
	
		accountRepo.save(updatedAccount);
		
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepo.findAll();
		
		
	}

}
