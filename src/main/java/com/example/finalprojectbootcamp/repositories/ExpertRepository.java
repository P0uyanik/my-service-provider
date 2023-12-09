package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Expert;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;



import java.util.List;
@Transactional
public interface ExpertRepository extends JpaRepository<Expert, Long> {

    @Modifying
    @Query(
            """
                    update Expert e set e.password =:password where e.email=:email
                    """
    )
    int updateExpertByPassword(String email, String password);

    @Query
            (
                    """
                            select e from  Expert  e where  e.email=:email and e.password=:password
                            """
            )

    Expert findExpertByEmailAndPassword (String email , String password) ;

    @Query
            (
                    """
                            select e from  Expert  e where  e.expertStatus = ?#{(T(com.example.finalprojectbootcamp.core.enums.ExpertStatus).NEW)} AND e.accessToTheSystem =false
                            """
            )
    List<Expert> findExpertByExpertStatus();

    @Modifying
    @Query(
            """
                    update Expert e set e.accessToTheSystem = true  , e.expertStatus = ?#{(T(com.example.finalprojectbootcamp.core.enums.ExpertStatus).APPROVED)} where e.email=:email
                    """
    )
    int updateExpertStatusByEmail(String email);

    Expert findExpertByEmail(String email) ;


}
