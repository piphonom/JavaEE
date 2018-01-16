DELIMITER //
CREATE PROCEDURE `GetMaxSalary`()
BEGIN
select u1.* from (select u.idUser, max(p.salary)  from User u join Position p on u.positionRef = p.idPosition) tmp join User u1 on tmp.idUser=u1.idUser;
END//
DELIMITER ;