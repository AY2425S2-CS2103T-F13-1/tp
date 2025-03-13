package seedu.address.model.note;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class which encapsulates all the information and methods pertaining to notes
 * Notes have a many-to-one relationship with patients
 */
public class Note implements Comparable<Note> {

    public static final String TITLE_AND_CONTENT_CONSTRAINTS = "Title and Content of Note cannot be empty"
            + "adhere to the following format:\n"
            + "note INDEX nt/TITLE nc/CONTENT\n"
            + "example: note 1 nt/JohnFirstAppt nc/john did well today";

    private final String title;
    private final String content;
    private final LocalDateTime dateTimeCreated;

    /**
     * Initializes a new Note object with the given title and content
     * dateTimeCreated is initialized to the current time
     * @param title   title of the note
     * @param content content of the note
     */
    public Note(@JsonProperty("title") String title,
            @JsonProperty("content") String content) {
        requireAllNonNull(title, content);
        checkArgument(isValidNote(title, content), TITLE_AND_CONTENT_CONSTRAINTS);
        this.title = title;
        this.content = content;
        this.dateTimeCreated = LocalDateTime.now();
    }

    /**
     * Initializes a new Note object with the given title, content and LocalDateTime
     * @param title title of the note
     * @param content content of the note
     * @param dateTimeCreated date and time the note was created
     */
    @JsonCreator
    public Note(@JsonProperty("title") String title,
                @JsonProperty("content") String content,
                @JsonProperty("dateTimeCreated") LocalDateTime dateTimeCreated) {
        requireAllNonNull(title, content);
        checkArgument(isValidNote(title, content), TITLE_AND_CONTENT_CONSTRAINTS);
        this.title = title;
        this.content = content;
        this.dateTimeCreated = dateTimeCreated;
    }

    public static boolean isValidNote(String title, String content) {
        return !title.isEmpty() && !content.isEmpty();
    }

    /**
     * Gets title of a note
     * @return title of the note
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets content of a note
     * @return content of the note
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Gets content of a note
     * @return content of the note
     */
    public String getTitledContent() {
        return "[" + this.title + "]" + this.content;
    }

    /**
     * Gets the date and time the note was created
     * @return LocalDateTime object, which stores the date and time the note was created
     */
    public LocalDateTime getDateTimeCreated() {
        return this.dateTimeCreated;
    }

    /**
     * Enables sorting of notes based on date and time created
     * @param other another note object to compare with
     * @return 0 if the two notes were created at the same time, a positive integer if
     *     this note was created after the other note, and a negative integer if this
     *     note was created before the other note
     */
    @Override
    public int compareTo(Note other) {
        return this.dateTimeCreated.compareTo(other.dateTimeCreated);
    }

    /**
     * Checks if two notes are equal
     * @param other another object to compare with
     * @return true if the two notes are equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return this.title.equals(otherNote.title)
                && this.content.equals(otherNote.content)
                && this.dateTimeCreated.equals(otherNote.dateTimeCreated);
    }

    /**
     * Returns a string representation of the note
     */
    @Override
    public String toString() {
        return "[" + this.title + "]";
    }

    @Override
    public int hashCode() {
        return this.title.hashCode() + this.content.hashCode() + this.dateTimeCreated.hashCode();
    }

}
