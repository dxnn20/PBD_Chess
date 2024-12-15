package com.pbd.PBD.Project.Service;

import com.pbd.PBD.Project.DTO.BestPlayerDTO;
import com.pbd.PBD.Project.DTO.GameDTO;
import com.pbd.PBD.Project.DTO.PlayerWithMostGamesDTO;
import com.pbd.PBD.Project.Entity.Joc;
import com.pbd.PBD.Project.Entity.Player;
import com.pbd.PBD.Project.Entity.Tip;
import com.pbd.PBD.Project.Repository.GameRepository;
import com.pbd.PBD.Project.Repository.PlayerRepository;
import com.pbd.PBD.Project.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.net.Proxy;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


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

        if ((gameDTO.getNrPartide() % 2) != 1)
            throw new IllegalArgumentException("Number of games must be odd.");

        // Fetching the related entities from the repository
        Tip type = typeRepository.getTypeByName(gameDTO.getType())
                .orElseThrow(() -> new IllegalArgumentException("Invalid type: " + gameDTO.getType()));

        // Get player 1, it needs to exist
        Player jucator1 = playerRepository.findById(gameDTO.getJucator1())
                .orElseThrow(() -> new IllegalArgumentException("Invalid player1 ID: " + gameDTO.getJucator1()));

        // Get player 2, same rules.
        Player jucator2 = playerRepository.findById(gameDTO.getJucator2())
                .orElseThrow(() -> new IllegalArgumentException("Invalid player2 ID: " + gameDTO.getJucator2()));



        java.sql.Date startDate = Date.valueOf(gameDTO.getStartDate());
        java.sql.Date endDate = Date.valueOf(gameDTO.getEndDate());

        System.out.println("Start date: " + startDate);
        System.out.println("End date: " + endDate);

        // Create new object with all the collected data
        Joc game = new Joc(
                startDate,
                endDate,
                gameDTO.getType(),
                type,
                null,
                jucator1,
                jucator2,
                gameDTO.getNrPartide(),
                0,
                0,
                0
        );

        // For debugging
        System.out.println(game);

        // Add the object to the GAMES table
        return gameRepository.save(game);
    }

    public List<Joc> findAllByDateRangeSortedByTypeAndStartDate(Date startDate, Date endDate) throws IllegalArgumentException {

        if (startDate.after(endDate))
            throw new IllegalArgumentException("Bad Dates!");

        return this.gameRepository.findAllByDateRangeSortedByTypeAndStartDate(startDate, endDate);
    }

    public BestPlayerDTO findBestPlayerWithWinCount() {
        return gameRepository.findBestPlayerWithWinCount()
                .orElseThrow(() -> new IllegalArgumentException("No games found"));
    }

    public PlayerWithMostGamesDTO findPlayerWithMostGamesPlayed() {
        return gameRepository.findPlayerWithMostGamesPlayed()
                .orElseThrow(() -> new IllegalArgumentException("No games found"));
    }

    public List<Joc> findGamesByPlayerId(Long id) {
        return gameRepository.findGamesByPlayerId(id);
    }

    public Joc updateGame(Joc game) {
        Joc gameToUpdate = gameRepository.findById(game.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid game ID: " + game.getId()));

        gameToUpdate.setStartDate(Date.valueOf(game.getStartDate().toLocalDate()));
        gameToUpdate.setEndDate(Date.valueOf(game.getEndDate().toLocalDate()));
        gameToUpdate.setType(game.getType());
        gameToUpdate.setNrPartide(game.getNrPartide());

        return gameRepository.save(gameToUpdate);
    }
}
