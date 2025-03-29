package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_NOTE_NOT_FOUND;
import static seedu.address.logic.Messages.MESSAGE_NO_NOTES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PATIENTS;

import java.util.List;
import java.util.TreeSet;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.note.Note;
import seedu.address.model.patient.Patient;

/**
 * Deletes a patient identified using it's displayed index from the address book.
 */
public class DeleteNoteCommand extends Command {

    public static final String COMMAND_WORD = "deletenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a specified note of a patient identified by the title string used "
            + "in the displayed note list.\n"
            + "Parameters: INDEX (must be a positive integer) nt/TITLE (must be a valid string)\n"
            + "Example: " + COMMAND_WORD + " 1 nt/JohnFirstCrashout";

    public static final String MESSAGE_DELETE_PATIENT_NOTE_SUCCESS = "Deleted Note of Patient: %1$s";

    private final Index targetIndex;
    private final String targetTitle;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * Delete the Note associated with the note title in the patient specified by targetIndex
     * @param targetIndex of the patient in the patient list to delete note from
     * @param targetTitle of the note in the patient specified by the index to delete
     */
    public DeleteNoteCommand(Index targetIndex, String targetTitle) {
        this.targetIndex = targetIndex;
        this.targetTitle = targetTitle;
        this.editPatientDescriptor = new EditPatientDescriptor();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        if (targetTitle.isEmpty() || targetTitle.equals(" ")) { // catch empty or blank space case
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DELETE_TITLE);
        }

        Patient patientToEdit = lastShownList.get(targetIndex.getZeroBased());
        TreeSet<Note> allNotes = patientToEdit.getNotes();
        TreeSet<Note> newCopyNotes = new TreeSet<>(allNotes);

        if (newCopyNotes.isEmpty()) {
            throw new CommandException(MESSAGE_NO_NOTES);
        }

        Note matchingNote = newCopyNotes.stream()
                .filter(n -> n.getTitle().equalsIgnoreCase(targetTitle))
                .findFirst().orElse(null);

        if (matchingNote == null) {
            throw new CommandException(String.format(MESSAGE_NOTE_NOT_FOUND, targetTitle));
        }

        newCopyNotes.remove(matchingNote);
        Patient editedPatient = new Patient(
                patientToEdit.getName(),
                patientToEdit.getPhone(),
                patientToEdit.getAddress(),
                patientToEdit.getTags(),
                newCopyNotes);


        model.deletePatientNote(patientToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PATIENTS);

        return new CommandResult(String.format(MESSAGE_DELETE_PATIENT_NOTE_SUCCESS, Messages.format(patientToEdit)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteNoteCommand)) {
            return false;
        }

        DeleteNoteCommand otherDeleteNoteCommand = (DeleteNoteCommand) other;
        return targetIndex.equals(otherDeleteNoteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
