CREATE TABLE task (
                      id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                      title         VARCHAR(255) NOT NULL,
                      status        VARCHAR(50) NOT NULL,
                      position      INT NOT NULL,
                      category      VARCHAR(50),
                      skipped_count INT NOT NULL DEFAULT 0
);