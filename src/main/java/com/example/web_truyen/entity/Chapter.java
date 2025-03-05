package com.example.web_truyen.entity;

import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "comic_id",referencedColumnName = "comic_id", nullable = false)
    private Comic comic;

    @Column(nullable = false,length = 255)
    private String title;

    @Column(name = "is_locked", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean isLocked = true;

    @Column(name = "price_xu", columnDefinition = "INT DEFAULT 0")
    private int priceXu = 0;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Chapter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public int getPriceXu() {
        return priceXu;
    }

    public void setPriceXu(int priceXu) {
        this.priceXu = priceXu;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
