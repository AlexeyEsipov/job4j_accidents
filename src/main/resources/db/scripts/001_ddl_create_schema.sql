CREATE TABLE IF NOT EXISTS type (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS rule (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS accident (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    text TEXT NOT NULL,
    address TEXT NOT NULL,
    type_id INT REFERENCES type(id)
);

CREATE TABLE IF NOT EXISTS accident_rule (
    id SERIAL PRIMARY KEY,
    rule_id INT REFERENCES rule(id),
    accident_id INT REFERENCES accident(id)
);