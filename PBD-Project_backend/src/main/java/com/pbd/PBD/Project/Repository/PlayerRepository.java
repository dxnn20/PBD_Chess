package com.pbd.PBD.Project.Repository;

import com.pbd.PBD.Project.Entity.Joc;
import com.pbd.PBD.Project.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("SELECT p FROM Player p")
    List<Player> getAllPlayers();

    @Query("SELECT j FROM Joc j WHERE j.jucator1.id = ?1 OR j.jucator2.id = ?1")
    List<Joc> findGamesByPlayerId(Integer id);

}
