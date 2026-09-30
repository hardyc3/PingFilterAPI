CREATE SCHEMA IF NOT EXISTS public;
SET SCHEMA public;

CREATE TABLE IF NOT EXISTS resources (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name TEXT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    deleted TIMESTAMP
);

CREATE TABLE IF NOT EXISTS resource_data (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    resource_id INTEGER NOT NULL,
    resource_key TEXT NOT NULL,
    resource_value TEXT NOT NULL,
    created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    deleted TIMESTAMP,
    UNIQUE (resource_id, resource_key, resource_value)
);

INSERT INTO resources (id, name) VALUES
    (1, 'Person1'),
    (2, 'Person2'),
    (3, 'Person3'),
    (4, 'Cell1'),
    (5, 'Website1');

INSERT INTO resource_data (resource_id, resource_key, resource_value) VALUES
    (1, 'firstname', 'mark'),
    (1, 'lastname', 'smith'),
    (1, 'role', 'admin'),
    (1, 'age', '40');

INSERT INTO resource_data (resource_id, resource_key, resource_value) VALUES
    (2, 'firstname', 'paul'),
    (2, 'lastname', 'adams'),
    (2, 'role', 'user'),
    (2, 'age', '23');

INSERT INTO resource_data (resource_id, resource_key, resource_value) VALUES
    (3, 'firstname', 'jenny'),
    (3, 'lastname', 'wallis'),
    (3, 'role', 'superuser'),
    (3, 'age', '33');

INSERT INTO resource_data (resource_id, resource_key, resource_value) VALUES
    (4, 'model', 'apple'),
    (4, 'version', '13'),
    (4, 'role', 'corporate'),
    (4, 'age', '2');

INSERT INTO resource_data (resource_id, resource_key, resource_value) VALUES
    (5, 'url', 'google.com'),
    (5, 'role', 'none');