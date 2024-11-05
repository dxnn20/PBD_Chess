package com.pbd.PBD.Project.Controller;

import com.pbd.PBD.Project.DTO.GameDTO;
import com.pbd.PBD.Project.Entity.Joc;
import com.pbd.PBD.Project.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @PostMapping("/create")
    public ResponseEntity<Joc> createGame(@RequestBody GameDTO game) {
        try{
            Joc savedGame = gameService.createGame(game);
            return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
