-- Topics
INSERT INTO topics (name) VALUES ('Hardware');
INSERT INTO topics (name) VALUES ('Software');
INSERT INTO topics (name) VALUES ('Network');
INSERT INTO topics (name) VALUES ('Accounts');
INSERT INTO topics (name) VALUES ('Other');

-- Requests "pending"
INSERT INTO requests (requester_name, topic, description, status, created_at)
VALUES ('Alice Smith', 'Hardware', 'Laptop not turning on', 'pending', CURRENT_TIMESTAMP);

INSERT INTO requests (requester_name, topic, description, status, created_at)
VALUES ('Bob Johnson', 'Software', 'Unable to install application', 'pending', CURRENT_TIMESTAMP);

INSERT INTO requests (requester_name, topic, description, status, created_at)
VALUES ('Carol White', 'Network', 'WiFi not connecting', 'pending', CURRENT_TIMESTAMP);

INSERT INTO requests (requester_name, topic, description, status, created_at)
VALUES ('David Lee', 'Accounts', 'Forgot password', 'pending', CURRENT_TIMESTAMP);

-- Requests "attended" (con fechas antiguas y técnico)
INSERT INTO requests (requester_name, topic, description, status, created_at, attended_at, technician)
VALUES ('Eve Adams', 'Hardware', 'Monitor flickering issue', 'attended',
        DATEADD('DAY', -2, CURRENT_TIMESTAMP),
        DATEADD('DAY', -2, CURRENT_TIMESTAMP),
        'John Tech');

INSERT INTO requests (requester_name, topic, description, status, created_at, attended_at, technician)
VALUES ('Frank Miller', 'Software', 'System crash during update', 'attended',
        DATEADD('DAY', -1, CURRENT_TIMESTAMP),
        DATEADD('DAY', -1, CURRENT_TIMESTAMP),
        'Sara Tech');

INSERT INTO requests (requester_name, topic, description, status, created_at, attended_at, technician)
VALUES ('Grace Hall', 'Network', 'VPN not connecting', 'attended',
        DATEADD('DAY', -3, CURRENT_TIMESTAMP),
        DATEADD('DAY', -3, CURRENT_TIMESTAMP),
        'Mike Tech');
