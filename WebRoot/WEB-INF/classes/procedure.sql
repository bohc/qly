--拷贝线路--
DELIMITER $$

USE `qly`$$
DROP PROCEDURE IF EXISTS `sp_copyline`$$
CREATE DEFINER=`root`@`%` PROCEDURE `sp_copyline`(IN lid INT(11))
BEGIN
    DECLARE nid INT(11);
    DECLARE t_error INTEGER DEFAULT 0;  
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1; 
    
    START TRANSACTION;
    
	INSERT INTO qly.line(lineprice,childprice,roompricediff,commreason,linename,titletuniu,platformtype,lineid,joinattribute,destinationtype,placename,placeid,passcity,linesubject,
		attribute,festivalact,linelevel,daynum,nightnum,journeypic,introduce,costinfo,costnothave,attention,prebook,calbook,state,credate,SIGN,isown,enman,viehcletime,backviehcletime)
        SELECT lineprice,childprice,roompricediff,commreason,linename,titletuniu,platformtype,lineid,joinattribute,destinationtype,placename,placeid,passcity,linesubject,attribute,
		festivalact,linelevel,daynum,nightnum,journeypic,introduce,costinfo,costnothave,attention,prebook,calbook,state,credate,SIGN,isown,enman,viehcletime,backviehcletime
		FROM qly.line WHERE id=lid;
        SELECT LAST_INSERT_ID() INTO nid;
	     
        INSERT INTO qly.citytocitypricecustom(linename,lineid,fromcityname,traficaltype,vehiclecomeseattype,vehiclecometime,price,childrenprice,vehiclebacktype,vehiclebackseattype,vehiclebacktime,entime,enman,fromcityjm,fromcitypy)
        SELECT linename,nid lineid,fromcityname,traficaltype,vehiclecomeseattype,vehiclecometime,price,childrenprice,vehiclebacktype,vehiclebackseattype,vehiclebacktime,entime,enman,fromcityjm,fromcitypy FROM qly.citytocitypricecustom WHERE lineid=lid;
	     
        INSERT INTO linepic(lineid,vpicfix) SELECT nid lineid,vpicfix FROM linepic WHERE lineid=lid;
	     
        INSERT INTO linetravel(ltid,lineid,linename,vehicle,daynum,travelcode,traveltitle,viewid,viewcontent,stay,hotel,innfeature,mark)
        SELECT nid ltid,lineid,linename,vehicle,daynum,travelcode,traveltitle,viewid,viewcontent,stay,hotel,innfeature,mark FROM linetravel WHERE ltid=lid;
	     
        UPDATE `citytocitypricecustom` a,`line`b SET a.linename=b.linename WHERE a.lineid=b.id AND a.lineid=lid;
        UPDATE `linetravel` a,`line`b SET a.linename=b.linename WHERE a.ltid=b.id AND a.ltid=lid;
	     
        SELECT p.id,p.lineid,p.lineprice,p.linename,p.joinattribute,p.destinationtype,p.placename,p.titletuniu,p.platformtype,p.placeid,p.passcity,p.linesubject,p.attribute,p.festivalact,
		p.linelevel,p.daynum,p.nightnum,p.journeypic,p.introduce,p.costinfo,p.costnothave,p.attention,p.prebook,p.calbook,p.state,p.credate,p.sign,
		p.isown,p.enman,p.commreason,p.childprice,p.roompricediff FROM qly.line p WHERE p.id=nid;
		
		CALL statCustomCount();
    
    IF t_error = 1 THEN  
		ROLLBACK;  
    ELSE  
        COMMIT;
    END IF;
    SELECT t_error;  /*将事务的执行状态返回给被调者*/
END$$

DELIMITER ;

--更新航班信息--
CREATE DEFINER=`root`@`%` PROCEDURE `airlinedeal`()
BEGIN
     declare sjm int default 0;
     declare ajm int default 0;
     
     select startcityjm,arrivecityjm into sjm,ajm from flytickettemp limit 1;
     
     delete from flyticket where DATE_FORMAT(starttime,'%Y-%m-%d') = (select max(DATE_FORMAT(starttime,'%Y-%m-%d')) from flytickettemp) and startcityjm=sjm and arrivecityjm=ajm;

     insert into qly.flyticket(fltno,aireline,airtype,startairport,arriveairport,startcity,startcityjm,arrivecity,arrivecityjm,starttime,arrivetime,offsetrate,
       offsettime,ticketprice,avgticketprice,agent,fetchdate,opttime,typetime,changeflyno,changecity)
       select fltno,aireline,airtype,startairport,arriveairport,startcity,startcityjm,arrivecity,arrivecityjm,starttime,arrivetime,offsetrate,
       offsettime,ticketprice,avgticketprice,agent,fetchdate,opttime,typetime,changeflyno,changecity 
       from qly.flytickettemp;
END;

--添加城市之间的价格--
CREATE DEFINER=`root`@`%` PROCEDURE `addCityToCityPrice`(in cnt int(4),
                                                                    in fc varchar(5000),
                                                                    in tc varchar(20),
                                                                    in price Double, 
                                                                    tra VARCHAR(50),
                                                                    enman VARCHAR(20),
                                                                    out result int(2))
BEGIN
     DECLARE existrecord int(2) default 0;
     DECLARE i int(4) default 0;
     DECLARE tfc VARCHAR(20);
     REPEAT SET i=i+1;
         SET tfc = SUBSTRING_INDEX(SUBSTRING_INDEX(fc,',',i),',',-1);
         SELECT COUNT(1) INTO existrecord from citytocityprice where fromcityjm=tfc and tocityjm=tc and traficaltype=tra;
         IF existrecord=0 THEN
            insert into citytocityprice(fromcityjm,fromcitypy,fromcityname,tocityjm,tocitypy,tocityname,entime,price,traficaltype,enman) 
            select *,now() entime,price,tra traficaltype,enman  from
            (select acode fromcityjm,areapy fromcitypy,remark fromcityname from tarea WHERE acode=tfc) a1,
            (select acode tocityjm,areapy tocitypy,remark tocityname from tarea WHERE acode=tc) a2;
         END IF;
     UNTIL i>=cnt END REPEAT;
     SET result=i;
END;

--城市之间自定义价格--
CREATE DEFINER=`root`@`%` PROCEDURE `addCityToCityPriceCustom`(in lid int(4),
                                                                    in lname varchar(1000),
                                                                    in cnt int(4),
                                                                    in fc varchar(4000),
                                                                    in price Double, 
                                                                    in childrenprice Double, 
                                                                    tra VARCHAR(50),
                                                                    vehiclecomeseattype VARCHAR(50),
                                                                    vehiclecometime VARCHAR(50),
                                                                    vehiclebacktype VARCHAR(50),
                                                                    vehiclebackseattype VARCHAR(50),
                                                                    vehiclebacktime VARCHAR(50),
                                                                    enman VARCHAR(20),
                                                                    out result int(2))
BEGIN
     DECLARE existrecord int(2) default 0;
     DECLARE i int(4) default 0;
     DECLARE tfc VARCHAR(20);
     REPEAT SET i=i+1;
         SET tfc = SUBSTRING_INDEX(SUBSTRING_INDEX(fc,',',i),',',-1);
         SELECT COUNT(1) INTO existrecord from citytocitypricecustom where fromcityjm=tfc and traficaltype=tra and lineid=lid;
         IF existrecord=0 THEN
            insert into citytocitypricecustom(fromcityjm,fromcitypy,fromcityname,entime,price,childrenprice,traficaltype,vehiclecomeseattype,vehiclecometime,vehiclebacktype,vehiclebackseattype,vehiclebacktime,enman,lineid,linename) 
            select *,now() entime,price,childrenprice,tra traficaltype,vehiclecomeseattype,vehiclecometime,vehiclebacktype,vehiclebackseattype,vehiclebacktime,enman,lid,lname  from
            (select acode fromcityjm,areapy fromcitypy,remark fromcityname from tarea WHERE acode=tfc) a1;
         END IF;
     UNTIL i>=cnt END REPEAT;
     SET result=i;
END;

--拷贝城市之间自定义价格--
CREATE DEFINER=`root`@`%` PROCEDURE `copyCityToCityPriceCustom`(in lid int(4),
                                                                    in tlen int(4),
                                                                    in tid varchar(2000),
                                                                    in pdif int(4),
                                                                    enman VARCHAR(20))
BEGIN
     DECLARE er int(2) default 0;
     DECLARE i int(4) default 0;
     DECLARE tfc VARCHAR(20);
     DECLARE lname VARCHAR(200);
     
     IF tlen>1 THEN
       REPEAT SET i=i+1;
           SET tfc = SUBSTRING_INDEX(SUBSTRING_INDEX(tid,',',i),',',-1);
           SELECT linename INTO lname FROM line where id=tfc;
           delete from citytocitypricecustom where lineid=tfc;
           insert into citytocitypricecustom(linename,lineid,fromcityname,traficaltype,vehiclecomeseattype,vehiclecometime,price,childrenprice,vehiclebacktype,vehiclebackseattype,vehiclebacktime,entime,enman,fromcityjm,fromcitypy)
           select lname,tfc,fromcityname,traficaltype,vehiclecomeseattype,vehiclecometime,price+pdif,childrenprice,vehiclebacktype,vehiclebackseattype,vehiclebacktime,now(),enman,fromcityjm,fromcitypy FROM citytocitypricecustom WHERE lineid=lid;
       UNTIL i>=tlen END REPEAT;
     ELSE
        SELECT linename INTO lname FROM line where id=tid;
        delete from citytocitypricecustom where lineid=tid;
        insert into citytocitypricecustom(linename,lineid,fromcityname,traficaltype,vehiclecomeseattype,vehiclecometime,price,childrenprice,vehiclebacktype,vehiclebackseattype,vehiclebacktime,entime,enman,fromcityjm,fromcitypy)
        select lname,tid,fromcityname,traficaltype,vehiclecomeseattype,vehiclecometime,price+pdif,childrenprice,vehiclebacktype,vehiclebackseattype,vehiclebacktime,now(),enman,fromcityjm,fromcitypy FROM citytocitypricecustom WHERE lineid=lid;
     END IF;
     call statCustomCount();
END;

--统计每条线路的自定义城市有多少
CREATE DEFINER=`root`@`%` PROCEDURE `statCustomCount`()
BEGIN
	update line a,
    (select count(1) c,lineid from `citytocitypricecustom` group by lineid) b
    set a.customcount=b.c where a.id=b.lineid;
END;


--删除线路--
DELIMITER $$
USE `qly`$$
DROP PROCEDURE IF EXISTS `sp_delline`$$
CREATE DEFINER=`root`@`%` PROCEDURE `sp_delline`(IN lid INT(11),OUT errcode INT(2))
BEGIN
    DECLARE nid INT(11);
    DECLARE t_error INTEGER DEFAULT 0;  
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1; 
    
    START TRANSACTION;
	DELETE FROM qly.`line` WHERE id=lid;
	DELETE FROM qly.`citytocitypricecustom` WHERE lineid=lid;
	DELETE FROM qly.`linepic` WHERE lineid=lid;
	DELETE FROM qly.`linetravel` WHERE ltid=lid;    
    IF t_error = 1 THEN  
	ROLLBACK;  
    ELSE
        COMMIT;
    END IF;
    SELECT t_error INTO errcode;  /*将事务的执行状态返回给被调者*/
END$$
DELIMITER ;

















