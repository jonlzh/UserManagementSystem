package com.practice.DBcontroller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.practice.pojo.User;


public class DBcontroller {
	
	private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    
    public List<User> showAllUser()
    {
    	List<User> allUser=new ArrayList<User>();
    	try {
    		Connection con = DBUtil.getConnection();
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM USER");
			
			while(rs.next())
			{
				User up = new User();
				up.setId(rs.getInt("id"));
				up.setUsername(rs.getString("username"));
				up.setPassword(rs.getString("password"));
				up.setState(rs.getString("state"));
				allUser.add(up);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allUser;
    }
      
    public List<User> showActivateUser()
    {
    	List<User> allUser=new ArrayList<User>();
    	try {
    		Connection con = DBUtil.getConnection();
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT id,username,password From user WHERE state='Activate'");
			
			while(rs.next())
			{
				User up = new User();
				up.setId(rs.getInt("id"));
				up.setUsername(rs.getString("username"));
				up.setPassword(rs.getString("password"));
				allUser.add(up);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allUser;
    }
    
    public List<User> showDeactivateUser()
    {
    	List<User> allUser=new ArrayList<User>();
    	try {
    		Connection con = DBUtil.getConnection();
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT id,username,password From user WHERE state='Deactivate'");
			
			while(rs.next())
			{
				User up = new User();
				up.setId(rs.getInt("id"));
				up.setUsername(rs.getString("username"));
				up.setPassword(rs.getString("password"));
				allUser.add(up);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allUser;
    }

    public int signUp(String u, String p)
    {
    	int count =0;
    	try {
    		Connection con = DBUtil.getConnection();
			Statement st = con.createStatement();
			
         // insert the data
            count = st.executeUpdate("INSERT INTO user (username,password)" + "VALUES ('"+ u + "','" + p +"')");
//            System.out.println("User created. user: " + u + " password : " + p);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    	return count;
    }
    
    public int deactivateUser(String u)
    {
    	int count =0;
    	try {
    		Connection con = DBUtil.getConnection();

         // insert the data
            String query = "UPDATE user SET state = 'Deactivate' WHERE username= ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, u);
            count = ps.executeUpdate();
            System.out.println("Update status to deactivate for " + u);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    	return count;
    }

    public int updatePassword(String u, String p)
    {
    	int count = 0;
    	try {
    		Connection con = DBUtil.getConnection();
			
         // insert the data
            String query = "UPDATE user SET password = ? WHERE username= ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, p);
            ps.setString(2, u);
            count = ps.executeUpdate();
//            System.out.println("asdasd: " + count );
        }catch (SQLException e) {
            e.printStackTrace();
        }
    	return count;
    }
    
    public int deleteUser(String u)
    {
    	int count = 0;
    	try {
    		Connection con = DBUtil.getConnection();
			Statement st = con.createStatement();
			
         // insert the data
			count = st.executeUpdate("DELETE FROM user WHERE username='" + u + "'");
            System.out.println("Delete user " + u);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    	return count;
    }
    
	public boolean login(String u, String p)
    {
    	boolean check = false;
    	String temp = "";
    	try {
    		Connection con = DBUtil.getConnection();
			Statement st = con.createStatement();
            ResultSet result = st.executeQuery("SELECT password From user WHERE username='" + u + "'");

            while (result.next()) {
            	temp = result.getString("password");
            }
            close();
            if(p.equals(temp))
            {
            	check = true;
            }
            else
            {
            	check = false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }    	
    	return check;
    }
    
	public int activateUser(String u)
    {
		int count = 0;
    	try {
    		Connection con = DBUtil.getConnection();

         // insert the data
            String query = "UPDATE user SET state = 'Activate' WHERE username= ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, u);
            count = ps.executeUpdate();
            System.out.println("Update status to activate for " + u);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    	return count;
    }
    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}

