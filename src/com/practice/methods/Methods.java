package com.practice.methods;

import java.util.List;

import com.practice.DBcontroller.DBcontroller;
import com.practice.pojo.User;

public class Methods {
	public int signUp(String a, String b)
	{
		DBcontroller db = new DBcontroller();
		return db.signUp(a, b);
	}
	public int updatePW(String a, String b)
	{
		DBcontroller db = new DBcontroller();
		return db.updatePassword(a, b);
	}
	public List<User> showAllUser()
	{
		DBcontroller db = new DBcontroller();
		return db.showAllUser();
	}
	public List<User> showActivate()
	{
		DBcontroller db = new DBcontroller();
		return db.showActivateUser();
	}
	public List<User> showDeactivate()
	{
		DBcontroller db = new DBcontroller();
		return db.showDeactivateUser();
	}
	public boolean login(String a, String b)
	{
		DBcontroller db = new DBcontroller();
		return db.login(a, b);
	}
	public int deleteUser(String a)
	{
		DBcontroller db = new DBcontroller();
		return db.deleteUser(a);
	}
	public int deactivateUser(String a)
	{
		DBcontroller db = new DBcontroller();
		return db.deactivateUser(a);
	}
	public int activateUser(String a)
	{
		DBcontroller db = new DBcontroller();
		return db.activateUser(a);
	}
	
}
