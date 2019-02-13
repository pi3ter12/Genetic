package pl.wiacekp;

import pl.wiacekp.command.ArgInterpreter;

public class Main {
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            start("learn");
        } else {
            start(args);
        }
    }

    private static void start(String... args) {
        new ArgInterpreter().run(args);
    }
}
