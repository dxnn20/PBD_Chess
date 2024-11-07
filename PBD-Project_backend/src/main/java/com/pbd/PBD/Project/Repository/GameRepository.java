package com.pbd.PBD.Project.Repository;

import com.pbd.PBD.Project.DTO.BestPlayerDTO;
import com.pbd.PBD.Project.DTO.PlayerWithMostGamesDTO;
import com.pbd.PBD.Project.Entity.Joc;
import com.pbd.PBD.Project.Entity.Player;
import com.pbd.PBD.Project.GameType.GameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Joc, Integer> {

    @Query("Select g from Joc g")
    List<Joc> getAllGames();

    @Query("SELECT g FROM Joc g WHERE g.startDate BETWEEN :startDate AND :endDate ORDER BY g.type, g.startDate")
    List<Joc> findAllByDateRangeSortedByTypeAndStartDate(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query("SELECT new com.pbd.PBD.Project.DTO.BestPlayerDTO(winner, COUNT(*)) " +
            "FROM Joc " +
            "GROUP BY winner " +
            "ORDER BY COUNT(*) DESC")
    Optional<BestPlayerDTO> findBestPlayerWithWinCount();

    @Query("SELECT new com.pbd.PBD.Project.DTO.PlayerWithMostGamesDTO(p, COUNT(g)) " +
            "FROM Joc g JOIN Player p ON g.jucator1.id = p.id OR g.jucator2.id = p.id " +
            "GROUP BY p " +
            "ORDER BY COUNT(g) DESC LIMIt 1")
    Optional<PlayerWithMostGamesDTO> findPlayerWithMostGamesPlayed();

}
