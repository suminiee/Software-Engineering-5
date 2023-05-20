-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: sw_5
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `id` int NOT NULL,
  `day` date DEFAULT NULL,
  KEY `attendance_FK` (`id`),
  CONSTRAINT `attendance_FK` FOREIGN KEY (`id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group` (
  `id` int NOT NULL,
  `groupName` char(15) NOT NULL,
  `groupId` int NOT NULL,
  PRIMARY KEY (`groupId`),
  KEY `group_FK` (`id`),
  CONSTRAINT `group_FK` FOREIGN KEY (`id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groupword`
--

DROP TABLE IF EXISTS `groupword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groupword` (
  `id` int NOT NULL,
  `groupId` int NOT NULL,
  `wordId` int NOT NULL,
  KEY `gw_FK_1` (`groupId`),
  KEY `gw_FK` (`id`),
  KEY `gw_FK_2` (`wordId`),
  CONSTRAINT `gw_FK` FOREIGN KEY (`id`) REFERENCES `member` (`id`),
  CONSTRAINT `gw_FK_1` FOREIGN KEY (`groupId`) REFERENCES `group` (`groupId`),
  CONSTRAINT `gw_FK_2` FOREIGN KEY (`wordId`) REFERENCES `word` (`wordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groupword`
--

LOCK TABLES `groupword` WRITE;
/*!40000 ALTER TABLE `groupword` DISABLE KEYS */;
/*!40000 ALTER TABLE `groupword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `loginId` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nickname` char(20) NOT NULL,
  `level` int DEFAULT NULL,
  `dailyword` int DEFAULT NULL,
  `social` tinyint(1) NOT NULL,
  `role` tinyint(1) NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('admin1234','admin1234','administrator',NULL,NULL,0,0,1);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study`
--

DROP TABLE IF EXISTS `study`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study` (
  `id` int NOT NULL,
  `day` date NOT NULL,
  `wordId` int NOT NULL,
  KEY `study_FK` (`id`),
  KEY `study_FK_1` (`wordId`),
  CONSTRAINT `study_FK` FOREIGN KEY (`id`) REFERENCES `member` (`id`),
  CONSTRAINT `study_FK_1` FOREIGN KEY (`wordId`) REFERENCES `word` (`wordId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study`
--

LOCK TABLES `study` WRITE;
/*!40000 ALTER TABLE `study` DISABLE KEYS */;
/*!40000 ALTER TABLE `study` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `word`
--

DROP TABLE IF EXISTS `word`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `word` (
  `wordId` int NOT NULL AUTO_INCREMENT,
  `spelling` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mean` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`wordId`)
) ENGINE=InnoDB AUTO_INCREMENT=555 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `word`
--

LOCK TABLES `word` WRITE;
/*!40000 ALTER TABLE `word` DISABLE KEYS */;
INSERT INTO `word` VALUES (1,'resume','이력서'),(2,'applicant','지원자, 신청자'),(3,'requirement','필요조건, 요건'),(4,'meet','(필요,요구 등을)만족시키다'),(5,'qualified','자격있는, 적격의'),(6,'candidate','후보자, 지원자'),(7,'confidence','확신, 자신, 신임'),(8,'highly','매우, 대단히'),(9,'professional','전문적인, 직업의, 전문가'),(10,'interview','면접, 면접을 보다'),(11,'hire','고용하다'),(12,'training','교육, 훈련'),(13,'reference','추천서, 참고'),(14,'position','일자리, 직책, 두다'),(15,'achievement','성취, 달성'),(16,'impressed','인상 깊게 생각하는, 감명을 받은'),(17,'excellent','훌륭한'),(18,'eligible','자격이 있는, 적격의'),(19,'identify','알아보다, 확인하다'),(20,'associate','관련시키다'),(21,'condition','조건'),(22,'employment','고용'),(23,'lack','~이 없다, 부족'),(24,'managerial','관리의'),(25,'diligent','성실한'),(26,'familiar','익숙한, 친숙한'),(27,'proficiency','숙달, 능숙'),(28,'prospective','장래의, 미래의'),(29,'appeal','관심을 끌다, 매력적이다'),(30,'specialize','~을 전공하다, 전문적으로 다루다'),(31,'apprehensive','걱정하는, 염려하는'),(32,'consultant','고문, 컨설턴트'),(33,'entitle','자격을 주다'),(34,'degree','학위'),(35,'payroll','임금대장, 금료명부'),(36,'recruit','(신입사원등을)모집하다'),(37,'certification','증명서, 증명'),(38,'occupation','직업'),(39,'wage','임금, 급료'),(40,'attire','복장, 옷차림새'),(41,'code','규범, 관례;암호'),(42,'concern','우려, 걱정, 문제, 일, ~을 걱정스럽게 하다; 영향을 미치다, 관련되다.'),(43,'policy','규정; 보험, 증권'),(44,'comply','준수하다, 따르다'),(45,'regulation','규정'),(46,'exception','예외'),(47,'adhere','지키다, 고수하다'),(48,'severely','엄격하게, 심하게'),(49,'refrain','자제하다, 삼가다'),(50,'permission','허락, 허가'),(51,'access','이용권한, 접근;통로, ~에 접근하다, 도달하다'),(52,'thoroughly','철저하게, 완전히, 대단히'),(53,'revise','(의견, 계획을)수정하다, 변경하다'),(54,'approach','접근법, 처리방법'),(55,'approval','승인, 인가'),(56,'form','종류, 유형, 양식'),(57,'immediately','즉시, 곧'),(58,'inspection','점검, 검사'),(59,'arrangement','준비, 마련, 주선'),(60,'procedure','절차'),(61,'negative','부정적인, 비관적인'),(62,'mandate','명령하다, 지시하다, 권한, 지시, 명령'),(63,'effect','(법률등의)효력, 효과, 영향, 결과로서 ~을 가져오다'),(64,'drastically','심하게, 과감하게, 철저하게'),(65,'according to','~에 따라'),(66,'enable','(무엇을)가능하게 하다'),(67,'standard','기준, 표준'),(68,'constant','지속적인, 끊임없이 계속하는'),(69,'act','법령, 결의서; 행위, 행동, 결정하다, 판결을 내리다, 행동하다'),(70,'compensation','보상금, 보상'),(71,'ban','금지, 금지하다'),(72,'obligation','의무, 책임'),(73,'authorize','~을 인가하다, 권한을 부여하다.'),(74,'prohibit','금지하다'),(75,'abolish','(제도, 법률등을)폐지하다'),(76,'enforce','(법률을)시행하다, 집행하다'),(77,'habit','습관, 버릇'),(78,'lagislation','법률, 법규'),(79,'restrict','한정하다, 제한하다'),(80,'accustomed','익숙한'),(81,'corporatiion','주식회사, 법인'),(82,'demanding','요구가 많은, 힘든'),(83,'colleague','동료'),(84,'division','부서'),(85,'request','요청, 요청하다'),(86,'efficiently','효율적으로'),(87,'manage','~을 경영하다; 가까스로 하다'),(88,'submit','제출하다'),(89,'directly','곧바로'),(90,'remind','~에게 상기시키다, 일깨우다'),(91,'instruct','지시하다, 가르치다'),(92,'deadline','마감일, 마감 시간'),(93,'sample','견본, 샘플, 표본시식하다, (견본으로 질을)시험하다'),(94,'notify','~에게 통지하다, 알리다'),(95,'perform','행하다, 실행하다'),(96,'monitor','감독하다, 감시하다, 실행하다'),(97,'deserve','~할 만하다, ~할 가치가 있다'),(98,'assignment','일, 임무, 과제'),(99,'entire','전체의'),(100,'release','발표하다, 공개하다, 출시, 발매'),(101,'extension','연장, 연기; (전화의)내선'),(102,'electronically','컴퓨터 통신망으로, 컴퓨터로'),(103,'attendance','출석, 출근'),(104,'absolutely','절대적으로, 완전히'),(105,'delegate','(권한등을)위임하다. 대표'),(106,'attentively','주의 깊게, 조심스럽게'),(107,'supervision','감독'),(108,'workshop','워크숍, 연수'),(109,'draw','끌다, 끌어당기다'),(110,'revision','수정, 개정'),(111,'reluctantly','마지못해, 꺼려하여'),(112,'acquaint','~에게 숙지시키다, 잘 알게 하다'),(113,'convey','(용건을)전달하다'),(114,'check','검사하다, 조사하다, 확인하다'),(115,'headquarters','본부'),(116,'file','(서류를)정리하다, 철하다;\n(서류, 신청, 고소 등을)정식으로 제기하다, 제출하다'),(117,'oversee','감독하다'),(118,'involved','관여하는, 관계된, 연루된'),(119,'concentrate','집중하다, 집중시키다'),(120,'lax','(행동 등이)느슨한, 규율에 못 미치는'),(121,'procrastinate','미루다, 꾸물거리다'),(122,'combined','결합된, 합동의'),(123,'accomplish','성취하다'),(124,'voluntarily','자발적으로'),(125,'undertake','(일을)떠맡다'),(126,'assume','(증거는 없으나)사실이라고 생각하다;\n(책임,역할을)맡다'),(127,'occasionally','가끔, 때때로'),(128,'employee','직원, 고용인'),(129,'assist','돕다, 조력하다'),(130,'satisfied','만족하는, 만족스러워하는'),(131,'manner','방식, 태도'),(132,'responsible','책임이 있는, 책임져야 할'),(133,'conduct','(업무 등을)수행하다'),(134,'adjust','적응하다'),(135,'personnel','(집합적)직원, 인원; 인사과'),(136,'agree','동의하다'),(137,'supervise','감독하다, 지도하다'),(138,'coworker','동료, 협력자'),(139,'direct','~에게 길을 안내하다, 가르쳐 주다'),(140,'confidential','기밀의, 내밀한'),(141,'assign','배정하다, 할당하다'),(142,'leading','선도적인, 일류의, 가장 중요한, 선두적인'),(143,'formal','격식을 갖춘'),(144,'remove','해임하다, 제거하다'),(145,'collect','모으다'),(146,'coordinate','조정하다'),(147,'hardly','거의~하지않다'),(148,'abstract','추상적인, 막연한'),(149,'directory','주소록'),(150,'accountable','책임이 있는'),(151,'skillfully','능숙하게, 솜씨있게'),(152,'exclusive','독점적인, 배타적인'),(153,'intention','의지, 의향'),(154,'transform','바꾸다, 변모시키다'),(155,'respectful','정중한, 존중하는'),(156,'duplicate','사본'),(157,'contrary','반대'),(158,'disturbing','충격적인, 불안감을 주는, 방해하는'),(159,'engage','관여하다, 종사하다'),(160,'foster','촉진하다, 육성하다'),(161,'neutrality','중립성, 중립'),(162,'widely','널리'),(163,'sophisticated','(기계가)정교한, 복잡한; 세련된'),(164,'timely','시기적절한, 때를 맞춘'),(165,'realistically','현실적으로'),(166,'promptly','즉시; 정각에'),(167,'accessible','출입할 수 있는; 이용할 수 있는'),(168,'implement','실시하다, 실행하다'),(169,'feedback','의견, 반응'),(170,'outstanding','우수한; (부채 등이)미지불된'),(171,'inform','~에게 알리다'),(172,'replacement','교체, 후임자'),(173,'announcement','공고, 발표'),(174,'department','(조직, 기구의)부서'),(175,'permanently','영구적으로, 불변으로'),(176,'fulfill','(조건을)만족시키다, (약속, 의무등을)지키다, 이행하다'),(177,'outline','개요, 설명하다, 약술하다'),(178,'explain','설명하다'),(179,'contain','포함하다'),(180,'compile','(자료 등을)편집하다; 모으다'),(181,'subsequent','차후의, 그 다음의'),(182,'overview','개요, 개관'),(183,'provider','공급자, 제공자'),(184,'matter','문제, 일'),(185,'expertise','전문지식, 전문기술'),(186,'demonstrate','증명하다; (모형, 실험등으로)설명하다'),(187,'remainder','나머지'),(188,'essential','필수적인, 극히 중요한, 본질적인'),(189,'divide','분배하다, 나누다'),(190,'major','주요한, 중대한'),(191,'compliance','(명령, 법규에의)준수'),(192,'clarify','명확하게 하다'),(193,'face','(문제 등에)직면하다; 향하다, 마주보다'),(194,'follow','~을 따라가다; 주시하다; (분명히)이해하다'),(195,'aspect','관점, 국면'),(196,'apparently','보기에 ~한 듯한, 외관상으로는'),(197,'aware','알고 있는, 인식하고 있는'),(198,'extended','(기간 등을)연장한, 장기간에 걸친'),(199,'accidentally','뜻하지 않게, 우연히'),(200,'advisable','바람직한, 합당한'),(201,'concerned','염려하는, 걱정하는; 관련된'),(202,'speak','이야기 하다'),(203,'collection','소장품, 수집물; 징수, 수금'),(204,'exhibition','전시회'),(205,'celebrity','유명인사, 명사'),(206,'live','(라디오, TV가)생방송인'),(207,'improvise','(연주, 연설 등을)즉흥적으로 하다, 즉석에서 만들다.'),(208,'popular','인기있는'),(209,'donation','기증, 기부'),(210,'alumni','동창생들, 졸업생들'),(211,'present','제시하다, 현재의, 참석한'),(212,'admission','입장'),(213,'banquet','연회, 만찬'),(214,'anniversary','기념일'),(215,'required','필수의, 의무적인'),(216,'succeed','성공하다, 뒤를 잇다'),(217,'rest','쉬다,휴식, 나머지'),(218,'fund-raising','모금'),(219,'resume','재개하다, 다시 시작하다'),(220,'issue','(출판물의)제 ~호; 문제, 쟁점'),(221,'subscription','(정기발행물의) 구독'),(222,'appear','나타나다, 출현하다'),(223,'accompany','동행하다, 동반하다'),(224,'edition','(간행물의)판'),(225,'specifically','명확하게, 분명히; 특히, 특별히'),(226,'anonymous','익명의, 이름을 모르는'),(227,'commit','헌신하다, 전념하다'),(228,'information','유익한, 정보를 주는'),(229,'audience','청중, 관중'),(230,'author','작가, 저자'),(231,'note','주목하다; 특별히 언급하다.'),(232,'antique','골동품'),(233,'manuscript','원고'),(234,'beneficial','유익한, 이로운'),(235,'upcoming','곧 있을, 다가오는'),(236,'lend','빌려주다'),(237,'current','현재의,; 현행의, 통용되는'),(238,'local','지방의, 고장의'),(239,'variety','다양성, 변화'),(240,'advocate','옹호자'),(241,'contributor','기고가, 공헌자'),(242,'defy','저항하다; (설명,묘사등이)거의 불가능하다'),(243,'fascinating','매혹적인, 황홀한'),(244,'showing','(영화, 연극의)상영; 전시'),(245,'survey','설문조사'),(246,'analysis','분석'),(247,'respondent','응답자'),(248,'competition','경쟁'),(249,'consistently','항상, 일관되게'),(250,'demand','수요, 요구하다'),(251,'do one\'s utmost','전력을 다하다'),(252,'expand','확장하다, 확대하다'),(253,'advanced','고급의; 진보한, 앞선'),(254,'postpone','연기하다, 뒤로 미루다'),(255,'additional','추가의, 부가적인'),(256,'appreciate','고맙게 생각하다; 높이 평가하다; 감상하다'),(257,'demonstration','설명; 드러냄, 시연'),(258,'buy','사다, 구입하다'),(259,'examine','조사하다'),(260,'effective','효과적인; (법률 등이)발표되는, 시행되는'),(261,'like','좋아하다'),(262,'especially','특히'),(263,'closely','면밀히, 엄밀히'),(264,'reserve','예약하다, 지정하다; 보존하다'),(265,'cooperate','협력하다, 협동하다'),(266,'very','매우, 대단히, 아주'),(267,'consecutive','연속적인'),(268,'expectation','예상, 기대'),(269,'publicize','공표하다; 광고하다, 선전하다'),(270,'raise','높이다, 올리다; (의문을)제기하다'),(271,'extremely','극도로, 대단히'),(272,'affect','~에 영향을 미치다, 불리하게 작용하다'),(273,'target','목표, 목표로 삼다'),(274,'campaign','운동, 캠페인'),(275,'probable','개연성이 높은, 유망한'),(276,'focus','집중시키다, 집중하다'),(277,'seasonal','계절의, 계절적인'),(278,'impact','영향, 충격'),(279,'comparison','비교'),(280,'gap','격차'),(281,'mounting','증가하는, 오르는'),(282,'reflective','반영하는'),(283,'advertisement','광고'),(284,'marginal','약간의; 주변의'),(285,'customer','고객'),(286,'influence','~에 영향을 주다, 영향'),(287,'instantly','즉각적으로, 즉시'),(288,'creative','창조적인, 독창적인'),(289,'aggressively','적극적으로'),(290,'aim','~을 겨누다, 목표, 목적'),(291,'strategy','전략'),(292,'indicate','보여주다, 나타내다'),(293,'attract','끌다, 유인하다'),(294,'experience','경험, 체험, 체험하다, 경험하다'),(295,'analyze','분석하다, 분석적으로 검토하다'),(296,'introduce','(신제품을)발표하다, 소개하다'),(297,'advise','조언해주다, 충고하다'),(298,'subscribe','구독하다'),(299,'absence','부재; 결근, 결석'),(300,'means','방법, 수단'),(301,'prefer','(다른 것보다)~을 더 좋아하다, 선호하다'),(302,'advantage','이점, 강점'),(303,'forward','앞으로, (물건, 정보를)보내다'),(304,'contemporary','동시대의; 현대의, 당대의'),(305,'discussion','토론, 토의'),(306,'initial','처음의, 최초의'),(307,'steadily','착실하게; 꾸준히'),(308,'necessarily','반드시'),(309,'resolve','(문제 등을)해결하다'),(310,'detect','간파하다, 탐지하다'),(311,'intensify','강화하다, 증대하다, 강렬하게 만들다.'),(312,'favorably','호의적으로; 순조롭게'),(313,'cover','포함하다; 지불하다; 덮다'),(314,'less','보다 적은, 덜한'),(315,'majority','대부분, 대다수'),(316,'adopt','채택하다'),(317,'largely','주로, 대부분'),(318,'disregard','소홀히 하다, 무시하다'),(319,'effort','노력'),(320,'incentive','혜택, 장려금'),(321,'need','필요, 요구, 욕구, ~할 필요가 있다.'),(322,'mastermind','(계획 등의)입안자, 지도자'),(323,'stagnant','침체된, 불경기의'),(324,'dramatically','극적으로'),(325,'brisk','활발한, 호황의'),(326,'unstable','불안정한, 변하기 쉬운'),(327,'rapidly','급속히, 빨리'),(328,'soar','(물가 등이)폭등하다, 높이 치솟다'),(329,'assert','단언하다, 주장하다'),(330,'boost','(경기를)부양시키다, 상승시키다'),(331,'analyst','분석가'),(332,'potential','잠재적인'),(333,'pleased','만족해하는, 기쁜'),(334,'remain','계속~한 상태이다; 아직 ~해야한다'),(335,'limited','제한된, 한정된'),(336,'costly','비용이 많이 드는, 손실이 큰'),(337,'particular','특정한, 독특한, 세심한'),(338,'drastic','과감한, 급격한'),(339,'evenly','고르게, 균등하게'),(340,'evidence','증거'),(341,'prospect','전망, 예상'),(342,'lead','이글다, 지휘하다; (어떤 결과에)이르다'),(343,'fall','(값, 가치가)하락하다'),(344,'period','기간, 시기'),(345,'indicator','지표, 지수'),(346,'industry','산업'),(347,'likely','~할 것 같은'),(348,'boom','붐, 호황'),(349,'director','임원, 책임자'),(350,'substitute','대용품, 대신하다'),(351,'consequence','결과'),(352,'fairly','상당히, 꽤'),(353,'economical','경제적인, 절약되는'),(354,'thrive','번영하다, 성공하다'),(355,'implication','영향, 밀접한 관계'),(356,'wane','감소, 감퇴'),(357,'prosperity','번영'),(358,'depression','불황'),(359,'dwindle','줄어들다, 감소되다'),(360,'impede','저해하다, 방해하다'),(361,'promising','유망한, 전망이 좋은'),(362,'adversity','역경, 불운'),(363,'purchase','구매하다, 구매'),(364,'installment','할부'),(365,'affordable','(가격이)알맞은, 감당할 수 있는'),(366,'exactly','정확히'),(367,'auction','경매'),(368,'authentic','진정한, 진짜의, 진품의'),(369,'charge','요금,청구,금액;책임,의무\n~을 청구하다; (외상으로)달아놓다'),(370,'notice','통지, 공고'),(371,'experienced','경험이 있는, 노련한, 능숙한'),(372,'instruction','설명, 지시'),(373,'expert','전문가, 전문가의, 전문적인'),(374,'warranty','(품질 등의)보증, 보증서'),(375,'refund','환불, 환불금'),(376,'subscriber','가입자, 구독자'),(377,'delivery','배달'),(378,'price','가격'),(379,'receipt','영수증'),(380,'offer','제공하다, 제공,오퍼'),(381,'carefully','주의깊게, 신중히'),(382,'benefit','혜택, 이익, 혜택을 보다, 이익을 얻다'),(383,'exclusively','오로지, 독점적으로'),(384,'description','(제품등의)설명, 해설'),(385,'relatively','상대적으로'),(386,'spare','아끼다, 할애하다, 예비의, 여분의'),(387,'preparation','준비, 대비'),(388,'area','지역, 구역'),(389,'clearance','정리, 없애기; 허가'),(390,'alter','(성질, 형상을)고치다, 바꾸다'),(391,'apply','적용하다; 지원하다'),(392,'mutually','서로, 상호 간에'),(393,'method','방식, 방법'),(394,'acceptable','용인되는, 받아들일 수 있는;\n훌륭한, 만족스러운'),(395,'desire','욕구, 갈망, 원하다, 바라다'),(396,'redeemable','(현금,상품과)교환할 수 있는, 환급할 수 있는'),(397,'officially','공식적으로'),(398,'consumption','소비(량), 소모'),(399,'qualify','~의 자격을 얻다'),(400,'fabric','섬유, 천'),(401,'valid','유효한'),(402,'vendor','노점상, 가판대; 판매업체'),(403,'research','연구, 조사'),(404,'devise','고안하다, 발명하다'),(405,'revolutionary','혁명적인'),(406,'innovative','혁신적인'),(407,'feature','특징, 특색, 특징으로 삼다, 특별히 포함하다'),(408,'inspiration','영감'),(409,'sufficiently','충분히'),(410,'patent','특허, 특허권, 특허품'),(411,'envision','계획하다, 상상하다'),(412,'extend','연장하다; (기간을)늘리다;\n(감사의 뜻을)말하다, 베풀다'),(413,'following','~후에, 다음의, 다음에 오는'),(414,'intend','~할 작정이다; 의도하다'),(415,'grant','(인정하여 정식으로)수여하다, 주다,\n(연구비, 장학금등의)보조금'),(416,'allow','~하게 하다, ~을 허락하다'),(417,'inspect','~을 검사하다, 조사하다'),(418,'improve','향상시키다, 개선하다'),(419,'increasingly','점점, 더욱 더'),(420,'invest','투자하다, 운용하다'),(421,'various','여러 가지의, 가지각색의'),(422,'upgrade','업그레이드, 개량형\n업그레이드하다, 개선하다'),(423,'manual','설명서, 안내서'),(424,'explore','조사하다, 탐구하다'),(425,'response','응답, 대답'),(426,'appearance','외관, 외모'),(427,'successful','성공적인, 성공한'),(428,'hold','~을 수용하다, ~을 담다;\n(회의 등을)개최하다, 열다'),(429,'advance','진보, 전진'),(430,'reliable','믿을 만한, 신뢰할 수 있는'),(431,'quality','품질, 질'),(432,'domestic','국내의, 국산의'),(433,'development','개발, 발전'),(434,'availability','(입수)가능성, 유효성, 유용성'),(435,'update','갱신, 개정, 갱신하다,\n최신의 것으로 하다'),(436,'accurate','정확한'),(437,'complicate','복잡한'),(438,'accomplished','숙련된, 노련한'),(439,'inquiry','문의, 질문'),(440,'indication','징후, 조짐'),(441,'manufacturer','제조회사, 제조업자'),(442,'compatible','호환되는, 양립할 수 있는'),(443,'superior','우수한, 상급의'),(444,'absolute','완전한, 완전무결한'),(445,'broaden','넓히다'),(446,'corrosion','부식'),(447,'epuipment','장비, 설비'),(448,'automate','자동화하다'),(449,'specification','명세서, 설명서, 사양'),(450,'properly','제대로, 정확하게'),(451,'safety','안전'),(452,'precaution','예방조치, 예방책'),(453,'operate','(기계 등이)작동하다, 움직이다'),(454,'processing','가공, 처리'),(455,'capacity','용량, 수용력; 역할'),(456,'assemble','(부품,기계 등을)조립하다;\n(사람을)모으다'),(457,'utilize','이용하다, 활용하다'),(458,'place','~을-한 상태에 두다;\n(지시,주문,신청등을)하다'),(459,'fill','~을 채우다; (주문대로)이행하다'),(460,'manufacturing','제조(업)의'),(461,'renovate','(낡은 건물, 가구등을)개조하다,\n수리하다, 보수하다'),(462,'decision','결정, 판단'),(463,'material','재료, 물질'),(464,'success','성과, 성공'),(465,'attribute','(원인을)~의 덕분으로 돌리다'),(466,'efficiency','효율, 능률'),(467,'limit','한계, 제한'),(468,'tailored','맞춤의, 주문에 따라 맞춘'),(469,'component','부품, (구성)요소'),(470,'capable','~을 할 수 있는, ~할 능력이 있는'),(471,'economize','절약하다, 아끼다'),(472,'flexible','융통성 있는; 유연한, 잘 구부러지는'),(473,'comparable','필적하는, 비교되는'),(474,'produce','생산하다'),(475,'respectively','각각, 따로'),(476,'device','장치'),(477,'trim','(깍아)다듬다, 없애다; 삭감하다'),(478,'launch','(신제품을)출시하다'),(479,'separately','개별적으로, 따로따로'),(480,'expiration','(기간,임기 등의)만료, 만기'),(481,'maneuver','이동시키다, 움직이다'),(482,'coming','다가오는'),(483,'damaged','손상된, 손해를 입은'),(484,'prevent','~을 막다, ~을 예방하다'),(485,'power','전력, 전기'),(486,'chemical','화학제품'),(487,'complaint','불평'),(488,'deal','처리하다; 거래하다; 분배하다'),(489,'argumentative','논쟁적인, 논쟁을 좋아하는'),(490,'appropriately','적절하게'),(491,'respond','응답하다'),(492,'infuriate','화나게하다, 격분시키다'),(493,'curteous','예의바른'),(494,'satisfaction','만족'),(495,'inconvenience','불편, ~에게 불편을 느끼게 하다'),(496,'complete','완료하다, 완성하다, 완료된, 완성된'),(497,'specific','구체적인, 명확한'),(498,'return','반환하다, 반송하다'),(499,'replace','~을 교체하다, 대체하다'),(500,'presentation','발표'),(501,'evaluation','평가'),(502,'confident','자신 있는'),(503,'cause','~을 야기하다, ~의 원인이 되다\n원인'),(504,'commentary','해설, 설명'),(505,'notification','통지'),(506,'apologize','사과하다'),(507,'interact','소통하다, 교류하다; 상호작용을 하다'),(508,'certain','확신하는, 확실한; 특정한'),(509,'commitment','헌신, 전념'),(510,'applaud','~에게 박수를 보내다; 칭찬하다'),(511,'biography','약력, 전기'),(512,'critical','비판적인; 중요한; 위기의'),(513,'depend on','~에 달려 있다, ~에게 의존하다'),(514,'combine','결합시키다'),(515,'priority','우선권, 우선사항'),(516,'observe','관찰하다, 주시하다;\n(규칙 등을)준수하다, 지키다'),(517,'defective','결함이 있는'),(518,'reflect','반영하다, 나타내다'),(519,'attitude','태도, 마음가짐'),(520,'disappoint','실망시키다'),(521,'inquire','문의하다, 질문하다'),(522,'insert','삽입하다'),(523,'disclose','공개하다, 드러내다'),(524,'guarantee','보장하다, 보장'),(525,'politely','공손하게, 예의바르게'),(526,'seriously','진지하게'),(527,'international','국제적인'),(528,'attraction','관광 명소'),(529,'itinerary','여행 일정'),(530,'exotic','이국적인, 매혹적인'),(531,'diverse','다양한'),(532,'superb','최고의, 뛰어난'),(533,'baggage','수화물'),(534,'destination','목적지'),(535,'missing','분실된, 없어진'),(536,'locate','(~의 위치를)찾아내다;\n~을 위치시키다'),(537,'approximately','대략'),(538,'duty','관세,세금; 직무,의무'),(539,'process','과정, 처리하다'),(540,'board','탑승하다, 이사회'),(541,'comfortable','편안한'),(542,'declare','(세관에서)신고하다'),(543,'specify','명시하다'),(544,'depart','출발하다'),(545,'emergency','비상시, 비상 사태'),(546,'passenger','승객'),(547,'outgoing','(장소를)출발하는, 떠나는;\n(지위를)떠나는'),(548,'tighly','단단히, 꽉'),(549,'tour','(공장,시설 등의)견학, 짧은 여행'),(550,'carrier','항공사, 수송기, 수송 회사'),(551,'customarily','관례상, 습관적으로'),(552,'confuse','혼란시키다'),(553,'arrive','도착하다');
/*!40000 ALTER TABLE `word` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sw_5'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-20 19:18:30
