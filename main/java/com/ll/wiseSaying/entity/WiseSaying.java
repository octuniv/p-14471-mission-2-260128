package com.ll.wiseSaying.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WiseSaying {
    private int id;
    private String wise;
    private String author;
    private boolean deleted;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private static int nextId = 1;
    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 H시 mm분 ss초");

    public WiseSaying(String wise, String author) {
        this.id = nextId;
        this.wise = wise;
        this.author = author;
        this.deleted = false;
        this.createDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();
        nextId++;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWise(String wise) {
        this.wise = wise;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDeleted() {
        this.deleted = true;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public static void undoNextId() { nextId--; }

    public static void setClear() { nextId = 1;}

    public int getId() {
        return this.id;
    }

    public String getWise() {
        return this.wise;
    }

    public String getAuthor() {
        return this.author;
    }

    public boolean getDeleted() {
        return this.deleted;
    }

    public static int getNextId() {
        return nextId;
    }

    public LocalDateTime getCreateDate() { return this.createDate; }

    public LocalDateTime getModifyDate() { return this.modifyDate; }

    public String toString() {
        if (this.deleted) {
            return this.id + "번 명언은 존재하지 않습니다.";
        }
        return this.id +
                " / " +
                this.author +
                " / " +
                this.wise +
                " / " +
                this.getCreateDate().format(formatter) +
                " / " +
                this.getModifyDate().format(formatter);
    }
}
