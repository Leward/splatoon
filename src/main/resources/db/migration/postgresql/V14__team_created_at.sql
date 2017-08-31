ALTER TABLE team
  ADD COLUMN created_at timestamp without time zone NOT NULL DEFAULT NOW();
