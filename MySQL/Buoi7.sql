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
						('Ban hang'),
                        ('waiting Department');
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
    gender ENUM('M','F','U') DEFAULT 'U',
    department_id INT,
    position_id INT,
    create_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO `account`
(email,username,full_name,gender,department_id,position_id)
VALUES
('admin@gmail.com','admin','Administrator','M',1,1),
('nguyenvana@gmail.com','nguyenvana','Nguyen Van A','M',1,1),
('tranthib@gmail.com','tranthib','Tran Thi B','F',2,2),
('levanc@gmail.com','levanc','Le Van C','M',3,3),
('phamthid@gmail.com','phamthid','Pham Thi D','F',4,4),
('hoangvane@gmail.com','hoangvane','Hoang Van E','M',5,1),
('dovanf@gmail.com','dovanf','Do Van F','M',6,2),
('buithig@gmail.com','buithig','Bui Thi G','F',7,3),
('dangvanh@gmail.com','dangvanh','Dang Van H','M',8,4),
('nguyenk@gmail.com','nguyenk','Nguyen Van K','M',9,3),
('nguyenl@gmail.com','nguyenl','Nguyen Thi L','F',10,4);
                        
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
INSERT INTO group_account(group_id,account_id)
VALUES
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
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
INSERT INTO answer(content,question_id,is_correct)
VALUES
('Answer A',1,1),
('Answer B',1,1),
('Answer C',1,0),
('Answer D',1,0),

('He quan tri CSDL',2,1),
('Ngon ngu lap trinh',2,0),
('Framework',2,0),
('IDE',2,0),

('Cong cu test',3,1),
('Database',3,0),
('Framework',3,0),
('Ngon ngu',3,0);
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
('EX001','Java Basic',1,20,1),
('EX002','SQL Basic',2,20,2),
('EX003','Testing Basic',3,20,3),

('EX004','Spring Basic',4,45,4),
('EX005','HTML Basic',5,45,5),
('EX006','CSS Basic',6,45,6),

('EX007','JavaScript Basic',7,90,7),
('EX008','Python Basic',8,90,8),
('EX009','CSharp Basic',9,90,9),
('EX010','Database Basic',10,90,10);
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
-- Question 1: Tạo trigger không cho phép người dùng nhập vào Group có ngày tạo trước 1 năm trước
drop TRIGGER bai_tap1;
DELIMITER $$
CREATE TRIGGER bai_tap1
BEFORE INSERT ON `group`
FOR EACH ROW
BEGIN
    IF NEW.create_date < DATE_SUB(NOW(),INTERVAL 1 YEAR) THEN
		SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'Ngay tao ko duoc cu hon 1 nam';
    END IF;
END $$
DELIMITER ;
-- Question 2: Tạo trigger Không cho phép người dùng thêm bất kỳ user nào vào
-- department "Sale" nữa, khi thêm thì hiện ra thông báo "Department
-- "Sale" cannot add more user"
DROp TRIGGER bai_tap2;
DELIMITER $$
CREATE TRIGGER bai_tap2
BEFORE INSERT ON `account`
FOR EACH ROW
BEGIN
    DECLARE dep_name VARCHAR(100);
    SELECT department_name INTO dep_name
    FROM department
    where department_id = new.department_id;
	IF dep_name = 'Sale' THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'Department Sale cannot add more user';
    END IF;
END $$
DELIMITER ;
insert INTO `account`	(email,username,full_name,gender,department_id,position_id)
VALUES					('sale_test@gmail.com', 'user_sale', 'Pham Thi Sale', 'U', 2,1);
-- Question 3: Cấu hình 1 group có nhiều nhất là 5 user
DROp TRIGGER bai_tap3;
DELIMITER $$
CREATE TRIGGER bai_tap3
BEFORE INSERT ON group_account
FOR EACH ROW
BEGIN
    DECLARE so_luong INT;
    SELECT COUNT(*) into so_luong
    FROM group_account
    where group_id = new.group_id;
    IF so_luong >= 5 Then
		SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = '1 group co toi da 5 user';
    END IF;
END $$
DELIMITER ;
insert INTO group_account	(group_id,account_id)
VALUES						(1,4);

-- Question 4: Cấu hình 1 bài thi có nhiều nhất là 10 Question
DROp TRIGGER bai_tap4;
DELIMITER $$
CREATE TRIGGER bai_tap4
BEFORE INSERT ON exam_question
FOR EACH ROW
BEGIN
    DECLARE so_luong INT;
	SELECT COUNT(*) INTO so_luong
    FROM exam_question 
    WHERE exam_id = NEW.exam_id;
	IF so_luong >= 10 THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = '1 bai thi co toi da 10 question';
    END IF;
END $$
DELIMITER ;
DELETE FROM exam_question WHERE exam_id = 1;
INSERT INTO exam_question (exam_id, question_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
(1, 6), (1, 7), (1, 8), (1, 9), (1, 10);
-- Question 5: Tạo trigger không cho phép người dùng xóa tài khoản có email là
-- admin@gmail.com (đây là tài khoản admin, không cho phép user xóa),
-- còn lại các tài khoản khác thì sẽ cho phép xóa và sẽ xóa tất cả các thông
-- tin liên quan tới user đó

DROp TRIGGER bai_tap5;
DELIMITER $$
CREATE TRIGGER bai_tap5
BEFORE DELETE ON `account`
FOR EACH ROW
BEGIN
	IF old.email = 'admin@gmail.com' THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'Ko dc phep xoa tai khoan admin';
	else
		DELETE FROM `question` WHERE creator_id = OLD.account_id;
        DELETE FROM `exam` WHERE creator_id = OLD.account_id;
    END IF;
END $$
DELIMITER ;
DELETE FROM `account` WHERE email = 'admin@gmail.com';
DELETE FROM `account` WHERE email = 'user1@gmail.com';
-- Question 6: Không sử dụng cấu hình default cho field DepartmentID của tableaccount
-- Account, hãy tạo trigger cho phép người dùng khi tạo account không điền
-- vào departmentID thì sẽ được phân vào phòng ban "waiting Department"
DROP TRIGGER bai_tap6;
DELIMITER $$
CREATE TRIGGER bai_tap6
BEFORE INSERT ON `account`
FOR EACH ROW
BEGIN
	DECLARE cho INT;
    SELECT department_id into cho
    FROM department
    WHERE department_name = "waiting Department";
    set new.department_id = cho;
END $$
DELIMITER ;
-- Question 7: Cấu hình 1 bài thi chỉ cho phép user tạo tối đa 4 answers cho mỗi
-- question, trong đó có tối đa 2 đáp án đúng.
drop TRIGGER bai_tap7;
DELIMITER $$
CREATE TRIGGER bai_tap7
BEFORE INSERT ON answer
FOR EACH ROW
BEGIN
    DECLARE so_luong_answer INT;
    DECLARE so_luong INT;
    SELECT COUNT(*)
    INTO so_luong_answer
    FROM answer
    WHERE question_id = NEW.question_id;
    IF so_luong_answer >= 4 THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'Toi da 4 cauhoi';
    END IF;
    IF NEW.is_correct = 1 THEN
		SELECT COUNT(*)
        INTO so_luong
        FROM answer
        WHERE question_id = NEW.question_id
        AND is_correct = 1;
        IF so_luong >= 2 THEN
            SIGNAL SQLSTATE '12345'
            SET MESSAGE_TEXT = 'toi da 2 dap an dung';
        END IF;
    END IF;
END $$
DELIMITER ;
select *
from answer;
-- Question 8: Viết trigger sửa lại dữ liệu cho đúng:
-- Nếu người dùng nhập vào gender của account là nam, nữ, chưa xác định
-- Thì sẽ đổi lại thành M, F, U cho giống với cấu hình ở database
Drop trigger bai_tap8;
DELIMITER $$
CREATE TRIGGER bai_tap8
BEFORE UPDATE ON `account`
FOR EACH ROW
BEGIN
    CASE NEW.gender
        WHEN 'Nam' THEN 
            SET NEW.gender = 'M';
        WHEN 'Nu' THEN 
            SET NEW.gender = 'F';
        WHEN 'Chua xac dinh' THEN 
            SET NEW.gender = 'U';
    END CASE;
END $$
DELIMITER ;
-- Question 9: Viết trigger không cho phép người dùng xóa bài thi mới tạo được 2 ngày
drop trigger bai_tap9;
DELIMITER $$
CREATE TRIGGER bai_tap9
BEFORE DELETE ON exam
FOR EACH ROW
BEGIN
	IF DATE_SUB(NOW(), INTERVAL 2 DAY) < 2 THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'ko cho phep xoa bai thi moi tao dc 2 ngay';
    END IF;
END $$
DELIMITER ;
-- Question 10: Viết trigger chỉ cho phép người dùng chỉ được update, delete các
-- question khi question đó chưa nằm trong exam nào
DELIMITER $$
CREATE TRIGGER bai_tap10_update
BEFORE UPDATE ON question
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT *
        FROM exam_question
        WHERE question_id = OLD.question_id
    ) THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'cau hoi da co trong bai thi';
    END IF;
END $$
DELIMITER ;
-- 
DELIMITER $$
CREATE TRIGGER bai_tap10_delete
BEFORE DELETE ON question
FOR EACH ROW
BEGIN
    IF EXISTS (
        SELECT *
        FROM exam_question
        WHERE question_id = OLD.question_id
    ) THEN
        SIGNAL SQLSTATE '12345'
        SET MESSAGE_TEXT = 'cau hoi da co trong bai thi';
    END IF;
END $$
DELIMITER ;
-- Question 12: Lấy ra thông tin exam trong đó:
-- Duration <= 30 thì sẽ đổi thành giá trị "Short time"
-- 30 < Duration <= 60 thì sẽ đổi thành giá trị "Medium time"
-- Duration > 60 thì sẽ đổi thành giá trị "Long time"
SELECT 	exam_id,code,title,duration,
		CASE
            WHEN duration <= 30 THEN 'Short time'
            WHEN duration <= 60 and duration >30 THEN 'Medium time'
            ELSE 'Long time'
		END AS duration_type
FROM exam;
-- Question 13: Thống kê số account trong mỗi group và in ra thêm 1 column nữa có tên
-- là the_number_user_amount và mang giá trị được quy định như sau:
-- Nếu số lượng user trong group =< 5 thì sẽ có giá trị là few
-- Nếu số lượng user trong group <= 20 và > 5 thì sẽ có giá trị là normal
-- Nếu số lượng user trong group > 20 thì sẽ có giá trị là higher
select g.group_id,g.group_name,count(ga.group_id) as so_luong,	CASE
																	WHEN COUNT(ga.account_id) <= 5 THEN 'few'
																	WHEN COUNT(ga.account_id) <= 20 THEN 'normal'
																	ELSE 'higher'
																END AS the_number_user_amount
from `group`g
left join group_account ga
on g.group_id = ga.group_id
group by g.group_id;
-- Question 14: Thống kê số mỗi phòng ban có bao nhiêu user, nếu phòng ban nào
-- không có user thì sẽ thay đổi giá trị 0 thành "Không có User"
select de.department_id,de.department_name,if(count(ac.department_id)=0,'ko co user',count(ac.department_id)) as so_luong
from department de
left join `account` ac
on de.department_id = ac.department_id
group by de.department_id;