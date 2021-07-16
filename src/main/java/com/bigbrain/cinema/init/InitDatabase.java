package com.bigbrain.cinema.init;

import com.bigbrain.cinema.domain.*;
import com.bigbrain.cinema.repository.*;
import lombok.AllArgsConstructor;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
public class InitDatabase {

    private final HallRepository hallRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final SeatRepository seatRepository;
    private final AgeRatingRepository ageRatingRepository;
    private final MovieRepository movieRepository;
    private final ShowingRepository showingRepository;

    @PostConstruct
    private void initDb(){

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

    private List<SeatType> initSeatTypes(){
        List<SeatType> seatTypes = new ArrayList<>();
        for(SeatTypeName seatTypeName: SeatTypeName.values()){
            seatTypes.add(seatTypeRepository.save(new SeatType(seatTypeName)));
        }
        return seatTypes;
    }

    private List<AgeRating> initAgeRatings(){
        List<AgeRating> ageRatings = new ArrayList<>();
        for(AgeRatingName ageRatingName: AgeRatingName.values()){
            ageRatings.add(ageRatingRepository.save(new AgeRating(ageRatingName)));
        }
        return ageRatings;
    }

}
