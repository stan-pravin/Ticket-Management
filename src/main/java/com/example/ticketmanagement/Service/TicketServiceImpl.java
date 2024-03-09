package com.example.ticketmanagement.Service;

import com.example.ticketmanagement.Entity.Ticket;
import com.example.ticketmanagement.Exception.TicketNotFoundException;
import com.example.ticketmanagement.Generic.RequestModel;
import com.example.ticketmanagement.Generic.ResultModel;
import com.example.ticketmanagement.Repository.TicketRepository;
import com.example.ticketmanagement.Service.dbSequence.SequenceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketServiceImpl implements TicketService {
    private final Logger logger = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketRepository repo;
    @Autowired
    private SequenceImpl sequence;

    @Override
    public ResultModel<Page<Ticket>> getAllTicket(Map<String, String> filters,
                                                  Integer id,
                                                  int page,
                                                  int size,
                                                  String sorting,
                                                  String sortDirection) {
        logger.info("Get Method Accessed");
        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sorting);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Ticket> ticket;
        ResultModel<Page<Ticket>> result = new ResultModel<>();
        if (id != null) {
            Optional<Ticket> optionalTicket = repo.findById(id);
            if (optionalTicket.isPresent()) {
                ticket = new PageImpl<>(Collections.singletonList(optionalTicket.get()), pageable, 1);
                result.setMessage("Ticket id " + id + " retrieved successfully");
                result.setSuccess(true);
                result.setData(ticket);
            } else {
                throw new TicketNotFoundException("Ticket id "+id +" is Not Found");
            }
        } else if (filters != null && !filters.isEmpty()) {
            Map.Entry<String, String> entry = filters.entrySet().iterator().next();
            ticket = (!isPageKey(entry.getKey())) ?
                    repo.findByFilter(entry.getKey(), entry.getValue(), pageable) :
                    repo.findAll(pageable);
            result.setMessage("Tickets Filtered successfully");
            result.setSuccess(true);
            result.setData(ticket);
            logger.info("Ticket Filtered Successfully");
        } else {
            ticket = repo.findAll(pageable);
            result.setMessage("Tickets retrieved successfully");
            result.setSuccess(true);
            result.setData(ticket);
            logger.info("Ticket Retrieval Successfully");
        }
        return result;
    }

    private boolean isPageKey(String key) {
        return key.equals("page") || key.equals("size") || key.equals("sorting") || key.equals("sortDirection");
    }

    @Override
    public ResultModel<List<Ticket>> createTicket(RequestModel<List<Ticket>> request) {
        logger.info("Post Method Accessed");
        ResultModel<List<Ticket>> result = new ResultModel<>();
        List<Ticket> newTicket = request.getData();
        newTicket.forEach(ticket -> ticket.setId(sequence.getSequenceNumber(Ticket.SEQUENCE_NAME)));
        try {
            List<Ticket> saveAllTickets = repo.saveAll(newTicket);
            result.setMessage("Ticket created successfully");
            result.setSuccess(true);
            result.setData(saveAllTickets);
            logger.info("Ticket Created Successfully");
            }
        catch (Exception e) {throw new TicketNotFoundException("Not able to Create a Ticket");
        }
        return result;
    }
    @Override

    public ResultModel<Ticket> deleteTicket(List<Integer> ids) {
        logger.info("Delete Method Accessed");
        ResultModel<Ticket> result = new ResultModel<>();
        try {
            for (Integer id : ids) {
                if (repo.existsById(id)) {
                repo.deleteById(id);
                result.setSuccess(true);
                result.setMessage("id is Deleted");
                logger.info("Id Deleted Successfully");
                }
                else{
                    result.setSuccess(false);
                    result.setMessage("id " + id+" not found");
                }
            }
        }catch (Exception e){throw new TicketNotFoundException(" Ticket id " + ids + " not found");}
        return result;
    }
    @Override
    public ResultModel<Ticket> updateTicket(Integer id, Ticket ticket) {
        logger.info("Put Method Accessed");
        Ticket existingTicket = repo.findById(id).orElse(null);
        ResultModel<Ticket> result = new ResultModel<>();
        try {
            if (existingTicket != null) {
                BeanUtils.copyProperties(ticket, existingTicket, "id");
                result.setSuccess(true);
                result.setMessage("Ticket is Updated");
                result.setData(repo.save(existingTicket));
                logger.info("Ticket is Updated Successfully");
            }else{throw new TicketNotFoundException("Ticket id " + id + " Not Found");}
        }catch (Exception e){throw new TicketNotFoundException("Ticket id " + id + " Not Found");}
        return result;
     }
}
