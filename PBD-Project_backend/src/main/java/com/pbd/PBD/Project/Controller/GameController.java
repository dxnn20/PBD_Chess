package com.pbd.PBD.Project.Controller;

import com.pbd.PBD.Project.DTO.GameDTO;
import com.pbd.PBD.Project.Entity.Joc;
import com.pbd.PBD.Project.Entity.Player;
import com.pbd.PBD.Project.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create")
    public ResponseEntity<Joc> createGame(@RequestBody GameDTO game) {

        try {
            Joc savedGame = gameService.createGame(game);
            return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-by-date", produces = "application/json")
    public ResponseEntity<List<Joc>> findAllByDateRangeSortedByTypeAndStartDate(
            @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {

        System.out.println(startDate + " " + endDate);

        java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

        try {
            List<Joc> games = gameService.findAllByDateRangeSortedByTypeAndStartDate(sqlStartDate, sqlEndDate);
            return ResponseEntity.ok(games); // Responds with 200 OK and the games list
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Responds with 400 Bad Request
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Joc> updateGame(@RequestBody Joc game) {
        try {
            // Ensure that we only update players and leave other fields intact
            Joc updatedGame = gameService.updateGame(game);
            return new ResponseEntity<>(updatedGame, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
