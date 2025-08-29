INSERT INTO topics (name) VALUES ('Hardware');
INSERT INTO topics (name) VALUES ('Software');
INSERT INTO topics (name) VALUES ('Network');
INSERT INTO topics (name) VALUES ('Accounts');
INSERT INTO topics (name) VALUES ('Other');

INSERT INTO requests (requester_name, topic, description, status, created_at)
VALUES ('Alice Smith', 'Hardware', 'Laptop not turning on', 'pending', CURRENT_TIMESTAMP);

INSERT INTO requests (requester_name, topic, description, status, created_at)
VALUES ('Bob Johnson', 'Software', 'Unable to install application', 'pending', CURRENT_TIMESTAMP);

INSERT INTO requests (requester_name, topic, description, status, created_at)
VALUES ('Carol White', 'Network', 'WiFi not connecting', 'pending', CURRENT_TIMESTAMP);

INSERT INTO requests (requester_name, topic, description, status, created_at)
VALUES ('David Lee', 'Accounts', 'Forgot password', 'pending', CURRENT_TIMESTAMP);
