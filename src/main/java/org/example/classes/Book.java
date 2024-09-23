package main.java.org.example.classes;

import main.java.org.example.input.IBuilder;

public class Book {
    private String name;
    private String author;
    private int pageCount;

    public int getPageCount() {
        return pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    private Book(){}

    private Book(Builder builder){
        name = builder.name;
        author = builder.author;
        pageCount = builder.pageCount;
    }

    public static class Builder implements IBuilder<Book> {
        private String name;
        private String author;
        private int pageCount;


        public Builder(){}

        public Builder(String model){}

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setPageCount(int pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Book build(){
            return new Book(this);
        }

        @Override
        public Book buildFromString(String line) {
            //TODO реализовать метод
            return null;
        }

        @Override
        public Book buildFromConsole() {
            //TODO реализовать метод
            return null;
        }

        @Override
        public Book buildRandomObj() {
            //TODO реализовать метод
            return null;
        }
    }

}
