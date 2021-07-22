package com.bigbrain.cinema.controller;

import com.bigbrain.cinema.converter.ShowingConverter;
import com.bigbrain.cinema.domain.Movie;
import com.bigbrain.cinema.domain.Showing;
import com.bigbrain.cinema.dto.SaveShowingDto;
import com.bigbrain.cinema.service.ShowingService;
import com.bigbrain.cinema.validator.ShowingValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ShowingController {

    private final ShowingValidator showingValidator;
    private final ShowingService showingService;
    private final ShowingConverter showingConverter;

    @InitBinder("saveShowingDto")
    public void setBinder(WebDataBinder binder){binder.setValidator(showingValidator);}

    @RequestMapping(value = "/api/showing/save", method = RequestMethod.POST)
    @PreAuthorize("hasPermission('MANAGE_SHOWINGS')")
    public ResponseEntity<?> saveShowing(@RequestBody @Validated SaveShowingDto saveShowingDto){
        Showing showing;
        if(saveShowingDto.getId() != 0){
            Showing oldShowing = showingService.getByIdOrThrowException(saveShowingDto.getId());
            showing = showingService.update(oldShowing, saveShowingDto);
        }else {
            showing = showingConverter.toEntity(saveShowingDto);
        }
        return new ResponseEntity<>(showingConverter.toDto(showingService.save(showing)), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/showing/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getShowings(){
        return new ResponseEntity<>(showingConverter.convertAllToDto(showingService.getAll()), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/showing/getByMovie/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getShowingsByMovie(@PathVariable("id")Movie movie){
        return new ResponseEntity<>(showingConverter.convertAllToDto(showingService.getAllByMovie(movie)), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/showing/getAllOfToday", method = RequestMethod.GET)
    public ResponseEntity<?> getShowingsFromToday(){
        return new ResponseEntity<>(showingConverter.convertAllToDto(showingService.getAllFromToday()), HttpStatus.OK);
    }

}
