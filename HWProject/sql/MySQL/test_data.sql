USE `Rik`

insert into `Department`(name, location) values ("dev", "Saint-Petersburg");
insert into `Position`(title, salary) values ("midle", 1000);
insert into `Role`(name) values ("developer");
insert into User(name,email,phone,departmentRef,positionRef,roleRef) values ("test user","send@email.me","123456789",1,1,1);