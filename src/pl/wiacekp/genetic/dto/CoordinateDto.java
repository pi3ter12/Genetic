package pl.wiacekp.genetic.dto;

import java.util.Objects;

public class CoordinateDto {
    private int x;
    private int y;

    public CoordinateDto() {
    }

    public CoordinateDto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static CoordinateBuilder builder() {
        return new CoordinateBuilder();
    }

    public static class CoordinateBuilder {
        private static CoordinateDto dto;

        public CoordinateBuilder() {
            dto = new CoordinateDto();
        }

        public CoordinateBuilder x(int x) {
            dto.setX(x);
            return this;
        }
        public CoordinateBuilder y(int y) {
            dto.setY(y);
            return this;
        }

        public CoordinateDto build() {
            return dto;
        }
    }

    @Override
    public int hashCode() {
        System.out.println("hash " + x + " " + y + "  " + Objects.hash(x, y));
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }
}
