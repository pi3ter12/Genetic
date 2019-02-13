package pl.wiacekp.genetic.learn;

import pl.wiacekp.command.StartProcess;
import pl.wiacekp.command.StartProcessFor;

import java.util.List;

@StartProcessFor(arg = "learn")
public class Learn implements StartProcess {
    @Override
    public void run(List<String> args) {
        Board board = new Board();
    }
}
