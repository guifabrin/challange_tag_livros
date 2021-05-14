package com.example.guilherme.taglivrosapp.GoodReadClasses;

/**
 * Created by Guilherme on 11/09/2017.
 */

public class LivroGoodRead {
    private Integer id;
    private String isbn;
    private String isbn13;
    private Integer ratingsCount;
    private Integer reviewsCount;
    private Integer textReviewsCount;
    private Integer workRatingsCount;
    private Integer workReviewsCount;
    private Integer workTextReviewsCount;
    private double averageRating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public Integer getTextReviewsCount() {
        return textReviewsCount;
    }

    public void setTextReviewsCount(Integer textReviewsCount) {
        this.textReviewsCount = textReviewsCount;
    }

    public Integer getWorkRatingsCount() {
        return workRatingsCount;
    }

    public void setWorkRatingsCount(Integer workRatingsCount) {
        this.workRatingsCount = workRatingsCount;
    }

    public Integer getWorkReviewsCount() {
        return workReviewsCount;
    }

    public void setWorkReviewsCount(Integer workReviewsCount) {
        this.workReviewsCount = workReviewsCount;
    }

    public Integer getWorkTextReviewsCount() {
        return workTextReviewsCount;
    }

    public void setWorkTextReviewsCount(Integer workTextReviewsCount) {
        this.workTextReviewsCount = workTextReviewsCount;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
