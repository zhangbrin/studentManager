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
	// rowData用来存放行数据
	// columnNames存放列名
	Vector<Vector<String>> rowData;
	Vector<String> columnNames;

	// 增删改学生信息
	public boolean updStu(String sql, String[] paras) {
		// 创建一个 SqlHelper(如果程序的并发性不考虑可以把对象做出静态的)
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.updExecute(sql, paras);
	}

	// 查询的本质就是初始化，带条件的查询
	public void queryStu(String sql, String[] paras) {
		SqlHelper sqlHelper = null;
		// 中间
		columnNames = new Vector<String>();

		// 从数据库中取出数据
		rowData = new Vector<Vector<String>>();
		try {
			sqlHelper = new SqlHelper();
			ResultSet rs = sqlHelper.queryExecute(sql, paras);

			// 从rs 对象中可以得到ResultSetMetadata
			// rsmt可以得到结果又多少列，而且可以知道每一列的名字
			ResultSetMetaData rsmt = rs.getMetaData();
			for (int i = 0; i < rsmt.getColumnCount(); i++) // 知道要查询要显示多少列
			{
				this.columnNames.add(rsmt.getColumnName(i + 1)); // 返回每一列的名字
			}

			// 把rs的结果集放入到ros中
			while (rs.next()) {
				Vector<String> temp = new Vector<String>();
				for (int i = 0; i < rsmt.getColumnCount(); i++) {
					temp.add(rs.getString(i + 1)); // 返回查询到的值
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

	// 用户只点击了查询按钮，没有输入用户名 的查询
	public void queryStu(String sql) {
		SqlHelper sqlHelper = null;
		// 中间
		columnNames = new Vector();


		// 从数据库中取出数据
		rowData = new Vector();
		try {
			sqlHelper = new SqlHelper();
			ResultSet rs = sqlHelper.quereyExecute(sql);
			
			
			// 从rs 对象中可以得到ResultSetMetadata
			// rsmt可以得到结果又多少列，而且可以知道每一列的名字
			ResultSetMetaData rsmt = rs.getMetaData();
			for (int i = 0; i < rsmt.getColumnCount(); i++) // 知道要查询要显示多少列
			{
				this.columnNames.add(rsmt.getColumnName(i + 1)); // 返回每一列的名字
			}

			// 把rs的结果集放入到ros中
			while (rs.next()) {
				Vector<String> temp = new Vector<String>();
				for (int i = 0; i < rsmt.getColumnCount(); i++) {
					temp.add(rs.getString(i + 1)); // 返回查询到的值
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

	// 得到共有多少列
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
		// return 0;
	}

	// 得到共有多少行
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
		// return 0;
	}

	// 得到某行某列的数据
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return ((Vector) this.rowData.get(arg0)).get(arg1); // arg0表示行 arg1 表示列
		// return null;
	}

	// 重写方法 getColumnName
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}

}
