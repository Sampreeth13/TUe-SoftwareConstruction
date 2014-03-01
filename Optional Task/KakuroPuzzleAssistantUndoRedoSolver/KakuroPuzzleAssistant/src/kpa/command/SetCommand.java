package kpa.command;

import kpa.gui.PuzzlePanel;

/**
 * @author Ilya Trofimov (272 Group)
 */
public class SetCommand extends Command {
    
    /** The command's parameter */
    private int newState;
    
    /** Previous state of the receiver, for undo() */
    private int oldState;    
    
    
    public SetCommand(final PuzzlePanel receiver, final int newState) {
        super(receiver);
        this.newState = newState;
    }
    
    @Override
    public void execute() {
        super.execute();
        oldState = receiver.getSelected().getState();
        receiver.getSelected().setState(newState);
    }
    
    @Override
    public void undo() {
        super.undo();
        receiver.getSelected().setState(oldState);
    }

}
