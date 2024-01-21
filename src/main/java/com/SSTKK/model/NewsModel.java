package com.SSTKK.model;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

@Entity
@Table(name = "news")
public class NewsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column (length = 500)
    private String content;
    @Column
    private String creator;
    @ManyToOne
    UsersModel autor;
    @Lob
    @Column(name = "pdf_content", length = 1000)
    private byte[] pdfContent;

    public UsersModel getAutor() {
        return autor;
    }

    public void setAutor(UsersModel autor) {
        this.autor = autor;
    }



    @Transient
    private MultipartFile pdfFile;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public NewsModel(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte[] getPdfContent() {
        return pdfContent;
    }

    public void setPdfContent(byte[] pdfContent) {
        this.pdfContent = pdfContent;
    }

    public MultipartFile getPdfFile() {
        return pdfFile;
    }

    public void setPdfFile(MultipartFile pdfFile) {
        this.pdfFile = pdfFile;
    }

}
