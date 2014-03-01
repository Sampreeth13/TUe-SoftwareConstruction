// package cp.command; // <<<<< Comment this line out when submitting to peach!

import java.util.Stack;

/**
 * Facilities for an undo-redo mechanism, on the basis of commands.
 *
 * @author Tom Verhoeff (TU/e, Eindhoven University of Technology)
 * <!--//# BEGIN TODO Name, id, and date-->
 * <p>
 * <font color="red"><b>Ilya Trofimov, Nov 28, 272</b></font></p>
 * <!--//# END TODO-->
 */
// -----8<----- cut line -----8<-----
public class UndoRedo {

//# BEGIN TODO Representation in terms of instance variables
    private Stack<Command> undoStack = new Stack<Command>();
    private Stack<Command> redoStack = new Stack<Command>();
//# END TODO

    /**
     * Returns whether an {@code undo} is possible.
     *
     * @return whether {@code undo} is possible
     */
    public boolean canUndo() {
//# BEGIN TODO Implementation of canUndo
        return !undoStack.isEmpty();
//# END TODO
    }

    /**
     * Returns whether a {@code redo} is possible.
     *
     * @return {@code redo().pre}
     */
    public boolean canRedo() {
//# BEGIN TODO Implementation of canRedo
        return !redoStack.isEmpty();
//# END TODO
    }

    /**
     * Returns command most recently done.
     *
     * @return command at top of undo stack
     * @throw IllegalStateException if precondition failed
     * @pre {@code canUndo()}
     */
    public Command lastDone() throws IllegalStateException {
//# BEGIN TODO Implementation of lastDone
        if (!canUndo()) {
            throw new IllegalStateException("lastDone: undo stack is empty");
        }

        return undoStack.lastElement();
//# END TODO
    }

    /**
     * Returns command most recently undone.
     *
     * @return command at top of undo stack
     * @throw IllegalStateException if precondition failed
     * @pre {@code canRedo()}
     */
    public Command lastUndone() throws IllegalStateException {
//# BEGIN TODO Implementation of lastUndone
        if (!canRedo()) {
            throw new IllegalStateException("Empty undo stack");
        }

        return redoStack.lastElement();
//# END TODO
    }

    /**
     * Clears all undo-redo history.
     */
    public void clear() {
//# BEGIN TODO Implementation of clear
        undoStack.clear();
        redoStack.clear();
//# END TODO
    }

    /**
     * Adds given command to the do-history. If the command was not yet
     * executed, it is first executed.
     *
     * @param command the command to incorporate
     */
    public void did(final Command command) throws IllegalStateException {
//# BEGIN TODO Implementation of did
        if (!command.isExecuted()) {
            command.execute();
        }

        redoStack.clear();

        undoStack.push(command);
//# END TODO
    }

    /**
     * Undo the most recently done command, optionally allowing it to be redone.
     *
     * @param redoable whether to allow redo
     * @throws IllegalStateException if precondition violated
     * @pre {@code canUndo()}
     */
    public void undo(final boolean redoable) {
//# BEGIN TODO Implementation of undo
        if (!canUndo()) {
            throw new IllegalStateException("Empty undo stack");
        }

        Command command = undoStack.pop();

        if (redoable) {
            redoStack.push(command);
        }

        command.undo();
//# END TODO
    }

    /**
     * Redo the most recently undone command.
     *
     * @throws IllegalStateException if precondition violated
     * @pre {@code canRedo()}
     */
    public void redo() {
//# BEGIN TODO Implementation of redo
        if (!canRedo()) {
            throw new IllegalStateException("Empty redo stack");
        }

        Command command = redoStack.pop();

        undoStack.push(command);

        command.execute();
//# END TODO
    }

    /**
     * Undo all done commands.
     */
    public void undoAll() {
//# BEGIN TODO Implementation of undoAll
        while (canUndo()) {
            undo(true);
        }
//# END TODO
    }

    /**
     * Redo all undone commands.
     */
    public void redoAll() {
//# BEGIN TODO Implementation of redoAll
        while (canRedo()) {
            redo();
        }
//# END TODO
    }

}
