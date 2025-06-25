INSERT INTO vets VALUES (default, 'James', 'Carter');
INSERT INTO vets VALUES (default, 'Helen', 'Leary');
INSERT INTO vets VALUES (default, 'Linda', 'Douglas');
INSERT INTO vets VALUES (default, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (default, 'Henry', 'Stevens');
INSERT INTO vets VALUES (default, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (default, 'radiology');
INSERT INTO specialties VALUES (default, 'surgery');
INSERT INTO specialties VALUES (default, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (default, 'cat');
INSERT INTO types VALUES (default, 'dog');
INSERT INTO types VALUES (default, 'lizard');
INSERT INTO types VALUES (default, 'snake');
INSERT INTO types VALUES (default, 'bird');
INSERT INTO types VALUES (default, 'hamster');

INSERT INTO temperaments VALUES (DEFAULT, 'Friendly');
INSERT INTO temperaments VALUES (DEFAULT, 'Aggressive');
INSERT INTO temperaments VALUES (DEFAULT, 'Calm');
INSERT INTO temperaments VALUES (DEFAULT, 'Playful');
INSERT INTO temperaments VALUES (DEFAULT, 'Shy');
INSERT INTO temperaments VALUES (DEFAULT, 'Loyal');
INSERT INTO temperaments VALUES (DEFAULT, 'Independent');

INSERT INTO owners VALUES (default, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023');
INSERT INTO owners VALUES (default, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749');
INSERT INTO owners VALUES (default, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763');
INSERT INTO owners VALUES (default, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198');
INSERT INTO owners VALUES (default, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765');
INSERT INTO owners VALUES (default, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654');
INSERT INTO owners VALUES (default, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387');
INSERT INTO owners VALUES (default, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683');
INSERT INTO owners VALUES (default, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435');
INSERT INTO owners VALUES (default, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487');

INSERT INTO pets VALUES (default, 'Leo',      '2010-09-07', 1, 1, 60.5, 25.0, 1);  -- Dog
INSERT INTO pets VALUES (default, 'Basil',    '2012-08-06', 6, 1, 15.0, 3.2,  2);  -- Hamster
INSERT INTO pets VALUES (default, 'Rosy',     '2011-04-17', 2, 1, 35.0, 12.0, 3);  -- Cat
INSERT INTO pets VALUES (default, 'Jewel',    '2010-03-07', 2, 1, 34.0, 11.5, 3);  -- Cat
INSERT INTO pets VALUES (default, 'Iggy',     '2010-11-30', 3, 1, 50.0, 4.5,  4);  -- Lizard
INSERT INTO pets VALUES (default, 'George',   '2010-01-20', 4, 1, 30.0, 0.5,  5);  -- Snake
INSERT INTO pets VALUES (default, 'Samantha', '2012-09-04', 1, 1, 55.0, 20.0, 6);  -- Dog
INSERT INTO pets VALUES (default, 'Max',      '2012-09-04', 1, 1, 58.0, 22.0, 6);  -- Dog
INSERT INTO pets VALUES (default, 'Lucky',    '2011-08-06', 5, 1, 10.0, 1.8,  7);  -- Bird
INSERT INTO pets VALUES (default, 'Mulligan', '2007-02-24', 2, 1, 36.0, 12.5, 8);  -- Cat
INSERT INTO pets VALUES (default, 'Freddy',   '2010-03-09', 5, 1, 12.0, 2.1,  9);  -- Bird
INSERT INTO pets VALUES (default, 'Lucky',    '2010-06-24', 2, 1, 33.0, 11.0, 10); -- Cat
INSERT INTO pets VALUES (default, 'Sly',      '2012-06-08', 1, 1, 62.0, 26.0, 10); -- Dog


INSERT INTO visits VALUES (default, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits VALUES (default, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits VALUES (default, 8, '2013-01-03', 'neutered');
INSERT INTO visits VALUES (default, 7, '2013-01-04', 'spayed');
