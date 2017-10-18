package guitests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.HistoryCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.ui.util.KeyListenerUtil;

public class KeyListenerTest extends RolodexGuiTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void assertExceptionForMissingKeyMapping() throws Exception {
        HashMap<String, KeyCombination> keys = KeyListenerUtil.getKeys();
        thrown.expect(NullPointerException.class);
        keys.get("MISSING_MAP").getName();
    }

    @Test
    public void executeKeyEventForFocusOnCommandBox() {
        guiRobot.push(KeyCode.ENTER);
        assertTrue(getCommandBox().isFocused());
        guiRobot.push(KeyCode.A);
        assertTrue(getCommandBox().isFocused());

        guiRobot.push(KeyCode.ESCAPE);
        assertFalse(getCommandBox().isFocused());
    }

    @Test
    public void executeKeyEventForFocusOnPersonListPanel() {
        guiRobot.push(KeyCode.ESCAPE);
        assertTrue(getPersonListPanel().isFocused());

        // Check scrolling
        guiRobot.push(KeyCode.UP);
        assertTrue(getPersonListPanel().isFocused());
        guiRobot.push(KeyCode.DOWN);
        assertTrue(getPersonListPanel().isFocused());

        guiRobot.push(KeyCode.ENTER);
        assertFalse(getPersonListPanel().isFocused());
    }

    @Test
    public void executeKeyEventForUndoCommand() {
        KeyCodeCombination undoKeyCode = (KeyCodeCombination) KeyCombination.valueOf("Ctrl+Z");

        // Empty undo stack
        guiRobot.push(undoKeyCode);
        assertEquals(UndoCommand.MESSAGE_FAILURE, getResultDisplay().getText());

        getCommandBox().run("delete 1");
        guiRobot.push(undoKeyCode);
        assertEquals(UndoCommand.MESSAGE_SUCCESS, getResultDisplay().getText());
    }

    @Test
    public void executeKeyEventForRedoCommand() {
        KeyCodeCombination redoKeyCode = (KeyCodeCombination) KeyCombination.valueOf("Ctrl+Y");

        // Empty redo stack
        guiRobot.push(redoKeyCode);
        assertEquals(RedoCommand.MESSAGE_FAILURE, getResultDisplay().getText());

        getCommandBox().run("delete 1");
        getCommandBox().run("undo");

        guiRobot.push(redoKeyCode);
        assertEquals(RedoCommand.MESSAGE_SUCCESS, getResultDisplay().getText());
    }

    @Test
    public void executeKeyEventForClearCommand() {
        KeyCodeCombination clearKeyCode = (KeyCodeCombination) KeyCombination.valueOf("Ctrl+Shift+D");

        guiRobot.push(clearKeyCode);
        assertEquals(ClearCommand.MESSAGE_SUCCESS, getResultDisplay().getText());
    }

    @Test
    public void executeKeyEventForListCommand() {
        KeyCodeCombination listKeyCode = (KeyCodeCombination) KeyCombination.valueOf("Ctrl+L");

        guiRobot.push(listKeyCode);
        assertEquals(ListCommand.MESSAGE_SUCCESS, getResultDisplay().getText());
    }

    @Test
    public void executeKeyEventForHistoryCommand() {
        KeyCodeCombination viewHistoryKeyCode = (KeyCodeCombination) KeyCombination.valueOf("Ctrl+H");

        guiRobot.push(viewHistoryKeyCode);
        assertEquals(HistoryCommand.MESSAGE_NO_HISTORY, getResultDisplay().getText());

        String command1 = "clear";
        getCommandBox().run(command1);
        guiRobot.push(viewHistoryKeyCode);

        String expectedMessage = String.format(HistoryCommand.MESSAGE_SUCCESS,
                String.join("\n", command1, "history"));

        assertEquals(expectedMessage, getResultDisplay().getText());
    }
}
