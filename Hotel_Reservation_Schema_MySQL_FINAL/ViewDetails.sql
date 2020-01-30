USE HotelReservation;

-- guests, reservations, check-in/check-out dates, ages --
SELECT res.ReservationID, cont.FullName AS Guest, cont.Age AS 'GuestAge', res.CheckInDate, res.CheckOutDate, 
DATEDIFF(res.CheckOutDate, res.CheckInDate) AS HotelDuration
FROM Reservation AS res
JOIN Guest AS g ON res.ReservationID = g.ReservationID
JOIN Customer AS cust ON res.CustomerID = cust.CustomerID
JOIN ContactInfo AS ci ON cust.ContactID = ci.ContactID
JOIN ContactInfo AS cont ON g.ContactID = cont.ContactID
ORDER BY HotelDuration;

-- display room info and all start/end dates for room rates --
SELECT room.RoomNum AS Room, room.FloorID AS Floor, rtype.RoomType, room.RoomOccupantLimit AS MaxOccupancy,
rrate.StartDate, rrate.EndDate, rrate.RoomRate, amen.Amenities
FROM Room room
JOIN RoomType rtype ON room.RoomTypeID = rtype.RoomTypeID
JOIN RoomRate rrate ON rtype.RoomTypeID = rrate.RoomTypeID
JOIN ReservationRoom rvr ON room.RoomNum = rvr.RoomNum
JOIN Reservation res ON rvr.ReservationID = res.ReservationID
JOIN RoomAmenities AS rmamen ON rmamen.RoomNum = room.RoomNum
JOIN Amenities AS amen ON amen.AmenitiesID = rmamen.AmenitiesID
ORDER BY RoomRate;

-- hotel customer bill summary sorted by reservation id #s --
SELECT res.ReservationID, ci.FullName AS Customer, hfb.Total, hfb.Tax, hfb.DiscountAmount, hfb.FinalTotal
FROM HeaderForBilling AS hfb
JOIN Reservation AS res ON hfb.BillID = res.BillID
JOIN Customer AS cust ON res.CustomerID = cust.CustomerID
JOIN ContactInfo AS ci ON cust.ContactID = ci.ContactID
ORDER BY ReservationID;

-- add-ons names, dates & rates --
SELECT ad.AddOnsName AS 'Add-On', arate.StartDate, arate.EndDate, arate.AddOnsRate AS Rate
FROM addons ad
JOIN addonsrate arate ON ad.AddOnsID = arate.AddOnsID
ORDER BY AddOnsRate;