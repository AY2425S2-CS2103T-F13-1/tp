package seedu.address.model.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Exception when fails to execute undo.
 */
public class UndoException extends CommandException {
    public UndoException(String message) {
        super(message);
    }
}
