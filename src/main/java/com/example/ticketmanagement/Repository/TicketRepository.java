package com.example.ticketmanagement.Repository;

import com.example.ticketmanagement.Entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends MongoRepository<Ticket,Integer> {
    @Query("{ ?0: ?1}")
    Page<Ticket> findByFilter(String filterKey, String filterValue, Pageable pageable);
    //void deleteByIdIn(List<Integer> ids);
    //void findAndModify(Integer id,String str );
}
