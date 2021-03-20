DROP TABLE EXPENSES IF EXISTS;

CREATE TABLE EXPENSES (
   userid BIGINT NOT NULL ,
   amount BIGINT NOT NULL,
   category VARCHAR(45) NOT NULL
);

insert into expenses values(1, 10, 'спорт');
insert into expenses values(1, 150, 'спорт');
insert into expenses values(1, 120, 'питание');
insert into expenses values(1, 50, 'развлечения');
insert into expenses values(2, 120, 'спорт');
insert into expenses values(2, 180, 'питание');
insert into expenses values(2, 110, 'развлечения');

COMMIT;
