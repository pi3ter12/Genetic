package pl.wiacekp;

import pl.wiacekp.command.StartProcessStore;

public class Main {
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            StartProcessStore.start("learn");
        } else {
            StartProcessStore.start(args);
        }
    }
}
