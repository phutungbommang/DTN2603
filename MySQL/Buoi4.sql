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
INSERT INTO `account`
(email,username,full_name,department_id,position_id)
VALUES
('dev1@gmail.com','dev1','Nguyen Van A',1,1),
('dev2@gmail.com','dev2','Nguyen Van B',1,1),
('dev3@gmail.com','dev3','Nguyen Van C',1,1),
('test1@gmail.com','test1','Tran Thi D',1,2),

('pm1@gmail.com','pm1','Le Van E',2,4),
('scrum1@gmail.com','scrum1','Pham Van F',2,3),
('dev4@gmail.com','dev4','Hoang Van G',2,1),

('test2@gmail.com','test2','Vu Thi H',3,2),
('dev5@gmail.com','dev5','Dang Van I',3,1),
('dev6@gmail.com','dev6','Bui Van K',3,1);
CREATE TABLE `group`(
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    group_name VARCHAR(50) NOT NULL,
    creator_id INT NOT NULL,
    create_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (creator_id)
    REFERENCES `account`(account_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO `group`	(group_name,creator_id)
VALUES				('Java Team',1),
					('SQL Team',2),
					('Testing Team',3),
					('Spring Team',4),
					('Empty Group',5);
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
INSERT INTO group_account	(group_id,account_id)
VALUES						(1,1),
							(1,2),
							(1,3),
							(1,4),
							(2,1),
							(2,5),
							(3,2),
							(3,3),
							(3,4),
							(4,6);
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
    content VARCHAR(50) NOT NULL,
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
INSERT INTO question	(content,category_id,type_id,creator_id)
VALUES					('Java la gi?',1,1,1),
						('OOP la gi?',1,2,2),
						('Collection la gi?',1,2,3),
						('SQL la gi?',2,1,4),
						('JOIN la gi?',2,2,5),
						('JUnit dung de lam gi?',3,2,6),
						('Spring Boot la gi?',4,1,7),
						('HTML la gi?',5,2,8),
						('CSS la gi?',6,2,9),
						('JavaScript la gi?',7,1,10);
CREATE TABLE answer(
    answer_id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(50) NOT NULL,
    question_id INT NOT NULL,
    is_correct BOOLEAN NOT NULL,
	FOREIGN KEY (question_id)
    REFERENCES question(question_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO answer	(content,question_id,is_correct)
VALUES				('Ngon ngu lap trinh',1,1),
					('Framework',1,0),
					('He dieu hanh',1,0),
					('Lap trinh huong doi tuong',2,1),
					('Sai 1',2,0),
					('Dung de luu du lieu',4,1),
					('Sai 2',4,0),
					('Framework Java',7,1);
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
INSERT INTO exam_question	(exam_id,question_id)
VALUES						(1,1),
							(1,2),
							(1,3),
							(2,1),
							(2,4),
							(2,5),
							(3,1),
							(3,6),
							(4,1),
							(4,7),
							(5,8),
							(6,9),
							(7,10);


-- Question 1: Viết lệnh để lấy ra danh sách nhân viên và thông tin phòng ban của họ
select *
from `account` acc
left join department de 
on acc.department_id = de.department_id;


-- Question 2: Viết lệnh để lấy ra thông tin các account được tạo sau ngày 20/12/2010
select *
from `account`
where create_date > '2026-08-02';


-- Question 3: Viết lệnh để lấy ra tất cả các developer
select *
from `account` acc
inner join `position` po
on acc.position_id = po.position_id
left join department de 
on acc.department_id = de.department_id
where position_name = 'Dev';


-- Question 4: Viết lệnh để lấy ra danh sách các phòng ban có >3 nhân viên
select de.*,count(acc.account_id) as so_luong
from department de
left join `account` acc
on acc.department_id = de.department_id
group by department_id
having so_luong>3;


-- Question 5: Viết lệnh để lấy ra danh sách câu hỏi được sử dụng trong đề thi nhiều nhất
SELECT que.*, COUNT(ex.exam_id) AS so_luong
FROM question que
LEFT JOIN exam_question ex
ON ex.question_id = que.question_id
GROUP BY que.question_id
HAVING COUNT(ex.exam_id) = (
    SELECT COUNT(exam_id)
    FROM exam_question
    GROUP BY question_id
    ORDER BY COUNT(exam_id) DESC
    LIMIT 1
);

-- Question 6: Thông kê mỗi category Question được sử dụng trong bao nhiêu Question
select ca.*, count(que.question_id) as so_luong
from category_question ca
left join question que
on ca.category_id = que.category_id
group by category_id;


-- Question 7: Thông kê mỗi Question được sử dụng trong bao nhiêu Exam
select que.*,count(ex.exam_id) as so_luong
from exam_question ex
left join question que
on ex.question_id = que.question_id
group by question_id;


-- Question 8: Lấy ra Question có nhiều câu trả lời nhất
select que.*, count(an.answer_id) as so_luong
from answer an
left join question que
on que.question_id = an.question_id
group by question_id
order by so_luong desc
limit 1;


-- Question 9: Thống kê số lượng account trong mỗi group
select gro.*, count(gr.group_id) as so_luong
from group_account gr
right join `group` gro
on gro.group_id = gr.group_id
group by group_id;


-- Question 10: Tìm chức vụ có ít người nhất
select po.*,count(acc.position_id) as so_luong
from `position` po 
left join `account` acc
on acc.position_id = po.position_id
group by po.position_id
having so_luong =
(select count(acc.position_id) as so_luong
from `position` po 
left join `account` acc
on acc.position_id = po.position_id
group by po.position_id
order by so_luong 
limit 1);


-- Question 11: Thống kê mỗi phòng ban có bao nhiêu dev, test, scrum master, PM
select po.*,de.*,count(acc.account_id) as so_luong
from `account` acc
inner join `position` po
on acc.position_id = po.position_id
inner join department de
on de.department_id = acc.department_id
group by position_id,department_id;


-- Question 12: Lấy thông tin chi tiết của câu hỏi bao gồm: thông tin cơ bản của question, loại câu hỏi, ai là người tạo ra câu hỏi, câu trả lời là gì, …
select *
from question que
inner join type_question te
on te.type_id = que.type_id
inner join answer an
on que.question_id = an.question_id
inner join `account` acc
on acc.account_id = que.creator_id;


-- Question 13: Lấy ra số lượng câu hỏi của mỗi loại tự luận hay trắc nghiệm
select te.*, count(que.question_id) as so_luong
from type_question te
left join question que
on te.type_id = que.type_id
group by type_id;


-- Question 14:Lấy ra group không có account nào
select *
from group_account gr
right join `group` gro
on gr.group_id = gro.group_id
where gr.account_id is null;


-- Question 15:Lấy ra account không tham gia group nào
select *
from group_account gr
right join `account` acc
on gr.account_id = acc.account_id
where gr.group_id is null;


-- Question 16: Lấy ra question không có answer nào.
select *
from question que 
left join answer an
on que.question_id = an.question_id
where an.answer_id is null;


