package com.pbd.PBD.Project.Repository;

import com.pbd.PBD.Project.Entity.Joc;
import com.pbd.PBD.Project.GameType.GameType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Joc, Integer> {

    @Query("Select g from Joc g")
    List<Joc> getAllGames();


}
