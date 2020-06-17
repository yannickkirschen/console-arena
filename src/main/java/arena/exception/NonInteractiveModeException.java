package arena.exception;

/**
 * The {@link NonInteractiveModeException} indicates, that the game is not started in a regular terminal, but in an IDE for example. That means, a user input is
 * not possible.
 *
 * @author Yannick Kirschen
 * @since 1.0.0
 */
public class NonInteractiveModeException extends Exception {
    private static final long serialVersionUID = -1563920918847564918L;
}
