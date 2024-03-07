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



    @PutMapping("/deposit/{number}")
    public ArrayList<Customers> deposit(@PathVariable int number){
        ArrayList<Customers> deposit = new ArrayList<>();
        for (Customers customer : customers) {
            customer.setBalance(number + customer.getBalance());
                deposit.add(customer);
        }
        return deposit ;
    }


    @PutMapping("/withdraw/{number}")
    public ApiResponse withdraw (@PathVariable int number){
        ArrayList<Customers> withdraw = new ArrayList<>();

        for (Customers customer : customers){
            if (customer.getBalance() >= number){
            customer.setBalance(customer.getBalance() - number);
            withdraw.add(customer);
            }else if (customer.getBalance() < number){
                return new ApiResponse("Invalid");
            }
        }
        return new ApiResponse("Done!") ;
    }


}
