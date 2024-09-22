package main.java.org.example.classes;

import main.java.org.example.input.IBuilder;

public class RootVegetable {
    private String type;
    private String color;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    private RootVegetable(){}

    private RootVegetable(Builder builder){
        type = builder.type;
        color = builder.color;
        weight = builder.weight;
    }

    public static class Builder implements IBuilder<RootVegetable> {
        private String type;
        private String color;
        private int weight;;

        public Builder(){}

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public RootVegetable build(){
            return new RootVegetable(this);
        }

        @Override
        public RootVegetable buildFromString(String line) {
            //TODO реализовать метод
            return null;
        }

        @Override
        public RootVegetable buildFromConsole() {
            //TODO реализовать метод
            return null;
        }

        @Override
        public RootVegetable buildRandomObj() {
            //TODO реализовать метод
            return null;
        }
    }

}