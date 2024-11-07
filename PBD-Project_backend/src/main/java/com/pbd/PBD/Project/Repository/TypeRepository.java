package com.pbd.PBD.Project.Repository;

import com.pbd.PBD.Project.Entity.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Tip, Integer> {
}
