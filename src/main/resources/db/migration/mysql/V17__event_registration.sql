ALTER TABLE tournament_event
    ADD COLUMN managed_registrations TINYINT(1) NOT NULL DEFAULT 0,
    ADD COLUMN registrations_open TINYINT(1) NOT NULL DEFAULT 0;
