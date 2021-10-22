package edu.unbosque.jdbctutorial;

import java.util.*;
import java.sql.*;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import edu.unbosque.jdbctutorial.dtos.Owner;
import edu.unbosque.jdbctutorial.dtos.Pet;
import edu.unbosque.jdbctutorial.dtos.UserApp;
import edu.unbosque.jdbctutorial.services.OwnersService;
import edu.unbosque.jdbctutorial.services.PetsService;
import edu.unbosque.jdbctutorial.services.UsersService;
import edu.unbosque.jdbctutorial.services.OfficialService;
import edu.unbosque.jdbctutorial.services.PetCaseService;
import edu.unbosque.jdbctutorial.services.VetService;
import edu.unbosque.jdbctutorial.services.VisitService;


public class Main {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/fourpaws";

    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "Esteban11082";

    public static void main(String[] args) {
        run();
    }

    public static void run(){
        // Objects for handling connection
        Connection conn = null;

        try {
            int caso=0;
            System.out.println("Opcion 1: Mostrar la infomacion de todas las tablas");
            System.out.println("Opcion 2:Consultar la lista de usuarios registrados para un rol dado");
            System.out.println("Opcion 3:Contar la lista de veterinarias registradas en la plataforma");
            System.out.println("Opcion 4:Consultar las visitas que se han registrado para un ID de mascota dado");
            System.out.println("Opcion 5:Registrar un nuevo caso de robo de una mascota dado su ID");
            System.out.println("Opcion 6: Para finalizar la ejecucion");

            Scanner myObj = new Scanner(System.in);
            try{
                caso=Integer.parseInt(myObj.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Por favo ingrese unicamente los numero, de las opciones dadas");
            }

            // Registering the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Opening database connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            if(caso!=0){
                switch (caso) {
                    case 1:
                        OfficialService officialService = new OfficialService(conn);
                        officialService.listUsers();

                        OwnersService ownersService = new OwnersService(conn);
                        ownersService.listUsers();

                        PetCaseService petCaseService = new PetCaseService(conn);
                        petCaseService.listUsers();

                        PetsService petsService = new PetsService(conn);
                        petsService.listUsers();

                        UsersService usersService = new UsersService(conn);
                        usersService.listUsers();

                        VetService vetService = new VetService(conn);
                        vetService.listUsers();

                        VisitService visitService = new VisitService(conn);
                        visitService.listUsers();

                        PetsService petService = new PetsService(conn);
                        petService.countBySpecies("dog");
                        run();
                        break;
                    case 2:
                        System.out.println("Opcion 1: Rol propietario");
                        System.out.println("Opcion 2: Rol funcionario");
                        System.out.println("Opcion 3: Rol veterinaria");

                        Scanner myObj1 = new Scanner(System.in);
                        int option=0;
                        try{
                            option=Integer.parseInt(myObj1.nextLine());
                        }catch (NumberFormatException e){
                            System.out.println("Por favo ingrese unicamente los numero, de las opciones dadas");
                        }
                        if(option==1){
                            UsersService userService = new UsersService(conn);
                            userService.listUsersRol("owner");
                        }else if(option==2){
                            UsersService userService = new UsersService(conn);
                            userService.listUsersRol("official");
                        }else if(option==3){
                            UsersService userService = new UsersService(conn);
                            userService.listUsersRol("vet");
                        }
                        run();
                        break;
                    case 3:
                        VetService vetService1 = new VetService(conn);
                        vetService1.countVet();
                        run();
                        break;
                    case 4:
                        System.out.println("Ingrese el Id de la mascota, a buscar");
                        Scanner id = new Scanner(System.in);
                        String id1= new String(id.nextLine());
                        PetsService petsService1 = new PetsService(conn);
                        petsService1.listPetById(id1);
                        run();
                        break;
                    case 5:

                        System.out.println("Ingrese el id del caso");
                        Scanner case_id = new Scanner(System.in);
                        String case_id1= new String(case_id.nextLine());
                        System.out.println("Ingrese la fecha de creacion del caso");
                        Scanner created_at = new Scanner(System.in);
                        String created_at1= new String(created_at.nextLine());
                        System.out.println("Ingrese tipo de caso (pérdida, robo o fallecimiento)");
                        Scanner type = new Scanner(System.in);
                        String type1= new String(type.nextLine());
                        System.out.println("Ingrese una breve descripcion");
                        Scanner description = new Scanner(System.in);
                        String description1= new String(description.nextLine());
                        System.out.println("Ingrese el id de la mascota");
                        Scanner id_pet = new Scanner(System.in);
                        String id_pet1= new String(id_pet.nextLine());
                        PetCaseService petCaseService1 = new PetCaseService(conn);
                        petCaseService1.addCase(case_id1,created_at1,type1,description1,id_pet1);
                        run();
                        break;
                    case 6:
                        break;
                }
            }

            //OwnersService ownersService = new OwnersService(conn);
            //ownersService.updateOwner(new Owner(6698, null, "Pepito Perez"));

            // Closing database connection
            conn.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } catch(ClassNotFoundException e) {
            e.printStackTrace(); // Handling errors from JDBC driver
        } finally {
            // Cleaning-up environment
            try {
                if(conn != null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }

    }
}
