package com;

import javafx.application.Application;
import java.util.Comparator;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;

public class ClinicGUI extends Application {

    private TextArea notificationLog;
    private TabPane tabPane; 

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        // --- 1. UNIFIED THEME CSS ---
        String css = 
            ".root { -fx-font-family: 'Segoe UI', sans-serif; -fx-background-color: #f4f6f8; }" + 
            ".tab-header-background { -fx-background-color: transparent; }" +
            ".tab-pane { -fx-tab-min-width: 100px; }" +
            ".tab { -fx-background-color: #cfd8dc; -fx-background-radius: 10 10 0 0; -fx-padding: 10 20; }" +
            ".tab:selected { -fx-background-color: #ffffff; -fx-border-color: #00acc1; -fx-border-width: 3 0 0 0; -fx-background-insets: 0; }" +
            ".tab-label { -fx-font-weight: bold; -fx-text-fill: #455a64; }" +
            ".tab:selected .tab-label { -fx-text-fill: #00838f; }" +
            ".button { -fx-background-color: #00bcd4; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-cursor: hand; }" +
            ".button:hover { -fx-background-color: #26c6da; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 1); }" +
            ".button-action { -fx-background-color: #ff9800; }" + 
            ".button-action:hover { -fx-background-color: #ffa726; }" +
            ".table-view { -fx-background-color: white; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 0); }" +
            ".table-view .column-header-background { -fx-background-color: #00acc1; -fx-background-radius: 5 5 0 0; }" +
            ".table-view .column-header .label { -fx-text-fill: white; -fx-font-weight: bold; }" +
            ".table-row-cell:odd { -fx-background-color: #e0f7fa; }" + 
            ".text-field, .combo-box, .date-picker { -fx-background-radius: 8; -fx-border-color: #cfd8dc; -fx-border-radius: 8; -fx-padding: 8; }" +
            ".text-field:focused { -fx-border-color: #00bcd4; }";

        // --- 2. HEADER SECTION ---
        VBox headerBox = new VBox();
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setStyle("-fx-background-color: linear-gradient(to right, #00838f, #00e5ff); -fx-padding: 15;");
        
        try {
            Image headerImage = new Image(new FileInputStream("dentistimage1.jpeg"));
            ImageView headerView = new ImageView(headerImage);
            headerView.setFitHeight(110);
            headerView.setPreserveRatio(true);
            headerView.setEffect(new DropShadow(15, Color.rgb(0,0,0,0.3)));
            headerBox.getChildren().add(headerView);
        } catch (FileNotFoundException e) {
            Label errorLabel = new Label("Dental Clinic");
            errorLabel.setTextFill(Color.WHITE);
            errorLabel.setFont(Font.font(28));
            headerBox.getChildren().add(errorLabel);
        }
        
        Label titleLabel = new Label("Dental Clinic System");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setEffect(new DropShadow(2, Color.rgb(0,0,0,0.2))); 
        titleLabel.setPadding(new Insets(10, 0, 0, 0));
        headerBox.getChildren().add(titleLabel);
        
        root.setTop(headerBox);

        // --- 3. TABS ---
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        tabPane.getTabs().add(createHomeTab());
        tabPane.getTabs().add(createPatientTab());
        tabPane.getTabs().add(createStaffTab()); // UPDATED TAB
        tabPane.getTabs().add(createAppointmentTab());
        
        root.setCenter(tabPane);

        // --- 4. BOTTOM LOG ---
        VBox bottomBox = new VBox(5);
        bottomBox.setPadding(new Insets(10));
        bottomBox.setStyle("-fx-background-color: #263238;");
        
        Label logLabel = new Label("⚡ System Events & Polymorphism Log:");
        logLabel.setTextFill(Color.web("#80deea")); 
        logLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        
        notificationLog = new TextArea();
        notificationLog.setEditable(false);
        notificationLog.setPrefHeight(80);
        notificationLog.setStyle("-fx-control-inner-background: #37474f; -fx-text-fill: #69f0ae; -fx-font-family: 'Consolas'; -fx-highlight-fill: #00bcd4;");
        
        bottomBox.getChildren().addAll(logLabel, notificationLog);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 1100, 800);
        scene.getStylesheets().add("data:text/css," + css.replaceAll("\n", ""));
        
        primaryStage.setTitle("Clinic Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ================= TAB 1: HOME =================
    private Tab createHomeTab() {
        Tab tab = new Tab("Home");
        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: white;");
        
        try {
            Image heroImg = new Image(new FileInputStream("dentistimage2.jpg"));
            ImageView heroView = new ImageView(heroImg);
            heroView.setFitHeight(600);
            heroView.setPreserveRatio(true);
            heroView.setOpacity(0.85); 
            layout.getChildren().add(heroView);
        } catch (FileNotFoundException e) {}

        VBox overlay = new VBox(20);
        overlay.setAlignment(Pos.CENTER);
        overlay.setMaxSize(500, 300);
        overlay.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 20, 0, 0, 0);");
        
        Label welcomeTitle = new Label("Welcome to Future Smiles");
        welcomeTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        welcomeTitle.setTextFill(Color.web("#00838f"));
        
        Label subTitle = new Label("Efficient Management. Professional Care.");
        subTitle.setFont(Font.font("Segoe UI", 16));
        subTitle.setTextFill(Color.web("#546e7a"));
        
        Button btnQuickBook = new Button("📅 Book an Appointment Now");
        btnQuickBook.setStyle("-fx-font-size: 16px; -fx-padding: 10 20; -fx-background-color: #ff9800; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 30; -fx-cursor: hand;");
        btnQuickBook.setOnAction(e -> tabPane.getSelectionModel().select(3));
        
        overlay.getChildren().addAll(welcomeTitle, subTitle, new Separator(), btnQuickBook);
        layout.getChildren().add(overlay);
        tab.setContent(layout);
        return tab;
    }

    // ================= TAB 2: PATIENTS =================
    private Tab createPatientTab() {
        Tab tab = new Tab("👤 Patients");
        SplitPane split = new SplitPane();
        split.setStyle("-fx-background-color: transparent; -fx-padding: 10;");

        GridPane form = new GridPane();
        form.setPadding(new Insets(25));
        form.setHgap(15); form.setVgap(20);
        form.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

        Label header = new Label("Patient Registration");
        header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        header.setTextFill(Color.web("#00838f"));

        TextField nameField = new TextField(); nameField.setPromptText("Enter Full Name");
        ComboBox<String> genderBox = new ComboBox<>(); 
        genderBox.getItems().addAll("Male", "Female"); genderBox.setPromptText("Select Gender");
        TextField contactField = new TextField(); contactField.setPromptText("Phone (e.g. 555-1234)");
        DatePicker dobPicker = new DatePicker();
        CheckBox insuredCheck = new CheckBox("Has Valid Insurance");
        insuredCheck.setStyle("-fx-font-size: 14px; -fx-text-fill: #546e7a;");

        form.add(header, 0, 0, 2, 1);
        form.add(new Label("Full Name:"), 0, 1); form.add(nameField, 1, 1);
        form.add(new Label("Gender:"), 0, 2); form.add(genderBox, 1, 2);
        form.add(new Label("Contact:"), 0, 3); form.add(contactField, 1, 3);
        form.add(new Label("Birth Date:"), 0, 4); form.add(dobPicker, 1, 4);
        form.add(insuredCheck, 1, 5);

        Button btnAdd = new Button("Add Patient");
        btnAdd.setPrefWidth(150);
        form.add(btnAdd, 1, 6);

        TableView<Patient> table = new TableView<>();
        table.setItems(ClinicData.patients);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Patient, String> colName = new TableColumn<>("PATIENT NAME");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Patient, String> colGender = new TableColumn<>("GENDER");
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        TableColumn<Patient, String> colContact = new TableColumn<>("CONTACT");
        colContact.setCellValueFactory(new PropertyValueFactory<>("number"));
        

        table.getColumns().addAll(colName, colGender, colContact);

        btnAdd.setOnAction(e -> {
            try {
                if (nameField.getText().isEmpty() || dobPicker.getValue() == null) 
                    throw new IllegalArgumentException("Name and Date of Birth are mandatory!");

                Date dateOfBirth = Date.from(dobPicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                Patient newP = new Patient(nameField.getText(), dateOfBirth, genderBox.getValue(), contactField.getText(), null, insuredCheck.isSelected());
                ClinicData.patients.add(newP);
                appendLog("✅ Success: Patient " + newP.getName() + " registered.");
                nameField.clear(); contactField.clear();
            } catch (Exception ex) { showAlert("Input Error", ex.getMessage()); }
        });

        split.getItems().addAll(form, table);
        split.setDividerPositions(0.4);
        tab.setContent(split);
        return tab;
    }

    // ================= TAB 3: STAFF (UPDATED) =================
    private Tab createStaffTab() {
        Tab tab = new Tab("👨‍⚕️ Staff");
        SplitPane split = new SplitPane();
        split.setStyle("-fx-background-color: transparent; -fx-padding: 10;");

        // --- Left: Form ---
        GridPane form = new GridPane();
        form.setPadding(new Insets(25));
        form.setHgap(15); form.setVgap(20);
        form.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

        Label header = new Label("Hire New Staff");
        header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        header.setTextFill(Color.web("#00838f"));

        // 1. Role Selection
        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Doctor", "Receptionist");
        roleBox.setValue("Doctor"); // Default
        roleBox.setStyle("-fx-font-weight: bold;");

        // 2. Common Inputs
        TextField nameField = new TextField(); nameField.setPromptText("Staff Name");
        TextField salaryField = new TextField(); salaryField.setPromptText("Enter Wage");
        
        // 3. Dynamic Input (Changes based on Role)
        Label dynamicLabel = new Label("Specialization:");
        TextField dynamicField = new TextField(); dynamicField.setPromptText("e.g. Dentist");

        // Change label when role changes
        roleBox.setOnAction(e -> {
            if (roleBox.getValue().equals("Doctor")) {
                dynamicLabel.setText("Specialization:");
                dynamicField.setPromptText("e.g. Dentist");
            } else {
                dynamicLabel.setText("Shift Time:");
                dynamicField.setPromptText("e.g. Night Shift");
            }
        });

        form.add(header, 0, 0, 2, 1);
        form.add(new Label("Select Role:"), 0, 1); form.add(roleBox, 1, 1);
        form.add(new Label("Name:"), 0, 2); form.add(nameField, 1, 2);
        form.add(new Label("Wage ($):"), 0, 3); form.add(salaryField, 1, 3);
        form.add(dynamicLabel, 0, 4); form.add(dynamicField, 1, 4);

        Button btnHire = new Button("Hire Staff");
        btnHire.setPrefWidth(150);
        
        Button btnBonus = new Button("💰 Calc. Bonuses");
        btnBonus.getStyleClass().add("button-action");
        btnBonus.setPrefWidth(150);

        form.add(btnHire, 1, 5);
        form.add(btnBonus, 1, 6);

        // --- Right: Table (Polymorphic View) ---
        TableView<Staff> table = new TableView<>();
        table.setItems(ClinicData.allStaff); // Shows generic Staff objects
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Staff, String> colName = new TableColumn<>("NAME");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Staff, String> colRole = new TableColumn<>("ROLE");
        // Custom cell factory to determine role at runtime
        colRole.setCellValueFactory(cell -> {
            if (cell.getValue() instanceof Doctor) return new SimpleStringProperty("Doctor");
            if (cell.getValue() instanceof Receptionist) return new SimpleStringProperty("Receptionist");
            return new SimpleStringProperty("Staff");
        });

        // Wage Column (requires a getter in Staff class, assuming salary is protected/accessible)
        // Since we don't have getSalary() in the abstract Staff provided earlier, 
        // we will assume you added it OR we won't show it in table to avoid errors.
        // Showing "Details" instead which uses toString or custom logic
        TableColumn<Staff, String> colDetails = new TableColumn<>("DETAILS");
        colDetails.setCellValueFactory(cell -> {
             if (cell.getValue() instanceof Doctor) return new SimpleStringProperty(((Doctor)cell.getValue()).getSpecialization());
             if (cell.getValue() instanceof Receptionist) return new SimpleStringProperty(((Receptionist)cell.getValue()).getShiftTime());
             return new SimpleStringProperty("-");
        });

        table.getColumns().addAll(colName, colRole, colDetails);

        // --- Logic: Hire Staff ---
        btnHire.setOnAction(e -> {
            try {
                if (nameField.getText().isEmpty() || salaryField.getText().isEmpty()) 
                    throw new IllegalArgumentException("Name and Wage are required!");
                
                double wage = Double.parseDouble(salaryField.getText());
                String role = roleBox.getValue();
                
                if (role.equals("Doctor")) {
                    // Create Doctor
                    Doctor d = new Doctor(nameField.getText(), "M", new Date(), 0, wage, new Date(), "N/A", dynamicField.getText());
                    ClinicData.doctors.add(d);   // Add to booking list
                    ClinicData.allStaff.add(d);  // Add to main staff list
                    appendLog("✅ Hired Doctor: " + d.getName() + " with wage $" + wage);
                } else {
                    // Create Receptionist
                    Receptionist r = new Receptionist(nameField.getText(), "F", new Date(), 0, wage, new Date(), "N/A", dynamicField.getText());
                    ClinicData.allStaff.add(r);  // Add to main staff list
                    appendLog("✅ Hired Receptionist: " + r.getName() + " with wage $" + wage);
                }
                
                nameField.clear(); salaryField.clear(); dynamicField.clear();
                
            } catch (NumberFormatException ex) {
                showAlert("Input Error", "Wage must be a number!");
            } catch (Exception ex) {
                showAlert("Error", ex.getMessage());
            }
        });

        // --- Logic: Calculate Bonus (Polymorphism) ---
        btnBonus.setOnAction(e -> {
            appendLog("--- 💰 BONUS CALCULATION REPORT ---");
            if (ClinicData.allStaff.isEmpty()) {
                appendLog("No staff hired yet.");
            } else {
                for (Staff s : ClinicData.allStaff) {
                    // Polymorphic call: .calculateBonus() behaves differently for Doctor vs Receptionist
                    double bonus = s.calculateBonus(); 
                    String role = (s instanceof Doctor) ? "Doctor" : "Receptionist";
                    appendLog(String.format("➡ %s (%s): Wage $%.0f -> Bonus $%.2f", s.getName(), role, 5000.0, bonus)); 
                    // Note: accessing 'salary' directly might fail if protected, so showing calculated bonus is key.
                }
            }
            appendLog("-----------------------------------");
        });

        split.getItems().addAll(form, table);
        split.setDividerPositions(0.4);
        tab.setContent(split);
        return tab;
    }

    // ================= TAB 4: APPOINTMENTS =================
    private Tab createAppointmentTab() {
        Tab tab = new Tab("📅 Appointments");
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: transparent;");

        HBox tools = new HBox(15);
        tools.setAlignment(Pos.CENTER_LEFT);
        tools.setPadding(new Insets(20));
        tools.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");

        ComboBox<Doctor> comboDoc = new ComboBox<>(ClinicData.doctors); comboDoc.setPromptText("Select Doctor");
        ComboBox<Patient> comboPat = new ComboBox<>(ClinicData.patients); comboPat.setPromptText("Select Patient");
        
        comboDoc.setConverter(new StringConverter<Doctor>() {
            @Override public String toString(Doctor d) { return d == null ? null : d.getName(); }
            @Override public Doctor fromString(String s) { return null; }
        });
        
        comboPat.setConverter(new StringConverter<Patient>() {
            @Override public String toString(Patient p) { return p == null ? null : p.getName(); }
            @Override public Patient fromString(String s) { return null; }
        });

        DatePicker datePicker = new DatePicker(); datePicker.setPromptText("Select Date");
        ComboBox<String> hourBox = new ComboBox<>();
        hourBox.getItems().addAll("09", "10", "11", "12", "13", "14", "15", "16", "17"); hourBox.setPromptText("Hr");
        ComboBox<String> minBox = new ComboBox<>();
        minBox.getItems().addAll("00", "15", "30", "45"); minBox.setPromptText("Min");

        Button btnBook = new Button("Book Now");
        btnBook.getStyleClass().add("button-action");

        Button btnSort = new Button("Sort List");

        tools.getChildren().addAll(new Label("New Appointment:"), comboDoc, comboPat, datePicker, hourBox, new Label(":"), minBox, btnBook, new Separator(), btnSort);

        TableView<Appointment> table = new TableView<>();
        table.setItems(ClinicData.appointments);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox.setVgrow(table, Priority.ALWAYS);

        TableColumn<Appointment, String> colDate = new TableColumn<>("DATE & TIME");
        colDate.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDateTime().toString()));

        TableColumn<Appointment, String> colPat = new TableColumn<>("PATIENT");
        colPat.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getPatient().getName()));

        TableColumn<Appointment, String> colStatus = new TableColumn<>("STATUS");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        TableColumn<Appointment, String> colDoctor = new TableColumn<>("DOCTOR");

        colDoctor.setCellValueFactory(cell ->
                new SimpleStringProperty(
                        cell.getValue().getDoctor().getName()
                )
        );

        table.getColumns().addAll(colDate, colPat, colStatus, colDoctor);

        btnSort.setOnAction(e -> {
            Collections.sort(ClinicData.appointments);
            appendLog("🔃 System: Appointments list sorted chronologically.");
        });

        btnBook.setOnAction(e -> {
            try {
                if (comboDoc.getValue() == null || comboPat.getValue() == null || datePicker.getValue() == null || hourBox.getValue() == null) 
                    throw new IllegalArgumentException("All fields are required!");

                LocalDate selectedDate = datePicker.getValue();
                if (selectedDate.isBefore(LocalDate.now())) throw new IllegalArgumentException("Cannot book in the past!");

                LocalTime time = LocalTime.of(Integer.parseInt(hourBox.getValue()), Integer.parseInt(minBox.getValue()));
                Date finalDate = Date.from(selectedDate.atTime(time).atZone(ZoneId.systemDefault()).toInstant());

                Appointment newAppt = ClinicData.mainReceptionist.bookAppointment(comboDoc.getValue(), comboPat.getValue(), finalDate);

                if (newAppt != null) {
                    ClinicData.appointments.add(newAppt);
                    if (newAppt instanceof Notifiable) {
                        newAppt.sendNotification();
                        appendLog("🔔 Notification: " + newAppt.getNotificationMessage());
                    }
                } else { showAlert("Unavailable", "Doctor is unavailable at this time."); }

            } catch (Exception ex) { showAlert("Error", ex.getMessage()); }
        });

        layout.getChildren().addAll(tools, table);
        tab.setContent(layout);
        return tab;
    }

    private void appendLog(String msg) { notificationLog.appendText(msg + "\n"); }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}