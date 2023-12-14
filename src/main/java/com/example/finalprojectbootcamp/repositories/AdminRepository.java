package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query(
            """
                    select a from Admin a where a.email=:email and a.password=:password
                    """
    )
    Admin findAdminByEmailAndAndPassword(String email, String password);


}
