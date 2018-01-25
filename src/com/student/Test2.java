/**
 * 从数据库中提取数据
 * 
//创建数据库及表如下所示：
create database spdb1;
--创建学生表
create table stu
(
	stuId varchar(30) primary key,
	stuName varchar(50) not null,
	stuSex nchar(1) check (stuSex in('男','女')) default'男', 
	stuAge int check (stuAge>1),
	sutJg nvarchar(30),
	stuDept nvarchar(40)
)
insert into stu values('sp001','孙悟空','男','20','花果山','少林派')
insert into stu values('sp002','猪八戒','男','15','高老庄','天上的')
insert into stu values('sp003','沙悟净','男','26','流沙河','水里的')
insert into stu values('sp004','唐三藏','男','21','长安','庙里的')
 * 
 * 
 */
package com.student;

import java.util.Vector;

import javax.swing.*;

import config.DB;

import java.sql.*;
public class Test2 extends JFrame{
	
	//rowData用来存放行数据
	//columnNames存放列名
	Vector rowData, columnNames;
	JTable jt=null;
	JScrollPane jsp=null;
	
	//定义连接数据库 
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test2 test=new Test2();
	}
	public Test2()
	{
		columnNames=new Vector();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");
		
		rowData=new Vector();
		//从数据库中取出数据
		rowData=new Vector();
		try {
			//加载驱动
			Class.forName(DB.driver);
			ct=DriverManager.getConnection(DB.url,DB.user,DB.password);
			
			//准备陈说语句 .注意要用  use spdb1; 否则会报 表不存在
			ps=ct.prepareStatement("select * from stu");	
			//执行查询
  			 rs=ps.executeQuery();      
  			 // 返回查询结果
			while(rs.next())
			{
				//rowData
				Vector hang=new Vector();
				
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				//加入到rowData
				rowData.add(hang);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//关闭资源
			try {
				if(rs!=null)
				{
					rs.close();
				}
				if(ps!=null)
				{
					ps.close();
				}
				if(ct!=null)
				{
					ct.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		//初始化JTable
		jt=new JTable(rowData,columnNames);
		
		//初始化jsp JScrollPane
		jsp=new JScrollPane(jt);
		
		//把jsp放入到 jrame
		
		this.add(jsp);
		
		this.setSize(400,300);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
	}
}

