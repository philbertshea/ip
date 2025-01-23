package phil;

/**
 * Enums representing Command Types, to be used with phil.InvalidArgumentException
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
