package com.project.library.entities;

public class Book {
    long id;
    String title;
    String author_name;
    int publish_year;

    public Book(long id, String title, String author_name, int publish_year) {
        this.id = id;
        this.title = title;
        this.author_name = author_name;
        this.publish_year = publish_year;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public int getPublish_year() {
        return publish_year;
    }

    public void setPublish_year(int publish_year) {
        this.publish_year = publish_year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author_name='" + author_name + '\'' +
                ", publish_year=" + publish_year +
                '}';
    }
}
