package pl.wiacekp.command;

import org.reflections.Reflections;

import java.util.*;

public class StartProcessStore {
    private final static String PACKAGE_TO_SEARCH = "pl.wiacekp";

    private static HashMap<String, Class<? extends StartProcess>> startClassMap = new HashMap<>();

    static {
        addAll();
    }

    private static void addAll() {
        Reflections reflections = new Reflections(PACKAGE_TO_SEARCH);
        for (Class<? extends StartProcess> aClass : reflections.getSubTypesOf(StartProcess.class)) {
            add(aClass);
        }
    }

    private static void add(Class<? extends StartProcess> clazz) {
        StartProcessFor annotation = clazz.getAnnotation(StartProcessFor.class);
        if (annotation == null) {
            System.out.println("Not found StartProcessFor annotation for class " + clazz.getName());
            return;
        }
        startClassMap.put(annotation.arg(), clazz);
    }

    public static void start(String... args) {
        Optional.ofNullable(getStartClass(args)).ifPresent(c -> {
            try {
                c.getDeclaredConstructor().newInstance().run(getArgs(args));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static Class<? extends StartProcess> getStartClass(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("args is empty");
            return null;
        }
        Class<? extends StartProcess> result = startClassMap.get(args[0]);
        if (result == null) {
            System.out.println("Not found pl.wiacekp.command.StartProcessFor with arg " + args[0]);
        }
        return result;
    }

    private static List<String> getArgs(String[] args) {
        if (args.length < 2) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>(Arrays.asList(args));
        result.remove(0);
        return result;
    }
}
