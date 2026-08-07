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
VALUES					('nguyenvana@gmail.com','nguyenvana','Nguyen Van An',1,1),
						('tranthib@gmail.com','tranthib','Tran Thi Bich',2,2),
						('levanc@gmail.com','levanc','Le Van Cuong',3,3),
						('phamthid@gmail.com','phamthid','Pham Thi Dung',4,4),
						('hoangvane@gmail.com','hoangvane','Hoang Van Hung',5,1),
						('dovanf@gmail.com','dovanf','Do Van F',6,2),
						('buithig@gmail.com','buithig','Bui Thi Giang',7,3),
						('dangvanh@gmail.com','dangvanh','Dang Van Hao',8,4),
						('vuthii@gmail.com','vuthii','Vu Thi Mai Anh',9,1),
						('nguyenvanj@gmail.com','nguyenvanj','Nguyen Thi Minh Chau',10,2);
CREATE TABLE `group`(
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    group_name VARCHAR(50) NOT NULL,
    creator_id INT NOT NULL,
    create_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (creator_id)
    REFERENCES `account`(account_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO `group` (group_name, creator_id)
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
INSERT INTO question	(content, category_id, type_id, creator_id)
VALUES					('Cau hoi Java',1,1,1),
						('Cau hoi SQL',2,2,2),
						('Cau hoi Testing',3,2,3),
						('Cau hoi Spring',4,1,4),
						('Cau hoi HTML',5,2,5),
						('Cau hoi CSS',6,2,6),
						('Cau hoi JavaScript',7,1,7),
						('Cau hoi Python',8,2,8),
						('Cau hoi CSharp',9,1,9),
						('Cau hoi Database',10,2,10);
CREATE TABLE answer(
    answer_id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(50) NOT NULL,
    question_id INT NOT NULL,
    is_correct BOOLEAN NOT NULL,
	FOREIGN KEY (question_id)
    REFERENCES question(question_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
INSERT INTO answer(content,question_id,is_correct)
VALUES
('A',1,1),
('B',1,0),
('C',1,0),
('D',1,0),

('A',2,1),
('B',2,0),
('C',2,0),
('D',2,0),

('A',3,1),
('B',3,0),

('A',4,1),
('B',4,0),
('C',4,0),
('D',4,0),
('E',4,0);
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
INSERT INTO exam
(code,title,category_id,duration,creator_id)
VALUES
('EX001','Java Basic',1,45,1),
('EX002','SQL Basic',2,60,2),
('EX003','Testing Basic',3,90,3),
('EX004','Spring Basic',4,120,4),
('EX005','HTML Basic',5,75,5),
('EX006','CSS Basic',6,50,6),
('EX007','JavaScript Basic',7,80,7),
('EX008','Python Basic',8,90,8),
('EX009','CSharp Basic',9,60,9),
('EX010','Database Basic',10,150,10);
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
-- Question 1: Thêm ít nhất 10 record vào mỗi table
-- Question 2: lấy ra tất cả các phòng ban
select department_name
from department;
-- Question 3: lấy ra id của phòng ban "Sale"
select department_id
from department
where department_name = 'Sale';
-- Question 4: lấy ra thông tin account có full name dài nhất
-- Trường hợp 1: đếm cả dấu cách
select *,char_length(full_name) as ten
from `account`
order by ten desc
limit 1;
-- Trường hợp 2: ko đếm dấu cách
select *,char_length(replace(full_name,' ','')) as ten
from `account`
order by ten desc
limit 1;
--
SELECT MAX(CHAR_LENGTH(full_name))
FROM `account`;
-- Question 5: Lấy ra thông tin account có full name dài nhất và thuộc phòng ban có id= 3
SELECT MAX(CHAR_LENGTH(full_name))
FROM `account`
where department_id =3;
-- th2


-- Question 6: Lấy ra tên group đã tham gia trước ngày 05/08/2026
select *
from `group`
where create_date <'2026-08-05';

-- Question 7: Lấy ra ID của question có >= 4 câu trả lời
SELECT question_id,count(1) as so_luong
FROM answer
GROUP BY question_id
HAVING COUNT(1) >= 4;
-- Question 8: Lấy ra các mã đề thi có thời gian thi >= 60 phút và được tạo trước ngày 05/08/2026
SELECT code
FROM exam
WHERE duration >= 60
AND create_date < '2026-08-05';
-- Question 9: Lấy ra 5 group được tạo gần đây nhất
SELECT *
FROM `group`
ORDER BY create_date DESC
LIMIT 5;
-- Question 10: Đếm số nhân viên thuộc department có id = 2
SELECT COUNT(*) AS so_nhan_vien
FROM account
WHERE department_id = 2;
-- Question 11: Lấy ra nhân viên có tên bắt đầu bằng chữ "D" và kết thúc bằng chữ "o"
SELECT *
FROM account
WHERE full_name LIKE 'D%o';
-- Question 12: Xóa tất cả các exam được tạo trước ngày 20/12/2019
delete 
from exam
where create_date < '2026-12-20';
-- Question 13: Xóa tất cả các question có nội dung bắt đầu bằng từ "câu hỏi"
delete
from question
where content like "Cau hoi";


