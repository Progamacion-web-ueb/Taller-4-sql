package edu.unbosque.jdbctutorial.services;

import edu.unbosque.jdbctutorial.dtos.Official;
import edu.unbosque.jdbctutorial.dtos.UserApp;
import edu.unbosque.jdbctutorial.dtos.Vet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VetService {

    private Connection conn;

    public VetService(Connection conn) {
        this.conn = conn;
    }

    public void listUsers() {
        Statement stmt = null;

        // Data structure to map results from database
        List<Vet> vet = new ArrayList<Vet>();

        try {

            System.out.println("=> Listing vets...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM vet";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {

                // Extracting row values by column name
                String username  = rs.getString("username");
                String name  = rs.getString("name");
                String address = rs.getString("address");
                String neighborhood = rs.getString("neighborhood");

                // Creating a new UserApp class instance and adding it to the array list
                vet.add(new Vet( username,  name,  address,  neighborhood));
            }

            System.out.println("UserName | name | date | neighborhood");
            for (Vet vet1 : vet) {
                System.out.print(vet1.getUsername() + " | ");
                System.out.print(vet1.getName() + " | ");
                System.out.print(vet1.getAddress() + " | ");
                System.out.println(vet1.getNeighborhood());
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
