INSERT INTO type (name) VALUES('Две машины');
INSERT INTO type (name) VALUES('Машина и человек');
INSERT INTO type (name) VALUES('Машина и велосипед');

INSERT INTO rule (name) VALUES('Статья 1');
INSERT INTO rule (name) VALUES('Статья 2');
INSERT INTO rule (name) VALUES('Статья 3');
INSERT INTO rule (name) VALUES('Статья 4');

INSERT INTO accident (name, text, address, type_id)
VALUES('Мелкое ДТП', 'Повреждений нет', 'ул. Сумская, д.5', 1);
INSERT INTO accident (name, text, address, type_id)
VALUES('Человек на переходе', 'Вызвали Скорую Помощь', 'пл. Павлова, д.32', 2);
INSERT INTO accident (name, text, address, type_id)
VALUES('Велосипед и автомобиль', 'Водитель автомобиля скрылся', 'перекресток ул. Кирова и Строителей', 3);
INSERT INTO accident (name, text, address, type_id)
VALUES('Мелкое ДТП', 'Обоюдное нарушение', 'бульвар Капуцинов, д.11', 1);

INSERT INTO accident_rule (rule_id, accident_id) VALUES(1, 1);
INSERT INTO accident_rule (rule_id, accident_id) VALUES(2, 2);
INSERT INTO accident_rule (rule_id, accident_id) VALUES(2, 3);
INSERT INTO accident_rule (rule_id, accident_id) VALUES(1, 4);
INSERT INTO accident_rule (rule_id, accident_id) VALUES(3, 4);
INSERT INTO accident_rule (rule_id, accident_id) VALUES(4, 2);