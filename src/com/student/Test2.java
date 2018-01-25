/**
 * �����ݿ�����ȡ����
 * 
//�������ݿ⼰��������ʾ��
create database spdb1;
--����ѧ����
create table stu
(
	stuId varchar(30) primary key,
	stuName varchar(50) not null,
	stuSex nchar(1) check (stuSex in('��','Ů')) default'��', 
	stuAge int check (stuAge>1),
	sutJg nvarchar(30),
	stuDept nvarchar(40)
)
insert into stu values('sp001','�����','��','20','����ɽ','������')
insert into stu values('sp002','��˽�','��','15','����ׯ','���ϵ�')
insert into stu values('sp003','ɳ��','��','26','��ɳ��','ˮ���')
insert into stu values('sp004','������','��','21','����','�����')
 * 
 * 
 */
package com.student;

import java.util.Vector;

import javax.swing.*;

import config.DB;

import java.sql.*;
public class Test2 extends JFrame{
	
	//rowData�������������
	//columnNames�������
	Vector rowData, columnNames;
	JTable jt=null;
	JScrollPane jsp=null;
	
	//�����������ݿ� 
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
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");
		
		rowData=new Vector();
		//�����ݿ���ȡ������
		rowData=new Vector();
		try {
			//��������
			Class.forName(DB.driver);
			ct=DriverManager.getConnection(DB.url,DB.user,DB.password);
			
			//׼����˵��� .ע��Ҫ��  use spdb1; ����ᱨ ������
			ps=ct.prepareStatement("select * from stu");	
			//ִ�в�ѯ
  			 rs=ps.executeQuery();      
  			 // ���ز�ѯ���
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
				//���뵽rowData
				rowData.add(hang);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//�ر���Դ
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
		
		//��ʼ��JTable
		jt=new JTable(rowData,columnNames);
		
		//��ʼ��jsp JScrollPane
		jsp=new JScrollPane(jt);
		
		//��jsp���뵽 jrame
		
		this.add(jsp);
		
		this.setSize(400,300);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
	}
}

