-- SET PATH
SET search_path TO allocation_systemv2;
INSERT INTO allocation_systemv2.staff (name, role, available_from, contact_details)
VALUES ('Alice Johnson', 'FRONT_END_DEVELOPER', '2024-10-01', 'alice@techsolutions.com'),
       ('Bob Smith', 'BACK_END_DEVELOPER', '2024-09-15', 'bob@techsolutions.com'),
       ('Catherine Lee', 'QA', '2024-09-20', 'catherine@financeworks.com');

INSERT INTO allocation_systemv2.clients (name, address, contact_details)
VALUES ('Tech Solutions Inc.', '123 Silicon Valley, CA', 'contact@techsolutions.com'),
       ('FinanceWorks LLC', '456 Wall Street, NY', 'info@financeworks.com');

INSERT INTO allocation_systemv2.skills (name)
VALUES ('Java'),
       ('React'),
       ('Spring Boot'),
       ('Selenium');

INSERT INTO allocation_systemv2.rate_cards (role, hourly_rate)
VALUES ('FRONT_END_DEVELOPER', 50.00),
       ('BACK_END_DEVELOPER', 60.00),
       ('QA', 45.00),
       ('MANAGER', 80.00);

INSERT INTO allocation_systemv2.contracts (client_id, start_date, end_date, billing_schedule, rate_card_id)
VALUES (1, '2024-01-01', '2024-12-31', 'MONTHLY', 1),
       (2, '2024-02-01', '2024-07-31', 'WEEKLY', 3);
INSERT INTO allocation_systemv2.staff_skills (staff_id, skill_id)
VALUES (1, 2), -- Alice has React skill
       (2, 1), -- Bob has Java skill
       (2, 3), -- Bob also knows Spring Boot
       (3, 4); -- Catherine is skilled in Selenium

INSERT INTO allocation_systemv2.projects (contract_id, name, description, start_date, end_date)
VALUES (1, 'Website Redesign', 'Redesign the client’s corporate website.', '2024-10-01', '2024-12-31'),
       (2, 'Trading Platform QA', 'Perform quality assurance for the trading platform.', '2024-10-15', '2024-12-01');

INSERT INTO allocation_systemv2.project_assignments (project_id, staff_id, role, start_date, end_date)
VALUES (1, 1, 'FRONT_END_DEVELOPER', '2024-10-01', '2024-12-31'),
       (2, 3, 'QA', '2024-10-15', '2024-12-01');

INSERT INTO allocation_systemv2.time_logs (project_assignment_id, date, hours_worked, description)
VALUES (1, '2024-10-10', 8.0, 'Frontend development'),
       (2, '2024-10-16', 6.5, 'Testing login feature');


INSERT INTO allocation_systemv2.invoices (client_id, total_amount, invoice_date, billing_period)
VALUES (1, 4000.00, '2024-10-31', 'MONTHLY');

INSERT INTO allocation_systemv2.invoice_projects (invoice_id, project_id)
VALUES (1, 1);

SELECT * FROM allocation_systemv2.staff;
