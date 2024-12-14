package com.pbd.PBD.Project.Repository;

import com.pbd.PBD.Project.Entity.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Tip, Integer> {

    @Query("SELECT t FROM Tip t WHERE t.nume = ?1")
    Optional<Tip> getTypeByName(String name);

}
