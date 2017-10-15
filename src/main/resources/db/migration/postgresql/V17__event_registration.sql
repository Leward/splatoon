ALTER TABLE tournament_event
    ADD COLUMN managed_registrations BOOLEAN NOT NULL DEFAULT 'false',
    ADD COLUMN registrations_open BOOLEAN NOT NULL DEFAULT 'false';
