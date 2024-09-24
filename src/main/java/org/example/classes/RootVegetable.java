package main.java.org.example.classes;

import main.java.org.example.input.IBuilder;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

//immutable class
public final class RootVegetable {
    private final String type;
    private final String color;
    private final double weight;

    public double getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    private RootVegetable(Builder builder) {
        this.type = builder.type;
        this.color = builder.color;
        this.weight = builder.weight;
    }

    public static class Builder implements IBuilder<RootVegetable> {
        private String type;
        private String color;
        private double weight;

        public Builder() {
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setWeight(double weight) {
            this.weight = weight;
            return this;
        }

        public RootVegetable build() {
            return new RootVegetable(this);
        }

        @Override
        public String getDataFileRequirements() {
            return """
                      тип, цвет, вес
                      тип, цвет, вес
                      ...
                    """;
        }

        @Override
        public RootVegetable buildFromString(String line) {
            String[] values = line.split(",");
            setType(values[0].trim());
            setColor(values[1].trim());
            double weight = -1;
            try {
                weight = Double.parseDouble(values[2].trim());
            } catch (NumberFormatException ignored) {
            }
            if (weight > 0) {
                setWeight(weight);
                return new RootVegetable(this);
            }
            return null;
        }

        @Override
        public RootVegetable buildFromConsole() {
            Scanner scanner = new Scanner(System.in);
            String input;
            do {
                System.out.println("Введите тип корнеплода или exit:");
                input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    return null;
                }
            } while (input.isEmpty() || input.isBlank());
            setType(input);
            do {
                System.out.println("Введите цвет корнеплода или exit:");
                input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    return null;
                }
            } while (input.isEmpty() || input.isBlank());
            setColor(input);
            double weight = -1;
            do {
                System.out.println("Введите вес корнеплода или exit:");
                input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    return null;
                }
                try {
                    weight = Double.parseDouble(input.trim());
                } catch (NumberFormatException exception) {
                    System.out.println("Вес должен быть числом!");
                }
            } while (weight < 1);
            setWeight(weight);
            return new RootVegetable(this);
        }

        @Override
        public RootVegetable buildRandomObj() {
            List<String> types = List.of("морковь", "свекла", "редька", "картофель", "репа", "дайкон", "пастернак",
                    "турнепс", "батат", "лавровый корень");
            List<String> colors = List.of("красный", "синий", "зеленый", "желтый", "фиолетовый", "оранжевый", "розовый",
                    "белый", "черный", "серый");
            Random random = new Random();
            setType(types.get(random.nextInt(types.size())));
            setColor(colors.get(random.nextInt(colors.size())));
            setWeight(random.nextDouble(11));
            return new RootVegetable(this);
        }
    }

    @Override
    public String toString() {
        return "RootVegetable{" +
                "type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}
