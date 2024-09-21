package org.example.classes;

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

    private Car(){}

    private Car(Builder builder){
        power = builder.power;
        model = builder.model;
        year = builder.year;
    }

    public static class Builder {
        private float power;
        private String model;
        private int year;

        public Builder(String model){}

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

        public Car build(){
            return new Car(this);
        }
    }

}
