package com.project.library.entities;

public class Book {
    long id;
    String title;
    String authorName;
    int publishYear;

    public Book(long id, String title, String authorName, int publishYear) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.publishYear = publishYear;
    }
    public Book(){

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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author_name='" + authorName + '\'' +
                ", publish_year=" + publishYear +
                '}';
    }
}
