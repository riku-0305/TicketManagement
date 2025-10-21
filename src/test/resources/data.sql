INSERT INTO gym_user (id, name, email_address, membership_fee, admission_day) VALUES ('090-1234-5678', 'テスト', 'test@gmail.com', 10000, '2025-09-01');
INSERT INTO gym_user (id, name, email_address, membership_fee, admission_day) VALUES ('080-1234-5678', 'テスト2', 'test2@gmail.com', 10000, '2025-09-01');
INSERT INTO gym_user (id, name, email_address, membership_fee, admission_day) VALUES ('090-1234-1234', 'テスト3', 'test3@gmail.com', 10000, '2025-09-01');

INSERT INTO tickets (user_id, remaining, buy_day, user_name, ticket_name) VALUES ('090-1234-5678', 10 , '2025-09-01', 'テスト', '指名回数券');
INSERT INTO tickets (user_id, remaining, buy_day, user_name, ticket_name) VALUES ('080-1234-5678', 10 , '2025-09-01', 'テスト2', '指名回数券');

INSERT INTO record (user_id, training_memory, training_memo, user_name) VALUES ('090-1234-5678', '2025-10-20', 'テストトレーニング', 'テスト');