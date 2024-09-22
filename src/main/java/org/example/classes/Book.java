package org.example.classes;

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

    public static class Builder {
        private String name;
        private String author;
        private int pageCount;

        public Builder(){}

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
    }

}
