package guru.springframework.converter;

import guru.springframework.command.NotesCommand;
import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    public static final Long ID = 1L;
    public static final String NOTES = "notes";
    NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullParameter(){
        assertNull(converter.convert(null));
    }

    @Test
    public void assertEmptyObject(){
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        //given
        NotesCommand cmd = new NotesCommand();
        cmd.setId(ID);
        cmd.setRecipeNotes(NOTES);


        //when
        Notes notes = converter.convert(cmd);

        //then
        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(NOTES, notes.getRecipeNotes());

    }
}