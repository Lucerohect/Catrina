/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.itson.ui;



import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mx.edu.itson.ui.Complemento.AccountStatement;
import mx.edu.itson.ui.ui.Transaction;

/**
 *
 * @author USER
 */
public class Main {

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Catrina - Estado de Cuenta");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

          
            JTable table = new JTable();
            JScrollPane scrollPane = new JScrollPane(table);
            frame.getContentPane().add(scrollPane);

          
            AccountStatement accountStatement = loadAccountStatement("data/account_statement.json");

           

            frame.setVisible(true);
        });
    }

    private static AccountStatement loadAccountStatement(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        AccountStatement accountStatement = null;

        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));

   
            String account = rootNode.get("account").asText();
            String clabe = rootNode.get("clabe").asText();
            String currency = rootNode.get("currency").asText();

           
            JsonNode customerNode = rootNode.get("customer");
            String customerId = customerNode.get("id").asText();
            String customerName = customerNode.get("name").asText();
            String customerAddress = customerNode.get("address").asText();
            String customerCity = customerNode.get("city").asText();
            String customerRfc = customerNode.get("rfc").asText();
            String customerZipCode = customerNode.get("zip_code").asText();

           
            List<Transaction> transactions = new ArrayList<>();
            ArrayNode transactionsNode = (ArrayNode) rootNode.get("transactions");
            for (JsonNode transactionNode : transactionsNode) {
                String date = transactionNode.get("date").asText();
                String description = transactionNode.get("description").asText();
                double amount = transactionNode.get("amount").asDouble();
                int type = transactionNode.get("type").asInt();

                Transaction transaction = new Transaction(date, description, amount, type);
                transactions.add(transaction);
            }

           
            accountStatement = new AccountStatement(account, clabe, currency,
                    customerId, customerName, customerAddress, customerCity, customerRfc, customerZipCode,
                    transactions);

        } catch (IOException e) {
        }

        return accountStatement;
    }
}


