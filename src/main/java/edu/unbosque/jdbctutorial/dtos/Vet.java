package edu.unbosque.jdbctutorial.dtos;

public class Vet {

    private String username;
    private String name;
    private String date;
    private String neighborhood;

    public Vet(String username, String name, String date, String neighborhood) {
        this.username = username;
        this.name = name;
        this.date = date;
        this.neighborhood = neighborhood;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
