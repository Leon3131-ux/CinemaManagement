package com.bigbrain.cinema.controller;

import com.bigbrain.cinema.converter.SeatConverter;
import com.bigbrain.cinema.dto.SeatDto;
import com.bigbrain.cinema.service.SeatService;
import com.bigbrain.cinema.validator.SeatValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class SeatController {

    private final SeatValidator seatValidator;
    private final SeatService seatService;
    private final SeatConverter seatConverter;

    @InitBinder("seatDto")
    public void setBinder(WebDataBinder binder){binder.setValidator(seatValidator);}

    @RequestMapping(value = "/api/seat/save", method = RequestMethod.POST)
    @PreAuthorize("hasPermission('MANAGE_CINEMA')")
    public ResponseEntity<?> saveSeat(@RequestBody @Validated SeatDto seatDto){
        return new ResponseEntity<>(seatConverter.toDto(seatService.save(seatConverter.toEntity(seatDto))), HttpStatus.OK);
    }

}
