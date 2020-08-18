package Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;

public class DBActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			if (b.getText().equals("관리자 모드")) {
				SejongCampingGUI sc2 = new SejongCampingGUI(1, SejongCampingGUI.con);
			} else if (b.getText().equals("일반 사용자 모드")) {
				SejongCampingGUI sc2 = new SejongCampingGUI(2, SejongCampingGUI.con);
			} else {
				stmt = SejongCampingGUI.con.createStatement();
				String query;

				if (b.getText().equals("초기화")) {
					SejongCampingGUI.txtResult.setText("");
					query = "DROP TABLE IF EXISTS CheckCar";
					stmt.executeUpdate(query);
					query = "DROP TABLE IF EXISTS RentalCar";
					stmt.executeUpdate(query);
					query = "DROP TABLE IF EXISTS Repair";
					stmt.executeUpdate(query);
					query = "DROP TABLE IF EXISTS RepairCenter";
					stmt.executeUpdate(query);
					query = "DROP TABLE IF EXISTS Customer";
					stmt.executeUpdate(query);
					query = "DROP TABLE IF EXISTS CampingCar";
					stmt.executeUpdate(query);
					query = "DROP TABLE IF EXISTS RentalOffice";
					stmt.executeUpdate(query);

					query = "CREATE TABLE RentalOffice(" + "OfficeID INT NOT NULL PRIMARY KEY,"
							+ "OfficeName VARCHAR(45)," + "OfficeAddress VARCHAR(45)," + "OfficePhone VARCHAR(45),"
							+ "ManagerName VARCHAR(45)," + "ManagerEmail VARCHAR(45)" + ")";
					stmt.executeUpdate(query);

					query = "CREATE TABLE CampingCar(" + "CarID INT NOT NULL PRIMARY KEY," + "CarName VARCHAR(45),"
							+ "CarNumber VARCHAR(45)," + "CarPeople INT," + "CarImage VARCHAR(45),"
							+ "CarDetail VARCHAR(45)," + "CarRentCost INT," + "OfficeID INT NOT NULL,"
							+ "EnrollDate VARCHAR(45)," + "FOREIGN KEY (OfficeID) REFERENCES RentalOffice(OfficeID)"
							+ ")";
					stmt.executeUpdate(query);

					query = "CREATE TABLE Customer(" + "CustID INT NOT NULL PRIMARY KEY," + "CustName VARCHAR(45),"
							+ "CustAddress VARCHAR(45)," + "CustPhone VARCHAR(45)," + "CustEmail VARCHAR(45)" + ")";
					stmt.executeUpdate(query);

					query = "CREATE TABLE RepairCenter(" + "CenterID INT NOT NULL PRIMARY KEY,"
							+ "CenterName VARCHAR(45)," + "CenterAddress VARCHAR(45)," + "CenterPhone VARCHAR(45),"
							+ "ManagerName VARCHAR(45)," + "ManagerEmail VARCHAR(45)" + ")";
					stmt.executeUpdate(query);

					query = "CREATE TABLE Repair(" + "RepairID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
							+ "CarID INT NOT NULL," + "CenterID INT NOT NULL," + "OfficeID INT NOT NULL,"
							+ "CustID INT NOT NULL," + "RepairDetail VARCHAR(45)," + "RepairDate VARCHAR(45),"
							+ "RepairCost INT," + "PayDate VARCHAR(45)," + "OtherDetail VARCHAR(45),"
							+ "FOREIGN KEY (CarID) REFERENCES CampingCar(CarID),"
							+ "FOREIGN KEY (CenterID) REFERENCES RepairCenter(CenterID),"
							+ "FOREIGN KEY (OfficeID) REFERENCES RentalOffice(OfficeID),"
							+ "FOREIGN KEY (CustID) REFERENCES Customer(CustID)" + ")";
					stmt.executeUpdate(query);

					query = "CREATE TABLE RentalCar(" + "RentalID INT NOT NULL PRIMARY KEY," + "CarID INT NOT NULL,"
							+ "CustID INT NOT NULL," + "OfficeID INT NOT NULL," + "RentalDate VARCHAR(45),"
							+ "RentalTerm INT," + "RentalCost INT," + "PayDate VARCHAR(45),"
							+ "OtherCostDetail VARCHAR(45)," + "OtherCostInfo VARCHAR(45),"
							+ "FOREIGN KEY (CarID) REFERENCES CampingCar(CarID),"
							+ "FOREIGN KEY (CustID) REFERENCES Customer(CustID),"
							+ "FOREIGN KEY (OfficeID) REFERENCES RentalOffice (OfficeID)" + ")";
					stmt.executeUpdate(query);

					query = "CREATE TABLE CheckCar(" + "RentalID INT NOT NULL PRIMARY KEY," + "CarID INT NOT NULL,"
							+ "OfficeID INT NOT NULL, " + "CustID INT NOT NULL, " + "FrontImage VARCHAR(45),"
							+ "LeftImage VARCHAR(45)," + "RightImage VARCHAR(45)," + "BackImage VARCHAR(45),"
							+ "RepairRequire INT" + ")";
					stmt.executeUpdate(query);

					query = "INSERT INTO RentalOffice VALUES"
							+ "(1, 'Company A', '대한민국 서울', '010-1534-1152', '가사장', 'aasdf@sejong.ac.kr'),"
							+ "(2, 'Company B', '대한민국 대전', '010-5232-1242', '나사장', 'b24b@sejong.ac.kr'),"
							+ "(3, 'Company C', '대한민국 대구', '010-3453-3263', '다사장', 'cae@sejong.ac.kr'),"
							+ "(4, 'Company D', '대한민국 부산', '010-8795-2232', '라사장', 'd2n6@sejong.ac.kr'),"
							+ "(5, 'Company E', '대한민국 서울', '010-3578-5373', '마사장', 'ean@sejong.ac.kr'),"
							+ "(6, 'Company F', '대한민국 대전', '010-7945-9623', '바사장', 'fna@sejong.ac.kr'),"
							+ "(7, 'Company G', '대한민국 대구', '010-3473-3642', '사사장', 'gna@sejong.ac.kr'),"
							+ "(8, 'Company H', '대한민국 부산', '010-6230-3430', '아사장', 'h6@sejong.ac.kr'),"
							+ "(9, 'Company I', '대한민국 서울', '010-2306-3062', '자사장', 'i34n@sejong.ac.kr'),"
							+ "(10, 'Company J', '대한민국 대전', '010-8754-6523', '차사장', 'jat3n@sejong.ac.kr'),"
							+ "(11, 'Company K', '대한민국 대구', '010-5464-9852', '카사장', 'kfdgb65@sejong.ac.kr'),"
							+ "(12, 'Company L', '대한민국 부산', '010-1428-2342', '타사장', 'l645fas@sejong.ac.kr'),"
							+ "(13, 'Company M', '대한민국 서울', '010-2574-3253', '파사장', 'masfd645@sejong.ac.kr'),"
							+ "(14, 'Company N', '대한민국 대전', '010-2145-8445', '하사장', 'n5465@sejong.ac.kr'),"
							+ "(15, 'Company O', '대한민국 대구', '010-6584-9706', '십오사장', 'o26@sejong.ac.kr'),"
							+ "(16, 'Company P', '대한민국 부산', '010-6443-2368', '십육사장', 'p6Q@sejong.ac.kr'),"
							+ "(17, 'Company Q', '대한민국 서울', '010-7806-8564', '십칠사장', 'qq2g%@sejong.ac.kr'),"
							+ "(18, 'Company R', '대한민국 대전', '010-0443-3653', '열여덟사장', 'r615vw4@sejong.ac.kr'),"
							+ "(19, 'Company S', '대한민국 대구', '010-3453-2361', '십구사장', 's684s@sejong.ac.kr'),"
							+ "(20, 'Company T', '대한민국 여수', '010-8324-3633', '십구사장', 's8bsd@sejong.ac.kr'),"
							+ "(21, 'Company U', '대한민국 속초', '010-2345-3541', '십구사장', 'as645s@sejong.ac.kr'),"
							+ "(22, 'Company V', '대한민국 서울', '010-6383-5483', '십구사장', 'safd615s@sejong.ac.kr'),"
							+ "(23, 'Company W', '대한민국 마산', '010-4625-6395', '십구사장', '62153vs@sejong.ac.kr'),"
							+ "(24, 'Company X', '대한민국 경기', '010-6594-5134', '십구사장', '2v156s@sejong.ac.kr'),"
							+ "(25, 'Company Y', '대한민국 경기', '010-2556-6153', '십구사장', '651s@sejong.ac.kr'),"
							+ "(26, 'Company Z', '대한민국 서울', '010-4943-3338', '십구사장', '2fr3q615s@sejong.ac.kr'),"
							+ "(27, 'Company AA', '대한민국 대전', '010-6530-1911', '십구사장', '65s@sejong.ac.kr'),"
							+ "(28, 'Company BB', '대한민국 대구', '010-1911-0978', '십구사장', 'vsda65s@sejong.ac.kr'),"
							+ "(29, 'Company CC', '대한민국 부산', '010-6537-1911', '십구사장', '65safs@sejong.ac.kr'),"
							+ "(30, 'Company DD', '대한민국 여수', '010-2111-7439', '이십사장', 'fsda654t@sejong.ac.kr')";
					int n = stmt.executeUpdate(query);
					String str = "RentalOffice table이 " + n + "개 튜플로 초기화되었습니다.\n";
					SejongCampingGUI.txtResult.append(str);

					query = "INSERT INTO CampingCar VALUES"
							+ "(1, '가차', '11가1111', 5, '가이미지', '가 상세정보', 100000, 1, '2015-03-09'),"
							+ "(2, '나차', '22나2222', 2, '나이미지', '나 상세정보', 150000, 2, '2015-03-12'),"
							+ "(3, '다차', '33다3333', 4, '다이미지', '다 상세정보', 200000, 3, '2015-04-15'),"
							+ "(4, '라차', '44라4444', 5, '라이미지', '라 상세정보', 90000, 4, '2015-05-16'),"
							+ "(5, '마차', '55마5555', 8, '마이미지', '마 상세정보', 110000, 5, '2015-07-20'),"
							+ "(6, '바차', '66바6666', 10, '바이미지', '바 상세정보', 130000, 6, '2015-07-25'),"
							+ "(7, '사차', '77사7777', 5, '사이미지', '사 상세정보', 130000, 7, '2015-09-09'),"
							+ "(8, '아차', '88아8888', 4, '아이미지', '아 상세정보', 80000, 8, '2015-10-12'),"
							+ "(9, '자차', '99자9999', 5, '자이미지', '자 상세정보', 120000, 9, '2015-12-15'),"
							+ "(10, '차차', '10파1010', 2, '차이미지', '차 상세정보', 70000, 10, '2016-01-05'),"
							+ "(11, '카차', '11카1111', 5, '카이미지', '카 상세정보', 80000, 11, '2016-03-06'),"
							+ "(12, '타차', '12타1212', 10, '타이미지', '타 상세정보', 130000, 12, '2016-03-27'),"
							+ "(13, '파차', '13파1313', 5, '파이미지', '파 상세정보', 100000, 13, '2016-06-06'),"
							+ "(14, '하차', '14하1414', 8, '하이미지', '하 상세정보', 150000, 14, '2016-08-20'),"
							+ "(15, '십오차', '15십1515', 6, '십오이미지', '십오 상세정보', 170000, 15, '2016-09-10'),"
							+ "(16, '십육차', '16십1616', 6, '십육이미지', '십육 상세정보', 40000, 16, '2016-11-12'),"
							+ "(17, '십칠차', '17십1717', 4, '십칠이미지', '십칠 상세정보', 50000, 17, '2016-12-25'),"
							+ "(18, '십팔차', '18십1818', 5, '십팔이미지', '십팔 상세정보', 200000, 18, '2017-02-14'),"
							+ "(19, '십구차', '19십2512', 6, '십구이미지', '십구 상세정보', 150000, 19, '2017-05-29'),"
							+ "(20, '이십차', '19십7432', 6, '십구이미지', '이십 상세정보', 150000, 20, '2017-05-29'),"
							+ "(21, '이십일차', '19십6553', 6, '십구이미지', '이십일 상세정보', 150000, 21, '2017-05-29'),"
							+ "(22, '이십이차', '19십6243', 6, '십구이미지', '이십이 상세정보', 150000, 22, '2017-05-29'),"
							+ "(23, '이십삼차', '19십2623', 6, '십구이미지', '이십삼 상세정보', 150000, 23, '2017-05-29'),"
							+ "(24, '이십사차', '19십6533', 6, '십구이미지', '이십사 상세정보', 150000, 24, '2017-05-29'),"
							+ "(25, '이십오차', '19십6543', 6, '십구이미지', '이십오 상세정보', 150000, 25, '2017-05-29'),"
							+ "(26, '이십육차', '19십6453', 6, '십구이미지', '이십육 상세정보', 150000, 26, '2017-05-29'),"
							+ "(27, '이십칠차', '19십8235', 6, '십구이미지', '이십칠 상세정보', 150000, 27, '2017-05-29'),"
							+ "(28, '이십팔차', '19십3643', 6, '십구이미지', '이십팔 상세정보', 150000, 28, '2017-05-29'),"
							+ "(29, '이십구차', '19십7474', 6, '십구이미지', '이십구 상세정보', 150000, 29, '2017-05-29'),"
							+ "(30, '삼심차', '20십2543', 5, '이십이미지', '삼십 상세정보', 130000, 30, '2017-06-18')";
					n = stmt.executeUpdate(query);
					str = "CampingCar table이 " + n + "개 튜플로 초기화되었습니다.\n";
					SejongCampingGUI.txtResult.append(str);

					query = "INSERT INTO Customer VALUES"
							+ "(1, '둘리', '대한민국 가평', '010-5554-4463', 'dool2@sejong.ac.kr'),"
							+ "(2, '뚱이', '대한민국 부산', '010-5555-7777', 'ddoong2@sejong.ac.kr'),"
							+ "(3, '징징이', '대한민국 여수', '010-7777-7777', 'zingzing@sejong.ac.kr'),"
							+ "(4, '짱구', '일본', '010-4554-5554', 'zzanggu@sejong.ac.kr'),"
							+ "(5, '또치', '대한민국 한강', '010-1212-5151', 'ddochicken@sejong.ac.kr'),"
							+ "(6, '김김김', '대한민국 서울', '010-5213-4273', 'dool2@sejong.ac.kr'),"
							+ "(7, '이이이', '대한민국 대전', '010-8161-9871', 'ee22@sejong.ac.kr'),"
							+ "(8, '정정정', '대한민국 대구', '010-6512-6411', 'jjj@sejong.ac.kr'),"
							+ "(9, '소소소', '대한민국 부산', '010-9865-1352', 'ssss@sejong.ac.kr'),"
							+ "(10, '설설설', '대한민국 광주', '010-0101-1011', 'susususu@sejong.ac.kr'),"
							+ "(11, '오오오', '대한민국 야탑', '010-1231-1212', 'ohoho@sejong.ac.kr'),"
							+ "(12, '유유유', '대한민국 서현', '010-2121-2121', 'uuu@sejong.ac.kr'),"
							+ "(13, '장장장', '대한민국 광교', '010-3131-4123', 'jangaj@sejong.ac.kr'),"
							+ "(14, '기기기', '대한민국 홍대', '010-1515-4674', 'gigigi@sejong.ac.kr'),"
							+ "(15, '위위위', '대한민국 이대', '010-1554-8181', 'wewewe@sejong.ac.kr'),"
							+ "(16, '가나다', '대한민국 신촌', '010-1356-1616', 'rkskek@sejong.ac.kr'),"
							+ "(17, '라마바', '대한민국 합정', '010-1515-6166', 'fkakqk@sejong.ac.kr'),"
							+ "(18, '사아자', '대한민국 마포', '010-1818-4978', 'tkdkwk@sejong.ac.kr'),"
							+ "(19, '가가가', '대한민국 공덕', '010-9191-5763', 'vkzkxk@sejong.ac.kr'),"
							+ "(20, '나나나', '대한민국 약수', '010-1235-4626', 'ag2a2@sejong.ac.kr'),"
							+ "(21, '다다다', '대한민국 어대', '010-9191-8122', '25ga65@sejong.ac.kr'),"
							+ "(22, '라라라', '대한민국 충정로', '010-4521-5763', 'v365as4df@sejong.ac.kr'),"
							+ "(23, '마마마', '대한민국 을지로3가', '010-9191-8244', 'a2fi2@sejong.ac.kr'),"
							+ "(24, '바바바', '대한민국 을지로입구', '010-3456-5763', 'fh93gw@sejong.ac.kr'),"
							+ "(25, '사사사', '대한민국 종각', '010-9191-4747', '651wrg3@sejong.ac.kr'),"
							+ "(26, '아아아', '대한민국 광화문', '010-3632-5763', 'bi92kb2@sejong.ac.kr'),"
							+ "(27, '자자자', '대한민국 안국', '010-9191-4567', '512f2@sejong.ac.kr'),"
							+ "(28, '차차차', '대한민국 을지로4가', '010-7375-5763', 'boiaf2@sejong.ac.kr'),"
							+ "(29, '카카카', '대한민국 종로3가', '010-9191-6154', '365g13@sejong.ac.kr'),"
							+ "(30, '타타타', '대한민국 이태원', '010-1202-0888', 'adfibm2@sejong.ac.kr')";
					n = stmt.executeUpdate(query);
					str = "Customer table이 " + n + "개 튜플로 초기화되었습니다.\n";
					SejongCampingGUI.txtResult.append(str);

					query = "INSERT INTO RepairCenter VALUES"
							+ "(1, '고치고', '대한민국 서울', '010-4885-4885', '김고쳐', 'GO@sejong.ac.kr'),"
							+ "(2, '노치고', '대한민국 대전', '010-4325-5121', '님고쳐', 'NO@sejong.ac.kr'),"
							+ "(3, '도치고', '대한민국 대구', '010-1725-2484', '딤고쳐', 'DO@sejong.ac.kr'),"
							+ "(4, '로치고', '대한민국 부산', '010-4585-9821', '림고쳐', 'RO@sejong.ac.kr'),"
							+ "(5, '모치고', '대한민국 서울', '010-1857-5214', '밈고쳐', 'MO@sejong.ac.kr'),"
							+ "(6, '보치고', '대한민국 대전', '010-3257-5152', '빔고쳐', 'BO@sejong.ac.kr'),"
							+ "(7, '소치고', '대한민국 대구', '010-8124-0550', '심고쳐', 'SO@sejong.ac.kr'),"
							+ "(8, '오치고', '대한민국 부산', '010-3252-8484', '임고쳐', 'EE@sejong.ac.kr'),"
							+ "(9, '조치고', '대한민국 서울', '010-1526-4444', '짐고쳐', 'ZO@sejong.ac.kr'),"
							+ "(10, '초치고', '대한민국 대전', '010-6181-1111', '침고쳐', 'CHO@sejong.ac.kr'),"
							+ "(11, '코치고', '대한민국 대구', '010-8005-1555', '킴고쳐', 'KIM@sejong.ac.kr'),"
							+ "(12, '토치고', '대한민국 부산', '010-2013-0215', '팀고쳐', 'TIM@sejong.ac.kr'),"
							+ "(13, '포치고', '대한민국 서울', '010-0854-2372', '핌고쳐', 'PIM@sejong.ac.kr'),"
							+ "(14, '호치고', '대한민국 대전', '010-3058-6508', '힘고쳐', 'HIM@sejong.ac.kr'),"
							+ "(15, '고치고치고', '대한민국 대전', '010-2153-6508', '가고쳐', 'FSADIM@sejong.ac.kr'),"
							+ "(16, '노치고치고', '대한민국 대전', '010-3058-4658', '나고쳐', 'F2HIM@sejong.ac.kr'),"
							+ "(17, '도치고치고', '대한민국 대구', '010-3034-6508', '다고쳐', 'H2GIM@sejong.ac.kr'),"
							+ "(18, '또도치고', '대한민국 대전', '010-3058-2643', '라고쳐', 'HI2A5M@sejong.ac.kr'),"
							+ "(19, '오치고', '대한민국 부선', '010-3535-6508', '마고쳐', 'HWAETIM@sejong.ac.kr'),"
							+ "(20, '초치초치초', '대한민국 서울', '010-3058-5252', '바고쳐', 'HIATWM@sejong.ac.kr'),"
							+ "(21, '고쳐고쳐', '대한민국 여수', '010-3654-6508', '사고쳐', 'H1TIM@sejong.ac.kr'),"
							+ "(22, '고쳐보아', '대한민국 순천', '010-3055-6428', '아고쳐', 'HAFSDGIM@sejong.ac.kr'),"
							+ "(23, '고쳐볼까', '대한민국 광양', '010-8962-6508', '자고쳐', 'HIF54M@sejong.ac.kr'),"
							+ "(24, '고치기', '대한민국 광교', '010-3058-5142', '차고쳐', 'HIFM@sejong.ac.kr'),"
							+ "(25, '노치기', '대한민국 광주', '010-2363-6508', '카고쳐', 'HFIM@sejong.ac.kr'),"
							+ "(26, '도치기', '대한민국 야탑', '010-3058-6315', '타고쳐', 'HFI5M@sejong.ac.kr'),"
							+ "(27, '로치기', '대한민국 대전', '010-4865-6508', '파고쳐', 'H5FIM@sejong.ac.kr'),"
							+ "(28, '모치기', '대한민국 대전', '010-3058-3745', '하고쳐', 'HIU7N6M@sejong.ac.kr'),"
							+ "(29, '호치기', '대한민국 서울', '010-2643-6508', '하하고쳐', 'HIF5M@sejong.ac.kr'),"
							+ "(30, '호호치기', '대한민국 대구', '010-5884-0686', '제갈고쳐', 'JE7U6NGAL@sejong.ac.kr')";
					n = stmt.executeUpdate(query);
					str = "RepairCenter table이 " + n + "개 튜플로 초기화되었습니다.\n";
					SejongCampingGUI.txtResult.append(str);

					query = "INSERT INTO Repair VALUES"
							+ "(16,16,16,16,16,'앞유리','2020-06-20',150000,'2020-06-27','타이어 점검'),"
							+ "(17,17,17,17,17,'뒷유리','2020-06-12',200000,'2020-06-19','타이어 점검'),"
							+ "(18,18,18,18,18,'앞좌석','2020-06-12',230000,'2020-06-19','타이어 점검'),"
							+ "(19,19,19,19,19,'뒷좌석','2020-06-11',420000,'2020-06-18','타이어 점검'),"
							+ "(20,20,20,20,20,'사이드미러','2020-06-13',510000,'2020-06-20','타이어 점검'),"
							+ "(21,21,21,21,21,'백미러','2020-06-20',240000,'2020-06-27','타이어 점검'),"
							+ "(22,22,22,22,22,'기어','2020-06-10',230000,'2020-06-17','타이어 점검'),"
							+ "(23,23,23,23,23,'핸들','2020-06-16',240000,'2020-06-23','타이어 점검'),"
							+ "(24,24,24,24,24,'핸들','2020-06-18',140000,'2020-06-25','타이어 점검'),"
							+ "(25,25,25,25,25,'기어','2020-06-29',150000,'2020-06-29','타이어 점검'),"
							+ "(26,26,26,26,26,'사이드미러','2020-06-28',170000,'2020-06-28','타이어 점검'),"
							+ "(27,27,27,27,27,'뒷좌석','2020-06-26',180000,'2020-06-27','타이어 점검'),"
							+ "(28,28,28,28,28,'앞좌석','2020-06-25',140000,'2020-06-27','타이어 점검'),"
							+ "(29,29,29,29,29,'앞유리','2020-06-24',270000,'2020-06-27','타이어 점검'),"
							+ "(30,30,30,30,30,'뒷유리','2020-06-18',350000,'2020-06-27','타이어 점검')";
					n = stmt.executeUpdate(query);
					str = "Repair table이 " + n + "개 튜플로 초기화하였습니다.\n";
					SejongCampingGUI.txtResult.append(str);

					query = "INSERT INTO RentalCar VALUES" + "(1,1,1,1,'2019-12-01',7,200000,'2019-12-08','기름값','바닥남'),"
							+ "(2,2,2,2,'2019-12-02',7,150000,'2019-12-09','없음','없음'),"
							+ "(3,3,3,3,'2019-12-03',7,170000,'2019-12-10','없음','없음'),"
							+ "(4,4,4,4,'2019-12-04',7,250000,'2019-12-11','없음','없음'),"
							+ "(5,5,5,5,'2019-12-05',7,350000,'2019-12-12','기름값','바닥남'),"
							+ "(6,6,6,6,'2019-12-06',7,250000,'2019-12-13','기름값','바닥남'),"
							+ "(7,7,7,7,'2019-12-07',7,350000,'2019-12-14','없음','없음'),"
							+ "(8,8,8,8,'2019-12-08',7,150000,'2019-12-15','기름값','바닥남'),"
							+ "(9,9,9,9,'2019-12-09',7,110000,'2019-12-16','기름값','바닥남'),"
							+ "(10,10,10,10,'2019-12-10',7,120000,'2019-12-17','기름값','바닥남'),"
							+ "(11,11,11,11,'2019-12-11',7,150000,'2019-12-18','기름값','바닥남'),"
							+ "(12,12,12,12,'2019-12-12',7,140000,'2019-12-19','없음','없음'),"
							+ "(13,13,13,13,'2019-12-13',7,160000,'2019-12-20','기름값','바닥남'),"
							+ "(14,14,14,14,'2019-12-14',7,120000,'2019-12-21','기름값','바닥남'),"
							+ "(15,15,15,15,'2019-12-15',7,120000,'2019-12-22','없음','없음')";
					n = stmt.executeUpdate(query);
					str = "RentalCar tabled이  " + n + "개 튜플로 초기화되었습니다.\n";
					SejongCampingGUI.txtResult.append(str);

					query = "INSERT INTO CheckCar VALUES" + "(16,16,16,16,'Front','Left','Right','Back',1),"
							+ "(17,17,17,17,'Front','Left','Right','Back',1),"
							+ "(18,18,18,18,'Front','Left','Right','Back',1),"
							+ "(19,19,19,19,'Front','Left','Right','Back',1),"
							+ "(20,20,20,20,'Front','Left','Right','Back',1),"
							+ "(21,21,21,21,'Front','Left','Right','Back',1),"
							+ "(22,22,22,22,'Front','Left','Right','Back',1),"
							+ "(23,23,23,23,'Front','Left','Right','Back',1),"
							+ "(24,24,24,24,'Front','Left','Right','Back',1),"
							+ "(25,25,25,25,'Front','Left','Right','Back',1),"
							+ "(26,26,26,26,'Front','Left','Right','Back',1),"
							+ "(27,27,27,27,'Front','Left','Right','Back',1),"
							+ "(28,28,28,28,'Front','Left','Right','Back',1),"
							+ "(29,29,29,29,'Front','Left','Right','Back',1),"
							+ "(30,30,30,30,'Front','Left','Right','Back',1)";
					n = stmt.executeUpdate(query);
					str = "CheckCar table이 " + n + "개 튜플로 초기화하였습니다.\n";
					SejongCampingGUI.txtResult.append(str);
				} else if (b.getText().equals("대여회사 입력")) {
					SejongCampingGUI.txtResult.setText("");
					query = "INSERT INTO RentalOffice VALUES(?,?,?,?,?,?)";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int OfficeID = Integer.parseInt(SejongCampingGUI.OITF.getText());
					String OfficeName = SejongCampingGUI.ONTF.getText().equals("") ? "NULL" : SejongCampingGUI.ONTF.getText();
					String OfficeAddress = SejongCampingGUI.OATF.getText().equals("") ? "NULL" : SejongCampingGUI.OATF.getText();
					String OfficePhone = SejongCampingGUI.OPTF.getText().equals("") ? "NULL" : SejongCampingGUI.OPTF.getText();
					String ManagerName = SejongCampingGUI.MNTF.getText().equals("") ? "NULL" : SejongCampingGUI.MNTF.getText();
					String ManagerEmail = SejongCampingGUI.METF.getText().equals("") ? "NULL" : SejongCampingGUI.METF.getText();

					pstmt.setInt(1, OfficeID);
					pstmt.setString(2, OfficeName);
					pstmt.setString(3, OfficeAddress);
					pstmt.setString(4, OfficePhone);
					pstmt.setString(5, ManagerName);
					pstmt.setString(6, ManagerEmail);

					int result = pstmt.executeUpdate();
					String str = "RentalOffice 테이블에 " + result + "개 행이 추가되었습니다.\n" + "==== 추가된 내용 ====\n"
							+ "OfficeID : " + String.valueOf(OfficeID) + "\n" + "OfficeName : "
							+ String.valueOf(OfficeName) + "\n" + "OfficeAddress : " + String.valueOf(OfficeAddress)
							+ "\n" + "OfficePhone : " + String.valueOf(OfficePhone) + "\n" + "ManagerName : "
							+ String.valueOf(ManagerName) + "\n" + "ManagerEmail : " + String.valueOf(ManagerEmail)
							+ "\n";
					SejongCampingGUI.txtResult.append(str);
				} else if (b.getText().equals("대여회사 삭제")) {
					SejongCampingGUI.txtResult.setText("");
					query = "DELETE FROM RentalOffice WHERE OfficeID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int OfficeID = Integer.parseInt(SejongCampingGUI.OITF.getText());
					pstmt.setInt(1, OfficeID);

					int result = pstmt.executeUpdate();
					String str;
					if (result >= 1)
						str = "RentalOffice 테이블에서 OfficeID가 " + String.valueOf(OfficeID) + "인 튜플을 제거했습니다.\n";
					else
						str = "변경된 사항이 없습니다.";
					SejongCampingGUI.txtResult.append(str);
				} else if (b.getText().equals("대여회사 변경")) {
					SejongCampingGUI.txtResult.setText("");
					query = "UPDATE RentalOffice SET OfficeName = ?," + " OfficeAddress = ?, OfficePhone = ?, "
							+ "ManagerName = ?, ManagerEmail = ? " + "WHERE OfficeID = ? ";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int OfficeID = Integer.parseInt(SejongCampingGUI.OITF.getText());
					String OfficeName = SejongCampingGUI.ONTF.getText().equals("") ? "NULL" : SejongCampingGUI.ONTF.getText();
					String OfficeAddress = SejongCampingGUI.OATF.getText().equals("") ? "NULL" : SejongCampingGUI.OATF.getText();
					String OfficePhone = SejongCampingGUI.OPTF.getText().equals("") ? "NULL" : SejongCampingGUI.OPTF.getText();
					String ManagerName = SejongCampingGUI.MNTF.getText().equals("") ? "NULL" : SejongCampingGUI.MNTF.getText();
					String ManagerEmail = SejongCampingGUI.METF.getText().equals("") ? "NULL" : SejongCampingGUI.METF.getText();

					pstmt.setInt(6, OfficeID);
					pstmt.setString(1, OfficeName);
					pstmt.setString(2, OfficeAddress);
					pstmt.setString(3, OfficePhone);
					pstmt.setString(4, ManagerName);
					pstmt.setString(5, ManagerEmail);

					int result = pstmt.executeUpdate();

					String str;
					if (result >= 1)
						str = "RentalOffice 테이블에 OfficeID가 " + OfficeID + "인 튜플의 내용을 변경했습니다.\n";
					else
						str = "변경된 사항이 없습니다.";
					SejongCampingGUI.txtResult.append(str);
				} else if (b.getText().equals("캠핑카 입력")) {
					SejongCampingGUI.txtResult.setText("");
					query = "INSERT INTO CampingCar VALUES(?,?,?,?,?,?,?,?,?)";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int CarID = Integer.parseInt(SejongCampingGUI.CCITF.getText());
					String CarName = SejongCampingGUI.CCNTF.getText().equals("") ? "NULL" : SejongCampingGUI.CCNTF.getText();
					String CarNumber = SejongCampingGUI.CCCNTF.getText().equals("") ? "NULL" : SejongCampingGUI.CCCNTF.getText();
					int CarPeople = SejongCampingGUI.CCPTF.getText().equals("") ? 0 : Integer.parseInt(SejongCampingGUI.CCPTF.getText());
					String CarImage = SejongCampingGUI.CCIMTF.getText().equals("") ? "NULL" : SejongCampingGUI.CCIMTF.getText();
					String CarDetail = SejongCampingGUI.CCDITF.getText().equals("") ? "NULL" : SejongCampingGUI.CCDITF.getText();
					int CarRentCost = SejongCampingGUI.CCRCTF.getText().equals("") ? 0 : Integer.parseInt(SejongCampingGUI.CCRCTF.getText());
					int OfficeID = SejongCampingGUI.CCROIDTF.getText().equals("") ? -1 : Integer.parseInt(SejongCampingGUI.CCROIDTF.getText());
					String EnrollDate = SejongCampingGUI.CCDTF.getText().equals("") ? "0000-00-00" : SejongCampingGUI.CCDTF.getText();

					pstmt.setInt(1, CarID);
					pstmt.setString(2, CarName);
					pstmt.setString(3, CarNumber);
					pstmt.setInt(4, CarPeople);
					pstmt.setString(5, CarImage);
					pstmt.setString(6, CarDetail);
					pstmt.setInt(7, CarRentCost);
					pstmt.setInt(8, OfficeID);
					pstmt.setString(9, EnrollDate);

					int result = pstmt.executeUpdate();

					String str = "CampingCar 테이블에" + result + "개 행이 추가되었습니다.\n" + "==== 추가된 내용 ====\n" + "CarID : "
							+ String.valueOf(CarID) + "\n" + "CarName : " + String.valueOf(CarName) + "\n"
							+ "CarNumber : " + String.valueOf(CarNumber) + "\n" + "CarPeople : "
							+ String.valueOf(CarPeople) + "\n" + "CarImage : " + String.valueOf(CarImage) + "\n"
							+ "CarDetail : " + String.valueOf(CarDetail) + "\n" + "CarRentCost : "
							+ String.valueOf(CarRentCost) + "\n" + "OfficeID : " + String.valueOf(OfficeID) + "\n"
							+ "EnrollDate : " + String.valueOf(EnrollDate) + "\n";
					SejongCampingGUI.txtResult.append(str);
				} else if (b.getText().equals("캠핑카 삭제")) {
					SejongCampingGUI.txtResult.setText("");
					query = "DELETE FROM CampingCar WHERE CarID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int CarID = Integer.parseInt(SejongCampingGUI.CCITF.getText());
					pstmt.setInt(1, CarID);

					int result = pstmt.executeUpdate();
					String str;
					if (result >= 1)
						str = "CampingCar 테이블에서 CarID가 " + String.valueOf(CarID) + "인 튜플을 제거했습니다.\n";
					else
						str = "변경된 사항이 없습니다.";
					SejongCampingGUI.txtResult.append(str);

				} else if (b.getText().equals("캠핑카 변경")) {
					SejongCampingGUI.txtResult.setText("");
					query = "UPDATE CampingCar SET CarName = ?, " + "CarNumber = ?, " + "CarPeople = ?, "
							+ "CarImage = ?, " + "CarDetail = ?, " + "CarRentCost = ?, " + "OfficeID = ?, "
							+ "EnrollDate = ? " + "WHERE CarID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int CarID = Integer.parseInt(SejongCampingGUI.CCITF.getText());
					String CarName = SejongCampingGUI.CCNTF.getText().equals("") ? "NULL" : SejongCampingGUI.CCNTF.getText();
					String CarNumber = SejongCampingGUI.CCCNTF.getText().equals("") ? "NULL" : SejongCampingGUI.CCCNTF.getText();
					int CarPeople = SejongCampingGUI.CCPTF.getText().equals("") ? 0 : Integer.parseInt(SejongCampingGUI.CCPTF.getText());
					String CarImage = SejongCampingGUI.CCIMTF.getText().equals("") ? "NULL" : SejongCampingGUI.CCIMTF.getText();
					String CarDetail = SejongCampingGUI.CCDITF.getText().equals("") ? "NULL" : SejongCampingGUI.CCDITF.getText();
					int CarRentCost = SejongCampingGUI.CCRCTF.getText().equals("") ? 0 : Integer.parseInt(SejongCampingGUI.CCRCTF.getText());
					int OfficeID = SejongCampingGUI.CCROIDTF.getText().equals("") ? -1 : Integer.parseInt(SejongCampingGUI.CCROIDTF.getText());
					String EnrollDate = SejongCampingGUI.CCDTF.getText().equals("") ? "0000-00-00" : SejongCampingGUI.CCDTF.getText();

					pstmt.setInt(9, CarID);
					pstmt.setString(1, CarName);
					pstmt.setString(2, CarNumber);
					pstmt.setInt(3, CarPeople);
					pstmt.setString(4, CarImage);
					pstmt.setString(5, CarDetail);
					pstmt.setInt(6, CarRentCost);
					pstmt.setInt(7, OfficeID);
					pstmt.setString(8, EnrollDate);
					int result = pstmt.executeUpdate();

					String str;
					if (result >= 1)
						str = "CampingCar 테이블에 CarID가 " + CarID + "인 튜플의 내용을 변경했습니다.\n";
					else
						str = "변경된 사항이 없습니다.";
					SejongCampingGUI.txtResult.append(str);
				} else if (b.getText().equals("고객 입력")) {
					SejongCampingGUI.txtResult.setText("");
					query = "INSERT INTO Customer VALUES(?,?,?,?,?)";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int CustID = Integer.parseInt(SejongCampingGUI.CUSTIDTF.getText());
					String CustName = SejongCampingGUI.CUSTNAMETF.getText().equals("") ? "NULL" : SejongCampingGUI.CUSTNAMETF.getText();
					String CustAddress = SejongCampingGUI.CUSTADDRESSTF.getText().equals("") ? "NULL" : SejongCampingGUI.CUSTADDRESSTF.getText();
					String CustPhone = SejongCampingGUI.CUSTPHONETF.getText().equals("") ? "NULL" : SejongCampingGUI.CUSTPHONETF.getText();
					String CustEmail = SejongCampingGUI.CUSTEMAILTF.getText().equals("") ? "NULL" : SejongCampingGUI.CUSTEMAILTF.getText();

					pstmt.setInt(1, CustID);
					pstmt.setString(2, CustName);
					pstmt.setString(3, CustAddress);
					pstmt.setString(4, CustPhone);
					pstmt.setString(5, CustEmail);

					int result = pstmt.executeUpdate();
					String str = "Customer 테이블에 " + result + "개 행이 추가되었습니다.\n" + "==== 추가된 내용 ====\n" + "CustID : "
							+ String.valueOf(CustID) + "\n" + "CustName : " + String.valueOf(CustName) + "\n"
							+ "CustAddress : " + String.valueOf(CustAddress) + "\n" + "CustPhone : "
							+ String.valueOf(CustPhone) + "\n" + "CustEmail : " + String.valueOf(CustEmail) + "\n";
					SejongCampingGUI.txtResult.append(str);
				} else if (b.getText().equals("고객 삭제")) {
					SejongCampingGUI.txtResult.setText("");
					query = "DELETE FROM Customer WHERE CustID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int CustID = Integer.parseInt(SejongCampingGUI.CUSTIDTF.getText());
					pstmt.setInt(1, CustID);

					int result = pstmt.executeUpdate();
					String str;
					if (result >= 1)
						str = "Customer 테이블에서 CustID가 " + String.valueOf(CustID) + "인 튜플을 제거했습니다.\n";
					else
						str = "변경된 사항이 없습니다.";
					SejongCampingGUI.txtResult.append(str);
				} else if (b.getText().equals("고객 변경")) {
					SejongCampingGUI.txtResult.setText("");
					query = "UPDATE Customer SET CustName = ?, " + "CustAddress = ?, " + "CustPhone = ?, "
							+ "CustEmail = ?" + "WHERE CustID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int CustID = Integer.parseInt(SejongCampingGUI.CUSTIDTF.getText());
					String CustName = SejongCampingGUI.CUSTNAMETF.getText().equals("") ? "NULL" : SejongCampingGUI.CUSTNAMETF.getText();
					String CustAddress = SejongCampingGUI.CUSTADDRESSTF.getText().equals("") ? "NULL" : SejongCampingGUI.CUSTADDRESSTF.getText();
					String CustPhone = SejongCampingGUI.CUSTPHONETF.getText().equals("") ? "NULL" : SejongCampingGUI.CUSTPHONETF.getText();
					String CustEmail = SejongCampingGUI.CUSTEMAILTF.getText().equals("") ? "NULL" : SejongCampingGUI.CUSTEMAILTF.getText();

					pstmt.setInt(5, CustID);
					pstmt.setString(1, CustName);
					pstmt.setString(2, CustAddress);
					pstmt.setString(3, CustPhone);
					pstmt.setString(4, CustEmail);

					int result = pstmt.executeUpdate();

					String str;
					if (result >= 1)
						str = "Customer 테이블에 CustID가 " + CustID + "인 튜플의 내용을 변경했습니다.\n";
					else
						str = "변경된 사항이 없습니다.";
					SejongCampingGUI.txtResult.append(str);
				} else if (b.getText().equals("정비소 입력")) {
					SejongCampingGUI.txtResult.setText("");
					query = "INSERT INTO RepairCenter VALUES(?,?,?,?,?,?)";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int CenterID = Integer.parseInt(SejongCampingGUI.CRIDTF.getText());
					String CenterName = SejongCampingGUI.CRNAMETF.getText().equals("") ? "NULL" : SejongCampingGUI.CRNAMETF.getText();
					String CenterAddress = SejongCampingGUI.CRADDRESSTF.getText().equals("") ? "NULL" : SejongCampingGUI.CRADDRESSTF.getText();
					String CenterPhone = SejongCampingGUI.CRPHONETF.getText().equals("") ? "NULL" : SejongCampingGUI.CRPHONETF.getText();
					String ManagerName = SejongCampingGUI.CRMANAGERTF.getText().equals("") ? "NULL" : SejongCampingGUI.CRMANAGERTF.getText();
					String ManagerEmail = SejongCampingGUI.CRMANAGEREMAILTF.getText().equals("") ? "NULL" : SejongCampingGUI.CRMANAGEREMAILTF.getText();

					pstmt.setInt(1, CenterID);
					pstmt.setString(2, CenterName);
					pstmt.setString(3, CenterAddress);
					pstmt.setString(4, CenterPhone);
					pstmt.setString(5, ManagerName);
					pstmt.setString(6, ManagerEmail);

					int result = pstmt.executeUpdate();
					String str = "RepairCenter 테이블에 " + result + "개 행이 추가되었습니다.\n" + "==== 추가된 내용 ====\n"
							+ "CenterID : " + String.valueOf(CenterID) + "\n" + "CenterName : "
							+ String.valueOf(CenterName) + "\n" + "CenterAddress : " + String.valueOf(CenterAddress)
							+ "\n" + "CenterPhone : " + String.valueOf(CenterPhone) + "\n" + "ManagerName : "
							+ String.valueOf(ManagerName) + "\n" + "ManagerEmail : " + String.valueOf(ManagerEmail)
							+ "\n";
					SejongCampingGUI.txtResult.append(str);

				} else if (b.getText().equals("정비소 삭제")) {
					SejongCampingGUI.txtResult.setText("");
					query = "DELETE FROM RepairCenter WHERE CenterID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int CenterID = Integer.parseInt(SejongCampingGUI.CRIDTF.getText());
					pstmt.setInt(1, CenterID);

					int result = pstmt.executeUpdate();
					String str;
					if (result >= 1)
						str = "RepairCenter 테이블에서 CenterID가 " + String.valueOf(CenterID) + "인 튜플을 제거했습니다.\n";
					else
						str = "변경된 사항이 없습니다.";
					SejongCampingGUI.txtResult.append(str);
				} else if (b.getText().equals("정비소 변경")) {
					SejongCampingGUI.txtResult.setText("");
					query = "UPDATE RepairCenter SET CenterName = ?, " + "CenterAddress = ?, " + "CenterPhone = ?, "
							+ "ManagerName = ?, " + "ManagerEmail = ?" + " WHERE CenterID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int CenterID = Integer.parseInt(SejongCampingGUI.CRIDTF.getText());
					String CenterName = SejongCampingGUI.CRNAMETF.getText().equals("") ? "NULL" : SejongCampingGUI.CRNAMETF.getText();
					String CenterAddress = SejongCampingGUI.CRADDRESSTF.getText().equals("") ? "NULL" : SejongCampingGUI.CRADDRESSTF.getText();
					String CenterPhone = SejongCampingGUI.CRPHONETF.getText().equals("") ? "NULL" : SejongCampingGUI.CRPHONETF.getText();
					String ManagerName = SejongCampingGUI.CRMANAGERTF.getText().equals("") ? "NULL" : SejongCampingGUI.CRMANAGERTF.getText();
					String ManagerEmail = SejongCampingGUI.CRMANAGEREMAILTF.getText().equals("") ? "NULL" : SejongCampingGUI.CRMANAGEREMAILTF.getText();

					pstmt.setInt(6, CenterID);
					pstmt.setString(1, CenterName);
					pstmt.setString(2, CenterAddress);
					pstmt.setString(3, CenterPhone);
					pstmt.setString(4, ManagerName);
					pstmt.setString(5, ManagerEmail);

					int result = pstmt.executeUpdate();

					String str;
					if (result >= 1)
						str = "RepairCenter 테이블에 CenterID가 " + CenterID + "인 튜플의 내용을 변경했습니다.\n";
					else
						str = "변경된 사항이 없습니다.";
					SejongCampingGUI.txtResult.append(str);

				} else if (b.getText().equals("반환")) {
					SejongCampingGUI.txtResult.setText("");
					int RentalID = Integer.parseInt(SejongCampingGUI.CRETURNIDTF.getText());
					query = "SELECT * From RentalCar WHERE RentalID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);
					pstmt.setInt(1, RentalID);

					int renid;
					int carid;
					int custid;
					int officeid;
					int RepairRequire;

					rs = pstmt.executeQuery();
					String str = "";
					if (rs.next()) {
						renid = rs.getInt(1);
						carid = rs.getInt(2);
						custid = rs.getInt(3);
						officeid = rs.getInt(4);

						RepairRequire = SejongCampingGUI.CRETURNREPAIRTF.getText().equals("") ? 0
								: Integer.parseInt(SejongCampingGUI.CRETURNREPAIRTF.getText());

						if (RepairRequire == 0) {
							str = "RentalID가 " + String.valueOf(RentalID) + "인 캠핑카는 수리가 필요없습니다.\n"
									+ "RentalCar 테이블에서 해당 ID 차를 반환하고 CheckCar 테이블에 수리 불필요로 입력됩니다.\n";
							query = "DELETE FROM RentalCar WHERE RentalID = ?";
							pstmt = SejongCampingGUI.con.prepareStatement(query);
							pstmt.setInt(1, RentalID);
							pstmt.executeUpdate();

							query = "INSERT INTO CheckCar(RentalID, CarID, OfficeID, CustID, RepairRequire) VALUES(?,?,?,?,0)";
							pstmt = SejongCampingGUI.con.prepareStatement(query);
							pstmt.setInt(1, RentalID);
							pstmt.setInt(2, carid);
							pstmt.setInt(3, officeid);
							pstmt.setInt(4, custid);
							pstmt.executeUpdate();
						} else {
							str = "RentalID가 " + String.valueOf(RentalID) + "인 캠핑카는 수리가 필요합니다.\n"
									+ "RentalCar 테이블에서 해당 ID 차를 반환하고 CheckCar 테이블에 수리 필요로 입력됩니다.\n";
							query = "DELETE FROM RentalCar WHERE RentalID = ?";
							pstmt = SejongCampingGUI.con.prepareStatement(query);
							pstmt.setInt(1, RentalID);
							pstmt.executeUpdate();

							query = "INSERT INTO CheckCar(RentalID, CarID, OfficeID, CustID, RepairRequire) VALUES(?,?,?,?,1)";
							pstmt = SejongCampingGUI.con.prepareStatement(query);
							pstmt.setInt(1, RentalID);
							pstmt.setInt(2, carid);
							pstmt.setInt(3, officeid);
							pstmt.setInt(4, custid);
							pstmt.executeUpdate();
						}
					} else {
						str = "RentalID가 " + String.valueOf(RentalID) + "인 캠핑카는  현재 대여중이 아닙니다.";
					}
					SejongCampingGUI.txtResult.append(str);

				} else if (b.getText().equals("정비정보 입력")) {
					SejongCampingGUI.txtResult.setText("");
					int RepairID = Integer.parseInt(SejongCampingGUI.CRNUMTF.getText());
					int CarID = Integer.parseInt(SejongCampingGUI.CCARIDTF.getText());
					int CenterID = Integer.parseInt(SejongCampingGUI.CREPAIRIDTF.getText());
					String RepairDetail = SejongCampingGUI.REPAIRDETAILTF.getText().equals("") ? "NULL" : SejongCampingGUI.REPAIRDETAILTF.getText();
					;
					String RepairDate = SejongCampingGUI.REPAIRDATETF.getText().equals("") ? "NULL" : SejongCampingGUI.REPAIRDATETF.getText();
					;
					int RepairCost = SejongCampingGUI.REPAIRCOSTTF.getText().equals("") ? 0 : Integer.parseInt(SejongCampingGUI.REPAIRCOSTTF.getText());
					String PayDate = SejongCampingGUI.RETURNDATETF.getText().equals("") ? "NULL" : SejongCampingGUI.RETURNDATETF.getText();
					;
					String OtherDetail = SejongCampingGUI.OTHERREPAIRINFOTF.getText().equals("") ? "NULL" : SejongCampingGUI.OTHERREPAIRINFOTF.getText();
					;

					String str = "";

					query = "SELECT * From CheckCar WHERE CarID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);
					pstmt.setInt(1, CarID);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						int officeid = rs.getInt(3);
						int custid = rs.getInt(4);
						int repairrequire = rs.getInt(9);

						if (repairrequire == 1) {
							query = "INSERT INTO Repair VALUES(?,?,?,?,?,?,?,?,?,?)";
							pstmt = SejongCampingGUI.con.prepareStatement(query);
							pstmt.setInt(1, RepairID);
							pstmt.setInt(2, CarID);
							pstmt.setInt(3, CenterID);
							pstmt.setInt(4, officeid);
							pstmt.setInt(5, custid);
							pstmt.setString(6, RepairDetail);
							pstmt.setString(7, RepairDate);
							pstmt.setInt(8, RepairCost);
							pstmt.setString(9, PayDate);
							pstmt.setString(10, OtherDetail);

							int result = pstmt.executeUpdate();
							str = "Repair 테이블에 " + result + "개 튜플이 추가되었습니다.\n" + "==== 추가된 내용 ====\n" + "RepairID : "
									+ String.valueOf(RepairID) + "\n" + "CarID : " + String.valueOf(CarID) + "\n"
									+ "CenterID : " + String.valueOf(CenterID) + "\n" + "OfficeID : "
									+ String.valueOf(officeid) + "\n" + "CustID : " + String.valueOf(custid) + "\n"
									+ "RepairDetail : " + String.valueOf(RepairDetail) + "\n" + "RepairDate : "
									+ String.valueOf(RepairDate) + "\n" + "RepairCost : " + String.valueOf(RepairCost)
									+ "\n" + "PayDate : " + String.valueOf(PayDate) + "\n" + "OtherDetail : "
									+ String.valueOf(OtherDetail) + "\n";
						} else {
							str = "CarID가 " + String.valueOf(CarID) + "인 차량은 수리가 필요없습니다.";
						}
					} else {
						str = "CarID가 " + String.valueOf(CarID) + "인 차량은 반환된 적이 없습니다.";
					}

					SejongCampingGUI.txtResult.append(str);

				} else if (b.getText().equals("정비정보 삭제")) {
					SejongCampingGUI.txtResult.setText("");

					int RepairID = Integer.parseInt(SejongCampingGUI.CRNUMTF.getText());

					query = "SELECT * FROM Repair WHERE RepairID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);
					pstmt.setInt(1, RepairID);
					rs = pstmt.executeQuery();
					int carid = 0;
					if (rs.next()) {
						carid = rs.getInt(2);
					}

					query = "DELETE FROM Repair WHERE RepairID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					pstmt.setInt(1, RepairID);

					int result = pstmt.executeUpdate();
					String str = "";
					if (result >= 1) {
						str = "Repair 테이블에서 RepairID가 " + String.valueOf(RepairID) + "인 튜플을 제거했습니다.\n"
								+ "수리가 끝났기 떄문에 CheckCar 테이블에서 " + "carID가 " + String.valueOf(carid)
								+ "인 튜플의 RepairRequire을 0으로 바꿔줍니다.\n";
						query = "UPDATE CheckCar SET RepairRequire = 0 WHERE CarID = ?";
						pstmt = SejongCampingGUI.con.prepareStatement(query);
						pstmt.setInt(1, carid);
						pstmt.executeUpdate();
					} else
						str = "변경된 사항이 없습니다.\n";

					SejongCampingGUI.txtResult.append(str);

				} else if (b.getText().equals("정비정보 변경")) {
					SejongCampingGUI.txtResult.setText("");

					query = "UPDATE Repair SET CarID = ?, " + "CenterID = ?, " + "RepairDetail = ?, "
							+ "RepairDate = ?, " + "RepairCost = ?, " + "PayDate = ?, " + "OtherDetail = ? "
							+ "WHERE RepairID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);

					int RepairID = Integer.parseInt(SejongCampingGUI.CRNUMTF.getText());
					int CarID = Integer.parseInt(SejongCampingGUI.CCARIDTF.getText());
					int CenterID = Integer.parseInt(SejongCampingGUI.CREPAIRIDTF.getText());
					String RepairDetail = SejongCampingGUI.REPAIRDETAILTF.getText().equals("") ? "NULL" : SejongCampingGUI.REPAIRDETAILTF.getText();
					;
					String RepairDate = SejongCampingGUI.REPAIRDATETF.getText().equals("") ? "NULL" : SejongCampingGUI.REPAIRDATETF.getText();
					;
					int RepairCost = SejongCampingGUI.REPAIRCOSTTF.getText().equals("") ? 0 : Integer.parseInt(SejongCampingGUI.REPAIRCOSTTF.getText());
					String PayDate = SejongCampingGUI.RETURNDATETF.getText().equals("") ? "NULL" : SejongCampingGUI.RETURNDATETF.getText();
					;
					String OtherDetail = SejongCampingGUI.OTHERREPAIRINFOTF.getText().equals("") ? "NULL" : SejongCampingGUI.OTHERREPAIRINFOTF.getText();
					;

					pstmt.setInt(8, RepairID);
					pstmt.setInt(1, CarID);
					pstmt.setInt(2, CenterID);
					pstmt.setString(3, RepairDetail);
					pstmt.setString(4, RepairDate);
					pstmt.setInt(5, RepairCost);
					pstmt.setString(6, PayDate);
					pstmt.setString(7, OtherDetail);

					int result = pstmt.executeUpdate();
					String str = "";

					if (result >= 1)
						str = "Repair 테이블에 RepairID가 " + String.valueOf(RepairID) + "인 튜플의 내용을 변경했습니다.\n";
					else
						str = "변경된 사항이 없습니다.\n";
					SejongCampingGUI.txtResult.append(str);
				} else if (b.getText().equals("검색1")) {
					SejongCampingGUI.txtResult.setText("");
					SejongCampingGUI.txtResult.append("==== 현재 수리가 필요한 차량 전체에 대한 현재 수리 진행 상황 ====\n");
					query = "SELECT  cc.RentalID, cc.CarID, cc.OfficeID, " + "cc.CustID, cc.RepairRequire, "
							+ "r.RepairID, r.CenterID, r.RepairDetail, " + "r.RepairDate, r.RepairCost, r.PayDate, "
							+ "r.OtherDetail " + "FROM CheckCar cc " + "LEFT OUTER JOIN " + "Repair r "
							+ "ON cc.CarID = r.CarID WHERE cc.RepairRequire = 1";
					SejongCampingGUI.txtResult.append("RentalID\tCarID\tOfficeID\tCustID\tRepairRequire\tRepairID\t");
					SejongCampingGUI.txtResult.append("CenterID\tRepairDetail\tRepairDate\tRepairCost\tPayDate\tOtherDetail\n");
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getInt(4)
								+ "\t" + rs.getInt(5) + "\t" + rs.getInt(6) + "\t" + rs.getInt(7) + "\t"
								+ rs.getString(8) + "\t" + rs.getString(9) + "\t" + rs.getInt(10) + "\t"
								+ rs.getString(11) + "\t" + rs.getString(12) + "\n";
						SejongCampingGUI.txtResult.append(str);
					}
				} else if (b.getText().equals("검색2")) {
					SejongCampingGUI.txtResult.setText("");
					SejongCampingGUI.txtResult.append("==== 현재 차량을 렌트 중인 고객의 전체 고객 정보와 해당 캠핑카 등록 번호 ====\n");

					query = "SELECT r.CarID, r.CustID, c.CustName, " + "c.CustAddress, c.CustPhone, c.CustEmail "
							+ "FROM Customer c " + "INNER JOIN RentalCar r " + "ON c.CustID = r.CustID";
					SejongCampingGUI.txtResult.append("CarID\tCustID\tCustName\tCustAddress\tCustPhone\tCustEmail\n");
					rs = stmt.executeQuery(query);

					while (rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getString(3) + "\t"
								+ rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\n";
						SejongCampingGUI.txtResult.append(str);
					}
				} else if (b.getText().equals("검색3")) {
					SejongCampingGUI.txtResult.setText("");
					SejongCampingGUI.txtResult.append("==== 현재 수리가 필요해서 정비중인 차량이 없는 캠핑카 정비소의 전체 정보 ====\n");

					query = "SELECT * FROM RepairCenter " + "WHERE CenterID " + "NOT IN "
							+ "(SELECT CenterID FROM Repair)";

					SejongCampingGUI.txtResult.append("CenterID\tCenterName\tCenterAddress\tCenterPhone\tManagerName\tManagerEmail\n");
					rs = stmt.executeQuery(query);

					while (rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
								+ rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\n";
						SejongCampingGUI.txtResult.append(str);
					}
				} else if (b.getText().equals("검색4")) {
					SejongCampingGUI.txtResult.setText("");
					SejongCampingGUI.txtResult.append("==== 현재 수리중인 차량의 대여회사와 수리를 맡은 정비소의 ID와 이름, 주소 ====\n");

					query = "SELECT ro.OfficeID, ro.OfficeName, ro.OfficeAddress, "
							+ "rc.CenterID, rc.CenterName, rc.CenterAddress "
							+ "FROM RentalOffice ro, RepairCenter rc, Repair r " + "WHERE ro.OfficeID = r.OfficeID "
							+ "AND r.CenterID = rc.CenterID";

					SejongCampingGUI.txtResult.append("OfficeID\tOfficeName\tOfficeAddress\tCenterID\tCenterName\tCenterAddress\n");
					rs = stmt.executeQuery(query);

					while (rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
								+ rs.getInt(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\n";
						SejongCampingGUI.txtResult.append(str);
					}
				} else if (b.getText().equals("검색하기")) {
					int wantCarPeople = SejongCampingGUI.usnumtf.getText().equals("") ? 0 : Integer.parseInt(SejongCampingGUI.usnumtf.getText());
					int wantRentCost = SejongCampingGUI.uscosttf.getText().equals("") ? Integer.MAX_VALUE
							: Integer.parseInt(SejongCampingGUI.uscosttf.getText());

					query = "SELECT * FROM CampingCar " + "WHERE CarRentCost <= ? " + "AND " + "CarPeople >= ? "
							+ "AND " + "CarID NOT IN " + "(SELECT CarID FROM RentalCar)";
					pstmt = SejongCampingGUI.con.prepareStatement(query);
					pstmt.setInt(1, wantRentCost);
					pstmt.setInt(2, wantCarPeople);

					rs = pstmt.executeQuery();
					SejongCampingGUI.txtResult.setText("");
					SejongCampingGUI.txtResult.append("==== 사용자가 입력한 조건에 맞는 대여 가능한 캠핑카 목록 ====\n");
					SejongCampingGUI.txtResult.append(
							"CarID\tCarName\tCarNumber\tCarPeople\tCarImage\tCarDetail\tCarRentCost\tOfficeID\tEnrollDate\n");

					while (rs.next()) {
						String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
								+ rs.getInt(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getInt(7)
								+ "\t" + rs.getInt(8) + "\t" + rs.getString(9) + "\n";
						SejongCampingGUI.txtResult.append(str);
					}
				} else if (b.getText().equals("대여하기")) {
					SejongCampingGUI.txtResult.setText("");

					int RentalID = Integer.parseInt(SejongCampingGUI.urrentidtf.getText());
					int CarID = Integer.parseInt(SejongCampingGUI.urcaridtf.getText());
					int CustID = Integer.parseInt(SejongCampingGUI.urcustidtf.getText());
					String RentalDate = SejongCampingGUI.urrentdatetf.getText().equals("") ? "NULL" : SejongCampingGUI.urrentdatetf.getText();
					int RentalTerm = SejongCampingGUI.urtermtf.getText().equals("") ? 7 : Integer.parseInt(SejongCampingGUI.urtermtf.getText());
					int OfficeID = -1;

					query = "SELECT * FROM RentalCar WHERE CarID = ?";
					pstmt = SejongCampingGUI.con.prepareStatement(query);
					pstmt.setInt(1, CarID);
					rs = pstmt.executeQuery();

					if (rs.next()) {
						String str = "현재 CarID가 " + String.valueOf(CarID) + "인 차량은 대여중입니다.\n";
						SejongCampingGUI.txtResult.append(str);
					} else {

						query = "SELECT OfficeID FROM CampingCar WHERE CarID = ?";
						pstmt = SejongCampingGUI.con.prepareStatement(query);
						pstmt.setInt(1, CarID);

						rs = pstmt.executeQuery();
						if (rs.next()) {
							OfficeID = rs.getInt(1);
						}

						query = "INSERT INTO RentalCar" + "(RentalID, CarID, CustID, OfficeID, RentalDate, RentalTerm) "
								+ "VALUES (?,?,?,?,?,?)";

						pstmt = SejongCampingGUI.con.prepareStatement(query);
						pstmt.setInt(1, RentalID);
						pstmt.setInt(2, CarID);
						pstmt.setInt(3, CustID);
						pstmt.setInt(4, OfficeID);
						pstmt.setString(5, RentalDate);
						pstmt.setInt(6, RentalTerm);

						int result = pstmt.executeUpdate();
						String str = "RentalCar 테이블에 " + result + "개 행이 추가되었습니다.\n" + "==== 대여 내역 ====\n"
								+ "RentalID : " + String.valueOf(RentalID) + "\n" + "CarID : " + String.valueOf(CarID)
								+ "\n" + "CustID : " + String.valueOf(CustID) + "\n" + "OfficeID : "
								+ String.valueOf(OfficeID) + "\n" + "RentalDate : " + String.valueOf(RentalDate) + "\n"
								+ "RentalTerm : " + String.valueOf(RentalTerm) + "\n";
						SejongCampingGUI.txtResult.append(str);
					}
				}
			}
		} catch (Exception e2) {
			if (e2.toString().contains("PRIMARY")) {
				SejongCampingGUI.txtResult.append("\n입력값이 PRIMARY KEY 제약조건을 위배했습니다.\n");
			} else if (e2.toString().contains("FOREIGN")) {
				SejongCampingGUI.txtResult.append("\n입력값이 FOREIGN KEY 제약조건을 위배했습니다.\n");
			} else if (e2.toString().contains("NumberFormatException")) {
				SejongCampingGUI.txtResult.append("\n------Int 항목에 잘못된 값을 입력하셨습니다.------\n");
			} else {
				SejongCampingGUI.txtResult.append("\nPRIMARY KEY, FOREIGH KEY, NumberFormat 외에 에러가 발생했습니다.\n");
			}

			SejongCampingGUI.txtResult.append("\n\n자세한 사항 : ");

			String str = e2.toString();
			SejongCampingGUI.txtResult.append(str);

			e2.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (pstmt != null)
					pstmt.close();
			} catch (Exception e3) {
				// TODO: handle exception
			}
		}

	}

}
