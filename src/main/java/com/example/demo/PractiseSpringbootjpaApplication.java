package com.example.demo;

import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Entities.User;
import Jpaa.UserRepo;

@SpringBootApplication
@EnableJpaRepositories("Jpaa")
@EntityScan("Entities")
public class PractiseSpringbootjpaApplication {

	public static void main(String[] args) {
	ApplicationContext context=	SpringApplication.run(PractiseSpringbootjpaApplication.class, args);
		
	UserRepo ur=context.getBean(UserRepo.class);
	
	int i=0;
	while(i<10)
	{
	System.out.println("Choose your options");
	System.out.println("1.Create   2.Update   3.Read   4.Delete");
	System.out.println("---------------------------------------------");
	System.out.println("Enter operations you want to perform");
	Scanner sc=new Scanner(System.in);
	String str=sc.nextLine();
	System.out.println("----------------------------------------------");
	if(str.equals("create"))
	{

		System.out.println("Enter user-name :");
		String name=sc.nextLine();
		System.out.println("Enter branch name :");
		String branch=sc.nextLine();
		System.out.println("--------------------------------------------");
		User u=new User(name,branch);
		
		ur.save(u);
		
		System.out.println("User saved successfully....");
	}
	
	if(str.equals("update"))
	{
		System.out.println("Enter user id for updation :");
		int id=sc.nextInt();
		sc.nextLine();
	    Optional<User>	o=ur.findById(id);
	    User u1= o.get();
	    System.out.println("Enter name you wanna update :");
	    String name=sc.nextLine();
	    u1.setName(name);
	    System.out.println("Enter branch you wanna update :");
	    String branch=sc.nextLine();
	    u1.setBranch(branch);
	    
	    
	    ur.save(u1);
	    System.out.println("---------------------------------------------");
	    
	    System.out.println("User updated successfully.....");
	}
	
	if(str.equals("read"))
	{
		System.out.println("You want to see [particular user] or [All users] Use :::   1 or 2");
		int a=sc.nextInt();
		sc.nextLine();
		if(a==1)
		{
			System.out.println("Enter User-Id");
			int id=sc.nextInt();
			sc.nextLine();
		    Optional<User> o=ur.findById(id);
		    User u=o.get();
		    System.out.println("----------------------------------------------");
		    System.out.println(u.getId());
		    System.out.println(u.getName());
		    System.out.println(u.getBranch());
		 
		}
		
		if(a==2)
		{
         
			System.out.println("------------------------------------------------");
			List<User> li=ur.findAll();
			li.forEach(u->{
				System.out.println(u.getId());
				System.out.println(u.getName());
				System.out.println(u.getBranch());
				System.out.println("---------------------------------------------");
			});
		
		}
		
		if(a==3)
		{
			String name=sc.nextLine();
			List<User> li=ur.findByName(name);
			li.forEach(u->System.out.println(u));
		}
		
		if(a==4)
		{		
		String name=sc.nextLine();
		List<User> li=ur.findByNameStartingWith(name);
		li.forEach(u->System.out.println(u));
			
		}
	}
	
	if(str.equals("delete"))
	{
		System.out.println("You want to delete [single user] or [All User ]     Use ::::  1 or 2");
		int a=sc.nextInt();
		sc.nextLine();
		if(a==1)
		{
			System.out.println("------------------------------------------------");
			System.out.println("Enter user Id :");
			int id=sc.nextInt();
			sc.nextLine();
			Optional<User> o=ur.findById(id);
			User u=o.get();
			ur.delete(u);
			System.out.println("User successfully deleted...");
		}
		
		if(a==2)
		{
			ur.deleteAll();
			System.out.println("All Users are deleted...");
		}
		
	}
	
	}
	i++;
	}

}
