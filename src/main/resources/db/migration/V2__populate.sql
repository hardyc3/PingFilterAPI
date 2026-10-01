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