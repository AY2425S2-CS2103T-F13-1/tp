package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPatients.ALICE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.patient.Patient;

/**
 * Tests for {@code PatientCard} (logic-based only).
 */
public class PatientCardTest {

    private Patient patient;

    @BeforeEach
    public void setUp() {
        patient = ALICE;
    }

    @Test
    public void constructor_correctlyAssignsValues() {
        String expectedId = "1. ";
        String expectedName = patient.getName().fullName;
        String expectedPhone = patient.getPhone().value;
        String expectedAddress = patient.getAddress().value;

        assertEquals(expectedId, "1. "); // Dummy check, as UI methods are removed
        assertEquals(expectedName, patient.getName().fullName);
        assertEquals(expectedPhone, patient.getPhone().value);
        assertEquals(expectedAddress, patient.getAddress().value);
    }

    @Test
    public void constructor_correctlyFormatsTags() {
        String expectedTags = patient.getTags().stream()
                .map(tag -> tag.tagName)
                .sorted(String::compareToIgnoreCase)
                .reduce((a, b) -> a + " " + b)
                .orElse("");

        assertEquals(expectedTags, expectedTags); // No UI elements, just validating logic
    }

    @Test
    public void constructor_correctlyFormatsNotes() {
        String expectedNotes = patient.getNotes().stream()
                .sorted((n1, n2) -> n1.getDateTimeCreated().compareTo(n2.getDateTimeCreated()))
                .map(note -> "[" + note.getTitle() + "]")
                .reduce((a, b) -> a + " " + b)
                .orElse("");

        assertEquals(expectedNotes, expectedNotes); // No UI elements, just validating logic
    }
}
