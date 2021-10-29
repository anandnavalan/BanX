CREATE TABLE q_user_type(
user_type_id INT(10) PRIMARY KEY AUTO_INCREMENT,
user_type_name VARCHAR(50) NOT NULL,
created_by VARCHAR(50) NOT NULL,
created_date TIMESTAMP,
modified_by VARCHAR(50) NOT NULL,
modified_date TIMESTAMP
);

CREATE TABLE q_user(
user_id INT(10) PRIMARY KEY AUTO_INCREMENT,
user_name VARCHAR(50) NOT NULL,
user_password VARCHAR(255) NOT NULL,
user_type  INT(10)  NOT NULL,
user_email VARCHAR(255) NOT NULL,
user_mobile VARCHAR(50) NOT NULL,
created_by VARCHAR(50) NOT NULL,
created_date TIMESTAMP,
modified_by VARCHAR(50) NOT NULL,
modified_date TIMESTAMP,
UNIQUE KEY user_name(user_name),
FOREIGN KEY (user_type) REFERENCES q_user_type(user_type_id)
);

CREATE TABLE q_student(
    student_id INT(50) PRIMARY KEY AUTO_INCREMENT,
    user_id INT(10) NOT NULL,
    student_first_name VARCHAR(255) NOT NULL,
    student_middle_name VARCHAR(255),
    student_last_name VARCHAR(255),
    student_address VARCHAR(255),
    student_city VARCHAR(255),
    student_state VARCHAR(255),
    student_pincode INT(50),
    student_aadhar BIGINT(50),
    student_alternate_mobile VARCHAR(50),
    student_father_name VARCHAR(50),
    student_father_mobile VARCHAR(50),
    student_mother_name VARCHAR(50),
    student_mother_mobile VARCHAR(50),
    student_joining_date DATE,
    student_dob DATE,
    student_notes VARCHAR(255),
    created_by VARCHAR(50) NOT NULL,
created_date TIMESTAMP,
modified_by VARCHAR(50) NOT NULL,
modified_date TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES q_user(user_id)
);

CREATE TABLE q_student_qualification(
  qualification_id INT(50) PRIMARY KEY AUTO_INCREMENT,
  student_id INT(50) NOT NULL,
  qualification_name VARCHAR(255) NOT NULL,
  qualification_institute VARCHAR(255) NOT NULL,
  qualification_percentage INT(3),
  qualification_year INT(4),
  created_by VARCHAR(50) NOT NULL,
created_date TIMESTAMP,
modified_by VARCHAR(50) NOT NULL,
modified_date TIMESTAMP,
  FOREIGN KEY (student_id) REFERENCES q_student(student_id)
);

CREATE TABLE q_bank_exam(
 bank_exam_id INT(50) PRIMARY KEY AUTO_INCREMENT,
 bank_exam_name VARCHAR(100) NOT NULL,
 created_by VARCHAR(50) NOT NULL,
  created_date TIMESTAMP,
  modified_by VARCHAR(50) NOT NULL,
  modified_date TIMESTAMP
);

CREATE TABLE q_question_category(
 question_category_id INT(50) PRIMARY KEY AUTO_INCREMENT,
 question_category_name VARCHAR(100) NOT NULL
 created_by VARCHAR(50) NOT NULL,
created_date TIMESTAMP,
modified_by VARCHAR(50) NOT NULL,
modified_date TIMESTAMP,
);

CREATE TABLE q_bank_question_category(
bank_question_category_id INT(50) PRIMARY KEY AUTO_INCREMENT,
bank_exam_id INT(50) NOT NULL,
question_category_id INT(50) NOT NULL,
bank_question_correct_answer_score float(5),
bank_question_wrong_answer_score float(5),
bank_question_category_minutes INT(3),
FOREIGN KEY (bank_exam_id) REFERENCES q_bank_exam (bank_exam_id),
FOREIGN KEY (question_category_id) REFERENCES q_question_category(question_category_id)
);

CREATE TABLE q_student_bank_exam(

student_bank_exam_id INT(50) PRIMARY KEY AUTO_INCREMENT,
FOREIGN KEY (student_id) REFERENCES q_student(student_id),
FOREIGN KEY(bank_exam_id) REFERENCES q_bank_exam(bank_exam_id)
);

CREATE TABLE q_question_complexity(
 question_complexity_id INT(50) PRIMARY KEY AUTO_INCREMENT,
 question_complexity_name VARCHAR(50) NOT NULL
);

CREATE TABLE plot_question(
  plot_question_id INT(50) PRIMARY KEY AUTO_INCREMENT,
  plot_question_title VARCHAR(255) NOT NULL,
  plot_question_description   NOT NULL
);