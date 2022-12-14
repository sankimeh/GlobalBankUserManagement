package com.example.demo.Controller;

import java.util.List;

import com.example.demo.Model.*;
import com.example.demo.ResponseBody.DateRange;
import com.example.demo.ResponseBody.Validate;
import com.example.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//Swagger: http://localhost:8080/swagger-ui/index.html#/
@RestController // generate & manage REST API in json format
@RequestMapping(value="/api")
@CrossOrigin(origins="http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerService cservice;

    /* http://localhost:8082/globalbank/api/customer */

    /* Request -- Client(React/PostMan) --> Controller ---> Service ---> Repository --> DB*/

    /* Response -- DB --> Repository --->  Service --> Controller --> Client(React/PostMan) */

    /*POST - http://localhost:8082/globalbank/api/customer */
    @PostMapping("/customer")
    public ResponseEntity<?> registerCustomer(@Validated @RequestBody Customer cust) {

        Customer c1=cservice.registerCustomer(cust); // invoke service method
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    /*GET - http://localhost:8082/globalbank/api/customers */

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        System.out.println("Customers is being called");
        return cservice.getAllCustomers();
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateCustomer(@RequestBody Validate validate){
        System.out.println("Validate is being called.....");
        if(cservice.validate(validate.getUsername(), validate.getPassword())){
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/customer/id/{id}")
    public ResponseEntity<?> getCustomerId(@PathVariable Long id){
        return new ResponseEntity<>(cservice.getById(id), HttpStatus.ACCEPTED);
    }

    @PostMapping("/loan/{id}")
    public ResponseEntity<?> applyForLoan(@RequestBody Loan loan, @PathVariable Long id){
        System.out.println("Loan is called");
        cservice.applyForLoan(id, loan);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/transaction/{id}")
    public ResponseEntity<?> transaction(@RequestBody Transaction transaction, @PathVariable Long id){
        System.out.println("Transaction is being called");
        cservice.withdrawOrDeposit(id, transaction);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/topdf/{id}")
    public ResponseEntity<?> topdf(@RequestBody DateRange dateRange, @PathVariable Long id) {
        return new ResponseEntity<>(cservice.dateFilter(id, dateRange.getStartDate(), dateRange.getEndDate()), HttpStatus.ACCEPTED);
    }

    @GetMapping("/lastid")
    public ResponseEntity<?> getLastId(){
        return new ResponseEntity<>(cservice.getLastId(), HttpStatus.ACCEPTED);
    }
}

