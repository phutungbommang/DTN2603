DROP DATABASE IF EXISTS dtn2603_testing_system;
CREATE DATABASE IF NOT exists dtn2603_testing_system;
USE dtn2603_testing_system;
CREATE TABLE department(
	department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(50)
);

CREATE TABLE `position`(
	position_id INT PRIMARY KEY AUTO_INCREMENT,
    position_name ENUM('DEV','TEST','SCRUM MASTER','PM')
);

CREATE TABLE `account`(
	account_id INT PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(50) UNIQUE,
    username VARCHAR(50),
    full_name VARCHAR(50),
    department_id INT,
    position_id INT,
    create_date DATETIME,
    FOREIGN KEY (department_id) 
    REFERENCES department(department_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (position_id) 
    REFERENCES `position`(position_id)
    ON DELETE CASCADE ON UPDATE CASCADE
   
);
CREATE TABLE `group`(
	group_id INT PRIMARY KEY AUTO_INCREMENT,
	group_name VARCHAR(50),
    creator_id INT,
    create_date DATETIME,
    FOREIGN KEY (creator_id) 
    REFERENCES `account`(account_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE group_account(
	group_id INT,
    account_id INT,
    join_date DATETIME,
    PRIMARY KEY(group_id, account_id),
    FOREIGN KEY (group_id) 
    REFERENCES `group`(group_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (account_id) 
    REFERENCES  `account`(account_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE type_question(
	type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name ENUM('Essay','Multiple-Choice')
);
CREATE TABLE category_question(
	category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50)
);
CREATE TABLE question(
	question_id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(50),
    category_id INT,
    type_id INT,
    creator_id INT,
    create_date DATETIME,
    FOREIGN KEY (category_id) 
    REFERENCES category_question(category_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (type_id) 
    REFERENCES  type_question(type_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (creator_id) 
    REFERENCES `account`(account_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE answer(
	answer_id INT PRIMARY KEY AUTO_INCREMENT,
    content VARCHAR(50),
    question_id INT,
    is_correct BOOLEAN,
    FOREIGN KEY (question_id) 
    REFERENCES question(question_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE exam(
	exam_id INT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50),
    title VARCHAR(50),
    category_id INT,
    duration INT,
    creator_id INT,
    create_date DATE,
    FOREIGN KEY (creator_id) 
    REFERENCES `account`(account_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (category_id) 
    REFERENCES category_question(category_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE exam_question(
	exam_id INT,
    question_id INT,
    PRIMARY KEY(exam_id, question_id),
    FOREIGN KEY (exam_id) 
    REFERENCES exam(exam_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (question_id) 
    REFERENCES question(question_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);