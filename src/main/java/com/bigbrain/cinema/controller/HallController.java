package com.bigbrain.cinema.controller;

import com.bigbrain.cinema.converter.HallConverter;
import com.bigbrain.cinema.domain.Hall;
import com.bigbrain.cinema.dto.SaveHallDto;
import com.bigbrain.cinema.service.HallService;
import com.bigbrain.cinema.validator.HallValidator;
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
public class HallController {

    private final HallService hallService;
    private final HallConverter hallConverter;
    private final HallValidator hallValidator;

    @InitBinder("saveHallDto")
    public void setBinder(WebDataBinder binder){binder.setValidator(hallValidator);}

    @RequestMapping(value = "/api/hall/create", method = RequestMethod.POST)
    public ResponseEntity<?> createHall(@RequestBody @Validated SaveHallDto saveHallDto){
        Hall hall;
        if(saveHallDto.getId() != 0){
            Hall oldHall = hallService.getByIdOrThrowException(saveHallDto.getId());
            hall = hallService.update(oldHall, saveHallDto);
        }else {
            hall = hallConverter.toEntity(saveHallDto);
        }
        return new ResponseEntity<>(hallConverter.toDto(hallService.save(hall)), HttpStatus.OK);
    }

}
