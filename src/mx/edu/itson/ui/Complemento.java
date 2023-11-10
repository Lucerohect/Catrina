/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.ui;
import java.util.List;
/**
 *
 * @author USER
 */
public class Complemento {
  


public class AccountStatement {
    private String account;
    private String clabe;
    private String currency;
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerCity;
    private String customerRfc;
    private String customerZipCode;
    private List<Transaction> transactions;

    public AccountStatement(String account, String clabe, String currency,
                            String customerId, String customerName, String customerAddress,
                            String customerCity, String customerRfc, String customerZipCode,
                            List<Transaction> transactions) {
        this.account = account;
        this.clabe = clabe;
        this.currency = currency;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerRfc = customerRfc;
        this.customerZipCode = customerZipCode;
        this.transactions = transactions;
    }

  
}

}
