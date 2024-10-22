SET search_path TO allocation_systemv2;
CREATE SCHEMA IF NOT EXISTS allocation_systemv2;

CREATE TABLE clients
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name            VARCHAR(255)                            NOT NULL,
    address         VARCHAR(255)                            NOT NULL,
    contact_details VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_clients PRIMARY KEY (id)
);

CREATE TABLE allocation_systemv2.contracts
(
    id               BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    client_id        BIGINT                                  NOT NULL,
    start_date       date                                    NOT NULL,
    end_date         date                                    NOT NULL,
    billing_schedule VARCHAR(255)                            NOT NULL,
    rate_card_id     BIGINT                                  NOT NULL,
    CONSTRAINT pk_contracts PRIMARY KEY (id)
);

CREATE TABLE allocation_systemv2.invoice_projects
(
    invoice_id BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    CONSTRAINT pk_invoice_projects PRIMARY KEY (invoice_id, project_id)
);

CREATE TABLE allocation_systemv2.invoices
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    client_id      BIGINT                                  NOT NULL,
    total_amount   DECIMAL(19, 4)                          NOT NULL,
    invoice_date   date                                    NOT NULL,
    billing_period VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_invoices PRIMARY KEY (id)
);

CREATE TABLE allocation_systemv2.project_assignments
(
    id         BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    project_id BIGINT                                  NOT NULL,
    staff_id   BIGINT                                  NOT NULL,
    role       VARCHAR(255)                            NOT NULL,
    start_date date                                    NOT NULL,
    end_date   date                                    NOT NULL,
    CONSTRAINT pk_project_assignments PRIMARY KEY (id)
);

CREATE TABLE allocation_systemv2.projects
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    contract_id BIGINT                                  NOT NULL,
    name        VARCHAR(255)                            NOT NULL,
    description VARCHAR(255),
    start_date  date                                    NOT NULL,
    end_date    date                                    NOT NULL,
    CONSTRAINT pk_projects PRIMARY KEY (id)
);

CREATE TABLE allocation_systemv2.rate_cards
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    role        VARCHAR(255)                            NOT NULL,
    hourly_rate DECIMAL(19, 4)                          NOT NULL,
    CONSTRAINT pk_rate_cards PRIMARY KEY (id)
);

CREATE TABLE allocation_systemv2.skills
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_skills PRIMARY KEY (id)
);

CREATE TABLE allocation_systemv2.staff
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name            VARCHAR(255)                            NOT NULL,
    role            VARCHAR(255)                            NOT NULL,
    available_from  date                                    NOT NULL,
    contact_details VARCHAR(255)                            NOT NULL,
    CONSTRAINT pk_staff PRIMARY KEY (id)
);

CREATE TABLE allocation_systemv2.staff_skills
(
    skill_id BIGINT NOT NULL,
    staff_id BIGINT NOT NULL,
    CONSTRAINT pk_staff_skills PRIMARY KEY (skill_id, staff_id)
);

CREATE TABLE allocation_systemv2.time_logs
(
    id                    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    project_assignment_id BIGINT                                  NOT NULL,
    date                  date                                    NOT NULL,
    hours_worked          DOUBLE PRECISION                        NOT NULL,
    description           VARCHAR(255),
    CONSTRAINT pk_time_logs PRIMARY KEY (id)
);

ALTER TABLE allocation_systemv2.skills
    ADD CONSTRAINT uc_skills_name UNIQUE (name);

ALTER TABLE allocation_systemv2.contracts
    ADD CONSTRAINT FK_CONTRACTS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES allocation_systemv2.clients (id);

ALTER TABLE allocation_systemv2.contracts
    ADD CONSTRAINT FK_CONTRACTS_ON_RATE_CARD FOREIGN KEY (rate_card_id) REFERENCES allocation_systemv2.rate_cards (id);

ALTER TABLE allocation_systemv2.invoices
    ADD CONSTRAINT FK_INVOICES_ON_CLIENT FOREIGN KEY (client_id) REFERENCES allocation_systemv2.clients (id);

ALTER TABLE allocation_systemv2.projects
    ADD CONSTRAINT FK_PROJECTS_ON_CONTRACT FOREIGN KEY (contract_id) REFERENCES allocation_systemv2.contracts (id);

ALTER TABLE allocation_systemv2.project_assignments
    ADD CONSTRAINT FK_PROJECT_ASSIGNMENTS_ON_PROJECT FOREIGN KEY (project_id) REFERENCES allocation_systemv2.projects (id);

ALTER TABLE allocation_systemv2.project_assignments
    ADD CONSTRAINT FK_PROJECT_ASSIGNMENTS_ON_STAFF FOREIGN KEY (staff_id) REFERENCES allocation_systemv2.staff (id);

ALTER TABLE allocation_systemv2.time_logs
    ADD CONSTRAINT FK_TIME_LOGS_ON_PROJECT_ASSIGNMENT FOREIGN KEY (project_assignment_id) REFERENCES allocation_systemv2.project_assignments (id);

ALTER TABLE allocation_systemv2.invoice_projects
    ADD CONSTRAINT fk_invpro_on_invoice FOREIGN KEY (invoice_id) REFERENCES allocation_systemv2.invoices (id);

ALTER TABLE allocation_systemv2.invoice_projects
    ADD CONSTRAINT fk_invpro_on_project FOREIGN KEY (project_id) REFERENCES allocation_systemv2.projects (id);

ALTER TABLE allocation_systemv2.staff_skills
    ADD CONSTRAINT fk_staski_on_skill FOREIGN KEY (skill_id) REFERENCES allocation_systemv2.skills (id);

ALTER TABLE allocation_systemv2.staff_skills
    ADD CONSTRAINT fk_staski_on_staff FOREIGN KEY (staff_id) REFERENCES allocation_systemv2.staff (id);