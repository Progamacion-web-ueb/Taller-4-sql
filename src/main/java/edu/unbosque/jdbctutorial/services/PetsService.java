package edu.unbosque.jdbctutorial.services;

import edu.unbosque.jdbctutorial.dtos.Pet;
import edu.unbosque.jdbctutorial.dtos.PetCase;
import edu.unbosque.jdbctutorial.dtos.UserApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PetsService {

    // Objects for handling connection
    private Connection conn;

    public PetsService(Connection conn) {
        this.conn = conn;
    }
    public void listUsers() {
        Statement stmt = null;

        // Data structure to map results from database
        List<Pet> pet = new ArrayList<Pet>();

        try {
            System.out.println("=> Listing pets...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM pet";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                // Extracting row values by column name
                String pet_id = rs.getString("pet_id");
                int microchip = rs.getInt("microchip");
                String name = rs.getString("name");
                String species = rs.getString("species");
                String race = rs.getString("race");
                String size = rs.getString("size");
                String sex = rs.getString("sex");
                String picture = rs.getString("picture");
                String ownerId = rs.getString("owner_id");


                // Creating a new UserApp class instance and adding it to the array list
                pet.add(new Pet( pet_id, microchip,  name,  species,race,  size,  sex,  picture,  ownerId));
            }

            System.out.println("case_id | created_at | type | description| pet_id");
            for (Pet pet1 : pet) {
                System.out.print(pet1.getPet_id() + " | ");
                System.out.print(pet1.getMicrochip() + " | ");
                System.out.print(pet1.getName() + " | ");
                System.out.print(pet1.getSpecies() + " | ");
                System.out.print(pet1.getPet_id());
                System.out.print(pet1.getRace() + " | ");
                System.out.print(pet1.getSize() + " | ");
                System.out.print(pet1.getPicture() + " | ");
                System.out.println(pet1.getOwnerId() + " | ");
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

    public void countBySpecies(String species) {

        // Objects for handling SQL statement
        Statement stmt = null;

        try {

            // Executing a SQL query
            System.out.println("=> Counting pets by species...");
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) AS count FROM Pet WHERE species = '" + species + "'";
            ResultSet rs = stmt.executeQuery(sql);

            // Pointing to fist row
            rs.next();

            // Printing results
            System.out.println("Total of pets by species: " + rs.getInt("count") + "\n");

            // Closing resources
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

    public void listPetById(String id) {
        Statement stmt = null;

        // Data structure to map results from database
        List<Pet> pet = new ArrayList<Pet>();

        try {
            System.out.println("=> Listing pet by id...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM pet WHERE pet_id='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                // Extracting row values by column name
                String pet_id = rs.getString("pet_id");
                int microchip = rs.getInt("microchip");
                String name = rs.getString("name");
                String species = rs.getString("species");
                String race = rs.getString("race");
                String size = rs.getString("size");
                String sex = rs.getString("sex");
                String picture = rs.getString("picture");
                String ownerId = rs.getString("owner_id");


                // Creating a new UserApp class instance and adding it to the array list
                pet.add(new Pet( pet_id, microchip,  name,  species,race,  size,  sex,  picture,  ownerId));
            }

            System.out.println("pet_id | microchip | name | species| race| size| sex| picture| ownerId");
            for (Pet pet1 : pet) {
                System.out.print(pet1.getPet_id() + " | ");
                System.out.print(pet1.getMicrochip() + " | ");
                System.out.print(pet1.getName() + " | ");
                System.out.print(pet1.getSpecies() + " | ");
                System.out.print(pet1.getPet_id());
                System.out.print(pet1.getRace() + " | ");
                System.out.print(pet1.getSize() + " | ");
                System.out.print(pet1.getPicture() + " | ");
                System.out.println(pet1.getOwnerId() + " | ");
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
