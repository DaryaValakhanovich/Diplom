import com.github.javafaker.Faker;


import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DatabaseGeneration {
    private static Faker faker = new Faker();
//    public static void generate(int numberOfUsers, int numberOfRooms, int numberOfRoomTypes, int numberOfReservations){
//        User user;
//        Room room;
//        Reservation reservation;
//
//        for (int i = 0; i < numberOfUsers; i++) {
//            user = new User();
//            user.setFirstName(faker.name().firstName());
//            user.setLastName(faker.name().lastName());
//            user.setBirthday(convertToLocalDate(faker.date().birthday()));
//            user.setEmail(faker.internet().emailAddress());
//            user.setPassword(faker.internet().password());
//            user.setRole(Role.USER);
//            UserDao.getInstance().create(user);
//        }
//
//        for (int i = 0; i < numberOfRooms; i++) {
//            room = new Room();
//            room.setType(RoomTypeDao.getInstance().findById(faker.random().nextInt(1, numberOfRoomTypes)).get());
//            room.setNumberOfPlaces(faker.random().nextInt(1, 4));
//            RoomDao.getInstance().create(room);
//        }
//
//        Date dateFrom = convertToDate(LocalDate.parse("2025-01-01"));
//        Date dateTo = convertToDate(LocalDate.parse("2026-01-01"));
//
//
//        for (int i = 0; i < numberOfReservations; i++) {
//            reservation = new Reservation();
//            Date dayOfArrival = faker.date().between(dateFrom, dateTo);
//            dayOfArrival.setTime(dayOfArrival.getTime() + 100000000);
//            Date dayOfDeparture = faker.date().between(dayOfArrival, dateTo);
//            reservation.setRoom(RoomDao.getInstance().findById(faker.random().nextInt(1, numberOfRooms)).get());
//            reservation.setDayOfArrival(convertToLocalDate(dayOfArrival));
//            reservation.setDayOfDeparture(convertToLocalDate(dayOfDeparture));
//            reservation.setUserId(faker.random().nextInt(1, numberOfUsers));
//            ReservationDao.getInstance().create(reservation);
        }
//    }
//
//    public static Date convertToDate(LocalDate dateToConvert) {
//        return java.sql.Date.valueOf(dateToConvert);
//    }
//
//    public static LocalDate convertToLocalDate(Date dateToConvert) {
//        return Instant.ofEpochMilli(dateToConvert.getTime())
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate();
//    }
//}
//