package com.satwik.crudapp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NoteFileService {

    private final NoteFileRepository noteFileRepo;

    public NoteFileService(NoteFileRepository noteFileRepository) {
        this.noteFileRepo = noteFileRepository;
    }

    public List<NoteFile> getAllNoteFiles() {
        return noteFileRepo.findAll();
    }

    public NoteFile getNoteFileById(Long id) {
        return noteFileRepo.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Note with ID " + id + " not found"));
    }

    public void deleteNoteFile(Long id) {
        boolean exists = noteFileRepo.existsById(id);
        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Note with ID " + id + " not found");
        }
        noteFileRepo.deleteById(id);
    }

    public void insertNoteFile(NoteFile noteFile) {
        noteFileRepo.save(noteFile);  // Works for both insert & update
    }

    public NoteFile updateNoteFile(Long id, NoteFile newNoteFile) {
        return noteFileRepo.findById(id).map(noteFile -> {
            noteFile.setFilename(newNoteFile.getFilename());  // Example fields
            noteFile.setContent(newNoteFile.getContent());
            return noteFileRepo.save(noteFile);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Note with ID " + id + " not found"));
    }

}