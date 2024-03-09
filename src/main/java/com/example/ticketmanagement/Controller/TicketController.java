package com.example.ticketmanagement.Controller;

import com.example.ticketmanagement.Entity.Ticket;
import com.example.ticketmanagement.Generic.RequestModel;
import com.example.ticketmanagement.Generic.ResultModel;
import com.example.ticketmanagement.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RequestMapping("/tickets")
@RestController
public class TicketController {
   @Autowired
    private TicketService ticketService;
    @GetMapping
    public ResponseEntity <ResultModel<Page<Ticket>>> getAllTicket(
            @RequestParam(required = false) Map<String, String> filters,
            @RequestParam (required = false)Integer id,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sorting", defaultValue = "id") String sorting,
            @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
            return new ResponseEntity<>(ticketService.getAllTicket(filters,id, page, size, sorting, sortDirection),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResultModel<List<Ticket>>> createTicket(@RequestBody RequestModel<List<Ticket>> request) {
            return new ResponseEntity<>(ticketService.createTicket(request),HttpStatus.BAD_GATEWAY);
    }
    @DeleteMapping
    public ResponseEntity<ResultModel<Ticket>> deleteTicket(@RequestBody List<Integer> ids) {
            return  new ResponseEntity<>(ticketService.deleteTicket(ids),HttpStatus.OK) ;
    }
    @PutMapping
    public ResponseEntity<ResultModel<Ticket>> updateTicket(@RequestParam Integer id, @RequestBody Ticket ticket) {
            return new ResponseEntity<>(ticketService.updateTicket(id, ticket),HttpStatus.OK);
    }
}



