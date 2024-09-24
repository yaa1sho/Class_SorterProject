package main.java.org.example.classes;

import main.java.org.example.input.IBuilder;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Car {
    private float power;
    private String model;
    private int year;

    public float getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    private Car() {
    }

    private Car(Builder builder) {
        power = builder.power;
        model = builder.model;
        year = builder.year;
    }

    public static class Builder implements IBuilder<Car> {
        private float power;
        private String model;
        private int year;

        public Builder() {
        }

        public Builder(String model) {
        }

        public Builder setPower(float power) {
            this.power = power;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Car build() {
            return new Car(this);
        }

        @Override
        public String getDataFileRequirements() {
            return """
                      мощность, модель, год выпуска
                      мощность, модель, год выпуска
                      ...
                    """;
        }

        @Override
        public Car buildFromString(String line) {
            String[] values = line.split(",");
            float power = -1;
            try {
                power = Float.parseFloat(values[0].trim());
            } catch (NumberFormatException ignored) {
            }
            setModel(values[1].trim());
            int year = -1;
            try {
                year = Integer.parseInt(values[2].trim());
            } catch (NumberFormatException ignored) {
            }
            if (power > 0 && year > 0) {
                setPower(power);
                setYear(year);
                return new Car(this);
            }
            return null;
        }

        @Override
        public Car buildFromConsole() {
            Scanner scanner = new Scanner(System.in);
            String input;
            float power = -1;
            do {
                System.out.println("Введите мощность автомобиля или exit:");
                input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    return null;
                }
                try {
                    power = Float.parseFloat(input.trim());
                } catch (NumberFormatException exception) {
                    System.out.println("Мощность должна быть положительным числом!");
                }
            } while (power < 0);
            setPower(power);
            do {
                System.out.println("Введите модель автомобиля или exit:");
                input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    return null;
                }
            } while (input.isEmpty() || input.isBlank());
            setModel(input);
            int year = -1;
            do {
                System.out.println("Введите год выпуска автомобиля или exit:");
                input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input)) {
                    return null;
                }
                try {
                    year = Integer.parseInt(input.trim());
                } catch (NumberFormatException exception) {
                    System.out.println("Год выпуска автомобиля должен быть целым положительным числом!");
                }
            } while (year < 1);
            setYear(year);
            return new Car(this);
        }

        @Override
        public Car buildRandomObj() {
            Random random = new Random();
            setPower(random.nextFloat(2027) + 1);
            List<String> models = List.of("Toyota Camry", "Honda Civic", "Ford Mustang", "BMW 3 Series", "Audi A4",
                    "Chevrolet Malibu", "Nissan Altima", "Mazda CX-5", "Subaru Outback", "Volkswagen Golf");
            setModel(models.get(random.nextInt(models.size())));
            setYear(random.nextInt(9999) + 1);
            return new Car(this);
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "power=" + power +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
