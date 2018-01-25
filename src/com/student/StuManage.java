/**
 * ���һ������汾��ѧ������ϵͳ
 * ���԰�Stu��Ĳ�����װ�ڸ�ģ����
 * 1:��ѯ����
 * 2�����һ��ѧ��
 */
package com.student;
import javax.swing.*;

import config.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class StuManage extends JFrame implements ActionListener{
	//����ؼ�
	JPanel jp1, jp2;
	JLabel jl1;
	JButton jb1, jb2, jb3, jb4;
	//JScrollPane jsp;
	JTextField jtf;
	StuModel sm;
	
	//�����������ݿ����
	PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
	
	JTable jt=null;
	JScrollPane jsp=null;
	
	     
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StuManage test=new StuManage();
	}
	//���캯��
	public StuManage()
	{
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
	    
		jl1=new JLabel("����������");
		
		//�Ѹ����ؼ����뵽jp1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2=new JPanel();
		
		
		jb2=new JButton("���");
		jb2.addActionListener(this);
		jb3=new JButton("�޸�");
		jb3.addActionListener(this);
		jb4=new JButton("ɾ��");
		jb4.addActionListener(this);
		
		//�Ѹ�����ť���뵽jp2
		
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		
		//����һ������ģ�Ͷ���
		sm=new StuModel();
		
	
		//	��ʼ��JTable
		jt=new JTable(sm);
		
		//��ʼ��jsp JScrollPane
		jsp=new JScrollPane(jt);
		
		//��jsp���뵽 jfame
		
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		this.setSize(400,300);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//�ж����Ǹ���ť������ .jb1 �� actonperformed������ͬһ��������
		if(arg0.getSource()==jb1)
		{
			System.out.println("�û����ѯ");
			//��Ϊ�ѱ�����ݷ�װ��StuModel���ˡ����Ǿͺ�������ɲ�ѯ
			String name=this.jtf.getText().trim();
			//дһ��sql���
		//	String sql="use spdb1; select *from stu where stuName='"+name+"'";
			//ģ����ѯ
			String sql="select * from stu where stuName like '%"+name+"%'";
			//�����µ�����ģ���࣬������
			sm=new StuModel(sql);
			//����Jtable
			jt.setModel(sm);
			
			}
		//���û������ѯ
		else if(arg0.getSource()==jb2)
		{
			StuAddDialog sa=new StuAddDialog(this,"���ѧ��",true);
			
			//���»����������
			//�����µ�����ģ���࣬������
			sm=new StuModel();
			//����Jtable
			jt.setModel(sm);
		}
		
		//��������޸�
		else if(arg0.getSource()==jb3)
		{
			//�û�ϣ���޸�
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1)
			{
				//��ʾ
				JOptionPane.showMessageDialog(this, "ѡ��һ��");
				return;
			}
			//��ʾ�޸ĶԻ���
			new StuUpdDialog(this,"�޸�ѧ��",true, sm, rowNum);
			
			//�����µ�����ģ���࣬������
			sm=new StuModel();
			//����Jtable
			jt.setModel(sm);
		}
		
		
		//�������ɾ��
		else if(arg0.getSource()==jb4)
		{
			//˵���û�ϣ��ɾ����¼
			//�õ���ѧ����id
			//������û�һ�ж�û��ѡ��ͷ���-1
			int rowNum=this.jt.getSelectedRow();
		
		if(rowNum==-1)
		{
			//��ʾ
				JOptionPane.showMessageDialog(this, "ѡ��һ��");
				return;
		}
		//�õ�ѧ���ı��
		String stuId=(String)sm.getValueAt(rowNum, 0);    //���ر��
		
	//	System.out.println("id="+stuId);
		
		//�������ݿ����ɾ������
		try {
			
			Class.forName(DB.driver);
			ct=DriverManager.getConnection(DB.url,DB.user,DB.password);
			
			//��������
//			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
//			ct=DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433; databaseName=spdb1","sa","sangliyang");
			
			
			//׼����˵��� .ע��Ҫ��  use spdb1; ����ᱨ ����
			ps=ct.prepareStatement("delete from stu where stuid=?");
			ps.setString(1, stuId);
			//ִ�в�ѯ
  			ps.executeUpdate();   
  			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally
		{
			//�ر�
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				
			}
			//�����µ�����ģ���࣬������
			sm=new StuModel();
			//����Jtable
			jt.setModel(sm);
		}
		}
	}

}
