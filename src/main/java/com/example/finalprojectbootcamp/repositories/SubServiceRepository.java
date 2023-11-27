package com.example.finalprojectbootcamp.repositories;

import com.example.finalprojectbootcamp.core.entities.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubServiceRepository extends JpaRepository<SubService, Long> {
    @Modifying
    @Query
            (
                    """
                            update SubService s set s.price=:price , s.description=:description where s.id=:id
                            """
            )
    int updateSubServiceByPriceAndDescription(long id, String price, String description);

    @Modifying
    @Query
            (
                    """
                            update SubService s set s.price=:price where s.id=:id
                            """
            )
    int updateSubServiceByPrice(long id, String price);

    @Modifying
    @Query
            (
                    """
                            update SubService s set  s.description=:description where s.id=:id
                            """
            )
    int updateSubServiceByDescription(long id, String description);
}
