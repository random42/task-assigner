INSERT INTO users(id,name,role)
VALUES
	(1,"Tony","chef"),
  (2,"Antonio","cook"),
	(3,"Mario","cook"),
  (4,"Marco","cook"),
  (5,"Roberto","cook"),
  (6,"Sara","cook"),
  (7,"Aurora","cook"),
  (8,"Luca","cook"),
  (9,"Giovanni","cook"),
  (10,"Alberto","cook"),
  (11,"Eleonora","cook"),
  (12,"Melania","cook");

INSERT INTO menus(id,title)
VALUES
	(1,"Menu' di pesce");

INSERT INTO recipes(id,name)
VALUES
	(1,"Fritto misto"),
  (2,"Sogliola grigliata"),
  (3,"Insalata di salmone crudo"),
  (4,"Calamari al sugo"),
  (5,"Trota al forno"),
  (6,"Branzino con patate");

INSERT INTO menu_recipes(menu,recipe)
VALUES
	(1,1),
  (1,2),
  (1,3),
  (1,4),
  (1,5),
  (1,6);

INSERT INTO events(id,name,menu,chef)
VALUES
	(1,"Matrimonio",1,1);

INSERT INTO workshifts(id,from_date,to_date)
VALUES
	(1,'2019-08-01 08:00:00','2019-08-01 12:00:00'),
  (2,'2019-08-02 14:00:00','2019-08-01 18:00:00'),
  (3,'2019-08-03 08:00:00','2019-08-01 12:00:00'),
  (4,'2019-08-04 14:00:00','2019-08-01 18:00:00'),
  (5,'2019-08-05 08:00:00','2019-08-01 12:00:00'),
  (6,'2019-08-06 14:00:00','2019-08-01 18:00:00');

INSERT INTO workshift_cooks(workshift,cook)
VALUES
	(6,12),
  (6,2),
  (6,3),
  (6,4),
  (6,5),
  (6,6),
  (1,7),
  (1,8),
  (1,9),
  (1,10),
  (1,11),
  (2,12),
  (2,2),
  (2,3),
  (2,4),
  (2,5),
  (2,6),
	(3,7),
  (3,8),
  (3,9),
  (3,10),
  (3,11),
  (4,12),
  (4,2),
  (4,3),
  (4,4),
  (4,5),
  (4,6),
  (5,7),
  (5,8),
  (5,9),
  (5,10),
  (5,11);
