/**
 * �޸�ѧ����Ϣ
 */
package com.student;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class StuAddDialog extends JDialog implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//��������Ҫ��swing���
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JButton jb1,jb2;
	JTextField jtf1, jtf2, jtf3, jtf4, jtf5, jtf6;
	JPanel jp1, jp2, jp3;
	
	//owner ���ĸ�����
	//title ������
	//modal ָ����ģʽ���ڣ����з�ģʽ����
	public StuAddDialog(Frame owner, String title, boolean modal)
	{
		super(owner, title, modal); //���ø���Ĺ��췽��
		jl1=new JLabel("ѧ��");
		jl2=new JLabel("����");
		jl3=new JLabel("�Ա�");
		jl4=new JLabel("����");
		jl5=new JLabel("����");
		jl6=new JLabel("ϵ��");
		
		jtf1=new JTextField();
		jtf2=new JTextField();
		jtf3=new JTextField();
		jtf4=new JTextField();
		jtf5=new JTextField();
		jtf6=new JTextField();
		
		jb1=new JButton("���");
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

			//ϣ�����
			StuModel temp=new StuModel();
			String sql="insert into stu values(?,?,?,?,?,?)";
			String paras[]={jtf1.getText(), jtf2.getText(), jtf3.getText(), jtf4.getText(), jtf5.getText(),  jtf6.getText()};
			if(!temp.updStu(sql, paras))   // ��δ���ǰ�治���ڳ���temp.add(sql,paras),����ᱨΥ������Լ��
			{
				//��ʾ
				JOptionPane.showMessageDialog(this, "���ʧ��");
				return;
			}
			//�رնԻ���
			this.dispose();
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
