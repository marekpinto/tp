package seedu.pettracker.parser;

import seedu.pettracker.exceptions.EmptyArgException;
import seedu.pettracker.exceptions.IllegalArgException;
import seedu.pettracker.commands.UnMarkTaskCommand;

public class UnMarkTaskParser implements ArgParser<UnMarkTaskCommand>{
    final String EMPTY_ARG_MESSAGE = "This command requires arguments.";
    final String INVALID_ARG_FORMAT_MESSAGE = "Invalid argument format. Please enter the arguments in the " +
            "following format: TASK_NUMBER.";
    final String INVALID_TASK_NUMBER_MESSAGE = "Invalid task number. Please enter a valid task number.";
    @Override
    public UnMarkTaskCommand parse(String commandArgs) throws IllegalArgException {
        if (commandArgs.isEmpty()) {
            throw new EmptyArgException(EMPTY_ARG_MESSAGE);
        }
        try {
            int taskNum;
            try {
                taskNum = Integer.parseInt(commandArgs);
            } catch (NumberFormatException e) {
                throw new IllegalArgException(INVALID_TASK_NUMBER_MESSAGE);
            }
            return new UnMarkTaskCommand(taskNum);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgException(INVALID_ARG_FORMAT_MESSAGE);
        }
    }
}
