package com.satwik.crudapp;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteFileService {

    private final NoteFileRepository noteFileRepo;

    public NoteFileService(NoteFileRepository noteFileRepository) {
        this.noteFileRepo = noteFileRepository;
    }

    public void insertNoteFile(NoteFile noteFile) {
        noteFileRepo.save(noteFile);
    }

    public List<NoteFile> getAllNoteFiles() {
        return noteFileRepo.findAll();
    }

    public NoteFile getNoteFilesbyId(Long id) {
        return noteFileRepo.findById(id).
                orElseThrow(() -> new IllegalStateException
                        (id+" not found"));
    }

    public void deleteNoteFile(Long id) {
        boolean exists = noteFileRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Note with "+ id + " not found");
        }
        noteFileRepo.deleteById(id);
    }
}