package pl.wiacekp.command;

import org.reflections.Reflections;

import java.util.*;
import java.util.stream.Collectors;

public class ArgInterpreter {
    public void run(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("args is empty");
        }

        List<String> argList = getArgs(args);
        Optional<StartClass> found = searchStartClass(args[0]);

        if (found.isPresent()) {
            try {
                found.get().clazz.getDeclaredConstructor().newInstance().run(argList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Optional<StartClass> searchStartClass(String search) {
        return getStartClasses().stream()
                .filter(c -> c.annotation.arg().equalsIgnoreCase(search))
                .findFirst();
    }

    private List<StartClass> getStartClasses() {
        Reflections reflections = new Reflections("pl.wiacekp");

        List<StartClass> classList = new ArrayList<>();

        Iterator<Class<? extends StartProcess>> iterator = reflections.getSubTypesOf(StartProcess.class).iterator();
        while (iterator.hasNext()) {
            StartClass startClass = new StartClass();
            startClass.clazz = iterator.next();
            startClass.annotation = startClass.clazz.getAnnotation(StartProcessFor.class);
            classList.add(startClass);
        }

        return classList.stream().filter(c -> {
            if (c.annotation == null) {
                System.out.println("Not found StartProcessFor annotation for class " + c.clazz.getName());
                return false;
            }
            return true;
        }).collect(Collectors.toList());
    }

    private List<String> getArgs(String[] args) {
        if (args.length < 2) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>(Arrays.asList(args));
        result.remove(0);
        return result;
    }

    public class StartClass {
        Class<? extends StartProcess> clazz;
        StartProcessFor annotation;
    }
}
