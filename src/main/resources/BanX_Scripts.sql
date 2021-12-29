drop table users_role;
drop table role;
drop table users;
drop table q_assessment_question;
drop table q_assessment;

create sequence question_sub_category_sequence 
start with 1 increment by 1 minvalue 1;

create table users(
  user_id int primary key AUTO_INCREMENT,
  username varchar(50) unique not null,
  password varchar(50) not null,
  disabled boolean default false,
  account_expired boolean default false,
  account_locked boolean default false,
  credentials_expired boolean default false
);

create table role (
  role_id integer primary key AUTO_INCREMENT,
  role_name varchar(50)
);

create table user_role (
  user_role_id integer primary key AUTO_INCREMENT,
  user_id integer references users(user_id),
  role_id integer references role(role_id)
);

insert into role (role_name) values('ADMIN');
insert into role (role_name) values('USER');

insert into users (username, password) values('admin', 'password');
insert into users (username, password) values('user', 'password');

insert into user_role (user_id, role_id) values(1,1);
insert into user_role (user_id, role_id) values(2,2);

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
  question_category_name VARCHAR(100) NOT NULL,
  question_category_description VARCHAR(100),
  created_by VARCHAR(50) NOT NULL,
  created_date TIMESTAMP,
  modified_by VARCHAR(50) NOT NULL,
  modified_date TIMESTAMP
);

CREATE TABLE q_question_sub_category(
  question_sub_category_id INT(50) PRIMARY KEY AUTO_INCREMENT,
  question_category_id INT(50) NOT NULL,
  question_sub_category_name VARCHAR(100) NOT NULL,
  question_sub_category_description VARCHAR(100),
  created_by VARCHAR(50) NOT NULL,
  created_date TIMESTAMP,
  modified_by VARCHAR(50) NOT NULL,
  modified_date TIMESTAMP,
  FOREIGN KEY (question_category_id) REFERENCES q_question_category(question_category_id)
);

CREATE TABLE q_question_type(
  question_type_id INT(50) PRIMARY KEY AUTO_INCREMENT,
  question_type_name VARCHAR(100) NOT NULL,
  question_type_description VARCHAR(100),
  created_by VARCHAR(50) NOT NULL,
  created_date TIMESTAMP,
  modified_by VARCHAR(50) NOT NULL,
  modified_date TIMESTAMP
);

INSERT INTO Q_QUESTION_TYPE (QUESTION_TYPE_ID ,QUESTION_TYPE_NAME ,QUESTION_TYPE_DESCRIPTION , created_by, created_date, modified_by, modified_date) 
  values('1','Type1','type1','admin', current_timestamp(), 'admin', current_timestamp());
  
  
CREATE TABLE q_bank_question_category(
  bank_question_category_id INT(50) PRIMARY KEY AUTO_INCREMENT,
  bank_exam_id INT(50) NOT NULL,
  question_category_id INT(50) NOT NULL,
  bank_question_correct_answer_score float(5),
  bank_question_wrong_answer_score float(5),
  bank_question_category_minutes INT(3),
  created_by VARCHAR(50) NOT NULL,
  created_date TIMESTAMP,
  modified_by VARCHAR(50) NOT NULL,
  modified_date TIMESTAMP,
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


CREATE TABLE q_question (
  question_id INT(50) PRIMARY KEY AUTO_INCREMENT,
  question_category_id INT(50) NOT NULL,
  question_sub_category_id INT(50) NOT NULL,
  question_sequence_no INT(50) NOT NULL,
  question_name VARCHAR(255) NOT NULL,
  question_option_a VARCHAR(255) NOT NULL,
  question_option_b VARCHAR(255) NOT NULL,
  question_option_c VARCHAR(255) NOT NULL,
  question_option_d VARCHAR(255) NOT NULL,
  question_option_e VARCHAR(255) NOT NULL,
  question_correct_answer VARCHAR(255) NOT NULL,
  question_correct_option VARCHAR(5) NOT NULL,
  question_notes VARCHAR(255),
  question_status INT(50) NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  created_date TIMESTAMP,
  modified_by VARCHAR(50) NOT NULL,
  modified_date TIMESTAMP,
  FOREIGN KEY (question_category_id) REFERENCES q_question_category(question_category_id),
  FOREIGN KEY (question_sub_category_id) REFERENCES q_question_sub_category(question_sub_category_id),
 
);

ALTER TABLE q_question  ALTER COLUMN question_name VARCHAR(1048576);
ALTER TABLE q_question  ALTER COLUMN question_notes VARCHAR(1048576);

ALTER TABLE q_question ADD question_type_id INT(50);
ALTER TABLE q_question ADD question_references VARCHAR(1048576);
ALTER TABLE q_question ADD question_rating INT(2);
ALTER TABLE q_question ADD FOREIGN KEY (question_type_id) REFERENCES q_question_type(question_type_id);

insert into q_question (question_category_id, question_subcategory_id, question_sequence_no, question_name, 
  question_option_a, question_option_b, question_option_c, question_option_d, 
  question_option_e, question_correct_answer, question_correct_option, question_notes, question_status,
  created_by, created_date, modified_by, modified_date) 
  values('1', '1', '1', 'What should come in the place of Question mark? in the following series 3 7 15 ? 63',
  '19', '27', '31', '35', 'None of the above', '19', '1', '(Corporation Bank Clerk Exam 10.08.2003)', 1,
  'admin', current_timestamp(), 'admin', current_timestamp());

-- CREATE TABLE q_question (
--   question_id INT(50) PRIMARY KEY AUTO_INCREMENT,
--   question_category_id INT(50) NOT NULL,
--   question_subcategory_id INT(50) NOT NULL,
--   question_sequence_no INT(50) NOT NULL,
--   question_name VARCHAR(255) NOT NULL,
--   question_notes VARCHAR(255),
--   question_status INT(50) NOT NULL,
--   created_by VARCHAR(50) NOT NULL,
--   created_date TIMESTAMP,
--   modified_by VARCHAR(50) NOT NULL,
--   modified_date TIMESTAMP
-- );

CREATE TABLE q_question_options (
  question_option_id INT(50) PRIMARY KEY AUTO_INCREMENT,
  question_option VARCHAR(255)  NOT NULL,
  is_answer INT(50) NOT NULL,
  question_id INT(50) NOT NULL,
  FOREIGN KEY (question_id) REFERENCES q_question (question_id)
);

CREATE TABLE q_assessment (
  assessment_id INT(50) PRIMARY KEY AUTO_INCREMENT,
  bank_exam_id INT(50) NOT NULL,
  user_id INT(50) NOT NULL,
  assessment_date DATE,
  start_date TIMESTAMP,
  end_date TIMESTAMP,
  status INT(50) NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  created_date TIMESTAMP,
  modified_by VARCHAR(50) NOT NULL,
  modified_date TIMESTAMP,
  FOREIGN KEY (bank_exam_id) REFERENCES q_bank_exam (bank_exam_id),
  FOREIGN KEY(user_id) REFERENCES q_user(user_id)
);

CREATE TABLE q_assessment_question(
  assessment_question_id INT(50) PRIMARY KEY AUTO_INCREMENT,
  assessment_id INT(50) NOT NULL,
  question_id INT(50) NOT NULL,
  user_answer VARCHAR(255) NOT NULL,
  correct_answer VARCHAR(255) NOT NULL,
  is_Answered boolean default false,
  is_Answered_Correctly boolean default false,
  status INT(50) NOT NULL,
  FOREIGN KEY (assessment_id) REFERENCES q_assessment(assessment_id),
  FOREIGN KEY(question_id) REFERENCES q_question(question_id)
);

create sequence q_exam_sequence 
start with 1 increment by 1 minvalue 1;

create sequence q_exam_pattern_sequence 
start with 1 increment by 1 minvalue 1;

CREATE TABLE q_exam (
  exam_id INT(50) PRIMARY KEY,
  bank_exam_id INT(50) NOT NULL,
  exam_date TIMESTAMP  NOT NULL,
  total_questions INT(5) NOT NULL,
  retry boolean default false,
  negative_mark_applicable boolean default false,
  negative_mark numeric(2,1)  NOT NULL,
  order_by INT(50) NOT NULL,
  status INT(2) NOT NULL,
  difficulty INT(2),
  created_by VARCHAR(50) NOT NULL,
  created_date TIMESTAMP,
  modified_by VARCHAR(50) NOT NULL,
  modified_date TIMESTAMP,
  FOREIGN KEY (bank_exam_id) REFERENCES q_bank_exam(bank_exam_id)
);

CREATE TABLE q_exam_pattern (
  exam_pattern_id INT(50) PRIMARY KEY,
  exam_id INT(50) NOT NULL,
  question_category_id INT(50) NOT NULL,
  question_sub_category_id INT(50) NOT NULL,
  no_of_questions INT(50) NOT NULL,
  FOREIGN KEY (exam_id) REFERENCES q_exam(exam_id),
  FOREIGN KEY (question_category_id) REFERENCES q_question_category(question_category_id),
  FOREIGN KEY (question_sub_category_id) REFERENCES q_question_sub_category(question_sub_category_id)
);

INSERT INTO q_exam 
(exam_id,bank_exam_id,exam_date,total_questions,retry,negative_mark_applicable,negative_mark,order_by,status,difficulty, created_by,created_date,modified_by,modified_date)
values
('1', '13', CURRENT_DATE(),'35', true,true,'0.25','1','0','2', 'admin', current_timestamp(), 'admin', current_timestamp());

INSERT INTO q_exam_pattern (exam_pattern_id,exam_id, question_category_id, question_sub_category_id,no_of_questions)
VALUES
('1','1','1','1','2');


CREATE TABLE q_question_type(
  question_type_id INT(50) PRIMARY KEY AUTO_INCREMENT,
  question_type_name VARCHAR(100) NOT NULL,
  question_type_description VARCHAR(100),
  created_by VARCHAR(50) NOT NULL,
  created_date TIMESTAMP,
  modified_by VARCHAR(50) NOT NULL,
  modified_date TIMESTAMP
);

INSERT INTO Q_QUESTION_TYPE (QUESTION_TYPE_ID ,QUESTION_TYPE_NAME ,QUESTION_TYPE_DESCRIPTION , created_by, created_date, modified_by, modified_date) 
  values('1','Type1','type1','admin', current_timestamp(), 'admin', current_timestamp());


ALTER TABLE q_question ADD question_type_id INT(50);
ALTER TABLE q_question ADD question_references VARCHAR(1048576);
ALTER TABLE q_question ADD question_rating INT(2);
ALTER TABLE q_question ADD FOREIGN KEY (question_type_id) REFERENCES q_question_type(question_type_id);


CREATE TABLE q_question_filter(
  question_filter_id INT(50) PRIMARY KEY AUTO_INCREMENT,
  question_category_id INT(50) NOT NULL,
  question_sub_category_id INT(50) NOT NULL,
  question_filter_name VARCHAR(100) NOT NULL,
  question_filter_description VARCHAR(100),
  created_by VARCHAR(50) NOT NULL,
  created_date TIMESTAMP,
  modified_by VARCHAR(50) NOT NULL,
  modified_date TIMESTAMP,
  FOREIGN KEY (question_category_id) REFERENCES q_question_category(question_category_id),
  FOREIGN KEY (question_sub_category_id) REFERENCES q_question_sub_category(question_sub_category_id)
);

INSERT INTO q_question_filter(question_filter_id,question_category_id,question_sub_category_id, question_filter_name,question_filter_description, created_by, created_date, modified_by, modified_date)
values('1','1','1','QuestionFilter1','questionfilter','admin', current_timestamp(), 'admin', current_timestamp());