package com.satwik.crudapp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NoteFileService {

    private final NoteFileRepository noteFileRepo;
    private final AiService aiService;

    public NoteFileService(NoteFileRepository noteFileRepository,
                           AiService aiService) {
        this.noteFileRepo = noteFileRepository;
        this.aiService = aiService;
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
        try {
            String prompt = """
                Based on the note file content in: %s,
                provide a summary of under 50 words to replace it.
                The response should only contain the summary""".formatted(noteFile.getContent());

            String chatResponse;
            try {
                chatResponse = aiService.chat(prompt);
            } catch (Exception e) {
                System.out.println("AI service failed: " + e.getMessage());
                chatResponse = "Summary not available due to AI service error.";
            }

            noteFile.setSummary(chatResponse);
            noteFileRepo.save(noteFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error inserting note file: " + e.getMessage());
        }
    }

    public NoteFile updateNoteFile(Long id, NoteFile newNoteFile) {
        return noteFileRepo.findById(id).map(noteFile -> {
            noteFile.setFilename(newNoteFile.getFilename());
            noteFile.setContent(newNoteFile.getContent());
            return noteFileRepo.save(noteFile);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Note with ID " + id + " not found"));
    }

}