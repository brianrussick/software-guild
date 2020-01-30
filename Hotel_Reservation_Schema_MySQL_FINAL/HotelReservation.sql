DROP DATABASE IF EXISTS HotelReservation;

-- Create database & tables w/ primary keys --
CREATE DATABASE HotelReservation;
USE HotelReservation;

CREATE TABLE ContactInfo (
ContactID INT(15) NOT NULL AUTO_INCREMENT,
FullName VARCHAR(70) NOT NULL,
Email VARCHAR(45) NULL,
PhoneNum CHAR(12) NOT NULL,
Age INT(3) NOT NULL,
PRIMARY KEY(ContactID)
);

CREATE TABLE Guest (
GuestID INT(15) NOT NULL AUTO_INCREMENT,
ContactID INT(15) NOT NULL,
PRIMARY KEY(GuestID),
CONSTRAINT FK_Guest_ContactInfo
FOREIGN KEY(ContactID) REFERENCES ContactInfo(ContactID)
);

CREATE TABLE Customer (
CustomerID INT(15) NOT NULL AUTO_INCREMENT,
ContactID INT(15) NOT NULL,
PRIMARY KEY(CustomerID),
CONSTRAINT FK_Customer_ContactInfo
FOREIGN KEY(ContactID) REFERENCES ContactInfo(ContactID)
);

CREATE TABLE RoomType (
RoomTypeID INT(15) NOT NULL AUTO_INCREMENT,
RoomType VARCHAR(20) NOT NULL,
PRIMARY KEY(RoomTypeID)
);

CREATE TABLE RoomRate (
RateID INT(15) NOT NULL AUTO_INCREMENT,
RoomTypeID INT(15) NOT NULL,
RoomRate DECIMAL NOT NULL,
StartDate DATE NOT NULL,
EndDate DATE,
PRIMARY KEY(RateID),
CONSTRAINT FK_RoomRate_RoomType
FOREIGN KEY(RoomTypeID) REFERENCES RoomType(RoomTypeID)
);

CREATE TABLE HotelFloor (
FloorID INT(15) NOT NULL AUTO_INCREMENT,
PRIMARY KEY(FloorID)
);

CREATE TABLE Amenities (
AmenitiesID INT(15) NOT NULL AUTO_INCREMENT,
Amenities VARCHAR(50) NOT NULL,
PRIMARY KEY(AmenitiesID)
);

CREATE TABLE Room (
RoomNum INT(15) NOT NULL,
RoomTypeID INT(15) NOT NULL,
FloorID INT(15) NOT NULL,
RoomOccupantLimit INT(3) NOT NULL,
PRIMARY KEY(RoomNum),
CONSTRAINT FK_Room_Floor 
FOREIGN KEY(FloorID) REFERENCES HotelFloor(FloorID),
CONSTRAINT FK_Room_RoomType
FOREIGN KEY(RoomTypeID) REFERENCES RoomType(RoomTypeID)
);

CREATE TABLE AddOns (
AddOnsID INT(15) NOT NULL AUTO_INCREMENT,
AddOnsName VARCHAR(55),
PRIMARY KEY(AddOnsID)
);

CREATE TABLE AddOnsRate (
AddOnsRateID INT(15) NOT NULL AUTO_INCREMENT,
AddOnsID INT(15) NOT NULL,
AddOnsRate DECIMAL NOT NULL,
StartDate DATE NOT NULL,
EndDate DATE NULL,
PRIMARY KEY(AddOnsRateID),
CONSTRAINT FK_AddOnsRate_AddO
FOREIGN KEY(AddOnsID) REFERENCES AddOns(AddOnsID)
);

CREATE TABLE AddOnsForGuest (
GuestID INT(15) NOT NULL,
AddOnsID INT(15) NOT NULL,
PRIMARY KEY(GuestID, AddOnsID),
CONSTRAINT FK_CustomerAddOns_Guest
FOREIGN KEY(GuestID) REFERENCES Guest(GuestID),
CONSTRAINT FK_AddOnsForGuest_AddOns
FOREIGN KEY(AddOnsID) REFERENCES AddOns(AddOnsID)
);

CREATE TABLE RoomAmenities (
RoomNum INT(15) NOT NULL,
AmenitiesID INT(15) NOT NULL,
PRIMARY KEY(RoomNum, AmenitiesID),
CONSTRAINT FK_RoomAmenities_Room
FOREIGN KEY(RoomNum) REFERENCES Room(RoomNum),
CONSTRAINT FK_RoomAmenities_Amenities
FOREIGN KEY(AmenitiesID) REFERENCES Amenities(AmenitiesID)
);

CREATE TABLE PromoCode (
PromoCodeID INT(15) NOT NULL AUTO_INCREMENT,
DiscountAmount DECIMAL NOT NULL,
StartDate DATE NOT NULL,
EndDate DATE NOT NULL,
PRIMARY KEY(PromoCodeID)
);

CREATE TABLE Bill (
BillID INT(15) NOT NULL AUTO_INCREMENT,
PRIMARY KEY(BillID)
);

CREATE TABLE SummaryBill (
SummaryBillID INT(15) NOT NULL AUTO_INCREMENT,
BillID INT(15) NOT NULL,
HotelDuration INT(15) NULL,
RoomNum INT(5) NULL,
Category VARCHAR(45) NOT NULL,
Cost DECIMAL NOT NULL,
FinalCost DECIMAL NOT NULL,
PRIMARY KEY(SummaryBillID),
CONSTRAINT FK_SummaryBill_Bill
FOREIGN KEY(BillID) REFERENCES Bill(BillID)
);

CREATE TABLE HeaderForBilling (
HeaderForBillingID INT(15) NOT NULL AUTO_INCREMENT,
BillID INT(15) NOT NULL,
Total DECIMAL NOT NULL,
DiscountAmount INT(15) NULL,
Tax DECIMAL NOT NULL,
FinalTotal DECIMAL NOT NULL,
PRIMARY KEY(HeaderForBillingID),
CONSTRAINT FK_HeaderForBilling_Bill
FOREIGN KEY(BillID) REFERENCES Bill(BillID)
);

CREATE TABLE Reservation (
ReservationID INT(15) NOT NULL AUTO_INCREMENT,
CustomerID INT(15) NOT NULL,
PromoCodeID INT (15) NULL,
BillID INT NOT NULL,
CheckInDate DATE NOT NULL,
CheckOutDate DATE NOT NULL,
PRIMARY KEY(ReservationID),
CONSTRAINT FK_Reservation_Customer
FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID),
CONSTRAINT FK_Reservation_PromoCode
FOREIGN KEY(PromoCodeID) REFERENCES PromoCode(PromoCodeID),
CONSTRAINT FK_Reservation_Bill
FOREIGN KEY(BillID) REFERENCES Bill(BillID)
);

CREATE TABLE ReservationRoom (
ReservationID INT(15) NOT NULL,
RoomNum INT(15) NOT NULL,
PRIMARY KEY(RoomNum, ReservationID),
CONSTRAINT FK_ReservationRoom_Room
FOREIGN KEY(RoomNum) REFERENCES Room(RoomNum),
CONSTRAINT FK_ReservationRoom_Reservation
FOREIGN KEY (ReservationID) REFERENCES Reservation(ReservationID)
);

-- alter table to add foreign key --
ALTER TABLE Guest
Add ReservationID INT NOT NULL,
ADD CONSTRAINT FK_Guest_Reservation 
FOREIGN KEY(ReservationID) REFERENCES Reservation(ReservationID);

-- inject values into the database --
INSERT INTO Bill (BillID)
VALUES (1), 
(2),
(3), 
(4), 
(5), 
(6),
(7), 
(8);

INSERT INTO ContactInfo(FullName, Email, PhoneNum, Age)
VALUES ('Jeremiah Geleti', 'JJGeleti@mail.com', '345-019-2930', 35), 
('Jim Nasium', 'JimmyBuildsGyms@gmail.com', '876-555-1010', 50), 
('Maggie T. Loner', 'LonerMaggie@yahoo.com', '123-456-9000', 29),
('Don Johnson', 'DJ1999@netscape.com', '545-000-2345', 70), 
('Homer J. Simpson', NULL, '800-959-9988', 39),
('Gonzo Sr.', NULL, '111-222-3333', 90), 
('Candy Land Man Jr.', 'candylandmancan@gmail.com', '800-959-9988', 40),
('Dr. Test Testerman III', 'tester@gmail.com', '111-444-8790', 50);

INSERT INTO Amenities (Amenities)
VALUES ('Pool w/ Spa'), 
('Mini Fridge'), 
('Free Breakfast'),
('Free Wi-Fi');

INSERT INTO AddOns (AddOnsName)
VALUES ('VIP Parking'), 
('Digital Movie Purchase'), 
('HD Gamer Package');

INSERT INTO AddOnsRate(AddOnsId, StartDate, EndDate, AddOnsRate)
VALUES (1, '2019-01-01', '2019-12-31', 95.05),
(1, '2020-01-01', '2020-12-31', 99.95),
(2, '2019-01-01', '2019-12-31', 5.00), 
(2, '2020-01-01', '2020-12-31', 5.95), 
(3, '2019-01-01', '2019-12-31', 49.05), 
(3, '2020-01-01', '2020-12-31', 55.05);

INSERT INTO PromoCode (StartDate, EndDate, DiscountAmount)
VALUES ('2019-01-01', '2019-12-31', 100.00), 
('2020-01-01', '2020-12-31', 200.00), 
('2021-01-01', '2021-12-31', 300.00),
('2022-01-01', '2022-12-31', 400.00);

INSERT INTO Customer (ContactID)
VALUES (1), 
(2), 
(3), 
(4), 
(5), 
(6), 
(7), 
(8);

INSERT INTO Reservation (CustomerID, CheckInDate, CheckOutDate, PromoCodeID, BillID)
VALUES (7, '2019-07-10', '2019-07-17', 1, 1), 
(3, '2019-09-15', '2019-09-18', 1, 2), 
(8, '2019-12-21', '2019-12-28', 1, 3), 
(1, '2020-01-05', '2020-01-25', NULL, 4), 
(4, '2020-03-17', '2020-04-17', 2, 5), 
(5, '2021-03-19', '2021-03-23', 3, 6),
(2, '2022-04-15', '2022-04-27', NULL, 7), 
(6, '2022-09-03', '2022-09-22', NULL, 8);

INSERT INTO HotelFloor (FloorID)
VALUES (1), 
(2), 
(3),
(4), 
(5); 

INSERT INTO Guest(ContactID, ReservationID)
VALUES (1, 1), 
(2, 2), 
(3, 3), 
(4, 4), 
(5, 5), 
(6, 6), 
(7, 7), 
(8, 8);

INSERT INTO AddOnsForGuest (GuestID, AddOnsID)
VALUES (1, 1), 
(2, 3), 
(3, 3), 
(4, 2), 
(6, 1),
(8, 2);

INSERT INTO RoomType (RoomType)
VALUES ('Single'), 
('Queen'), 
('King'), 
('Deluxe Suite'), 
('Penthouse Suite');

INSERT INTO Room (RoomNum, RoomOccupantLimit, FloorID, RoomTypeID)
VALUES (100, 2, 1, 3), 
(199, 1, 1, 1), 
(200, 2, 2, 2), 
(299, 3, 2, 3), 
(300, 3, 3, 3), 
(399, 3, 3, 3), 
(400, 4, 4, 4),
(595, 5, 5, 5);

INSERT INTO ReservationRoom(RoomNum, ReservationID)
VALUES (100, 1), 
(199, 2), 
(200, 3), 
(299, 4), 
(300, 5), 
(399, 6), 
(400, 7), 
(595, 8);

INSERT INTO RoomRate (RoomTypeID, StartDate, EndDate, RoomRate)
VALUES (1, '2019-01-01', '2022-12-31', 99.99),
(1, '2023-01-01', '2024-12-31', 109.99),  
(2, '2019-01-01', '2022-12-31', 149.99), 
(2, '2023-01-01', '2024-12-31', 159.99), 
(3, '2019-01-01', '2022-12-31', 199.99), 
(3, '2023-01-01', '2024-12-31', 209.99), 
(4, '2019-01-01', '2022-12-31', 455.99),  
(4, '2023-01-01', '2024-12-31', 475.99),
(5, '2019-01-01', '2022-12-31', 9999.99),  
(5, '2023-01-01', '2024-12-31', 10500.99);

INSERT INTO RoomAmenities (RoomNum, AmenitiesID)
VALUES (100, 1), 
(199, 3), 
(200, 4), 
(299, 1), 
(300, 2), 
(399, 4), 
(400, 4), 
(595, 2);

-- create views for billing summary and room details --
CREATE VIEW ViewRoomDetails AS
SELECT bill.BillID, DATEDIFF(res.CheckOutDate, res.CheckInDate) AS HotelDuration, rrate.RoomRate, rtype.RoomType, room.RoomNum 
FROM Bill AS bill 
JOIN Reservation AS res ON bill.BillID = res.BillID
JOIN ReservationRoom AS rvr ON rvr.ReservationID = res.ReservationID
JOIN Room ON rvr.RoomNum = room.RoomNum
JOIN RoomType as rtype ON rtype.RoomTypeID = room.RoomTypeID
JOIN RoomRate as rrate ON rtype.RoomTypeID = rrate.RoomTypeID
WHERE res.CheckInDate BETWEEN rrate.StartDate AND rrate.EndDate; 

CREATE VIEW ViewBillingHeader AS
SELECT res.BillID, SUM(IFNULL(pro.DiscountAmount,0)) AS Discount, SUM(sb.FinalCost) AS Total, SUM(sb.FinalCost * .08) as Tax 
From SummaryBill as sb
JOIN Reservation as res ON sb.BillID = res.BillId
LEFT JOIN PromoCode as pro ON pro.PromoCodeID = res.PromoCodeID
GROUP BY RoomNum;

INSERT INTO SummaryBill (BillID, RoomNum, Category, Cost, FinalCost, HotelDuration)
SELECT BillID, RoomNum, RoomType, RoomRate, SUM(RoomRate * HotelDuration), HotelDuration
FROM ViewRoomDetails
GROUP BY RoomNum;

INSERT INTO HeaderForBilling(BillID, Total, Tax, DiscountAmount, FinalTotal)
SELECT BillID, Total, Tax, Discount, SUM(Total + Tax - Discount)
FROM ViewBillingHeader
GROUP BY Total;