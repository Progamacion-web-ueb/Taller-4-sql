package edu.unbosque.jdbctutorial.services;

import edu.unbosque.jdbctutorial.dtos.Official;
import edu.unbosque.jdbctutorial.dtos.UserApp;
import edu.unbosque.jdbctutorial.dtos.Visit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VisitService {

    private Connection conn;

    public VisitService(Connection conn) {
        this.conn = conn;
    }

    public void listUsers() {
        Statement stmt = null;

        // Data structure to map results from database
        List<Visit> visit = new ArrayList<Visit>();

        try {

            System.out.println("=> Listing visit...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM visit";
            ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()) {
                    // Extracting row values by column name
                    String visit_id  = rs.getString("visit_id");
                    String created_at  = rs.getString("created_at");
                    String type = rs.getString("type");
                    String description = rs.getString("description");
                    String vet_id = rs.getString("vet_id");
                    String pet_id = rs.getString("pet_id");

                    // Creating a new UserApp class instance and adding it to the array list
                    visit.add(new Visit( visit_id,created_at,type,description,vet_id,pet_id));
                }

            System.out.println("visit_id | created_at | type | description | vet_id | pet_id");
            for (Visit visit1 : visit) {
                System.out.print(visit1.getVisit_id() + " | ");
                System.out.print(visit1.getCreated_at() + " | ");
                System.out.print(visit1.getType() + " | ");
                System.out.print(visit1.getDescription() + " | ");
                System.out.print(visit1.getVet_id() + " | ");
                System.out.println(visit1.getPet_id());
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
