-- Passwords is 123 encrypted
INSERT INTO user_tbl (name, email, password) VALUES('Admin', 'admin@email.com', '$2a$12$Wya/oflFfseMnsRuzxAd/ehjl3lbqfjvis9PvpnHOItWSqiJc5rEi');
INSERT INTO user_tbl (name, email, password) VALUES('Manager', 'manager@email.com', '$2a$12$Wya/oflFfseMnsRuzxAd/ehjl3lbqfjvis9PvpnHOItWSqiJc5rEi');
INSERT INTO user_tbl (name, email, password) VALUES('User', 'user@email.com', '$2a$12$Wya/oflFfseMnsRuzxAd/ehjl3lbqfjvis9PvpnHOItWSqiJc5rEi');

INSERT INTO role (name) VALUES ('ADMIN');
INSERT INTO role (name) VALUES ('MANAGER');
INSERT INTO role (name) VALUES ('USER');

-- Assign role ADMIN to the user Admin
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
-- Assign role MANAGER to the user Manager
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
-- Assign role User to the user User
INSERT INTO user_role (user_id, role_id) VALUES (3, 3);