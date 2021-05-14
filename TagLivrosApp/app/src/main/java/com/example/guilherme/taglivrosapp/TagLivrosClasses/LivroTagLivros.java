package com.example.guilherme.taglivrosapp.TagLivrosClasses;

import com.example.guilherme.taglivrosapp.GoodReadClasses.LivroGoodRead;

import java.util.Date;

/**
 * Created by Guilherme on 11/09/2017.
 */

public class LivroTagLivros {
    private String objectId;
    private Integer pages;
    private Date createdAt;
    private Date updatedAt;
    private Autor author;
    private String name;
    private String isbn;
    private Autor curator;
    private Capa cover;
    private Edicao edition;
    private Boolean blocked;
    private Integer numRatings;
    private Integer totalRatings;
    private Boolean active;
    private LivroGoodRead livroGoodRead;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Autor getAuthor() {
        return author;
    }

    public void setAuthor(Autor author) {
        this.author = author;
        this.author.getLivroTagLivroses().add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Autor getCurator() {
        return curator;
    }

    public void setCurator(Autor curator) {
        this.curator = curator;
    }

    public Capa getCover() {
        return cover;
    }

    public void setCover(Capa cover) {
        this.cover = cover;
    }

    public Edicao getEdition() {
        return edition;
    }

    public void setEdition(Edicao edition) {
        this.edition = edition;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Integer getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }

    public Integer getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(Integer totalRatings) {
        this.totalRatings = totalRatings;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LivroGoodRead getLivroGoodRead() {
        return livroGoodRead;
    }

    public void setLivroGoodRead(LivroGoodRead livroGoodRead) {
        this.livroGoodRead = livroGoodRead;
    }
}
