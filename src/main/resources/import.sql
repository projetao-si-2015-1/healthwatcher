ALTER TABLE `healthwatcher`.`user_authorization` ADD PRIMARY KEY (`username`, `nome`);
INSERT INTO `healthwatcher`.`authorization` (`name`) VALUES ('ROLE_ADMIN');
INSERT INTO `healthwatcher`.`authorization` (`name`) VALUES ('ROLE_USER');
INSERT INTO `healthwatcher`.`user`(username, administrador, enable, password) values ('admin', true, true, '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.');
INSERT INTO `healthwatcher`.`user_authorization` (`username`, `nome`) VALUES ('admin', 'ROLE_ADMIN');