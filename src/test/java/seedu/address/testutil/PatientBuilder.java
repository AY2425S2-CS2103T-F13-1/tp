package seedu.address.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.model.note.Note;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Patient objects.
 */
public class PatientBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_TITLE = "Note Title";
    public static final String DEFAULT_CONTENT = "Note Content";
    public static final Note DEFAULT_NOTE = new Note(DEFAULT_TITLE, DEFAULT_CONTENT);

    private Name name;
    private Phone phone;
    private Address address;
    private Set<Tag> tags;
    private TreeSet<Note> notes;

    /**
     * Creates a {@code PatientBuilder} with the default details.
     */
    public PatientBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        notes = new TreeSet<>();
    }

    /**
     * Initializes the PatientBuilder with the data of {@code patientToCopy}.
     */
    public PatientBuilder(Patient patientToCopy) {
        name = patientToCopy.getName();
        phone = patientToCopy.getPhone();
        address = patientToCopy.getAddress();
        tags = new HashSet<>(patientToCopy.getTags());
        notes = new TreeSet<>(patientToCopy.getNotes());
    }

    /**
     * Sets the {@code Name} of the {@code Patient} that we are building.
     */
    public PatientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Patient} that we are building.
     */
    public PatientBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Patient} that we are building.
     */
    public PatientBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Patient} that we are building.
     */
    public PatientBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Patient} that we are building.
     */
    public PatientBuilder withNotes(Note... notes) {
        this.notes.addAll(Arrays.asList(notes));
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Patient} that we are building to none.
     */
    public PatientBuilder withNoNote() {
        this.notes = new TreeSet<>();
        return this;
    }

    public Patient build() {
        return new Patient(name, phone, address, tags, notes);
    }

}
