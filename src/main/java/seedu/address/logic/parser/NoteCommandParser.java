package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE_TITLE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.logic.commands.NoteCommand.AddNoteDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Note;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class NoteCommandParser implements Parser<NoteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@code NoteCommand}
     * and returns a {@code NoteCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NOTE_TITLE, PREFIX_NOTE_CONTENT);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE), ive);
        }

        AddNoteDescriptor editNoteDescriptor = new AddNoteDescriptor();
        if (argMultimap.getValue(PREFIX_NOTE_TITLE).isPresent()
                && argMultimap.getValue(PREFIX_NOTE_CONTENT).isPresent()) {
            Note fullNote = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE_TITLE).get(),
                    argMultimap.getValue(PREFIX_NOTE_CONTENT).get());
            editNoteDescriptor.setTitle(fullNote.getTitle());
            editNoteDescriptor.setContent(fullNote.getContent());
        }

        if (!editNoteDescriptor.isAnyFieldEdited()) {
            throw new ParseException(NoteCommand.MESSAGE_NOT_ADDED_NOTE);
        }

        String title = argMultimap.getValue(PREFIX_NOTE_TITLE).orElse("");
        String content = argMultimap.getValue(PREFIX_NOTE_CONTENT).orElse("");

        return new NoteCommand(index, new Note(title, content));
    }
}
