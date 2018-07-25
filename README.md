# CRUD 
Простое create, read, update, delete приложение. Используется Apache tomcat, MySQL, Servlet и Servlet Filter.
Для работы нужно создать базу CRUDdb, создать для нее пользователя CRUD и пароль к нему 1234. Создаем в базе таблицу users:

CREATE TABLE `users` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

Теперь создаем администратора: 

INSERT INTO `users` (`id`, `name`, `login`, `password`, `role`)
VALUES
	(1, 'Administration', '1', '1', 'admin');

