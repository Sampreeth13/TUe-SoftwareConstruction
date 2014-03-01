package kpa.command; 

import java.util.Stack;

/**
 * Facilities for an undo-redo mechanism, on the basis of commands.
 *
 * @author Ilya Trofimov (272 Group)
 */
public class UndoRedo {

    private Stack<Command> undoStack = new Stack<Command>();
    private Stack<Command> redoStack = new Stack<Command>();

    /**
     * Returns whether an {@code undo} is possible.
     *
     * @return whether {@code undo} is possible
     */
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    /**
     * Returns whether a {@code redo} is possible.
     *
     * @return {@code redo().pre}
     */
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    /**
     * Returns command most recently done.
     *
     * @return command at top of undo stack
     * @throw IllegalStateException if precondition failed
     * @pre {@code canUndo()}
     */
    public Command lastDone() throws IllegalStateException {
        if (!canUndo()) {
            throw new IllegalStateException("Empty undo stack");
        }

        return undoStack.lastElement();
    }

    /**
     * Returns command most recently undone.
     *
     * @return command at top of undo stack
     * @throw IllegalStateException if precondition failed
     * @pre {@code canRedo()}
     */
    public Command lastUndone() throws IllegalStateException {
        if (!canRedo()) {
            throw new IllegalStateException("Empty redo stack");
        }

        return redoStack.lastElement();
    }

    /**
     * Clears all undo-redo history.
     */
    public void clear() {
        undoStack.clear();
        redoStack.clear();
    }

    /**
     * Adds given command to the do-history. If the command was not yet
     * executed, it is first executed.
     *
     * @param command the command to incorporate
     */
    public void did(final Command command) throws IllegalStateException {
        if (!command.isExecuted()) {
            command.execute();
        }

        redoStack.clear();

        undoStack.push(command);
    }

    /**
     * Undo the most recently done command, optionally allowing it to be redone.
     *
     * @param redoable whether to allow redo
     * @throws IllegalStateException if precondition violated
     * @pre {@code canUndo()}
     */
    public void undo(final boolean redoable) {
        if (!canUndo()) {
            throw new IllegalStateException("Empty undo stack");
        }

        Command command = undoStack.pop();

        if (redoable) {
            redoStack.push(command);
        }

        command.undo();
    }

    /**
     * Redo the most recently undone command.
     *
     * @throws IllegalStateException if precondition violated
     * @pre {@code canRedo()}
     */
    public void redo() {
        if (!canRedo()) {
            throw new IllegalStateException("Empty redo stack");
        }

        Command command = redoStack.pop();

        undoStack.push(command);

        command.execute();
    }

    /**
     * Undo all done commands.
     */
    public void undoAll() {
        while (canUndo()) {
            undo(true);
        }
    }

    /**
     * Redo all undone commands.
     */
    public void redoAll() {
        while (canRedo()) {
            redo();
        }
    }

}