package kpa.command;

import kpa.gui.PuzzlePanel;

/**
 * @author Ilya Trofimov (272 Group)
 */
public class Command {
    
    /** The receiving Counter */
    protected final PuzzlePanel receiver;
    
    /** Execution state */
    private boolean executed;

    public boolean isExecuted() {
        return executed;
    }
    
    /**
     * Constructs a command for a given receiver.
     * 
     * @pre {@code receiver != null}
     */
    public Command(final PuzzlePanel receiver) throws NullPointerException {
        if (receiver == null) {
            throw new NullPointerException("Command(PuzzlePanel).pre violated: "
                    + "receiver == null");
        }
        this.receiver = receiver;
        this.executed = false;
    }

    /**
     * Executes the command.
     * A concrete command will override this method.
     * 
     * @throws IllegalStateException  if {@code executed}
     * @pre {@code ! executed && }
     *   precondition of the command holds in the receiver
     * @post {@code executed}
     */
    public void execute() throws IllegalStateException {
        if (executed) {
            throw new IllegalStateException("Command.execute().pre violated: "
                    + "command was already executed");
        }
        executed = true;
    }
    
    /**
     * Undoes the command.
     * A concrete command will override this method.
     *
     * @pre {@code executed && }
     *   precondition of the undo holds in the receiver
     * @post {@code ! executed}
     */
    public void undo() throws IllegalStateException {
        if (!executed) {
            throw new IllegalStateException("Command.undo().pre violated: "
                    + "command was not yet executed");
        }
        executed = false;
    }
    
}
