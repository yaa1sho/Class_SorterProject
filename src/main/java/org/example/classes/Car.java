package main.java.org.example.classes;

import main.java.org.example.input.IBuilder;

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
        public Car buildFromString(String line) {
            //TODO реализовать метод
            return null;
        }

        @Override
        public Car buildFromConsole() {
            //TODO реализовать метод
            return null;
        }

        @Override
        public Car buildRandomObj() {
            //TODO реализовать метод
            this.setModel("testModel").setPower(100).setYear(2024);
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
