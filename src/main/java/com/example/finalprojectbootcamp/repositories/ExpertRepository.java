package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpertRepository extends JpaRepository<Expert, Long> {
    @Modifying
    @Query(
            """
                    update Expert e set e.password =:password where e.id=:id
                    """
    )
    int updateExpertByPassword(long id, String password);

    Expert findExpertById(long id);

    @Query
            (
                    """
                            select Expert from  Expert  e where  e.expertStatus = ?#{(T(com.example.finalprojectbootcamp.core.enums.ExpertStatus).NEW)} AND e.accessToTheSystem =false
                            """
            )
    List<Expert> findExpertByExpertStatus();

    @Modifying
    @Query(
            """
                    update Expert e set e.accessToTheSystem = true  , e.expertStatus = ?#{(T(com.example.finalprojectbootcamp.core.enums.ExpertStatus).APPROVED)} where e.id=:id
                    """
    )
    int updateExpertById(long id);
}
