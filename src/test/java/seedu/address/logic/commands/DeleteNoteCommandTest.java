package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.note.Note;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.PatientBuilder;

public class DeleteNoteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexAndTitle_success() {
        Patient patientToDeleteNote = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());

        Patient deletedNotePatient = new PatientBuilder(patientToDeleteNote)
                .withNoNote()
                .build();

        Note matchingNote = patientToDeleteNote.getNotes().stream()
                .filter(n -> n.getTitle().equalsIgnoreCase("4th Session with Alice"))
                .findFirst().orElse(null);
        assert matchingNote != null;
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(INDEX_FIRST_PATIENT, matchingNote.getTitle());

        String expectedMessage = String.format(DeleteNoteCommand.MESSAGE_DELETE_PATIENT_NOTE_SUCCESS,
                Messages.format(deletedNotePatient));

        ModelManager expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.deletePatientNote(patientToDeleteNote, deletedNotePatient);

        assertCommandSuccess(deleteNoteCommand, model, expectedMessage, expectedModel);
    }
}
