package com.conference.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;

public class DashboardController {

    @FXML
    private ListView<String> availableRoomsList;

    @FXML
    private ListView<String> userReservationsList;

    @FXML
    private Button createReservationButton;

    @FXML
    public void initialize() {
        // Initialize the lists with available rooms and user reservations
        loadAvailableRooms();
        loadUserReservations();
    }

    private void loadAvailableRooms() {
        // TODO: Load available rooms from the database
        availableRoomsList.getItems().addAll("Room A", "Room B", "Room C"); // Example data
    }

    private void loadUserReservations() {
        // TODO: Load user reservations from the database
        userReservationsList.getItems().addAll("Reservation 1", "Reservation 2"); // Example data
    }

    @FXML
    private void createReservation() {
        // TODO: Handle the creation of a new reservation
        System.out.println("Create new reservation clicked!");
    }
}
