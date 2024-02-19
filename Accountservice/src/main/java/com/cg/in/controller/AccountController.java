package com.cg.in.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.in.entities.Account;
import com.cg.in.exception.AccountException;
import com.cg.in.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@GetMapping
	public List<Account> getAllAccounts()
	{
		return accountService.getAllAccounts();
	}
	
	@PostMapping
	public Account createaccount(@RequestBody Account account)
	{
		 return accountService.createAccount(account);
	}
	@GetMapping("/{id}")
	public Account getAccountById(@PathVariable int id)
	{
		return accountService.getAccountById(id);
	}
	
	@PostMapping("/deposit/{id}")
	public  String deposit(@PathVariable int id,@RequestBody Map<String, Double> requestBody) throws AccountException
	{
		 double amount = requestBody.get("amount");
	   accountService.deposit(id, amount);
		 
		return "Deposited " + amount + " into account with ID " + id + " successfully";
	}
	@PostMapping("/withdraw/{id}")
	public String withdraw(@PathVariable int id,@RequestBody Map<String, Double> requestBody) throws AccountException
	{
		double amount = requestBody.get("amount");
		 accountService.withdraw(id, amount);
		 
			return "Withdrawn " + amount + "  from account with ID " + id + " successfully";
	}
	
	@PostMapping("/transfer/{fid}/{tid}")
	public String transfer(@PathVariable int fid,@PathVariable int tid,@RequestBody Map<String, Double> requestBody) throws AccountException
	{
		double amount = requestBody.get("amount");
		 accountService.transfer(fid, tid, amount);
		 
			return "Transfered " + amount + "  from account with ID " + fid +" to " +tid+" successfully";
	}
	

}
