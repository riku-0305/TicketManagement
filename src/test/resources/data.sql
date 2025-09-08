INSERT INTO gym_user (id, name, email_address, membership_fee, admission_day) VALUES ('090-1234-5678', 'テスト', 'test@gmail.com', 10000, '2025-09-01');
INSERT INTO gym_user (id, name, email_address, membership_fee, admission_day) VALUES ('080-1234-5678', 'テスト2', 'test2@gmail.com', 10000, '2025-09-01');
INSERT INTO gym_user (id, name, email_address, membership_fee, admission_day) VALUES ('090-1234-1234', 'テスト3', 'test3@gmail.com', 10000, '2025-09-01');

INSERT INTO nomination_ticket (user_id, remaining, buy_day, user_name) VALUES ('090-1234-5678', 10 , '2025-09-01', 'テスト');
INSERT INTO nomination_ticket (user_id, remaining, buy_day, user_name) VALUES ('080-1234-5678', 10 , '2025-09-01', 'テスト2');

INSERT INTO stretch_ticket (user_id, remaining, buy_day, user_name) VALUES ('090-1234-5678', 10 , '2025-09-01', 'テスト');
INSERT INTO stretch_ticket (user_id, remaining, buy_day, user_name) VALUES ('080-1234-5678', 10 , '2025-09-01', 'テスト2');