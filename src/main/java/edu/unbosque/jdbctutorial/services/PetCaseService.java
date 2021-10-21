package edu.unbosque.jdbctutorial.services;

import edu.unbosque.jdbctutorial.dtos.Official;
import edu.unbosque.jdbctutorial.dtos.PetCase;
import edu.unbosque.jdbctutorial.dtos.UserApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PetCaseService {

    private Connection conn;

    public PetCaseService(Connection conn) {
        this.conn = conn;
    }

    public void listUsers() {
        Statement stmt = null;

        // Data structure to map results from database
        List<PetCase> petCase = new ArrayList<PetCase>();

        try {



            System.out.println("=> Listing petcase...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM petcase";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                // Extracting row values by column name
                String case_id = rs.getString("case_id");
                String created_at = rs.getString("created_at");
                String type = rs.getString("type");
                String description = rs.getString("description");
                String pet_id = rs.getString("pet_id");

                // Creating a new UserApp class instance and adding it to the array list
                petCase.add(new PetCase( case_id, created_at,type, description, pet_id));
            }

            System.out.println("case_id | created_at | type | description| pet_id");
            for (PetCase petCase1 : petCase) {
                System.out.print(petCase1.getCase_id() + " | ");
                System.out.print(petCase1.getCreated_at() + " | ");
                System.out.print(petCase1.getType() + " | ");
                System.out.print(petCase1.getDescription() + " | ");
                System.out.println(petCase1.getPet_id());
            }

            rs.close();
            stmt.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if(stmt != null) stmt.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
