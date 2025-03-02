package com.satwik.crudapp;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "note_files")
public class NoteFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier

    @Column(columnDefinition = "TEXT")
    private String filename; // Name of the note file

    @Column(columnDefinition = "TEXT")
    private String content; // The actual note content

    @Column(nullable = false) //needs to be false
    private boolean pinned; // To mark important notes

    @Column(nullable = false, //needs to be false
            updatable = false)
    @CreationTimestamp
    private Date createdAt; // Date when note was created

    @UpdateTimestamp
    private Date updatedAt; // Last modified date

    private String sourceUrl; // If the note is saved from a link

    /*Constructors*/

    public NoteFile() {
    }

    public NoteFile(Long id,
                    String filename,
                    String content,
                    boolean pinned,
                    Date createdAt,
                    Date updatedAt,
                    String sourceUrl) {
        this.id = id;
        this.filename = filename;
        this.content = content;
        this.pinned = pinned;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sourceUrl = sourceUrl;
    }

    /*Getters & Setters*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isPinned() {
        return pinned;
    }

    public void setPinned(boolean pinned) {
        this.pinned = pinned;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    /*Equals & HashCode*/

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NoteFile noteFile = (NoteFile) o;
        return pinned == noteFile.pinned && Objects.equals(id, noteFile.id) && Objects.equals(filename, noteFile.filename) && Objects.equals(content, noteFile.content) && Objects.equals(createdAt, noteFile.createdAt) && Objects.equals(updatedAt, noteFile.updatedAt) && Objects.equals(sourceUrl, noteFile.sourceUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filename, content, pinned, createdAt, updatedAt, sourceUrl);
    }
}

