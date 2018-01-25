/**
 * ���һ������汾��ѧ������ϵͳ
 * ���԰�Stu��Ĳ�����װ�ڸ�ģ����
 * 1:��ѯ����
 * 2�����һ��ѧ��
 */
package com.student;

import javax.swing.*;

import java.awt.event.*;

public class StuManage extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ����ؼ�
	JPanel jp1, jp2;
	JLabel jl1;
	JButton jb1, jb2, jb3, jb4;
	// JScrollPane jsp;
	JTextField jtf = null;
	StuModel sm = null;

	JTable jt = null;
	JScrollPane jsp = null;

	public static void main(String[] args) {
		StuManage test = new StuManage();
	}

	// ���캯��
	public StuManage() {
		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("��ѯ");
		jb1.addActionListener(this);

		jl1 = new JLabel("����������");

		// �Ѹ����ؼ����뵽jp1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);

		jp2 = new JPanel();

		jb2 = new JButton("���");
		jb2.addActionListener(this);
		jb3 = new JButton("�޸�");
		jb3.addActionListener(this);
		jb4 = new JButton("ɾ��");
		jb4.addActionListener(this);

		// �Ѹ�����ť���뵽jp2

		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);

		// ����һ������ģ�Ͷ���
		sm = new StuModel();
		String[] paras = { "1" };
		sm.queryStu("select * from stu where 1=?", paras);

		// ��ʼ��JTable
		jt = new JTable(sm);

		// ��ʼ��jsp JScrollPane
		jsp = new JScrollPane(jt);

		// ��jsp���뵽 jfame

		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");
		this.setSize(400, 300);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent arg0) {
		// �ж����Ǹ���ť������ .jb1 �� actonperformed������ͬһ��������
		if (arg0.getSource() == jb1) {
			// ��Ϊ�ѱ�����ݷ�װ��StuModel���ˡ����Ǿͺ�������ɲ�ѯ
			String name = this.jtf.getText().trim();
			System.out.println("name: "+name);
			
			if (!(name == null || name.length() <= 0)){
				System.out.println("-------");
				
				// дһ��sql���
				String sql = "select * from stu where stuName=?";
				String paras[] = { name };
				// ģ����ѯ
				// String sql="use spdb1; select *from stu where stuName like
				// '%"+name+"%'";
				// �����µ�����ģ���࣬������
				sm = new StuModel();
				sm.queryStu(sql, paras);
			}else{
				sm = new StuModel();
				sm.queryStu("select * from stu");
				
			}

			// ����Jtable
			jt.setModel(sm);

		}

		// ���û������ѯ
		else if (arg0.getSource() == jb2) {
			StuAddDialog sa = new StuAddDialog(this, "���ѧ��", true);

			// ���»����������
			// �����µ�����ģ���࣬������
			sm = new StuModel();
			String[] paras2 = { "1" };
			sm.queryStu("select * from stu where 1=?", paras2);
			// ����Jtable
			jt.setModel(sm);
		}

		// ��������޸�
		else if (arg0.getSource() == jb3) {
			// �û�ϣ���޸�
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// ��ʾ
				JOptionPane.showMessageDialog(this, "ѡ��һ��");
				return;
			}
			// ��ʾ�޸ĶԻ���
			new StuUpdDialog(this, "�޸�ѧ��", true, sm, rowNum);

			// �����µ�����ģ���࣬������
			sm = new StuModel();
			String[] paras2 = { "1" };
			sm.queryStu("select * from stu where 1=?", paras2);
			// ����Jtable
			jt.setModel(sm);
		}

		// �������ɾ��
		else if (arg0.getSource() == jb4) {
			// ˵���û�ϣ��ɾ����¼
			// �õ���ѧ����id
			// ������û�һ�ж�û��ѡ��ͷ���-1
			int rowNum = this.jt.getSelectedRow();

			if (rowNum == -1) {
				// ��ʾ
				JOptionPane.showMessageDialog(this, "ѡ��һ��");
				return;
			}

			// ɾ�����ݿ��е�һ����¼
			// �õ�ѧ���ı��
			String stuId = (String) sm.getValueAt(rowNum, 0); // ���ر��
			// ����һ��sql���
			String sql = "delete from stu where stuid=?";
			String[] paras = { stuId };
			StuModel temp = new StuModel();
			temp.updStu(sql, paras);

			// �����µ�����ģ���࣬������
			sm = new StuModel();
			String[] paras2 = { "1" };
			sm.queryStu("select * from stu where 1=?", paras2);
			// ����Jtable
			jt.setModel(sm);
		}
	}
}
