package edu.unbosque.jdbctutorial.services;

import edu.unbosque.jdbctutorial.dtos.Owner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OwnersService {

    // Objects for handling connection
    private Connection conn;

    public OwnersService(Connection conn) {
        this.conn = conn;
    }

        public void listUsers() {
            Statement stmt = null;

            // Data structure to map results from database
            List<Owner> owner = new ArrayList<Owner>();

            try {

                System.out.println("=> Listing Owner...");
                stmt = conn.createStatement();
                String sql = "SELECT * FROM owner";
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()) {
                    // Extracting row values by column name
                    String username  = rs.getString("username");
                    int person_id  = rs.getInt("person_id");
                    String name  = rs.getString("name");
                    String address  = rs.getString("address");
                    String neighborhood  = rs.getString("neighborhood");
                    // Creating a new UserApp class instance and adding it to the array list
                    owner.add(new Owner( username, person_id,name,address, neighborhood));
                }

                System.out.println("UserName | Name");
                for (Owner official : owner) {
                    System.out.print(official.getUsername() + " | ");
                    System.out.print(official.getPerson_id() + " | ");
                    System.out.print(official.getName() + " | ");
                    System.out.print(official.getAddress() + " | ");
                    System.out.println(official.getNeighborhood());
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
