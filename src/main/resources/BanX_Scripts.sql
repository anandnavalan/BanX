CREATE TABLE q_user_type(
user_type_id INT(10) PRIMARY KEY AUTO_INCREMENT,
user_type_name VARCHAR(50) NOT NULL
);

CREATE TABLE q_user(
user_id INT(10) PRIMARY KEY AUTO_INCREMENT,
user_name VARCHAR(50) NOT NULL,
user_password VARCHAR(255) NOT NULL,
user_type  INT(10)  NOT NULL,
user_email VARCHAR(255) NOT NULL,
user_mobile VARCHAR(50) NOT NULL,
UNIQUE KEY user_name(user_name),
FOREIGN KEY (user_type) REFERENCES q_user_type(user_type_id)
);

CREATE TABLE q_student(
    student_id INT(50) PRIMARY KEY AUTO_INCREMENT,
    user_id INT(10) NOT NULL,
    student_name VARCHAR(255) NOT NULL,
    student_middle_name VARCHAR(255),
    student_last_name VARCHAR(255),
    student_address VARCHAR(255),
    student_city VARCHAR(255),
    student_state VARCHAR(255),
    student_pincode INT(50),
    student_aadhar INT(50),
    student_alternate_mobile VARCHAR(50),
    student_father_name VARCHAR(50),
    student_father_mobile VARCHAR(50),
    student_mother_name VARCHAR(50),
    student_mother_mobile VARCHAR(50),
    student_joining_date DATE,
    student_dob DATE,
    student_notes VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES q_user(user_id)

);

CREATE TABLE q_student_qualification(
  qualification_id INT(50) PRIMARY KEY AUTO_INCREMENT,
  student_id INT(50) NOT NULL,
  qualification_name VARCHAR(255) NOT NULL,
  qualification_institute VARCHAR(255) NOT NULL,
  qualification_percentage INT(3),
  qualification_year INT(4),
  FOREIGN KEY (student_id) REFERENCES q_student(student_id)

);

CREATE TABLE q_bank_exam(
 bank_exam_id INT(50) PRIMARY KEY AUTO_INCREMENT,
 bank_exam_name VARCHAR(100) NOT NULL
);

CREATE TABLE q_question_category(
 question_category_id INT(50) PRIMARY KEY AUTO_INCREMENT,
 question_category_name VARCHAR(100) NOT NULL
);

