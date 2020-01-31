package Utilities;

public final class LoggerUtilities {
    public static String getMessage(String text, String param) {
        return String.format("[SERENITY_CUCUMBER_TEST] - %s %s", text, param);
    }
}
