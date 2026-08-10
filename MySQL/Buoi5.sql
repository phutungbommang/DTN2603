DROP DATABASE IF EXISTS dtn2603_testing_system;
CREATE DATABASE IF NOT EXISTS dtn2603_testing_system;
USE dtn2603_testing_system;
CREATE TABLE department(
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(50) NOT NULL
);
INSERT INTO department	(department_name)
VALUES					('Marketing'),
						('Sale'),
						('Bao ve'),
						('Nhan su'),
						('Ky thuat'),
						('Tai chinh'),
						('Pho giam doc'),
						('Giam doc'),
						('Thu ky'),
						('Ban hang');
CREATE TABLE `position`(
    position_id INT PRIMARY KEY AUTO_INCREMENT,
    position_name ENUM('DEV','TEST','SCRUM MASTER','PM') NOT NULL
);
INSERT INTO `position`	(position_name)
VALUES					('DEV'),
						('TEST'),
						('SCRUM MASTER'),
						('PM'),
						('DEV'),
						('TEST'),
						('SCRUM MASTER'),
						('PM'),
						('DEV'),
						('TEST');
CREATE TABLE `account`(
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50) NOT NULL UNIQUE,
    username VARCHAR(50) NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    department_id INT,
    position_id INT,
    create_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (department_id)
    REFERENCES department(department_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (position_id)
    REFERENCES `position`(position_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO `account`	(email, username, full_name, department_id, position_id)
VALUES					('nguyenvana@gmail.com','nguyenvana','Nguyen Van A',1,1),
						('tranthib@gmail.com','tranthib','Tran Thi B',2,2),
						('levanc@gmail.com','levanc','Le Van C',3,3),
						('phamthid@gmail.com','phamthid','Pham Thi D',4,4),
						('hoangvane@gmail.com','hoangvane','Hoang Van E',5,1),
						('dovanf@gmail.com','dovanf','Do Van F',6,2),
						('buithig@gmail.com','buithig','Bui Thi G',7,3),
						('dangvanh@gmail.com','dangvanh','Dang Van H',8,4),
                        ('nguyenk@gmail.com','nguyenk','Nguyen Van K',9,3),
						('nguyenl@gmail.com','nguyenl','Nguyen Thi L',10,4);
                        
CREATE TABLE `group`(
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    group_name VARCHAR(50) NOT NULL,
    creator_id INT NOT NULL,
    create_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (creator_id)
    REFERENCES `account`(account_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO `group`	(group_name, creator_id)
VALUES				('Java Team',1),
					('SQL Team',2),
					('Testing Team',3),
					('Spring Team',4),
					('Backend Team',5),
					('Frontend Team',6),
					('Mobile Team',7),
					('AI Team',8),
					('DevOps Team',9),
					('Project Team',10);
CREATE TABLE group_account(
    group_id INT NOT NULL,
    account_id INT NOT NULL,
    join_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(group_id, account_id),
    FOREIGN KEY (group_id)
    REFERENCES `group`(group_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (account_id)
    REFERENCES `account`(account_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO group_account	(group_id, account_id)
VALUES						(1,2),
							(1,3),
							(1,4),
							(2,1),
							(3,1),
							(4,1);
CREATE TABLE type_question(
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name ENUM('Essay','Multiple-Choice') NOT NULL
);
INSERT INTO type_question	(type_name)
VALUES						('Essay'),
							('Multiple-Choice');
CREATE TABLE category_question(
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL
);
INSERT INTO category_question	(category_name)
VALUES							('Java'),
								('SQL'),
								('Testing'),
								('Spring'),
								('HTML'),
								('CSS'),
								('JavaScript'),
								('Python'),
								('C#'),
								('Database');
CREATE TABLE question(
    question_id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(1000) NOT NULL,
    category_id INT NOT NULL,
    type_id INT NOT NULL,
    creator_id INT NOT NULL,
    create_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id)
    REFERENCES category_question(category_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (type_id)
    REFERENCES type_question(type_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (creator_id)
    REFERENCES `account`(account_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO question	(content, category_id, type_id, creator_id)
VALUES					('Java la gi?',1,1,1),
						('SQL la gi?',2,2,2),
						('JUnit dung de lam gi?',3,2,3),
						('Spring Boot la gi?',4,1,4),
						('HTML dung de lam gi?',5,2,5),
						('CSS dung de lam gi?',6,2,6),
						('JavaScript la gi?',7,1,7),
						('Python la gi?',8,2,8),
						('C# la gi?',9,1,9),
                        ('Đây là một câu hỏi rất dài.............................................................
						.............................................................
						.............................................................
						.............................................................
						.............................................................
						.............................................................',
						10,1,10);
CREATE TABLE answer(
    answer_id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(50) NOT NULL,
    question_id INT NOT NULL,
    is_correct BOOLEAN NOT NULL,
	FOREIGN KEY (question_id)
    REFERENCES question(question_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO answer	(content, question_id, is_correct)
VALUES				('Ngon ngu lap trinh',1,1),
					('He quan tri co so du lieu',2,1),
					('Cong cu kiem thu',3,1),
					('Framework Java',4,1),
					('Ngon ngu danh dau',5,1),
					('Ngon ngu dinh dang',6,1),
					('Ngon ngu lap trinh web',7,1),
					('Ngon ngu lap trinh',8,1),
					('Ngon ngu cua Microsoft',9,1),
					('Noi luu tru du lieu',10,1);
CREATE TABLE exam(
    exam_id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) NOT NULL,
    title VARCHAR(50) NOT NULL,
    category_id INT NOT NULL,
    duration INT NOT NULL,
    creator_id INT NOT NULL,
    create_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (creator_id)
    REFERENCES `account`(account_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (category_id)
    REFERENCES category_question(category_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO exam	(code, title, category_id, duration, creator_id)
VALUES				('EX001','Java Basic',1,60,1),
					('EX002','SQL Basic',2,60,2),
					('EX003','Testing Basic',3,60,3),
					('EX004','Spring Basic',4,60,4),
					('EX005','HTML Basic',5,60,5),
					('EX006','CSS Basic',6,60,6),
					('EX007','JavaScript Basic',7,60,7),
					('EX008','Python Basic',8,60,8),
					('EX009','CSharp Basic',9,60,9),
					('EX010','Database Basic',10,60,10);
CREATE TABLE exam_question(
    exam_id INT NOT NULL,
    question_id INT NOT NULL,
    PRIMARY KEY(exam_id, question_id),
    FOREIGN KEY (exam_id)
    REFERENCES exam(exam_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (question_id)
    REFERENCES question(question_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO exam_question	(exam_id, question_id)
VALUES						(1,1),
							(2,2),
							(3,3),
							(4,4),
							(5,5),
							(6,6),
							(7,7),
							(8,8),
							(9,9),
							(10,10);

-- Question 1: Tạo view có chứa danh sách nhân viên thuộc phòng ban sale
-- VIEW
drop view if exists bai_1;
create view bai_1 as
select de.*,email,username,full_name,create_date
from department de
inner join `account` acc
on de.department_id = acc.department_id
where department_name = 'Sale';
select *
from bai_1;

-- subquery
select *
from `account`
where department_id =(
select department_id
from department
where department_name = 'Sale'); 

-- cte
with cte_1 as(
select de.*,email,username,full_name,create_date
from `account` acc
inner join department de
on acc.department_id = de.department_id)
select *
from cte_1
where department_name = 'Sale';
-- Question 2: Tạo view có chứa thông tin các account tham gia vào nhiều group nhất
-- VIEW và subquery
drop view if exists bai_2;
create view bai_2 as
select acc.*,count(gr.account_id) as so_luong
from group_account gr
inner join `account` acc
on gr.account_id = acc.account_id
group by acc.account_id
having so_luong = ( select count(gr.account_id) as so_luong
					from group_account gr
					inner join `account` acc
					on gr.account_id = acc.account_id
					group by acc.account_id
                    order by so_luong desc
                    limit 1);
select *
from bai_2;

-- cte
with cte_2 as(
select acc.*
from group_account gr
inner join `account` acc
on gr.account_id = acc.account_id)
select account_id,username,full_name,count(account_id) as so_luong
from cte_2
group by account_id 
having so_luong = ( select count(account_id) as so_luong
					from cte_2
					group by account_id
                    order by so_luong desc
                    limit 1);


-- Question 3: Tạo view có chứa câu hỏi có những content quá dài (content quá 300 từ
-- được coi là quá dài) và xóa nó đi
-- VIEW 
drop view if exists bai_3;
create view bai_3 as
select *
from question 
where char_length(content)>300;
delete from question
where char_length(content)>300;
select *
from bai_3;


-- subquery
select *
from question
where question_id = (
select question_id
from question
where char_length(content)>300);
-- cte
with cte_3 as(
select *,char_length(content) as do_dai
from question)
select *
from cte_3
where do_dai >300;
update account set username = '2' where account_id = 1;
-- Question 4: Tạo view có chứa danh sách các phòng ban có nhiều nhân viên nhất
-- VIEW và subquery
drop view if exists bai_4;
create view bai_4 as
select de.*,count(acc.department_id) as so_luong
from `account` acc
inner join department de
on acc.department_id = de.department_id
group by de.department_id
having so_luong =(
					select count(acc.department_id) as so_luong
					from `account` acc
					inner join department de
					on acc.department_id = de.department_id
					group by de.department_id
					order by so_luong desc
					limit 1);
select *
from bai_4;


-- cte 
with cte_4 as(
select de.*
from `account` acc
inner join department de
on acc.department_id = de.department_id
)
select department_id,count(department_id) as so_luong
from cte_4
group by department_id
having so_luong =(
					select count(department_id) as so_luong
                    from cte_4
					group by department_id
					order by so_luong desc
					limit 1);
-- Question 5: Tạo view có chứa tất các các câu hỏi do user họ Nguyễn tạo.
-- VIEW
drop view if exists bai_5;
create view bai_5 as
select que.*,username,full_name
from `account` acc
inner join question que
on acc.account_id = que.creator_id
where full_name like 'nguyen%';
select *
from bai_5;

-- subquery và cte
with cte_5 as(
select acc.*
from `account` acc
inner join question que
on acc.account_id = que.creator_id)
select account_id,username,full_name
from cte_5
where account_id in (
select account_id
from cte_5
where full_name like 'nguyen%');

