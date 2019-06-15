INSERT INTO singer (first_name, last_name, birth_date)
VALUES ('John', 'Mayer', '1977-10-16'),
       ('Eric', 'Clapton', '1945-03-30'),
       ('John', 'Butler', '1975-04-01'),
       ('y', 'z', '1993-05-19');

INSERT INTO album (singer_id, title, release_date)
VALUES (1, 'The Search For Everything', '2017-01-20'),
       (1, 'Battle Studies', '2009-11-17'),
       (2, ' From The Cradle ', '1994-09-03'),
       (4, '第一张唱片！', '2000-01-01');

INSERT INTO instrument (instrument_id)
 VALUES ('Guitar'),
        ('Piano'),
        ('Voice'),
        ('Drums'),
        ('Synthesizer');

INSERT INTO singer_instrument (singer_id, instrument_id)
 VALUES (1, 'Guitar'),
        (1,'Piano'),
        (2, 'Guitar');