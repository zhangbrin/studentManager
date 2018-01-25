# studentManager

本程序为一个简单的客户端形式的学生管理小系统
使用了直接的jdbc操作数据库，仅限于数据量小的数据；
能够作为操作数据库的学习范本。
调试的数据库为mysql；



-- 初始化数据如下：
create table stu
(
	stuId varchar(30) primary key,
	stuName varchar(50) not null,
	stuSex nchar(1) check (stuSex in('男','女')), 
	stuAge int check (stuAge>1),
	sutJg nvarchar(30),
	stuDept nvarchar(40)
);

 insert into stu values('sp001','孙悟空','男','20','花果山','少林派');
 insert into stu values('sp002','猪八戒','男','15','高老庄','天上的');
 insert into stu values('sp003','沙悟净','男','26','流沙河','水里的');
 insert into stu values('sp004','唐三藏','男','21','长安','庙里的');
