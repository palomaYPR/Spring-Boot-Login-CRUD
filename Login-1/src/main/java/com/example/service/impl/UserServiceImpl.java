package com.example.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.User;
import com.example.UserRepository;
import com.example.commons.GenericServicelmpl;
import com.example.service.UserService;

@Service
public class UserServiceImpl extends GenericServicelmpl<User, Long> implements UserService	{

	@Autowired
	 private UserRepository personaDao;
	 
	 @Override
	 public CrudRepository<User, Long> getDao() {
	  return personaDao;
	 }
}
