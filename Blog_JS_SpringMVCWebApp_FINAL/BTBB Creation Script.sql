DROP DATABASE IF EXISTS bennyTheBakerBlog;
CREATE DATABASE bennyTheBakerBlog;
USE bennyTheBakerBlog;

CREATE TABLE IF NOT EXISTS designation_Role
(roleId INT NOT NULL AUTO_INCREMENT,
name VARCHAR(35) NOT NULL,
PRIMARY KEY (roleId));

CREATE TABLE IF NOT EXISTS user 
(userId INT NOT NULL AUTO_INCREMENT,
name VARCHAR(75) NOT NULL,
userName VARCHAR(75) NOT NULL,
email VARCHAR(75) NOT NULL,
password VARCHAR(75) NOT NULL,    
PRIMARY KEY (userId));

CREATE TABLE IF NOT EXISTS user_designation_Role 
(userRoleId INT NOT NULL AUTO_INCREMENT,
userId int NOT NULL,
roleId int NOT NULL,
PRIMARY KEY (userRoleId));

ALTER TABLE user_designation_Role
ADD CONSTRAINT fk_UserRole_UserId FOREIGN KEY (userId)
REFERENCES user(userId) ON DELETE NO ACTION;

ALTER TABLE user_designation_Role
ADD CONSTRAINT fk_UserRole_RoleId FOREIGN KEY (roleId)
REFERENCES designation_Role(roleId) ON DELETE NO ACTION;

CREATE TABLE IF NOT EXISTS Tags 
(tagId INT NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
PRIMARY KEY (tagId));

create table post_Approval_Status (
approvalStatusId tinyint PRIMARY KEY auto_increment,
description VARCHAR(50) NOT NULL);

CREATE TABLE IF NOT EXISTS blog_Post 
(blogPostId INT NOT NULL AUTO_INCREMENT,
blogTitle VARCHAR(75) NOT NULL,
content VARCHAR(50000) NOT NULL,
approvalStatusId tinyint NOT NULL,
blogPostDate datetime NOT NULL,
imageLocation VARCHAR(400),
userId int NOT NULL,
PRIMARY KEY (blogPostId));

ALTER TABLE blog_Post
ADD CONSTRAINT fk_Post_UserId FOREIGN KEY (userId)
REFERENCES user(userId) ON DELETE NO ACTION;
    
ALTER TABLE blog_Post
ADD CONSTRAINT fk_Post_ApprovalStatusId FOREIGN KEY (approvalStatusId)
REFERENCES post_Approval_Status(approvalStatusId) ON DELETE NO ACTION;

CREATE TABLE IF NOT EXISTS blog_Post_Tags 
(postTagId INT NOT NULL AUTO_INCREMENT,
blogPostId int NOT NULL,
tagId int NOT NULL,
PRIMARY KEY (postTagId));

ALTER TABLE blog_Post_Tags
ADD CONSTRAINT fk_PostTags_blogPostId FOREIGN KEY (blogPostId)
REFERENCES blog_Post(blogPostId) ON DELETE NO ACTION;

ALTER TABLE blog_Post_Tags
ADD CONSTRAINT fk_PostTags_tagId FOREIGN KEY (tagId)
REFERENCES Tags(tagId) ON DELETE NO ACTION;

INSERT INTO user (name, userName, password, email)
VALUES('Admin', 'admin', '$2a$10$LPY6NE9Svn0DHGaIa2P8a.1jOjmsbpBviP3m5Ht9piipMklZC3rcC', 'admin@btbb.com'),
('Admin', 'admin', '$2a$10$LPY6NE9Svn0DHGaIa2P8a.1jOjmsbpBviP3m5Ht9piipMklZC3rcC','a@a.com');

INSERT INTO post_Approval_Status (description)
VALUES ('Unapproved'), ('Approved');

INSERT INTO designation_Role (name)
VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO user_designation_Role (userId, roleId)
VALUES (1, 1), (1, 2);
