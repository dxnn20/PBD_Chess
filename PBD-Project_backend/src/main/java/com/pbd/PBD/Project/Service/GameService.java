package com.pbd.PBD.Project.Service;

import com.pbd.PBD.Project.DTO.GameDTO;
import com.pbd.PBD.Project.Entity.Joc;
import com.pbd.PBD.Project.Entity.Player;
import com.pbd.PBD.Project.Entity.Tip;
import com.pbd.PBD.Project.Repository.GameRepository;
import com.pbd.PBD.Project.Repository.PlayerRepository;
import com.pbd.PBD.Project.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Proxy;
import java.sql.Date;


@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final TypeRepository typeRepository;

    @Autowired
    public GameService(GameRepository gameRepository, PlayerRepository playerRepository, TypeRepository typeRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.typeRepository = typeRepository;
    }

    public Joc createGame(GameDTO gameDTO) {

        System.out.println(gameDTO.getTypeID());
        System.out.println(gameDTO.getWinner());


        if ((gameDTO.getNrPartide() % 2) != 1)
            throw new IllegalArgumentException("Number of games must be odd.");

        // Fetching the related entities from the repository
        Tip type = typeRepository.findById(gameDTO.getTypeID())
                .orElseThrow(() -> new IllegalArgumentException("Invalid type ID: " + gameDTO.getTypeID()));

        // Fetch the players, allowing for the winner to be null
        Player winner = gameDTO.getWinner() != null ?
                playerRepository.findById(gameDTO.getWinner())
                        .orElseThrow(() -> new IllegalArgumentException("Invalid winner ID: " + gameDTO.getWinner())) : null;

        Player jucator1 = playerRepository.findById(gameDTO.getJucator1())
                .orElseThrow(() -> new IllegalArgumentException("Invalid player1 ID: " + gameDTO.getJucator1()));

        Player jucator2 = playerRepository.findById(gameDTO.getJucator2())
                .orElseThrow(() -> new IllegalArgumentException("Invalid player2 ID: " + gameDTO.getJucator2()));

        Joc game = new Joc(
                Date.valueOf(gameDTO.getStartDate()),
                Date.valueOf(gameDTO.getEndDate()),
                gameDTO.getType(),
                type,
                winner,
                jucator1,
                jucator2,
                gameDTO.getNrPartide(),
                gameDTO.getNrPartideJucate(),
                gameDTO.getScorJucator1(),
                gameDTO.getScorJucator2()
        );

        System.out.println(game);

        return gameRepository.save(game);
    }

}
