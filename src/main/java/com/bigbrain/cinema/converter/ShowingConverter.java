package com.bigbrain.cinema.converter;

import com.bigbrain.cinema.domain.Showing;
import com.bigbrain.cinema.dto.ReturnShowingDto;
import com.bigbrain.cinema.dto.SaveShowingDto;
import com.bigbrain.cinema.service.HallService;
import com.bigbrain.cinema.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShowingConverter {

    private final HallService hallService;
    private final MovieService movieService;
    private final HallConverter hallConverter;
    private final MovieConverter movieConverter;

    public Showing toEntity(SaveShowingDto saveShowingDto){
        Showing showing = new Showing();
        showing.setHall(hallService.getByIdOrThrowException(saveShowingDto.getHallId()));
        showing.setMovie(movieService.getByIdOrThrowException(saveShowingDto.getMovieId()));
        showing.setDateTime(saveShowingDto.getDateTime());
        return showing;
    }

    public ReturnShowingDto toDto(Showing showing){
        return new ReturnShowingDto(
                showing.getId(),
                movieConverter.toDto(showing.getMovie()),
                hallConverter.toShowingHallDto(showing.getHall()),
                showing.getDateTime()
        );
    }

}
