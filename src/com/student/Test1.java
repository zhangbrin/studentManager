/**
 * JTalbe��ʹ��
 * 
 * 
 */
package com.student;
import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.event.*;
public class Test1 extends JFrame{
	//rowData�������������
	//columnNames�������
	Vector rowData, columnNames;
	JTable jt=null;
	JScrollPane jsp=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 test=new Test1();
	}
	//���캯��
	public Test1()
	{
		columnNames=new Vector();
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");
		
		rowData=new Vector();
		//rowData ���Դ�Ŷ���
		Vector hang=new Vector();
		hang.add("spoo1");
		hang.add("�����");
		hang.add("�Ա�");
		hang.add("����");
		hang.add("500");
		hang.add("����ɽ");
		hang.add("������");
		
		//���뵽rowData
		rowData.add(hang);
		
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
