package com.bigbrain.cinema.controller;

import com.bigbrain.cinema.converter.ReservationConverter;
import com.bigbrain.cinema.domain.Reservation;
import com.bigbrain.cinema.domain.Showing;
import com.bigbrain.cinema.domain.User;
import com.bigbrain.cinema.dto.SaveReservationDto;
import com.bigbrain.cinema.service.ReservationService;
import com.bigbrain.cinema.service.UserService;
import com.bigbrain.cinema.validator.ReservationValidator;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final ReservationConverter reservationConverter;
    private final ReservationValidator reservationValidator;
    private final UserService userService;

    @InitBinder("saveReservationDto")
    public void setBinder(WebDataBinder binder){binder.setValidator(reservationValidator);}

    @RequestMapping(value = "/api/reservation/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveReservation(@RequestBody @Validated SaveReservationDto saveReservationDto, Principal principal){
        User user = userService.getByEmailOrThrowException(principal.getName());
        Reservation reservation = reservationService.save(reservationConverter.toEntity(saveReservationDto, user));
        return new ResponseEntity<>(reservationConverter.toDto(reservation), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/reservation/getByShowing/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getReservationsByShowing(@PathVariable("id")Showing showing){
        return new ResponseEntity<>(reservationConverter.convertAllToDto(reservationService.getByShowing(showing)), HttpStatus.OK);
    }

}
