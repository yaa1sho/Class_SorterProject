package org.example.classes;

<<<<<<< HEAD
//immutable class
public final class RootVegetable {
    private final String type;
    private final String color;
    private final int weight;
=======
public class RootVegetable {
    private String type;
    private String color;
    private int weight;
>>>>>>> 087e127 (Добавил классы: автомобиль, книга, корнеплод. Реализовал для них паттерн builder. Также в main реализовал цикл и выход из него по требованию юзера.)

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

<<<<<<< HEAD
    private RootVegetable(Builder builder) {
        this.type = builder.type;
        this.color = builder.color;
        this.weight = builder.weight;
=======
    private RootVegetable(){}

    private RootVegetable(Builder builder){
        type = builder.type;
        color = builder.color;
        weight = builder.weight;
>>>>>>> 087e127 (Добавил классы: автомобиль, книга, корнеплод. Реализовал для них паттерн builder. Также в main реализовал цикл и выход из него по требованию юзера.)
    }

    public static class Builder {
        private String type;
        private String color;
<<<<<<< HEAD
        private int weight;

        public Builder() {}
=======
        private int weight;;

        public Builder(String model){}
>>>>>>> 087e127 (Добавил классы: автомобиль, книга, корнеплод. Реализовал для них паттерн builder. Также в main реализовал цикл и выход из него по требованию юзера.)

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

<<<<<<< HEAD
        public RootVegetable build() {
            return new RootVegetable(this);
        }
    }
=======
        public RootVegetable build(){
            return new RootVegetable(this);
        }
    }

>>>>>>> 087e127 (Добавил классы: автомобиль, книга, корнеплод. Реализовал для них паттерн builder. Также в main реализовал цикл и выход из него по требованию юзера.)
}