package com.bigbrain.cinema.init;

import com.bigbrain.cinema.domain.*;
import com.bigbrain.cinema.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class InitDatabase {

    private final HallRepository hallRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final SeatRepository seatRepository;
    private final AgeRatingRepository ageRatingRepository;
    private final MovieRepository movieRepository;
    private final ShowingRepository showingRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct()
    private void initDb(){
//        initAgeRatings();
//        initSeatTypes();
//        List<Permission> permissions = initPermissions();
//        initAdminUser(permissions);
    }

    private Hall initHall(){
        Hall hall = new Hall(
                "hall1",
                2,
                10,
                new ArrayList<>()
        );

        return hallRepository.save(hall);
    }

    private Movie initMovie(List<AgeRating> ageRatings){
        return movieRepository.save(
                new Movie(
                        "Really Epic Movie",
                        "Its a movie about 2 brothers",
                        149,
                        ageRatings.get(0),
                        new ArrayList<>()
                )
        );
    }

    private Showing initShowing(Movie movie, Hall hall){
        return showingRepository.save(new Showing(movie, hall, LocalDateTime.now().plusDays(10)));
    }

    private void initReservations(Movie movie, Showing showing, User user){

    }

    private List<Seat> initSeats(Hall hall, List<SeatType> seatTypes){
        List<Seat> seats = new ArrayList<>();
        for(int i = 0; i < hall.getSeatRows(); i++){
            for (int y = 0; y < hall.getSeatColumns(); y++){
                seats.add(seatRepository.save(new Seat(hall, seatTypes.get(0), y, i)));
            }
        }
        return seats;
    }

    private void initSeatTypes(){
        for(SeatTypeName seatTypeName: SeatTypeName.values()){
            seatTypeRepository.save(new SeatType(seatTypeName));
        }
    }

    private void initAgeRatings(){
        for(AgeRatingName ageRatingName: AgeRatingName.values()){
            ageRatingRepository.save(new AgeRating(ageRatingName));
        }
    }

    private List<Permission> initPermissions(){
        List<Permission> permissions = new ArrayList<>();
        for(PermissionName permissionName: PermissionName.values()){
            permissions.add(permissionRepository.save(new Permission(permissionName)));
        }
        return permissions;
    }

    private void initAdminUser(List<Permission> permissions){
        userRepository.save(new User(
                "admin@email.com",
                bCryptPasswordEncoder.encode("admin"),
                "Admin",
                "Admin",
                "Adminstreet",
                new Date(),
                "00000000000",
                permissions));
    }

}
