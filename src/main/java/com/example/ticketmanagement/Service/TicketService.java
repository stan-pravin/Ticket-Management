package com.example.ticketmanagement.Service;

import com.example.ticketmanagement.Entity.Ticket;
import com.example.ticketmanagement.Generic.RequestModel;
import com.example.ticketmanagement.Generic.ResultModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
public interface TicketService {
    ResultModel<Page<Ticket>> getAllTicket(Map<String, String> filters,
                                           Integer id,
                                           int page,
                                           int size,
                                           String sorting,
                                           String sortDirection);
    ResultModel<List<Ticket>> createTicket(RequestModel<List<Ticket>> request);
    ResultModel<Ticket> deleteTicket(List<Integer> ids);
    ResultModel<Ticket> updateTicket(Integer id, Ticket ticket);
}
