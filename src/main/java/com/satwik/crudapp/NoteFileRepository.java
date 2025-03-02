package com.satwik.crudapp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteFileRepository extends JpaRepository<NoteFile, Long> {

}
