package com.nagarro.main.authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class LoginService extends HttpServlet{

	public boolean validate(String uname,String pass) {
		
		    String url ="jdbc:mysql://localhost:3306/springwebapp";
	        String u = uname;
	        String p = pass;

	        try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection con = DriverManager.getConnection(url,u,p);
	            PreparedStatement pst = con.prepareStatement("Select * from user where user_id=? and user_pass=?");
	            pst.setString(1,uname);
	            pst.setString(2,pass);
	            ResultSet rs = pst.executeQuery();
              
	            if(rs.next()){
	                return true;
	            }
	        }catch (Exception e){
	            System.out.println(e);
	        }

	        return false;
	}
	
	


}
