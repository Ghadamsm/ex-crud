package com.example.bankmanagement.Controller;


import com.example.bankmanagement.API.ApiResponse;
import com.example.bankmanagement.Model.Customers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    ArrayList<Customers> customers = new ArrayList<>();


    @GetMapping("/get")
    public ArrayList<Customers> getCustomer(){
        return customers;
    }



    @PostMapping("/add")
    public ApiResponse addCustomer (@RequestBody Customers customer){

        customers.add(customer);

        return new ApiResponse("added");
    }


    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index , @RequestBody Customers customer){

        customers.set(index , customer);

        return new ApiResponse("UpDated");
    }


    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer (@PathVariable int index){

        customers.remove(index);

        return new ApiResponse("deleted");
    }



    @PutMapping("/deposit/{index}/{number}")
    public ApiResponse deposit(@PathVariable int number ,@PathVariable int index ){

        int i = customers.get(index).getBalance() + number;

        return new ApiResponse("Done!") ;

    }


    @PutMapping("/withdraw/{index}/{number}")
    public ApiResponse withdraw ( @PathVariable int index ,@PathVariable int number){


           for (Customers customer : customers){
                  if (customer.getBalance() >= number){
                  customer.setBalance(customer.getBalance() - number);
                  return new ApiResponse("Done!");
              }
           }

        return new ApiResponse("Invalid!") ;
    }


}
