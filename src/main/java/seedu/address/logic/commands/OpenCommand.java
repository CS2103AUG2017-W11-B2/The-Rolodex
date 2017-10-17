package seedu.address.logic.commands;

import java.io.File;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.events.storage.OpenRolodexRequestEvent;
import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Opens an existing Rolodex in a different directory.
 */
public class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";
    public static final String COMMAND_WORD_ABBREV = "o"; //TODO: Add `cd` and `ls` abbreviations

    public static final String MESSAGE_OPENING = "Opened file: %1$s";
    public static final String MESSAGE_NOT_EXIST = "Unable to find `%1$s`. "
            + "Use the `new` command for creating a new file.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":"
            + "Reloads the application using the rolodex supplied at the given file path. \n"
            + "Parameters: [FILEPATH] \n"
            + "Example: open C:/CS2103/MyRolodex.rldx";

    public final String filePath;

    public OpenCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute() throws CommandException {
        if (new File(filePath).exists()) {
            EventsCenter.getInstance().post(new OpenRolodexRequestEvent(filePath));
            return new CommandResult(String.format(MESSAGE_OPENING, filePath));
        } else {
            return new CommandResult(String.format(MESSAGE_NOT_EXIST, filePath));
        }
    }
}
