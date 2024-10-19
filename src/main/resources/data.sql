
-- SET PATH
SET SEARCH_PATH TO allocationSystem;

INSERT INTO roles (name)
VALUES ('DEVELOPER'),
       ('MANAGER'),
       ('QA'),
       ('DESIGNER');

INSERT INTO billing_periods (period)
VALUES ('WEEKLY'),
       ('MONTHLY'),
       ('QUARTERLY'),
       ('YEARLY');

INSERT INTO billing_schedules (schedule)
VALUES ('WEEKLY'),
       ('BI_WEEKLY'),
       ('MONTHLY'),
       ('QUARTERLY');

INSERT INTO skills (name)
VALUES ('Java');
--        ,
--        ('Spring Boot'),
--        ('Hibernate'),
--        ('React'),
--        ('SQL');

INSERT INTO clients (name, address, contact_details)
VALUES ('Acme Corporation', '123 Elm Street, Springfield', 'contact@acme.com');

INSERT INTO rate_cards (role_id, hourly_rate)
VALUES (1, 50.00), -- DEVELOPER
       (2, 80.00), -- MANAGER
       (3, 40.00), -- QA
       (4, 45.00); -- DESIGNER


INSERT INTO contracts (client_id, start_date, end_date, billing_schedule_id, rate_card_id)
VALUES (1, '2024-05-01', '2024-12-31', 1, 1);

INSERT INTO projects (contract_id, name, description, start_date, end_date)
VALUES (1, 'Website Redesign', 'Redesigning the corporate website with new features.', '2024-05-01', '2024-05-14');


INSERT INTO staff (name, role_id, available_from, contact_details)
VALUES ('Alice Johnson', 1, '2024-04-25', 'alice.johnson@acme.com'),
       ('Bob Smith', 2, '2024-04-20', 'bob.smith@acme.com'),
       ('Charlie Brown', 3, '2024-04-22', 'charlie.brown@acme.com');
