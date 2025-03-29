package seedu.address.ui;

import java.util.Comparator;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.patient.Patient;

/**
 * A UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";

    private static final String[] TAG_COLORS = {
        "#7B506F", // dark pink
        "#718355", // olive green
        "#C1666B", // dusty rose
        "#4B5267", // slate blue
        "#A17C6B" // warm brown
    };

    public final Patient patient;

    @FXML
    private HBox cardPane;

    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox notes;

    /**
     * Creates a {@code PatientCard} with the given {@code Patient} and index to
     * display.
     *
     * @param patient        The patient whose details should be displayed.
     * @param displayedIndex The index of the patient in the list.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.patient = patient;
        id.setText(displayedIndex + ". ");
        name.setText(patient.getName().fullName);
        phone.setText(patient.getPhone().value);
        address.setText(patient.getAddress().value);

        // Display tags with dynamic colors only for multiple tags
        var patientTags = patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .collect(Collectors.toList());

        boolean hasMultipleTags = patientTags.size() > 1;

        for (int i = 0; i < patientTags.size(); i++) {
            var tag = patientTags.get(i);
            Label tagLabel = new Label(tag.tagName);
            tagLabel.getStyleClass().add("label");

            // If patient has multiple tags, use different colors
            String tagColor;
            if (hasMultipleTags) {
                tagColor = TAG_COLORS[i % TAG_COLORS.length];
            } else {
                // Single tag - use default color
                tagColor = "#7B506F";
            }

            tagLabel.setStyle(String.format("-fx-background-color: %s; -fx-text-fill: #EAD7D1;", tagColor));
            tags.getChildren().add(tagLabel);
        }

        // Display notes in a more structured way
        // Make sure the notes container in your FXML has the notes-container style
        // class
        notes.getStyleClass().add("notes-container");

        patient.getNotes().stream()
                .sorted(Comparator.comparing(note -> note.getDateTimeCreated()))
                .forEach(note -> {
                    VBox noteBox = new VBox();
                    noteBox.getStyleClass().add("note-box");

                    // Create a label with the title and add style
                    Label titleLabel = new Label(note.getTitle());
                    titleLabel.getStyleClass().add("note-title");
                    titleLabel.setWrapText(true);

                    // Format the date as DD-MM-YYYY
                    String formattedDate = note.getDateTimeCreated().format(
                            java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    Label dateLabel = new Label(formattedDate);
                    dateLabel.getStyleClass().add("note-date");

                    // Add the title and date directly to the noteBox
                    noteBox.getChildren().addAll(titleLabel, dateLabel);

                    // Add the noteBox directly to the notes VBox
                    notes.getChildren().add(noteBox);
                });
    }

    /**
     * Retrieves the displayed index text.
     *
     * @return The index as a string.
     */
    public String getDisplayedIndexText() {
        return id.getText();
    }

    /**
     * Retrieves the patient's name text.
     *
     * @return The patient's name.
     */
    public String getPatientNameText() {
        return name.getText();
    }

    /**
     * Retrieves the patient's phone number text.
     *
     * @return The phone number.
     */
    public String getPhoneText() {
        return phone.getText();
    }

    /**
     * Retrieves the patient's address text.
     *
     * @return The address.
     */
    public String getAddressText() {
        return address.getText();
    }

    /**
     * Retrieves the formatted tags as a space-separated string.
     *
     * @return The formatted tag string.
     */
    public String getFormattedTags() {
        return tags.getChildren().stream()
                .map(node -> ((Label) node).getText())
                .collect(Collectors.joining(" "));
    }

    /**
     * Retrieves the formatted notes as a space-separated string.
     *
     * @return The formatted note titles.
     */
    public String getFormattedNotes() {
        if (notes.getChildren().isEmpty() || !(notes.getChildren().get(0) instanceof VBox)) {
            return "";
        }

        VBox notesContainer = (VBox) notes.getChildren().get(0);
        return notesContainer.getChildren().stream()
                .filter(node -> node instanceof VBox)
                .map(noteBox -> {
                    VBox box = (VBox) noteBox;
                    if (box.getChildren().size() >= 2) {
                        Label titleLabel = (Label) box.getChildren().get(0);
                        Label contentLabel = (Label) box.getChildren().get(1);
                        return titleLabel.getText() + ": " + contentLabel.getText();
                    }
                    return "";
                })
                .collect(Collectors.joining(" | "));
    }
}
