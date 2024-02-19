package com.cg.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.in.entities.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
