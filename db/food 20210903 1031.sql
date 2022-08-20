-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.20-MariaDB


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema foodcalorie
--

CREATE DATABASE IF NOT EXISTS foodcalorie;
USE foodcalorie;

--
-- Definition of table `dietplan`
--

DROP TABLE IF EXISTS `dietplan`;
CREATE TABLE `dietplan` (
  `did` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `breakfast` varchar(400) NOT NULL,
  `lunch` varchar(400) NOT NULL,
  `dinner` varchar(400) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dietplan`
--

/*!40000 ALTER TABLE `dietplan` DISABLE KEYS */;
INSERT INTO `dietplan` (`did`,`breakfast`,`lunch`,`dinner`,`type`) VALUES 
 (1,'porridge, clotted cream, fresh cream custard & fruit.<br> Toasted malt loaf with butter & chocolate spread','high fat roasted meat with olive oil. Roasted vegetables with olive oil.<br> Milky way white chocolate dessert with<br> fresh banana.','tuna in olive oil. Freshly made mash wiith added butter.<br> Pureed full fat baked beans.<br> Rice pudding with added clotted cream. ','Under Weight'),
 (2,'2-3 scrambled egg whites with a whole grain toast and<br> a fruit of your choice / a bowl of fuit oats porridge with sprouts salad','a bowl of dal/chicken/fish curry with<br> brown rice / roti and a veg salad','a bowl of chicken/fish/paneer with roti/chila/quinoa preparations<br> and soup or salad with veggies','Normal'),
 (3,'2 boiled eggs and 1 fruit','2 apples, sweet potato, green vegitables, low fat cheese, tomato,<br> 1 fruit, steamed vegetables','1 plate salad, boild egg, salad and chicken, grilled fish, steame vegetables','Over Weight'),
 (4,'coffee, baked egg, apple, butter sandwich','spinach salad, curried chicken salad, lemon &<br> beet salad, veggie wraps','fruits, 1 hard boiled egg, vegetable salad, black coffee','Obese'),
 (5,'milk 150ML, sprouted 20gm','2 chapati, 1/2 plate rice, 1 cup dhal, 1 cup green leafy veg, 1 plate salad','1 chapati, 1 plate khichadi, 1 cup kadi,, 1 cup vegetables, <br>1 cup veg soup, 1 plate salad','diabetic');
/*!40000 ALTER TABLE `dietplan` ENABLE KEYS */;


--
-- Definition of table `exercisedata`
--

DROP TABLE IF EXISTS `exercisedata`;
CREATE TABLE `exercisedata` (
  `euid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ecalories` varchar(45) NOT NULL,
  `edate` varchar(45) NOT NULL,
  PRIMARY KEY (`euid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exercisedata`
--

/*!40000 ALTER TABLE `exercisedata` DISABLE KEYS */;
INSERT INTO `exercisedata` (`euid`,`ecalories`,`edate`) VALUES 
 (16,'50','2019-10-01');
/*!40000 ALTER TABLE `exercisedata` ENABLE KEYS */;


--
-- Definition of table `fooddetails`
--

DROP TABLE IF EXISTS `fooddetails`;
CREATE TABLE `fooddetails` (
  `fid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(45) NOT NULL,
  `energy` varchar(45) NOT NULL COMMENT 'kcal',
  `protien` varchar(45) NOT NULL COMMENT 'grams',
  `carbohydrates` varchar(45) NOT NULL COMMENT 'grams',
  `fiber` varchar(45) NOT NULL COMMENT 'A,B,C,D,E',
  `fat` varchar(45) NOT NULL COMMENT 'grams',
  `cholesterol` varchar(45) NOT NULL COMMENT 'grams',
  `calcium` varchar(45) NOT NULL,
  `iron` varchar(45) NOT NULL,
  `magnesium` varchar(45) NOT NULL,
  `phosphorus` varchar(45) NOT NULL,
  `sodium` varchar(45) NOT NULL,
  `potassium` varchar(45) NOT NULL,
  `zinc` varchar(45) NOT NULL,
  `vita` varchar(45) NOT NULL,
  `vitb1` varchar(45) NOT NULL,
  `vitb2` varchar(45) NOT NULL,
  `vitb3` varchar(45) NOT NULL,
  `vitc` varchar(45) NOT NULL,
  `vite` varchar(45) NOT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fooddetails`
--

/*!40000 ALTER TABLE `fooddetails` DISABLE KEYS */;
INSERT INTO `fooddetails` (`fid`,`fname`,`energy`,`protien`,`carbohydrates`,`fiber`,`fat`,`cholesterol`,`calcium`,`iron`,`magnesium`,`phosphorus`,`sodium`,`potassium`,`zinc`,`vita`,`vitb1`,`vitb2`,`vitb3`,`vitc`,`vite`) VALUES 
 (1,'poha','180 cal','2.2 gm','25 gm','0.3 gm','7.9 gm','0 mg','13.7 mg','6.1 mg','32.9 mg','79.7 mg','3.9 mg','67.2 mg','0.1 mg','67.6 mcg','0.1 mg','0 mg','1.3 mg','1.9 mg','0 mg'),
 (2,'idli','33 cal','1 pm','7.2gm','0.3 gm','0.1 gm','0 mg','4 mg','0.2 mg','7.5 mg','19.2mg','1.1 mg','30.8 mg','0.2 mg','0.8 mcg','0 mg','0 mg','0.3mg','0 mg','0 mg'),
 (3,'medu vada','73 cal','5 gm','12.5 gm','2.5 gm','0.3 gm','0 mg','32.3 gm','0.8 mg','27.3 mg','80.8 mg','8.4 mg','168 mg','0.6 mg','8 mcg','0.1 mg','0.1 mg','0.4 mg','0 mg','0 mg'),
 (4,'dosa','133 cal','2.7 gm','18.8 gm','1.1 gm','5.2 gm','0 mg','10.9 gm','0.5 mg','22.4 mg','52.6 mg','3.3 mg','73.2 mg','0.4 mg','47.2 mcg','0.1 mg','0 mg','0.7 mg','0 mg','0 mg'),
 (5,'samosa','91 cal','1.4 gm','7.9 gm','1.6 gm','6 gm','0 mg','13.1 mg','0.5 mg','15.5 mg','34.3 mg','5.5 mg','71.7 mg','0.2 mg','114.7 mcg','0 mg','0 mg','0.4 mg','23.8 mg','0.1 mg');
/*!40000 ALTER TABLE `fooddetails` ENABLE KEYS */;


--
-- Definition of table `foodintakedata`
--

DROP TABLE IF EXISTS `foodintakedata`;
CREATE TABLE `foodintakedata` (
  `fid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fcalories` varchar(45) NOT NULL,
  `fdate` varchar(45) NOT NULL,
  `fuid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `foodintakedata`
--

/*!40000 ALTER TABLE `foodintakedata` DISABLE KEYS */;
INSERT INTO `foodintakedata` (`fid`,`fcalories`,`fdate`,`fuid`) VALUES 
 (3,'25','2019-10-01',16);
/*!40000 ALTER TABLE `foodintakedata` ENABLE KEYS */;


--
-- Definition of table `userdata`
--

DROP TABLE IF EXISTS `userdata`;
CREATE TABLE `userdata` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ulogin` varchar(45) NOT NULL,
  `upass` varchar(45) NOT NULL,
  `urole` varchar(45) NOT NULL,
  `uaddr` varchar(45) NOT NULL,
  `uname` varchar(45) NOT NULL,
  `umail` varchar(45) NOT NULL,
  `uapprove` varchar(45) NOT NULL,
  `uphone` varchar(10) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userdata`
--

/*!40000 ALTER TABLE `userdata` DISABLE KEYS */;
INSERT INTO `userdata` (`uid`,`ulogin`,`upass`,`urole`,`uaddr`,`uname`,`umail`,`uapprove`,`uphone`) VALUES 
 (15,'doc','doc','1','addre dco','doc doc','doc@g.com','Y','9874563210'),
 (16,'vik','vik','2','add','vik more','vik@g.com','Y','7894561230'),
 (17,'prakash','prakash','2','prakash\'s addrsssss','praksh fale','prakshfale@gmail.com','Y','9876543210'),
 (18,'seema','seema','2','seema mam\'s addrsss','Seema mam','seemamam@gmail.com','Y','9876541230'),
 (19,'eshwari','eshwari','2','eshwari mam\'s addrssss','Eshvari mam','eshwarimam@gmail.com','Y','9874563210'),
 (20,'vikram','vikram','2','vikram\'s addrrsss','vikram more','vikrammore@gmail.com','Y','9874521630'),
 (21,'Vaishu','1234','2','Pune','Vaishali Gupta','vaishaligupta.g@gmail.com','Y','9049913552'),
 (22,'Kittu','kittu','2','Kannad','Kiran Kale','kirankale0999@gmail.com','Y','9637283704'),
 (23,'rajesh','Raj@1234','1','kiran','raje raj','raj@gmail.com','Y','4564564566');
/*!40000 ALTER TABLE `userdata` ENABLE KEYS */;


--
-- Definition of table `userstats`
--

DROP TABLE IF EXISTS `userstats`;
CREATE TABLE `userstats` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` varchar(45) NOT NULL,
  `userh` varchar(45) NOT NULL COMMENT 'Height',
  `userw` varchar(45) NOT NULL COMMENT 'Weight',
  `usera` varchar(45) NOT NULL COMMENT 'Age',
  `userg` varchar(45) NOT NULL COMMENT 'Gender',
  `useraim` varchar(45) NOT NULL COMMENT 'Aim/Goal:- 1:Gain Weight, 2: LooseWeight, 3:MaintainWeight',
  `userls` varchar(45) NOT NULL COMMENT 'Lifestyle:- 1:Sedentary, 2:Low, 3: Active, 4:Very ACtive',
  `userwst` varchar(45) NOT NULL COMMENT 'Waist',
  `userglu` varchar(45) NOT NULL COMMENT 'Glucose',
  `userbld` varchar(45) NOT NULL COMMENT 'Blood Pressure',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userstats`
--

/*!40000 ALTER TABLE `userstats` DISABLE KEYS */;
INSERT INTO `userstats` (`id`,`userid`,`userh`,`userw`,`usera`,`userg`,`useraim`,`userls`,`userwst`,`userglu`,`userbld`) VALUES 
 (11,'16','145','70','25','M','3','2','30','160','120'),
 (12,'17','149','64','22','M','3','3','26','140','110'),
 (13,'18','145','52','80','F','1','2','22','145','80'),
 (14,'19','135','70','28','F','2','2','29','150','100'),
 (15,'20','168','62','28','M','3','1','36','120','56'),
 (16,'21','152','80','21','F','2','2','38','120','580'),
 (17,'22','55','5','45','M','1','1','5','5','5');
/*!40000 ALTER TABLE `userstats` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
