/*
 * RouteRepository.java -- Defines RouteRepository class 
 * This code is implemented as part of assignment given to group #3 for  
 * course Scalable Service of MTECH Program Software Engineering
 * Assignment Group #3
 * Student Name : Yogesh Kshatriya
 * Student Id   : 2022MT93005
 * Course       : Scalable Services
 * Program      : MTECH Software Engineering
 * Student Email: 2022MT93005@wilp.bits-pilani.ac.in
 */

package scalable.assignment.apigateway;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The <code>RouteRepository</code> is data access object that manages
 * persistence of RouteEntity 
 * It provides functionality to create and retrieve, update and delete
 * RouteEntity from database.
 * @author Yogesh Kshatriya
 */
@Repository
public interface RouteRepository extends JpaRepository<RouteEntity, Long> {
    
    /**
     * Retrieve RouteEntiy by name 
     * @param name           : name of RouteEntity to be retrieved
     * @return               : Optional<RouteEntity>
     */
    public Optional<RouteEntity> findByName(String name);
}
