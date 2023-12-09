package com.example.finalprojectbootcamp.repositories;
import com.example.finalprojectbootcamp.core.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service findServicesById(long id);

    @Query
            (
                    """
                            select s from Service s where s.name=:name
                            """
            )
    Service findServicesByName(@Param("name") String name) ;
}
