import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ReservationController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> equipmentComboBox;

    @FXML
    private TextField participantCountField;

    @FXML
    private Button submitButton;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        // Initialize equipment options
        equipmentComboBox.getItems().addAll("Projector", "Screen", "Whiteboard");

        submitButton.setOnAction(event -> createReservation());
    }

    private void createReservation() {
        String date = datePicker.getValue().toString();
        String equipment = equipmentComboBox.getValue();
        String participantCount = participantCountField.getText();

        // Process reservation logic here
        // For example: save reservation to the database

        messageLabel.setText("Reservation created for " + participantCount + " participants on " + date + " with equipment: " + equipment);
    }
}