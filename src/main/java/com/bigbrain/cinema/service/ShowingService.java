package com.bigbrain.cinema.service;

import com.bigbrain.cinema.domain.Movie;
import com.bigbrain.cinema.domain.Showing;
import com.bigbrain.cinema.dto.SaveShowingDto;
import com.bigbrain.cinema.repository.ShowingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShowingService {

    private final ShowingRepository showingRepository;
    private final HallService hallService;
    private final MovieService movieService;

    public Showing getByIdOrThrowException(long id){
        return showingRepository.findById(id).orElseThrow();
    }

    public Showing update(Showing oldShowing, SaveShowingDto saveShowingDto){
        oldShowing.setHall(hallService.getByIdOrThrowException(saveShowingDto.getHallId()));
        oldShowing.setMovie(movieService.getByIdOrThrowException(saveShowingDto.getMovieId()));
        oldShowing.setDateTime(saveShowingDto.getDateTime());
        return oldShowing;
    }

    public Showing save(Showing showing){
        return showingRepository.save(showing);
    }

    public List<Showing> getAll(){
        return showingRepository.findAll();
    }

    public List<Showing> getAllByMovie(Movie movie){
        return showingRepository.findAllByMovie(movie);
    }

}
