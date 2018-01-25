/**
 * JTalbe的使用
 * 
 * 
 */
package com.student;
import javax.swing.*;
import java.util.*;
import java.sql.*;
import java.awt.event.*;
public class Test1 extends JFrame{
	//rowData用来存放行数据
	//columnNames存放列名
	Vector rowData, columnNames;
	JTable jt=null;
	JScrollPane jsp=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 test=new Test1();
	}
	//构造函数
	public Test1()
	{
		columnNames=new Vector();
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");
		
		rowData=new Vector();
		//rowData 可以存放多行
		Vector hang=new Vector();
		hang.add("spoo1");
		hang.add("孙悟空");
		hang.add("性别");
		hang.add("年龄");
		hang.add("500");
		hang.add("花果山");
		hang.add("少林派");
		
		//加入到rowData
		rowData.add(hang);
		
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
