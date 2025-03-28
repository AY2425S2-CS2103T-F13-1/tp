package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.exceptions.RedoException;
import seedu.address.model.exceptions.UndoException;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.UniquePatientList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePatient comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePatientList patients;
    private final VersionedAddressBook versionedAddressBook = new VersionedAddressBook();

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        patients = new UniquePatientList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Patients in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
        versionedAddressBook.saveState(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the patient list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setPatients(patients);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPatients(newData.getPatientList());
    }

    //// patient-level operations

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the app.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patients.contains(patient);
    }

    /**
     * Adds a patient to the app.
     * The patient must not already exist in the app.
     */
    public void addPatient(Patient p) {
        patients.add(p);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the app.
     * The patient identity of {@code editedPatient} must not be the same as another
     * existing patient in the app.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        patients.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the app.
     */
    public void removePatient(Patient key) {
        patients.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patients", patients)
                .toString();
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return patients.equals(otherAddressBook.patients);
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }

    /**
     * Stores the current states.
     */
    public void saveState() {
        ReadOnlyAddressBook copy = new AddressBook(this);
        versionedAddressBook.saveState(copy);
    }

    /**
     * Returns previous state.
     */
    public ReadOnlyAddressBook getOldState() throws UndoException {
        ReadOnlyAddressBook res = versionedAddressBook.getOldState();
        return res;
    }

    /**
     * Returns the previously undone state.
     */
    public ReadOnlyAddressBook getFutureState() throws RedoException {
        ReadOnlyAddressBook res = versionedAddressBook.getFutureState();
        return res;
    }
}
