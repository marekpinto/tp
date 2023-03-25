package seedu.pettracker;

import seedu.pettracker.commands.Command;
import seedu.pettracker.parser.CommandParser;
import seedu.pettracker.storage.Storage;
import seedu.pettracker.ui.Ui;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Entry point for the Pet Tracker Application
 * Initializes required classes of the application and begins
 */

public class Main {
    private static final Logger logger = Logger.getLogger("MainLogger");
    private final Ui ui;
    private final CommandParser commandParser;

    private final Storage storage;

    private final String PET_STORAGE_FILE_PATH = "./output/petoutput.txt";
    private final String TASK_STORAGE_FILE_PATH = "./output/taskoutput.txt";


    /**
     * Creates the Main class by initializing the other classes
     */
    public Main() {
        ui = new Ui();
        commandParser = new CommandParser();
        storage = new Storage(PET_STORAGE_FILE_PATH, TASK_STORAGE_FILE_PATH);
        storage.createPetFile(ui);
        storage.createTaskFile(ui);

    }

    /**
     * Runs the program by showing a welcome message
     * and proceed to run commands until Exit command is received
     */
    public void run() {
        ui.showWelcomeMessage();
        runCommandTillExit();
        ui.showEndingMessage();
        System.exit(0);
    }

    /**
     * Executes commands until isExit is changed to true
     */
    public void runCommandTillExit() {
        boolean isExit = false;
        while (!isExit) {
            String commandString = ui.getUserInput();
            Command command = commandParser.parseCommand(commandString);
            command.execute(ui, storage);
            isExit = command.isExit();
        }
        logger.log(Level.INFO, "End of processing commands");
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
