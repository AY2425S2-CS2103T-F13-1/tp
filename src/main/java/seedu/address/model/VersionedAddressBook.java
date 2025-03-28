package seedu.address.model;

import static seedu.address.logic.Messages.MESSAGE_REDO_FAILURE;
import static seedu.address.logic.Messages.MESSAGE_UNDO_FAILURE;

import java.util.LinkedList;

import seedu.address.model.exceptions.RedoException;
import seedu.address.model.exceptions.UndoException;

/**
 * Class to store past and future snapshots of patientlist
 */
public class VersionedAddressBook {
    private final LinkedList<ReadOnlyAddressBook> list = new LinkedList<>();
    private int pointer = -1;

    /**
     * Gets future snapshots that have been undone.
     *
     * @return ReadOnlyAddressBook.
     * @throws RedoException when the pointer has exceeded the size of linkedlist.
     */
    public ReadOnlyAddressBook getFutureState() throws RedoException {
        if (!canRedo()) {
            throw new RedoException(MESSAGE_REDO_FAILURE);
        }
        pointer++;
        return list.get(pointer);
    }

    /**
     * Checks whether the pointer has exceeded the linkedlist when doing redo.
     */
    public boolean canRedo() {
        return pointer < list.size() - 1;
    }

    /**
     * Gets history snapshot.
     *
     * @return ReadOnlyAddressBook.
     * @throws UndoException when the pointer has no past state to point to..
     */
    public ReadOnlyAddressBook getOldState() throws UndoException {
        if (!canUndo()) {
            throw new UndoException(MESSAGE_UNDO_FAILURE);
        }
        pointer--;
        return list.get(pointer);
    }

    /**
     * Checks whether the pointer is at the right start.
     */
    public boolean canUndo() {
        return pointer > 0;
    }

    /**
     * Saves the state into the list.
     */
    public void saveState(ReadOnlyAddressBook state) {
        assert state != null;

        if (canRedo()) {
            list.subList(pointer + 1, list.size()).clear();
        }
        list.add(state);
        pointer++;
    }
}
