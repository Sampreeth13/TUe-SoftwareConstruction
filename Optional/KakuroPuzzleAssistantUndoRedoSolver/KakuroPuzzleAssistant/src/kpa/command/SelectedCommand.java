package kpa.command;

import kpa.gui.PuzzlePanel;
import kpa.model.KCell;

/**
 * @author Ilya Trofimov (272 Group)
 */
public class SelectedCommand extends Command {
    
    /** The command's parameter */
    private KCell newSelected;
    
    /** Previous state of the receiver, for undo() */
    private KCell oldSelected;
    
    public SelectedCommand(final PuzzlePanel receiver, final KCell newSel) {
        super(receiver);
        this.newSelected = newSel;
    }
    
    @Override
    public void execute() {
        super.execute();
        oldSelected = receiver.getSelected();
        receiver.setSelected(newSelected);
    }
    
    @Override
    public void undo() {
        super.undo();
        receiver.setSelected(oldSelected);
    }
    
}
