package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ExpertRepository extends JpaRepository<Expert, Long> {
    @Modifying
    @Query(
            """
                    update Expert e set e.password =:password where e.id=:id
                    """
    )
    int updateExpertByPassword(long id, String password);
    Expert findExpertById(long id) ;
}
