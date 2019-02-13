package pl.wiacekp.genetic.util;

import java.util.concurrent.ThreadLocalRandom;

public class GenericUtils {
    public static int getRandomInRange(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
