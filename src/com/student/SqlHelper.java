package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DB;

public class SqlHelper {

	//�����������ݿ����
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	String url=DB.url;
	String user=DB.user;
	String passwd=DB.password;
	String driver=DB.driver;
	
	
	//�ر����ݿ���Դ
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
	
	public ResultSet quereyExecute(String sql)
	{
		try {
			//��������
			Class.forName(driver);
			//�õ�����
			ct=DriverManager.getConnection(url, user, passwd);
			//����ps
			ps=ct.prepareStatement(sql);
			
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر���Դ
			
		}
		return rs;
	}
	
	//��ѯ���ݿ�
	public ResultSet queryExectue(String sql, String []paras)
	{
		try {
			//��������
			Class.forName(driver);
			//�õ�����
			ct=DriverManager.getConnection(url, user, passwd);
			//����ps
			ps=ct.prepareStatement(sql);
			//��sql����е�?�Ÿ�ֵ
			for(int i=0; i<paras.length; i++)
			{
				ps.setString(i+1, paras[i]);
			}
			rs=ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//�ر���Դ
			
		}
		return rs;
	}
	
	//����ɾ�ĺ���һ��  �������ݿ�
	public boolean updExecute(String sql, String []paras)
	{
		boolean b=true;
		try {
			//��������
			Class.forName(driver);
			//�õ�����
			ct=DriverManager.getConnection(url, user, passwd);
			//����ps
			ps=ct.prepareStatement(sql);
			//��sql����е�?�Ÿ�ֵ
			for(int i=0; i<paras.length; i++)
			{
				ps.setString(i+1, paras[i]);
			}
			//4ִ�в���
			// �����ڳ���ps.executeUpdate()����ᱨΥ������Լ��
			if(ps.executeUpdate()!=1)  // ִ��sql���
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
