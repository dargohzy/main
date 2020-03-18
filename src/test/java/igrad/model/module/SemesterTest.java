package igrad.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SemesterTest {

    @Test
    public void constructor_invalidSemester_throwsIllegalArgumentException() {
        String invalidSemester = "Y1S3";
        assertThrows(IllegalArgumentException.class, () -> new Semester(invalidSemester));
    }

    @Test
    public void isValidSemester() {
        // invalid semester
        assertFalse(Semester.isValidSemester("Y1S3"));

        // invalid semester
        assertFalse(Semester.isValidSemester("11"));

        // valid semester
        assertTrue(Semester.isValidSemester("Y1S1"));
    }
}
