INSERT INTO `healthwatcher`.`authorization` (`name`) VALUES ('ROLE_ADMIN');
INSERT INTO `healthwatcher`.`authorization` (`name`) VALUES ('ROLE_USER');
INSERT INTO `healthwatcher`.`employee`(login, name, enable, password) values ('admin', 'Administrador', true, '$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.');

INSERT INTO `healthwatcher`.`medicalspecialty`(code, description) values (0, 'Cardiology');
INSERT INTO `healthwatcher`.`medicalspecialty`(code, description) values (1, 'Odontology');
INSERT INTO `healthwatcher`.`medicalspecialty`(code, description) values (2, 'Oncology');
INSERT INTO `healthwatcher`.`medicalspecialty`(code, description) values (3, 'Psicotherapy');
INSERT INTO `healthwatcher`.`medicalspecialty`(code, description) values (4, 'Urology');