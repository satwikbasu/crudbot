package com.satwik.crudapp;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/note-file")
public class NoteFileController {

    private final NoteFileService noteFileService;

    public NoteFileController(NoteFileService noteFileService) {
        this.noteFileService = noteFileService;
    }

    @GetMapping
    public List<NoteFile> getNoteFiles() {
        return noteFileService.getAllNoteFiles();
    }

    @GetMapping("{id}")
    public NoteFile getNoteFileById(@PathVariable Long id) {
        return noteFileService.getNoteFileById(id);
    }

    @PostMapping
    public void addNewNoteFile(@RequestBody NoteFile noteFile) {
        noteFileService.insertNoteFile(noteFile);
    }

    @DeleteMapping("{id}")
    public void deleteNoteFile(@PathVariable Long id){
        noteFileService.deleteNoteFile(id);
    }

    @PutMapping("{id}")
    public NoteFile updateNoteFile(@PathVariable Long id, @RequestBody NoteFile noteFile) {
        return noteFileService.updateNoteFile(id, noteFile);
    }

}
