DROP DATABASE IF EXISTS dtn2603_testing_system;
CREATE DATABASE IF NOT EXISTS dtn2603_testing_system;
USE dtn2603_testing_system;
CREATE TABLE department(
    department_id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(50) NOT NULL UNIQUE
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
						('Cho viec');
CREATE TABLE `position`(
    position_id INT PRIMARY KEY AUTO_INCREMENT,
    position_name ENUM('DEV','TEST','SCRUM MASTER','PM') NOT NULL
);

INSERT INTO `position`	(position_name)
VALUES					('DEV'),
						('TEST'),
						('SCRUM MASTER'),
						('PM');
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
INSERT INTO account	(email,username,full_name,department_id,position_id)
VALUES				('nguyenvana@gmail.com','nguyenvana','Nguyen Van A',1,1),
					('tranthib@gmail.com','tranthib','Tran Thi B',2,2),
					('levanc@gmail.com','levanc','Le Van C',3,3),
					('phamthid@gmail.com','phamthid','Pham Thi D',4,4),
					('hoangvane@gmail.com','hoangvane','Hoang Van E',5,1),
					('dovanf@gmail.com','dovanf','Do Van F',6,2),
					('buithig@gmail.com','buithig','Bui Thi G',7,3),
					('dangvanh@gmail.com','dangvanh','Dang Van H',8,4),
					('nguyenk@gmail.com','nguyenk','Nguyen Van K',9,1),
					('nguyenl@gmail.com','nguyenl','Nguyen Thi L',10,2);
                        
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
INSERT INTO group_account	(group_id,account_id)
VALUES						(1,2),
							(1,3),
							(1,4),
							(1,5),

							(2,1),
							(2,3),
							(2,4),

							(3,1),
							(3,2),
							(3,5),

							(4,1),
							(4,6),
							(4,7),

							(5,8),
							(5,9),
							(5,10);
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
INSERT INTO question	(content,category_id,type_id,creator_id,create_date)
VALUES					('Java la gi?',1,1,1,'2025-01-15'),
						('SQL la gi?',2,2,2,'2025-02-20'),
						('JUnit dung de lam gi?',3,2,3,'2025-03-10'),
						('Spring Boot la gi?',4,1,4,'2025-04-05'),
						('HTML dung de lam gi?',5,2,5,'2025-05-12'),
						('CSS dung de lam gi?',6,2,6,'2025-06-18'),
						('JavaScript la gi?',7,1,7,'2025-07-22'),
						('Python la gi?',8,2,8,'2025-08-01'),
						('C# la gi?',9,1,9,'2026-01-10'),

						('Essay content rat dai...........................................................................................................................................................................................................',
						10,1,10,'2026-08-01'),

						('Essay content rat dai...........................................................................................................................................................................................................',
						1,1,1,'2026-08-09'),

						('Multiple Choice rat dai...........................................................................................................................................................................................................',
						2,2,2,'2026-08-09');
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
INSERT INTO exam	(code,title,category_id,duration,creator_id,create_date)
VALUES				('EX001','Java Basic',1,60,1,'2022-01-01'),
					('EX002','SQL Basic',2,60,2,'2023-03-15'),
					('EX003','Testing Basic',3,60,3,'2024-05-20'),
					('EX004','Spring Basic',4,60,4,'2025-02-10'),
					('EX005','HTML Basic',5,60,5,'2025-06-01'),
					('EX006','CSS Basic',6,60,6,'2025-07-01'),
					('EX007','JS Basic',7,60,7,'2025-08-01'),
					('EX008','Python Basic',8,60,8,'2026-01-01'),
					('EX009','CSharp Basic',9,60,9,'2026-02-01'),
					('EX010','Database Basic',10,60,10,'2026-03-01');
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
INSERT INTO exam_question
VALUES		(1,1),
			(1,2),
			(1,3),

			(2,2),
			(2,3),
			(2,4),

			(3,3),
			(3,4),
			(3,5),

			(4,1),
			(4,5),
			(4,6),

			(5,7),
			(5,8),

			(6,9),
			(6,10);

-- Question 1: Tạo store để người dùng nhập vào tên phòng ban và in ra tất cả các account thuộc phòng ban đó.
DROP PROCEDURE baitap_1
delimiter $$
CREATE PROCEDURE baitap_1(in department_ten char(100))
begin
	select *
    from department de
    inner join `account` acc
    on acc.department_id = de.department_id
    where de.department_name = department_ten;
end $$
delimiter ;
call baitap_1('Sale');

    
    


-- Question 2: Tạo store để in ra số lượng account trong mỗi group.
DROP PROCEDURE baitap_2;
DELIMITER $$
CREATE PROCEDURE baitap_2(
							IN gr_group_id int,
							OUT so_luong int)
BEGIN
	SELECT count(account_id)  into so_luong
    FROM group_account gr
    where gr.group_id = gr_group_id;
END $$
DELIMITER ;
CALL baitap_2(1,@so_luong);
SELECT @so_luong;
-- Question 3: Tạo store để thống kê mỗi type question có bao nhiêu question được tạo trong tháng hiện tại.
DROP PROCEDURE baitap_3;
DELIMITER $$
CREATE PROCEDURE baitap_3(
							IN tp_name VARCHAR(50),
							OUT so_luong INT)
BEGIN
	SELECT count(question_id) INTO so_luong
	FROM type_question t
    INNER JOIN question q
    ON t.type_id = q.type_id
    WHERE t.type_name = tp_name
    AND MONTH(q.create_date) = MONTH(CURDATE())
	AND YEAR(q.create_date) = YEAR(CURDATE());
   END $$
DELIMITER ;
CALL baitap_3('Essay',@so_luong);
SELECT @so_luong;
-- Question 4: Tạo store để trả ra id của type question có nhiều câu hỏi nhất.
DROP PROCEDURE baitap_4;
DELIMITER $$
CREATE PROCEDURE baitap_4(
							OUT so_luong INT)
BEGIN
    SELECT type_id INTO so_luong
    FROM question
    WHERE type_id 
    GROUP BY type_id
    ORDER BY COUNT(question_id) DESC
    LIMIT 1;
END $$
DELIMITER ;
CALL baitap_4(@so_luong);
SELECT @so_luong;

-- Question 5: Sử dụng store ở question 4 để tìm ra tên của type question.
CALL baitap_4(@so_luong);
SELECT type_name
FROM type_question 
WHERE type_id = @so_luong;
-- Question 6: Viết 1 store cho phép người dùng nhập vào 1 chuỗi và trả về group có tên
-- chứa chuỗi của người dùng nhập vào hoặc trả về user có username chứa chuỗi của người dùng nhập vào.
DROP PROCEDURE baitap_6;
DELIMITER $$
CREATE PROCEDURE baitap_6(
							IN chuoi VARCHAR(1000),
							OUT name VARCHAR(50))
BEGIN
    SELECT COUNT(*) INTO name
    FROM `group`
    WHERE group_name LIKE CONCAT('%', chuoi, '%');
END $$
DELIMITER ;
CALL baitap_6('Java',@name);
SELECT @name;
-- Question 7: Viết 1 store cho phép người dùng nhập vào thông tin fullName, email và trong store sẽ tự động gán:
-- username sẽ giống email nhưng bỏ phần @..mail đi
-- positionID: sẽ có default là developer
-- departmentID: sẽ được cho vào 1 phòng chờ
-- Sau đó in ra kết quả tạo thành công 
-- Em chưa làm được bài này
DROP PROCEDURE baitap_7;
DELIMITER $$
CREATE PROCEDURE baitap_7(
							IN name VARCHAR(100),
							IN mail VARCHAR(100))
BEGIN
    SET name = SUBSTRING_INDEX(p_email, '@', 1);
END $$
DELIMITER ;

-- Question 8: Viết 1 store cho phép người dùng nhập vào Essay hoặc Multiple-Choice
-- để thống kê câu hỏi essay hoặc multiple-choice nào có content dài nhất
DROP PROCEDURE baitap_8;
DELIMITER $$
CREATE PROCEDURE baitap_8(
							IN ty_name VARCHAR(100),
							OUT so_luong INT)
BEGIN
    SELECT (CHAR_LENGTH(TRIM(content)) - CHAR_LENGTH(REPLACE(TRIM(content), ' ', '')) + 1) INTO so_luong
    FROM question q
    INNER JOIN type_question t ON q.type_id = t.type_id
    WHERE t.type_name = ty_name
    ORDER BY (CHAR_LENGTH(TRIM(content)) - CHAR_LENGTH(REPLACE(TRIM(content), ' ', '')) + 1) DESC
    LIMIT 1;
END $$
DELIMITER ;
CALL baitap_8('Essay',@so_luong);
SELECT @so_luong;
-- Question 9: Viết 1 store cho phép người dùng xóa exam dựa vào ID
DROP PROCEDURE baitap_9;
DELIMITER $$
CREATE PROCEDURE baitap_9(
							IN delete_id INT)
BEGIN
	DELETE FROM exam_question WHERE exam_id = delete_id;
    DELETE FROM exam WHERE exam_id = delete_id;
END $$
DELIMITER ;
CALL baitap_9(1);

-- Question 10: Tìm ra các exam được tạo từ 3 năm trước và xóa các exam đó đi (sử
-- dụng store ở câu 9 để xóa)
-- Sau đó in số lượng record đã remove từ các table liên quan trong khi removing
DROP PROCEDURE baitap_10;
DELIMITER $$

CREATE PROCEDURE baitap_10()
BEGIN

    SELECT COUNT(*) AS so_exam_question_xoa
    FROM exam_question
    WHERE exam_id IN (
						SELECT exam_id
						FROM exam
						WHERE YEAR(create_date) <= YEAR(CURDATE()) - 3
    );
	SELECT COUNT(*) AS so_exam_xoa
    FROM exam
    WHERE YEAR(create_date) <= YEAR(CURDATE()) - 3;
	DELETE FROM exam_question
    WHERE exam_id IN (
        SELECT exam_id
        FROM (
				SELECT exam_id
				FROM exam
				WHERE YEAR(create_date) <= YEAR(CURDATE()) - 3
        )
    );
    DELETE FROM exam
    WHERE YEAR(create_date) <= YEAR(CURDATE()) - 3;
END $$
DELIMITER ;
CALL baitap_10();
-- Question 11: Viết store cho phép người dùng xóa phòng ban bằng cách người dùng
-- nhập vào tên phòng ban và các account thuộc phòng ban đó sẽ được
-- chuyển về phòng ban default là phòng ban chờ việc
DROP PROCEDURE baitap_11;
DELIMITER $$
CREATE PROCEDURE baitap_11(
							IN name VARCHAR(50))
BEGIN
	UPDATE `account`
    SET department_id = (
						SELECT department_id
						FROM department
						WHERE department_name = 'Cho viec')
	WHERE department_id =(
				SELECT department_id
				FROM department
				WHERE department_name = name
			);
	DELETE FROM department
	WHERE department_name = name;
		END $$
DELIMITER ;
CALL baitap_11('Sale');
SELECT *
FROM department;
-- Question 12: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong năm nay
DROP PROCEDURE IF EXISTS baitap_12;
DELIMITER $$
CREATE PROCEDURE baitap_12()
BEGIN
	SELECT MONTH(create_date) AS thang,COUNT(question_id) AS so_luong
	FROM question
	WHERE YEAR(create_date) = YEAR(CURDATE())
	GROUP BY MONTH(create_date)
	ORDER BY MONTH(create_date);
END $$
DELIMITER ;
CALL baitap_12();
-- Question 13: Viết store để in ra mỗi tháng có bao nhiêu câu hỏi được tạo trong 6 tháng gần đây nhất
-- (Nếu tháng nào không có thì sẽ in ra là "không có câu hỏi nào trong tháng")
-- em chưa nghĩ ra a
