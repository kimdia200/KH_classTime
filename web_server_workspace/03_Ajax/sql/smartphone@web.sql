create table smartphone(
    pname varchar2(30) not null,
    amount number,
    pdate date default sysdate
);

insert into smartphone values('GalaxyNote8',3,default);
insert into smartphone values('GalaxyNote8',4,default);
insert into smartphone values('GalaxyNote8',2,default);
insert into smartphone values('iPhone7',3,default);
insert into smartphone values('iPhone7',2,default);
insert into smartphone values('iPhone7Plus',2,default);
insert into smartphone values('GalaxyNote8',2,default);
insert into smartphone values('LGV30',2,default);
insert into smartphone values('iPhoneX',5,default);
insert into smartphone values('iPhoneX',2,default);
insert into smartphone values('Galaxy8',2,default);
insert into smartphone values('샤오미맥스2',2,default);
insert into smartphone values('샤오미맥스2',4,default);
insert into smartphone values('Galaxy8',1,default);


commit;

select * from smartphone
order by pdate desc;
