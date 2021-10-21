package edu.unbosque.jdbctutorial.services;

import edu.unbosque.jdbctutorial.dtos.Official;
import edu.unbosque.jdbctutorial.dtos.UserApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OfficialService {

    private Connection conn;

    public OfficialService(Connection conn) {
        this.conn = conn;
    }
    public void listUsers() {
        Statement stmt = null;

        // Data structure to map results from database
        List<Official> officcial = new ArrayList<Official>();

        try {

            System.out.println("=> Listing official...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM official";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                // Extracting row values by column name
                String username  = rs.getString("username");
                String name  = rs.getString("name");
                // Creating a new UserApp class instance and adding it to the array list
                officcial.add(new Official(username,name));
            }

            System.out.println("UserName | Name");
            for (Official official : officcial) {
                System.out.print(official.getUsername() + " | ");
                System.out.println(official.getName());
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
