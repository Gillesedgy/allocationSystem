SET SEARCH_PATH TO allocationSystemV2;
---------------------------- Main Data Tables --------------------------
CREATE TABLE IF NOT EXISTS clients
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    address         VARCHAR(255) NOT NULL,
    contact_details VARCHAR(100) NOT NULL
);
CREATE TABLE IF NOT EXISTS projects
(
    id          SERIAL PRIMARY KEY,
    contract_id INTEGER      NOT NULL,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    start_date  DATE         NOT NULL,
    end_date    DATE         NOT NULL,
    FOREIGN KEY (contract_id) REFERENCES contracts (id)
);


CREATE TABLE IF NOT EXISTS staff
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    role_id         INTEGER      NOT NULL,
    available_from  DATE         NOT NULL,
    contact_details VARCHAR(100) NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS skills
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS rate_cards
(
    id          SERIAL PRIMARY KEY,
    role_id     INTEGER        NOT NULL,
    hourly_rate DECIMAL(19, 4) NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS contracts
(
    id                  SERIAL PRIMARY KEY,
    client_id           INTEGER NOT NULL,
    start_date          DATE    NOT NULL,
    end_date            DATE    NOT NULL,
    billing_schedule_id INTEGER NOT NULL,
    rate_card_id        INTEGER NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (billing_schedule_id) REFERENCES billing_schedules (id),
    FOREIGN KEY (rate_card_id) REFERENCES rate_cards (id)
);

CREATE TABLE IF NOT EXISTS project_assignments
(
    id         SERIAL PRIMARY KEY,
    project_id INTEGER NOT NULL,
    staff_id   INTEGER NOT NULL,
    role_id    INTEGER NOT NULL,
    start_date DATE    NOT NULL,
    end_date   DATE    NOT NULL,
    FOREIGN KEY (project_id) REFERENCES projects (id),
    FOREIGN KEY (staff_id) REFERENCES staff (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE IF NOT EXISTS invoices
(
    id                SERIAL PRIMARY KEY,
    client_id         INTEGER        NOT NULL,
    total_amount      DECIMAL(19, 4) NOT NULL,
    invoice_date      DATE           NOT NULL,
    billing_period_id INTEGER        NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (billing_period_id) REFERENCES billing_periods (id)
);

CREATE TABLE IF NOT EXISTS time_logs
(
    id                    SERIAL PRIMARY KEY,
    project_assignment_id INTEGER       NOT NULL,
    date                  DATE          NOT NULL,
    hours_worked          DECIMAL(5, 2) NOT NULL,
    description           TEXT,
    FOREIGN KEY (project_assignment_id) REFERENCES project_assignments (id)
);

CREATE TABLE IF NOT EXISTS staff_skills
(
    staff_id INTEGER NOT NULL,
    skill_id INTEGER NOT NULL,
    PRIMARY KEY (staff_id, skill_id),
    FOREIGN KEY (staff_id) REFERENCES staff (id),
    FOREIGN KEY (skill_id) REFERENCES skills (id)
);

CREATE TABLE IF NOT EXISTS invoice_projects
(
    invoice_id INTEGER NOT NULL,
    project_id INTEGER NOT NULL,
    PRIMARY KEY (invoice_id, project_id),
    FOREIGN KEY (invoice_id) REFERENCES invoices (id),
    FOREIGN KEY (project_id) REFERENCES projects (id)
);

------------------------ ENUMS -----------------------------------
CREATE TABLE IF NOT EXISTS roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS billing_periods
(
    id     SERIAL PRIMARY KEY,
    period VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS billing_schedules
(
    id       SERIAL PRIMARY KEY,
    schedule VARCHAR(20) NOT NULL UNIQUE
);
