package com.bigbrain.cinema.controller;

import com.bigbrain.cinema.converter.ShowingConverter;
import com.bigbrain.cinema.domain.Showing;
import com.bigbrain.cinema.dto.SaveShowingDto;
import com.bigbrain.cinema.service.ShowingService;
import com.bigbrain.cinema.validator.ShowingValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class ShowingController {

    private final ShowingValidator showingValidator;
    private final ShowingService showingService;
    private final ShowingConverter showingConverter;

    @InitBinder("fridgeDto")
    public void initFridgeDtoBinder(WebDataBinder binder){binder.setValidator(showingValidator);}

    @RequestMapping(value = "/api/showing/create", method = RequestMethod.POST)
    public ResponseEntity<?> createShowing(@RequestBody @Validated SaveShowingDto saveShowingDto){
        Showing showing;
        if(saveShowingDto.getId() != 0){
            Showing oldShowing = showingService.getByIdOrThrowException(saveShowingDto.getId());
            showing = showingService.update(oldShowing, saveShowingDto);
        }else {
            showing = showingConverter.toEntity(saveShowingDto);
        }
        return new ResponseEntity<>(showingConverter.toDto(showingService.save(showing)), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/showing/get", method = RequestMethod.GET)
    public ResponseEntity<?> getShowings(){
        return new ResponseEntity<>(showingConverter.convertAllToDto(showingService.getAll()), HttpStatus.OK);
    }

}
