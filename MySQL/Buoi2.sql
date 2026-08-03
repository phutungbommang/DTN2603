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
						('vuthii@gmail.com','vuthii','Vu Thi I',9,1),
						('nguyenvanj@gmail.com','nguyenvanj','Nguyen Van J',10,2);
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
VALUES					('Java la gi?',1,1,1),
						('SQL la gi?',2,2,2),
						('JUnit dung de lam gi?',3,2,3),
						('Spring Boot la gi?',4,1,4),
						('HTML dung de lam gi?',5,2,5),
						('CSS dung de lam gi?',6,2,6),
						('JavaScript la gi?',7,1,7),
						('Python la gi?',8,2,8),
						('C# la gi?',9,1,9),
						('Database la gi?',10,2,10);
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
