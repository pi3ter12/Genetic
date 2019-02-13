package pl.wiacekp.genetic.learn;

import pl.wiacekp.exceptions.NullObjectException;
import pl.wiacekp.exceptions.TooMuchCoordinatesException;
import pl.wiacekp.genetic.dto.CoordinateDto;
import pl.wiacekp.genetic.dto.InputDto;
import pl.wiacekp.genetic.enums.InputEnum;
import pl.wiacekp.genetic.util.GenericUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
    private static final int DEFAULT_WIDTH = 20;
    private static final int DEFAULT_HEIGHT = 30;
    private static final int DEFAULT_BOTTLES = 10;

    private int width;
    private int height;
    private int bottles;

    private Boolean[][] table; // [x][y]

    public Board() {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.bottles = DEFAULT_BOTTLES;
        defineTable();
    }

    public Board(int width, int height, int bottles) {
        this.width = width;
        this.height = height;
        this.bottles = bottles;
        defineTable();
    }

    public InputDto getValue(int x, int y) {
        InputDto result = new InputDto();
        result.setCurrent(getValueByCoordinates(x, y));
        result.setUp(getValueByCoordinates(x, y - 1));
        result.setDown(getValueByCoordinates(x, y + 1));
        result.setLeft(getValueByCoordinates(x - 1, y));
        result.setRight(getValueByCoordinates(x + 1, y));

        return result;
    }

    private InputEnum getValueByCoordinates(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return InputEnum.BORDER;
        }
        return (table[x][y]) ? InputEnum.BOTTLE : InputEnum.EMPTY;
    }

    private void defineTable() {
        table = new Boolean[width][height];
        try {
            List<CoordinateDto> coordinates = getDistinctCoordinates(bottles);
            assignBottles(coordinates);
        } catch (TooMuchCoordinatesException | NullObjectException e) {
            e.printStackTrace();
        }
    }

    private List<CoordinateDto> getDistinctCoordinates(int count) throws TooMuchCoordinatesException {
        Set<CoordinateDto> coordinateDtoSet = new HashSet<>();
        if (count > width * height) {
            throw new TooMuchCoordinatesException();
        }
        while (coordinateDtoSet.size() < count) {
            coordinateDtoSet.add(CoordinateDto.builder()
                    .x(GenericUtils.getRandomInRange(0, width - 1))
                    .y(GenericUtils.getRandomInRange(0, height - 1))
                    .build());
        }
        return new ArrayList<>(coordinateDtoSet);
    }

    private void assignBottles(List<CoordinateDto> coordinates) throws NullObjectException {
        if (table == null) {
            throw new NullObjectException();
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                table[i][j] = false;
            }
        }
        for (int i = 0; i < coordinates.size(); i++) {
            table[coordinates.get(i).getX()][coordinates.get(i).getY()] = true;
        }
    }
}
