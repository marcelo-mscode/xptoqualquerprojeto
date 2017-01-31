package br.com.sysloccOficial.model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Conexao {

	   public static Connection con;  
	   public Statement stm;  
	   public ResultSet res = null;  
	   
	   static String status;
	   
	   
	   
	   public static Connection getConnection() throws InstantiationException, IllegalAccessException, SQLException {
		   Connection conn = null;
		    	   
	   	   try{
	   		   Class.forName("com.mysql.jdbc.Driver").newInstance();
	   		   String url = "jdbc:mysql://localhost/test?user=root&password=root";
	   		   conn = (Connection) DriverManager.getConnection(url);
	   		      		   
	   		   
	   		   status = "Connection Opened";
	   	   }catch (ClassNotFoundException e){
	   		   System.out.println("N�o foi possivel encontrar a classe");
	   	   }
		return conn;
	   			   
  }

	   public static void executa(String sql)throws Exception{
		   Statement st = null;
		   st = (Statement) con.createStatement();
		   st.executeUpdate(sql);
		   
	   }
	   
}	   
