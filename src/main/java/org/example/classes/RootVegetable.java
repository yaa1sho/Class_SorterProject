package org.example.classes;

//immutable class
public final class RootVegetable {
    private final String type;
    private final String color;
    private final int weight;

    public int getWeight() {
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

    public static class Builder {
        private String type;
        private String color;
        private int weight;

        public Builder() {}

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

        public RootVegetable build() {
            return new RootVegetable(this);
        }
    }
}