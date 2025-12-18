import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.ZoneId;
import java.util.Date;

public class ClinicGUI extends Application {

    private BorderPane rootLayout;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dentist Clinic Management System");

        rootLayout = new BorderPane();

        // 1. Sidebar
        VBox sidebar = createSidebar();
        rootLayout.setLeft(sidebar);

        // 2. Default Center
        rootLayout.setCenter(new PatientPane());

        // 3. Scene
        Scene scene = new Scene(rootLayout, 1100, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // --- Sidebar Design ---
    private VBox createSidebar() {
        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #2c3e50;"); // Professional Dark Blue
        sidebar.setPrefWidth(220);

        Label title = new Label("DENTAL SYS");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));
        
        Label subtitle = new Label("Logged in as: " + DataManager.receptionist.getName());
        subtitle.setTextFill(Color.LIGHTGRAY);
        subtitle.setFont(Font.font("Segoe UI", 12));

        Button btnPatients = createNavButton("Manage Patients");
        Button btnDoctors = createNavButton("Manage Doctors");
        Button btnBook = createNavButton("Book Appointment");

        // Navigation Logic
        btnPatients.setOnAction(e -> rootLayout.setCenter(new PatientPane()));
        btnDoctors.setOnAction(e -> rootLayout.setCenter(new DoctorPane()));
        btnBook.setOnAction(e -> rootLayout.setCenter(new AppointmentPane()));

        sidebar.getChildren().addAll(title, subtitle, new Separator(), btnPatients, btnDoctors, btnBook);
        return sidebar;
    }

    private Button createNavButton(String text) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(45);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT; -fx-cursor: hand;");
        
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px; -fx-alignment: CENTER_LEFT;"));
        
        return btn;
    }

    // --- Helper: Convert Local Date to Util Date ---
    public static Date toDate(java.time.LocalDate localDate) {
        if(localDate == null) return null;
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    // ========================================================
    // INTERNAL CLASS: PATIENT PANE
    // ========================================================
    class PatientPane extends VBox {
        public PatientPane() {
            setSpacing(20);
            setPadding(new Insets(20));

            Label header = new Label("Patient Records");
            header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));

            // Form
            HBox form = new HBox(10);
            TextField txtName = new TextField(); txtName.setPromptText("Full Name");
            TextField txtPhone = new TextField(); txtPhone.setPromptText("Phone");
            ComboBox<String> cmbGender = new ComboBox<>(); cmbGender.getItems().addAll("Male", "Female"); cmbGender.setPromptText("Gender");
            CheckBox chkIns = new CheckBox("Insured");
            Button btnAdd = new Button("Add Patient");
            btnAdd.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");

            form.getChildren().addAll(txtName, txtPhone, cmbGender, chkIns, btnAdd);

            // Table
            TableView<Patient> table = new TableView<>();
            TableColumn<Patient, Integer> colId = new TableColumn<>("ID");
            colId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
            
            TableColumn<Patient, String> colName = new TableColumn<>("Name");
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            
            TableColumn<Patient, String> colPhone = new TableColumn<>("Contact");
            colPhone.setCellValueFactory(new PropertyValueFactory<>("number")); // Uses getNumber() from Person
            
            TableColumn<Patient, Boolean> colIns = new TableColumn<>("Insurance");
            colIns.setCellValueFactory(new PropertyValueFactory<>("insuranceStatus"));

            table.getColumns().addAll(colId, colName, colPhone, colIns);
            table.setItems(DataManager.patients);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            // Add Logic
            btnAdd.setOnAction(e -> {
                if (txtName.getText().isEmpty()) return;
                Patient p = new Patient(txtName.getText(), new Date(), cmbGender.getValue(), txtPhone.getText(), null, chkIns.isSelected());
                DataManager.patients.add(p);
                txtName.clear(); txtPhone.clear();
            });

            getChildren().addAll(header, form, table);
        }
    }

    // ========================================================
    // INTERNAL CLASS: DOCTOR PANE
    // ========================================================
    class DoctorPane extends VBox {
        public DoctorPane() {
            setSpacing(20);
            setPadding(new Insets(20));


            Label header = new Label("Doctor Management");
            header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));

            // Form
            HBox form = new HBox(10);
            TextField txtName = new TextField(); txtName.setPromptText("Dr Name");
            TextField txtSpec = new TextField(); txtSpec.setPromptText("Specialization");
            Button btnAdd = new Button("Hire Doctor");
            btnAdd.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;");

            form.getChildren().addAll(txtName, txtSpec, btnAdd);

            // Table
            TableView<Doctor> table = new TableView<>();
            TableColumn<Doctor, String> colName = new TableColumn<>("Name");
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            
            TableColumn<Doctor, String> colSpec = new TableColumn<>("Specialization");
            colSpec.setCellValueFactory(new PropertyValueFactory<>("specialization"));

            table.getColumns().addAll(colName, colSpec);
            table.setItems(DataManager.doctors);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            // Add Logic
            btnAdd.setOnAction(e -> {
                if (txtName.getText().isEmpty()) return;
                Doctor d = new Doctor(txtName.getText(), "N/A", new Date(), 0, 5000, new Date(), "N/A", txtSpec.getText());
                DataManager.doctors.add(d);
                txtName.clear(); txtSpec.clear();
            });

            getChildren().addAll(header, form, table);
        }
    }

    // ========================================================
    // INTERNAL CLASS: APPOINTMENT PANE
    // ========================================================
    class AppointmentPane extends VBox {
        public AppointmentPane() {
            setSpacing(20);
            setPadding(new Insets(20));

            Label header = new Label("Book Appointment");
            header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));

            // Input Grid
            GridPane grid = new GridPane();
            grid.setHgap(15); grid.setVgap(15);

            ComboBox<Patient> cmbPatient = new ComboBox<>(DataManager.patients);
            cmbPatient.setPromptText("Select Patient");
            cmbPatient.setPrefWidth(200);
            // Converter to show Name instead of Object ID
            cmbPatient.setConverter(new StringConverter<Patient>() {
                @Override public String toString(Patient p) { return p == null ? "" : p.getName(); }
                @Override public Patient fromString(String string) { return null; }
            });

            ComboBox<Doctor> cmbDoctor = new ComboBox<>(DataManager.doctors);
            cmbDoctor.setPromptText("Select Doctor");
            cmbDoctor.setPrefWidth(200);
            cmbDoctor.setConverter(new StringConverter<Doctor>() {
                @Override public String toString(Doctor d) { return d == null ? "" : d.getName() + " (" + d.getSpecialization() + ")"; }
                @Override public Doctor fromString(String string) { return null; }
            });

            DatePicker datePicker = new DatePicker();
            
            Button btnBook = new Button("Confirm Booking");
            btnBook.setStyle("-fx-background-color: #e67e22; -fx-text-fill: white; -fx-font-weight: bold;");

            grid.add(new Label("Patient:"), 0, 0); grid.add(cmbPatient, 1, 0);
            grid.add(new Label("Doctor:"), 0, 1); grid.add(cmbDoctor, 1, 1);
            grid.add(new Label("Date:"), 0, 2); grid.add(datePicker, 1, 2);
            grid.add(btnBook, 1, 3);

            // Log / Status Area
            TextArea logArea = new TextArea();
            logArea.setEditable(false);
            logArea.setPrefHeight(100);

            // Table of All Appointments
            TableView<Appointment> table = new TableView<>();
            TableColumn<Appointment, String> colDate = new TableColumn<>("Date");
            colDate.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
            
            TableColumn<Appointment, String> colStatus = new TableColumn<>("Status");
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            table.getColumns().addAll(colDate, colStatus);
            table.setItems(DataManager.appointments);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            // Logic
            btnBook.setOnAction(e -> {
                if (cmbPatient.getValue() == null || cmbDoctor.getValue() == null || datePicker.getValue() == null) {
                    logArea.appendText("Error: Please fill all fields\n");
                    return;
                }

                Date date = toDate(datePicker.getValue());
                
                // Use the Receptionist Logic provided in your code
                Appointment app = DataManager.receptionist.bookAppointment(
                    cmbDoctor.getValue(), 
                    cmbPatient.getValue(), 
                    date
                );

                if (app != null) {
                    logArea.appendText("Success: Booked for " + app.getPatient().getName() + " with " + DataManager.receptionist.getName() + "\n");
                    DataManager.appointments.add(app);
                    app.sendNotification(); // Trigger console notification
                } else {
                    logArea.appendText("Failed: Doctor is not available on this date.\n");
                }
            });

            getChildren().addAll(header, grid, new Label("Recent Activity:"), logArea, new Label("All Appointments:"), table);
        }
    }
}
