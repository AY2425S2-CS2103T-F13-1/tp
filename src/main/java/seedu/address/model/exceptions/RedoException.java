package seedu.address.model.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Exception when fail to execute redo.
 */
public class RedoException extends CommandException {
    public RedoException(String message) {
        super(message);
    }
}
