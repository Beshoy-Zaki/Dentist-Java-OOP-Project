// ---------------- IMPORTS ----------------
// Imports the base class for all JavaFX applications.
package com;

import javafx.application.Application;
// Importing property wrappers to allow the UI to automatically update when data changes.
import javafx.beans.property.SimpleStringProperty;
// Importing collections transformation classes to handle the "Live Search" filtering logic.
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
// Importing geometry classes to handle layout alignment and internal spacing (padding).
import javafx.geometry.Insets;
import javafx.geometry.Pos;
// Importing Scene (the window content container) and essential UI controls.
import javafx.scene.Scene;
import javafx.scene.control.*;
// Importing PropertyValueFactory to map table columns to class fields (e.g., "name").
import javafx.scene.control.cell.PropertyValueFactory;
// Importing visual effects like DropShadow to create a modern, elevated look.
import javafx.scene.effect.DropShadow;
// Importing image handling classes to render dental clinic photos.
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// Importing layout panes like VBox (Vertical), HBox (Horizontal), and BorderPane.
import javafx.scene.layout.*;
// Importing Color class to define theme colors for text and backgrounds.
import javafx.scene.paint.Color;
// Importing Font classes to set the typography style and weight.
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
// Importing Stage, which represents the physical window frame.
import javafx.stage.Stage;
// Importing StringConverter to format objects (like Doctors) into text for dropdowns.
import javafx.util.StringConverter;

// Importing IO classes to read the image files from your project folder.
import java.io.FileInputStream;
import java.io.FileNotFoundException;
// Importing modern Time API classes for date and time selection logic.
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
// Importing Collections utility to sort lists using the Comparable interface.
import java.util.Collections;
// Importing the legacy Date class required by the core logic classes.
import java.util.Date;

// The main class 'ClinicGUI' extends 'Application' to launch the graphical window.
public class ClinicGUI extends Application {

    // A TextArea component to display real-time system logs and polymorphism results.
    private TextArea notificationLog;
    // A reference to the main TabPane to allow programmatic navigation between tabs.
    private TabPane tabPane; 

    // Entry point for the Java Virtual Machine.
    public static void main(String[] args) {
        // Initializes JavaFX and triggers the start() method.
        launch(args);
    }

    // The start method: where we define the layout, styling, and window properties.
    @Override
    public void start(Stage primaryStage) {
        // Creates a BorderPane to organize the app into Top (Header), Bottom (Logs), and Center (Tabs).
        BorderPane root = new BorderPane();
        
        // --- 1. UNIFIED THEME CSS ---
        // CSS string used to apply a professional and eye-catchy look to all components.
        String css = 
            // Global styling: sets font family and a light grey app background.
            ".root { -fx-font-family: 'Segoe UI', sans-serif; -fx-background-color: #f4f6f8; }" + 
            // Makes the tab header background clean and transparent.
            ".tab-header-background { -fx-background-color: transparent; }" +
            // Sets a uniform width for the tab buttons.
            ".tab-pane { -fx-tab-min-width: 100px; }" +
            // Styles inactive tabs with a soft blue-grey background.
            ".tab { -fx-background-color: #cfd8dc; -fx-background-radius: 10 10 0 0; -fx-padding: 10 20; }" +
            // Highlights the active tab with a white background and a cyan top border.
            ".tab:selected { -fx-background-color: #ffffff; -fx-border-color: #00acc1; -fx-border-width: 3 0 0 0; -fx-background-insets: 0; }" +
            // Sets tab labels to Bold.
            ".tab-label { -fx-font-weight: bold; -fx-text-fill: #455a64; }" +
            // Changes label color to deep Cyan when selected.
            ".tab:selected .tab-label { -fx-text-fill: #00838f; }" +
            // Styles standard Buttons: Cyan background, rounded edges, and bold white text.
            ".button { -fx-background-color: #00bcd4; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-cursor: hand; }" +
            // Adds a hover effect to buttons for interactivity.
            ".button:hover { -fx-background-color: #26c6da; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 1); }" +
            // Defines a secondary "Orange" style for primary actions.
            ".button-action { -fx-background-color: #ff9800; }" + 
            ".button-action:hover { -fx-background-color: #ffa726; }" +
            // Defines a "Red" style specifically for the Cancel button.
            ".button-danger { -fx-background-color: #e53935; }" + 
            ".button-danger:hover { -fx-background-color: #c62828; }" +
            // Styles the TableView with rounded corners and a soft shadow.
            ".table-view { -fx-background-color: white; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 0); }" +
            // Styles column headers with the theme color (Cyan).
            ".table-view .column-header-background { -fx-background-color: #00acc1; -fx-background-radius: 5 5 0 0; }" +
            ".table-view .column-header .label { -fx-text-fill: white; -fx-font-weight: bold; }" +
            // Zebra Striping: alternating row colors to help the user read data easily.
            ".table-row-cell:odd { -fx-background-color: #e0f7fa; }" + 
            // Styles input fields with rounded borders and padding.
            ".text-field, .combo-box, .date-picker { -fx-background-radius: 8; -fx-border-color: #cfd8dc; -fx-border-radius: 8; -fx-padding: 8; }" +
            // Glow effect when an input field is active.
            ".text-field:focused { -fx-border-color: #00bcd4; }";

        // --- 2. HEADER SECTION ---
        // VBox layout to stack logo and title vertically.
        VBox headerBox = new VBox();
        // Centers the content in the top bar.
        headerBox.setAlignment(Pos.CENTER);
        // Deep Cyan linear gradient for a modern feel.
        headerBox.setStyle("-fx-background-color: linear-gradient(to right, #00838f, #00e5ff); -fx-padding: 15;");
        
        try {
            // Loads 'dentistimage1.jpeg' from the project root.
            Image headerImage = new Image(new FileInputStream("dentistimage1.jpeg"));
            ImageView headerView = new ImageView(headerImage);
            // Sets image size for the header.
            headerView.setFitHeight(110);
            headerView.setPreserveRatio(true);
            // Adds a DropShadow to the logo.
            headerView.setEffect(new DropShadow(15, Color.rgb(0,0,0,0.3)));
            headerBox.getChildren().add(headerView);
        } catch (FileNotFoundException e) {
            // Backup text if image is missing.
            Label errorLabel = new Label("Dental Clinic");
            errorLabel.setTextFill(Color.WHITE);
            errorLabel.setFont(Font.font(28));
            headerBox.getChildren().add(errorLabel);
        }
        
        // Main title of the system.
        Label titleLabel = new Label("Dental Clinic System");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 22));
        titleLabel.setTextFill(Color.WHITE);
        // Text shadow for high contrast.
        titleLabel.setEffect(new DropShadow(2, Color.rgb(0,0,0,0.2))); 
        titleLabel.setPadding(new Insets(10, 0, 0, 0));
        headerBox.getChildren().add(titleLabel);
        
        // Attaches the header to the Top of the BorderPane.
        root.setTop(headerBox);

        // --- 3. TABS ---
        // Main Tab container.
        tabPane = new TabPane();
        // Tabs cannot be closed by the user.
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        // Loads the specialized panels for each tab.
        tabPane.getTabs().add(createHomeTab());
        tabPane.getTabs().add(createPatientTab());
        tabPane.getTabs().add(createStaffTab()); 
        tabPane.getTabs().add(createAppointmentTab());
        
        // Attaches the tabs to the Center.
        root.setCenter(tabPane);

        // --- 4. BOTTOM LOG ---
        // VBox for the system log.
        VBox bottomBox = new VBox(5);
        bottomBox.setPadding(new Insets(10));
        // Dark Charcoal color for a terminal look.
        bottomBox.setStyle("-fx-background-color: #263238;");
        
        Label logLabel = new Label("⚡ System Events & Polymorphism Log:");
        logLabel.setTextFill(Color.web("#80deea")); 
        logLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        
        notificationLog = new TextArea();
        // Prevents user editing.
        notificationLog.setEditable(false);
        notificationLog.setPrefHeight(80);
        // Neon green "Console" font style.
        notificationLog.setStyle("-fx-control-inner-background: #37474f; -fx-text-fill: #69f0ae; -fx-font-family: 'Consolas'; -fx-highlight-fill: #00bcd4;");
        
        bottomBox.getChildren().addAll(logLabel, notificationLog);
        root.setBottom(bottomBox);

        // Creates the final scene.
        Scene scene = new Scene(root, 1100, 800);
        // Applies the CSS styling rules.
        scene.getStylesheets().add("data:text/css," + css.replaceAll("\n", ""));
        
        primaryStage.setTitle("Clinic Management System");
        primaryStage.setScene(scene);
        // Displays the application.
        primaryStage.show();
    }

    // ================= TAB 1: HOME (NO COUNTERS) =================
    // Builds the landing page without the dashboard statistics.
    private Tab createHomeTab() {
        Tab tab = new Tab("Home");
        // StackPane allows overlaying the message box on top of the image.
        StackPane layout = new StackPane();
        layout.setStyle("-fx-background-color: white;");
        
        try {
            // Loads 'dentistimage2.jpg' as the hero background.
            Image heroImg = new Image(new FileInputStream("dentistimage2.jpg"));
            ImageView heroView = new ImageView(heroImg);
            heroView.setFitHeight(600);
            heroView.setPreserveRatio(true);
            heroView.setOpacity(0.85); 
            layout.getChildren().add(heroView);
        } catch (FileNotFoundException e) {
            // Ignore error if image is missing.
        }

        // Card container for the welcome message.
        VBox overlay = new VBox(20);
        overlay.setAlignment(Pos.CENTER);
        overlay.setMaxSize(500, 300);
        // Semi-transparent white card with professional shadow.
        overlay.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 20, 0, 0, 0);");
        
        Label welcomeTitle = new Label("Welcome to Future Smiles");
        welcomeTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        welcomeTitle.setTextFill(Color.web("#00838f"));
        
        Label subTitle = new Label("Efficient Management. Professional Care.");
        subTitle.setFont(Font.font("Segoe UI", 16));
        subTitle.setTextFill(Color.web("#546e7a"));
        
        // Navigation button to go to appointments.
        Button btnQuickBook = new Button("📅 Book an Appointment Now");
        btnQuickBook.setStyle("-fx-font-size: 16px; -fx-padding: 10 20; -fx-background-color: #ff9800; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 30; -fx-cursor: hand;");
        // Switch to the 4th tab (Appointments) when clicked.
        btnQuickBook.setOnAction(e -> tabPane.getSelectionModel().select(3));
        
        // Adds textual elements and action button into the card.
        overlay.getChildren().addAll(welcomeTitle, subTitle, new Separator(), btnQuickBook);
        layout.getChildren().add(overlay);
        tab.setContent(layout);
        return tab;
    }

    // ================= TAB 2: PATIENTS (WITH LIVE SEARCH) =================
    // Builds the patient management screen including the searching functionality.
    private Tab createPatientTab() {
        Tab tab = new Tab("👤 Patients");
        SplitPane split = new SplitPane();
        split.setStyle("-fx-background-color: transparent; -fx-padding: 10;");

        // --- Left Side: Form ---
        GridPane form = new GridPane();
        form.setPadding(new Insets(25));
        form.setHgap(15); form.setVgap(20);
        form.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 0);");

        Label header = new Label("Patient Registration");
        header.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        header.setTextFill(Color.web("#00838f"));

        // Registration input components.
        TextField nameField = new TextField(); nameField.setPromptText("Enter Full Name");
        ComboBox<String> genderBox = new ComboBox<>(); 
        genderBox.getItems().addAll("Male", "Female"); genderBox.setPromptText("Select Gender");
        TextField contactField = new TextField(); contactField.setPromptText("Phone (e.g. 555-1234)");
        DatePicker dobPicker = new DatePicker();
        CheckBox insuredCheck = new CheckBox("Has Valid Insurance");
        insuredCheck.setStyle("-fx-font-size: 14px; -fx-text-fill: #546e7a;");

        // Adds inputs to the grid layout.
        form.add(header, 0, 0, 2, 1);
        form.add(new Label("Full Name:"), 0, 1); form.add(nameField, 1, 1);
        form.add(new Label("Gender:"), 0, 2); form.add(genderBox, 1, 2);
        form.add(new Label("Contact:"), 0, 3); form.add(contactField, 1, 3);
        form.add(new Label("Birth Date:"), 0, 4); form.add(dobPicker, 1, 4);
        form.add(insuredCheck, 1, 5);

        Button btnAdd = new Button("Add Patient");
        btnAdd.setPrefWidth(150);
        form.add(btnAdd, 1, 6);

        // --- Right Side: Search & Table ---
        VBox rightSide = new VBox(10);
        
        // Live search bar input.
        TextField searchField = new TextField();
        searchField.setPromptText("🔍 Search Patients by Name...");
        
        // Wraps the patient list in a filter.
        FilteredList<Patient> filteredData = new FilteredList<>(ClinicData.patients, p -> true);
        // Updates the filter every time the user types in the search bar.
        searchField.textProperty().addListener((obs, oldVal, newVal) -> {
            filteredData.setPredicate(patient -> {
                if (newVal == null || newVal.isEmpty()) return true;
                return patient.getName().toLowerCase().contains(newVal.toLowerCase());
            });
        });

        // Patient data table.
        TableView<Patient> table = new TableView<>(filteredData);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Patient, String> colName = new TableColumn<>("PATIENT NAME");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Patient, String> colGender = new TableColumn<>("GENDER");
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        TableColumn<Patient, String> colContact = new TableColumn<>("CONTACT");
        colContact.setCellValueFactory(new PropertyValueFactory<>("number"));
        
        table.getColumns().addAll(colName, colGender, colContact);
        rightSide.getChildren().addAll(searchField, table);

        // Logic: Create Patient and clear form.
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

        split.getItems().addAll(form, rightSide);
        split.setDividerPositions(0.4);
        tab.setContent(split);
        return tab;
    }

    // ================= TAB 3: STAFF =================
    // Builds the Staff management tab showing polymorphism in bonus calculation.
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

        ComboBox<String> roleBox = new ComboBox<>();
        roleBox.getItems().addAll("Doctor", "Receptionist");
        roleBox.setValue("Doctor"); 
        roleBox.setStyle("-fx-font-weight: bold;");

        TextField nameField = new TextField(); nameField.setPromptText("Staff Name");
        TextField salaryField = new TextField(); salaryField.setPromptText("Enter Wage");
        
        Label dynamicLabel = new Label("Specialization:");
        TextField dynamicField = new TextField(); dynamicField.setPromptText("e.g. Dentist");

        // Logic: Role-specific input label.
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

        // Staff listing table.
        TableView<Staff> table = new TableView<>();
        table.setItems(ClinicData.allStaff); 
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Staff, String> colName = new TableColumn<>("NAME");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Staff, String> colRole = new TableColumn<>("ROLE");
        // Logic: Polymorphic check to display role type.
        colRole.setCellValueFactory(cell -> {
            if (cell.getValue() instanceof Doctor) return new SimpleStringProperty("Doctor");
            if (cell.getValue() instanceof Receptionist) return new SimpleStringProperty("Receptionist");
            return new SimpleStringProperty("Staff");
        });

        TableColumn<Staff, String> colDetails = new TableColumn<>("DETAILS");
        colDetails.setCellValueFactory(cell -> {
             if (cell.getValue() instanceof Doctor) return new SimpleStringProperty(((Doctor)cell.getValue()).getSpecialization());
             if (cell.getValue() instanceof Receptionist) return new SimpleStringProperty(((Receptionist)cell.getValue()).getShiftTime());
             return new SimpleStringProperty("-");
        });

        table.getColumns().addAll(colName, colRole, colDetails);

        // Logic: Add either Doctor or Receptionist to list.
        btnHire.setOnAction(e -> {
            try {
                if (nameField.getText().isEmpty() || salaryField.getText().isEmpty()) 
                    throw new IllegalArgumentException("Name and Wage are required!");
                
                double wage = Double.parseDouble(salaryField.getText());
                String role = roleBox.getValue();
                
                if (role.equals("Doctor")) {
                    Doctor d = new Doctor(nameField.getText(), "M", new Date(), 0, wage, new Date(), "N/A", dynamicField.getText());
                    ClinicData.doctors.add(d);   
                    ClinicData.allStaff.add(d);  
                    appendLog("✅ Hired Doctor: " + d.getName() + " with wage $" + wage);
                } else {
                    Receptionist r = new Receptionist(nameField.getText(), "F", new Date(), 0, wage, new Date(), "N/A", dynamicField.getText());
                    ClinicData.allStaff.add(r);  
                    appendLog("✅ Hired Receptionist: " + r.getName() + " with wage $" + wage);
                }
                nameField.clear(); salaryField.clear(); dynamicField.clear();
            } catch (Exception ex) { showAlert("Error", ex.getMessage()); }
        });

        // Logic: Bonus calculation (Polymorphism Demo).
        btnBonus.setOnAction(e -> {
            appendLog("--- 💰 BONUS CALCULATION REPORT ---");
            if (ClinicData.allStaff.isEmpty()) { appendLog("No staff hired yet."); } 
            else {
                for (Staff s : ClinicData.allStaff) {
                    // Polymorphism: Java executes the correct logic based on object type.
                    double bonus = s.calculateBonus(); 
                    String role = (s instanceof Doctor) ? "Doctor" : "Receptionist";
                    appendLog(String.format("➡ %s (%s): Wage $%.0f -> Bonus $%.2f", s.getName(), role, 5000.0, bonus)); 
                }
            }
            appendLog("-----------------------------------");
        });

        split.getItems().addAll(form, table);
        split.setDividerPositions(0.4);
        tab.setContent(split);
        return tab;
    }

    // ================= TAB 4: APPOINTMENTS (WITH CANCEL) =================
    // Builds the appointment scheduler with both Sorting and Cancellation.
    private Tab createAppointmentTab() {
        Tab tab = new Tab("📅 Appointments");
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: transparent;");

        // Action Toolbar.
        HBox tools = new HBox(15);
        tools.setAlignment(Pos.CENTER_LEFT);
        tools.setPadding(new Insets(20));
        tools.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);");

        ComboBox<Doctor> comboDoc = new ComboBox<>(ClinicData.doctors); comboDoc.setPromptText("Select Doctor");
        ComboBox<Patient> comboPat = new ComboBox<>(ClinicData.patients); comboPat.setPromptText("Select Patient");
        
        // Logic: Formats dropdown items to show Names.
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

        // --- NEW FUNCTIONALITY: CANCEL BUTTON ---
        Button btnCancel = new Button("❌ Cancel");
        btnCancel.getStyleClass().add("button-danger");

        Button btnSort = new Button("Sort List");

        tools.getChildren().addAll(new Label("New Appointment:"), comboDoc, comboPat, datePicker, hourBox, new Label(":"), minBox, btnBook, new Separator(), btnCancel, btnSort);

        // Appointment table view.
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
        // Retrieves name from Doctor object inside Appointment.
        colDoctor.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDoctor().getName()));

        table.getColumns().addAll(colDate, colPat, colStatus, colDoctor);

        // Logic: Uses the Comparable interface inside Appointment class.
        btnSort.setOnAction(e -> {
            Collections.sort(ClinicData.appointments);
            appendLog("🔃 System: Appointments list sorted chronologically.");
        });

        // Logic: Deletes the selected record.
        btnCancel.setOnAction(e -> {
            Appointment selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                ClinicData.appointments.remove(selected);
                appendLog("🗑 System: Selected appointment record removed.");
            } else { showAlert("Notice", "Please select a row to cancel."); }
        });

        // Logic: Creates appointment and handles past date validation.
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

    // Console logging helper.
    private void appendLog(String msg) { notificationLog.appendText(msg + "\n"); }

    // Alert popup helper.
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
