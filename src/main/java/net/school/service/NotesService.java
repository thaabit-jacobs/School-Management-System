package net.school.service;

import net.school.doa.lesson.NotesDoa;
import net.school.doa.lesson.NotesDoaImpl;
import net.school.doa.student.StudentDoa;
import net.school.doa.student.StudentDoaImpl;
import net.school.model.Student;
import net.school.model.lessons.Notes;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NotesService {

    private final NotesDoa notesDoa = new NotesDoaImpl();
    private final static NotesService instance = new NotesService();

    private NotesService(){}

    public static synchronized NotesService getInstance(){
        return instance;
    }

    public synchronized boolean insertNotes(Notes notes){
        return notesDoa.insertNote(notes);
    }

    public synchronized Notes selectNotes(int id){
        return notesDoa.selectNote(id);
    }

    public synchronized List<Notes> selectAllNotes(){
        return notesDoa.selectAllNotes();
    }

    public synchronized boolean deleteNotes(int id){
        return notesDoa.deleteNote(id);
    }


    public int getUniqueId(){
        List<Notes> notes = selectAllNotes();

        Comparator<Notes> byId = (n1, n2) -> n1.getId() - n2.getId();
        Collections.sort(notes, byId);

        if(notes.size()==0)
            return 1;

        int uniqueId = notes.get(notes.size() - 1).getId() + 1;

        return uniqueId;
    }
}
