package mamans.maman02.src.q1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <h3> PolynomMain </h3>
 *
 * <p>
 *     main terminal user interface (tui) that allowed to users to perform polynomial operations
 *     supported by our Polynom class object
 * </p>
 * <br>
 * <p> Note: HTML tags helps to improve comments readability in editors like IntelliJ that support it </p>
 *
 * @maman   02
 * @question    1
 * @author  Omer Shraibshtein (205984271)
 * @email   omershreib@gmail.com
 * @since   2026-05-14
 * */


public class PolynomMain extends PolynomTUIBackend implements PolynomHelpText {

    private static final String[] COMMANDS = {"help", "exit", "set", "show", "derive", "add", "sub", "equals?"};

    private static boolean exitTUI = false;

    public static void setExitTUI(boolean value) {
        exitTUI = value;
    }

    private static void parseUserInput(String userInput) throws CoefsAndPowersLengthMismatchException {

        userInput = userInput.trim();

        // case of ENTER press
        if (userInput.isEmpty()) {
            return;
        }

        String[] tokens = userInput.split("\\s+");
        String cmd = tokens[0];
        AtomicBoolean isSupportedCommand = new AtomicBoolean(false);

        Arrays.stream(COMMANDS).forEach(command -> {
            if (command.equals(cmd)) { isSupportedCommand.set(true); }
        });

        if (!isSupportedCommand.get()) {
            System.out.println("unrecognized command: " + cmd);
        }

        if (cmd.equals("help")) {
            PolynomHelpText.printHelpText();
        }

        if (cmd.equals("exit")) {
            setExitTUI(true);
        }

        if (cmd.equals("set")) {
            set(tokens);
        }

        if (cmd.equals("show")) {
            show(tokens);
        }

        if (cmd.equals("derive")) {
            derive(tokens);
        }

        if (cmd.equals("add")) {
            add();
        }

        if (cmd.equals("sub")) {
            sub();
        }

        if (cmd.equals("equals?")) {
            equals();
        }

    }

    public static void main(String[] args) throws CoefsAndPowersLengthMismatchException {

        System.out.println("Maman02 Question 1: Polynom Terminal User Interface\n\nEnter command (type 'help' to display commands reference)");
        Scanner scanner = new Scanner(System.in);

        do parseUserInput(scanner.nextLine());
        while (!exitTUI);

    }

}
