/**
 * �޸�ѧ����Ϣ
 */
package com.student;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import config.DB;

public class StuUpdDialog extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5111753693908579465L;
	//��������Ҫ��swing���
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JButton jb1,jb2;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JPanel jp1, jp2, jp3;
	
	//owner ���ĸ�����
	//title ������
	//modal ָ����ģʽ���ڣ����з�ģʽ����
	public StuUpdDialog(Frame owner, String title, boolean modal, StuModel sm, int rowNums)
	{
		super(owner, title, modal); //���ø���Ĺ��췽��
		jl1=new JLabel("ѧ��");
		jl2=new JLabel("����");
		jl3=new JLabel("�Ա�");
		jl4=new JLabel("����");
		jl5=new JLabel("����");
		jl6=new JLabel("ϵ��");
		
		jtf1=new JTextField();
		//��ʼ������
		jtf1.setText((String)sm.getValueAt(rowNums, 0));
		//��jtf1�����޸�
		jtf1.setEditable(false);
		jtf2=new JTextField();
		jtf2.setText((String)sm.getValueAt(rowNums, 1));
		jtf3=new JTextField();
		jtf3.setText((String)sm.getValueAt(rowNums, 2));
		jtf4=new JTextField();
		jtf4.setText(sm.getValueAt(rowNums, 3).toString());
		jtf5=new JTextField();
		jtf5.setText((String)sm.getValueAt(rowNums, 4));
		jtf6=new JTextField();
		jtf6.setText((String)sm.getValueAt(rowNums, 5));
		jb1=new JButton("�޸�");
		//ע�����
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//���ò���
		jp1.setLayout(new GridLayout(6,1));
		jp2.setLayout(new GridLayout(6,1));
		
		//������
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);
		
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);
		
		//չ��
		this.setSize(300,200);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			//���û�����޸İ�ť�����Ӧ����
			//�������ݿ�
			Connection conn=null;
			Statement stmt=null;
			ResultSet rs=null;
			PreparedStatement pstmt=null;
			//�������ݿ�,�ж����ݿ��Ƿ�Ϸ�
			try {
				
				Class.forName(DB.driver);
				conn=DriverManager.getConnection(DB.url,DB.user,DB.password);
				
//				//1����������
//				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
//				//�������ݶ���
//				String url="jdbc:microsoft:sqlserver://127.0.0.1:1433; databaseName=spdb1";
//				//2���������ݿ⡢
//				conn=DriverManager.getConnection(url,"sa","sangliyang");
				//4���༭������
				String strsql="update stu set stuName=?, stuSex=?,stuAge=?, sutJg=?, stuDept=? where stuId=? ";
				pstmt=conn.prepareStatement(strsql);
				//�����Ÿ�ֵ
				pstmt.setString(1, jtf2.getText());
				pstmt.setString(2, jtf3.getText());
				pstmt.setString(3, jtf4.getText());
				pstmt.setString(4, jtf5.getText());
				pstmt.setString(5, jtf6.getText());
				pstmt.setString(6, jtf1.getText());
				
				//4ִ�в���
				pstmt.executeUpdate();
				this.dispose();    //�ر�ѧ���Ի���
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}finally
			{
				//�ͷ���Դ
				try {
					if(rs!=null) rs.close();
					if(stmt!=null) stmt.close();
					if(conn!=null)	conn.close();
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
			
		}
		else if (e.getSource()==jb2)
		{
			try {
				this.dispose();    //�ر�ѧ���Ի���
			} catch (Exception e3) {
				// TODO: handle exception
				e3.printStackTrace();
			}
		}
	}

}
