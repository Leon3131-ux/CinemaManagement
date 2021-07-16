package com.bigbrain.cinema.controller;

import com.bigbrain.cinema.converter.HallConverter;
import com.bigbrain.cinema.domain.Hall;
import com.bigbrain.cinema.dto.SaveHallDto;
import com.bigbrain.cinema.service.HallService;
import com.bigbrain.cinema.validator.HallValidator;
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
public class HallController {

    private final HallService hallService;
    private final HallConverter hallConverter;
    private final HallValidator hallValidator;

    @InitBinder("saveHallDto")
    public void setBinder(WebDataBinder binder){binder.setValidator(hallValidator);}

    @RequestMapping(value = "/api/hall/save", method = RequestMethod.POST)
    @PreAuthorize("hasPermission('MANAGE_CINEMA')")
    public ResponseEntity<?> saveHall(@RequestBody @Validated SaveHallDto saveHallDto){
        Hall hall;
        if(saveHallDto.getId() != 0){
            Hall oldHall = hallService.getByIdOrThrowException(saveHallDto.getId());
            hall = hallService.update(oldHall, saveHallDto);
        }else {
            hall = hallConverter.toEntity(saveHallDto);
        }
        return new ResponseEntity<>(hallConverter.toDto(hallService.save(hall)), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/hall/getById/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getHallById(@PathVariable("id") Hall hall){
        return new ResponseEntity<>(hallConverter.toDto(hall), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/hall/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAllHalls(){
        return new ResponseEntity<>(hallConverter.convertAllToDto(hallService.getAll()), HttpStatus.OK);
    }

}
