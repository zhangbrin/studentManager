package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DB;

public class SqlHelper {

	//定义连接数据库操作
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	String url=DB.url;
	String user=DB.user;
	String passwd=DB.password;
	String driver=DB.driver;
	
	
	//关闭数据库资源
	public void close()
	{
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
	//无条件查询
	public ResultSet quereyExecute(String sql)
	{
		try {
			//加载驱动
			Class.forName(driver);
			//得到连接
			ct=DriverManager.getConnection(url, user, passwd);
			//创建ps
			ps=ct.prepareStatement(sql);
			
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭资源
			
		}
		return rs;
	}
	
	//带条件查询
	public ResultSet queryExecute(String sql, String []paras)
	{
		try {
			//加载驱动
			Class.forName(driver);
			//得到连接
			ct=DriverManager.getConnection(url, user, passwd);
			//创建ps
			ps=ct.prepareStatement(sql);
			//给sql语句中的?号赋值
			for(int i=0; i<paras.length; i++)
			{
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭资源
			
		}
		return rs;
	}
	
	//把增删改和在一起  操作数据库
	public boolean updExecute(String sql, String []paras)
	{
		boolean b=true;
		try {
			//加载驱动
			Class.forName(driver);
			//得到连接
			ct=DriverManager.getConnection(url, user, passwd);
			//创建ps
			ps=ct.prepareStatement(sql);
			//给sql语句中的?号赋值
			for(int i=0; i<paras.length; i++)
			{
				ps.setString(i+1, paras[i]);
			}
			//4执行操作
			// 不能在出现ps.executeUpdate()否则会报违反主键约束
			if(ps.executeUpdate()!=1)  // 执行sql语句
			{
				b=false;
			}
			
		} catch (Exception e) {
			b=false;
			e.printStackTrace();
		}finally{
			this.close();
		}
		return b;
		
	}
}
