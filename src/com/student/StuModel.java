package com.student;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.table.*;

public class StuModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// rowData�������������
	// columnNames�������
	Vector<Vector<String>> rowData;
	Vector<String> columnNames;

	// ��ɾ��ѧ����Ϣ
	public boolean updStu(String sql, String[] paras) {
		// ����һ�� SqlHelper(�������Ĳ����Բ����ǿ��԰Ѷ���������̬��)
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.updExecute(sql, paras);
	}

	// ��ѯ�ı��ʾ��ǳ�ʼ�����������Ĳ�ѯ
	public void queryStu(String sql, String[] paras) {
		SqlHelper sqlHelper = null;
		// �м�
		columnNames = new Vector<String>();

		// �����ݿ���ȡ������
		rowData = new Vector<Vector<String>>();
		try {
			sqlHelper = new SqlHelper();
			ResultSet rs = sqlHelper.queryExecute(sql, paras);

			// ��rs �����п��Եõ�ResultSetMetadata
			// rsmt���Եõ�����ֶ����У����ҿ���֪��ÿһ�е�����
			ResultSetMetaData rsmt = rs.getMetaData();
			for (int i = 0; i < rsmt.getColumnCount(); i++) // ֪��Ҫ��ѯҪ��ʾ������
			{
				this.columnNames.add(rsmt.getColumnName(i + 1)); // ����ÿһ�е�����
			}

			// ��rs�Ľ�������뵽ros��
			while (rs.next()) {
				Vector<String> temp = new Vector<String>();
				for (int i = 0; i < rsmt.getColumnCount(); i++) {
					temp.add(rs.getString(i + 1)); // ���ز�ѯ����ֵ
				}
				rowData.add(temp);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlHelper.close();
		}
	}

	// �û�ֻ����˲�ѯ��ť��û�������û��� �Ĳ�ѯ
	public void queryStu(String sql) {
		SqlHelper sqlHelper = null;
		// �м�
		columnNames = new Vector();


		// �����ݿ���ȡ������
		rowData = new Vector();
		try {
			sqlHelper = new SqlHelper();
			ResultSet rs = sqlHelper.quereyExecute(sql);
			
			
			// ��rs �����п��Եõ�ResultSetMetadata
			// rsmt���Եõ�����ֶ����У����ҿ���֪��ÿһ�е�����
			ResultSetMetaData rsmt = rs.getMetaData();
			for (int i = 0; i < rsmt.getColumnCount(); i++) // ֪��Ҫ��ѯҪ��ʾ������
			{
				this.columnNames.add(rsmt.getColumnName(i + 1)); // ����ÿһ�е�����
			}

			// ��rs�Ľ�������뵽ros��
			while (rs.next()) {
				Vector<String> temp = new Vector<String>();
				for (int i = 0; i < rsmt.getColumnCount(); i++) {
					temp.add(rs.getString(i + 1)); // ���ز�ѯ����ֵ
				}
				rowData.add(temp);
			}
			
			
			
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlHelper.close();
		}
	}

	// �õ����ж�����
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
		// return 0;
	}

	// �õ����ж�����
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
		// return 0;
	}

	// �õ�ĳ��ĳ�е�����
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return ((Vector) this.rowData.get(arg0)).get(arg1); // arg0��ʾ�� arg1 ��ʾ��
		// return null;
	}

	// ��д���� getColumnName
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}

}
