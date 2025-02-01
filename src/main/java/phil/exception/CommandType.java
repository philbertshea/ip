package phil.exception;

/**
 * Enums representing Command Types, to be used with phil.exception.InvalidArgumentException
 *
 */
public enum CommandType {
    CREATE_TODO,
    CREATE_DEADLINE,
    CREATE_EVENT,
    MARK_DONE,
    MARK_UNDONE,
    DELETE_TASK
}
