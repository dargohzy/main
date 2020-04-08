package igrad.model.module;

import static igrad.logic.commands.CommandTestUtil.VALID_MODULE_CODE_PROGRAMMING_METHODOLOGY;
import static igrad.logic.commands.CommandTestUtil.VALID_TITLE_COMPUTER_ORGANISATION;
import static igrad.testutil.Assert.assertThrows;
import static igrad.testutil.TypicalModules.COMPUTER_ORGANISATION;
import static igrad.testutil.TypicalModules.PROGRAMMING_METHODOLOGY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import igrad.model.module.exceptions.DuplicateModuleException;
import igrad.model.module.exceptions.ModuleNotFoundException;
import igrad.testutil.ModuleBuilder;

public class UniqueModuleListTest {

    private final UniqueModuleList uniqueModuleList = new UniqueModuleList();
    private final Module CS1101S = PROGRAMMING_METHODOLOGY;
    private final Module CS2100 = COMPUTER_ORGANISATION;

    @Test
    public void contains_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.contains((Module) null));
    }

    @Test
    public void contains_nullModules_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.contains((List<Module>) null));
    }

    @Test
    public void contains_moduleNotInList_returnsFalse() {
        assertFalse(uniqueModuleList.contains(CS1101S));
    }

    @Test
    public void contains_moduleInList_returnsTrue() {
        uniqueModuleList.add(CS1101S);
        assertTrue(uniqueModuleList.contains(CS1101S));
    }

    @Test
    public void contains_moduleWithSameIdentityFieldsInList_returnsTrue() {
        uniqueModuleList.add(CS1101S);
        Module editedModule = new ModuleBuilder()
                .withModuleCode(VALID_MODULE_CODE_PROGRAMMING_METHODOLOGY)
                .build();
        assertTrue(uniqueModuleList.contains(editedModule));
    }

    @Test
    public void add_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.add((Module) null));
    }

    @Test
    public void add_duplicateModule_throwsDuplicateModuleException() {
        uniqueModuleList.add(CS1101S);
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.add(CS1101S));
    }

    @Test
    public void setModule_nullTargetModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList
            .setModule(null, CS1101S));
    }

    @Test
    public void setModule_nullEditedModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList
                .setModule(CS1101S, null));
    }

    @Test
    public void setModule_targetModuleNotInList_throwsPersonNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList
            .setModule(CS1101S, CS1101S));
    }

    @Test
    public void setModule_editedModuleIsSameModule_success() {
        uniqueModuleList.add(CS1101S);
        uniqueModuleList.setModule(CS1101S, CS1101S);

        UniqueModuleList differentUniqueModuleList = new UniqueModuleList();
        differentUniqueModuleList.add(CS1101S);

        assertEquals(differentUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasSameIdentity_success() {
        uniqueModuleList.add(CS1101S);
        Module editedModule = new ModuleBuilder(CS1101S)
                .withModuleCode(VALID_MODULE_CODE_PROGRAMMING_METHODOLOGY)
               .build();
        uniqueModuleList.setModule(CS1101S, editedModule);
        UniqueModuleList differentUniqueModuleList = new UniqueModuleList();
        differentUniqueModuleList.add(editedModule);
        assertEquals(differentUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModule_editedModuleHasDifferentIdentity_success() {
        uniqueModuleList.add(CS1101S);
        uniqueModuleList.setModule(CS1101S, CS2100);
        UniqueModuleList differentUniqueModuleList = new UniqueModuleList();
        differentUniqueModuleList.add(CS2100);
        assertEquals(differentUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueModuleList.add(CS1101S);
        Module newModule = new ModuleBuilder()
                .withTitle(VALID_TITLE_COMPUTER_ORGANISATION)
                .withModuleCode(VALID_MODULE_CODE_PROGRAMMING_METHODOLOGY)
                .build();
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.add(newModule));
    }

    @Test
    public void remove_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.remove((Module) null));
    }

    @Test
    public void remove_moduleDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.remove(CS1101S));
    }

    @Test
    public void remove_existingModule_removesModule() {
        uniqueModuleList.add(CS1101S);
        uniqueModuleList.remove(CS1101S);
        UniqueModuleList differentUniqueModuleList = new UniqueModuleList();
        assertEquals(differentUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullUniqueModuleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList
            .setModules((UniqueModuleList) null));
        assertThrows(NullPointerException.class, () -> uniqueModuleList
            .setModules((List<Module>) null));
    }

    @Test
    public void setModules_uniqueModuleList_replacesOwnListWithProvidedUniqueModuleList() {
        uniqueModuleList.add(CS1101S);
        UniqueModuleList newUniqueModuleList = new UniqueModuleList();
        newUniqueModuleList.add(CS2100);
        uniqueModuleList.setModules(newUniqueModuleList);
        assertEquals(newUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueModuleList.setModules((List<Module>) null));
    }

    @Test
    public void setModules_list_replacesOwnListWithProvidedList() {
        uniqueModuleList.add(CS1101S);
        List<Module> moduleList = Collections.singletonList(CS2100);
        uniqueModuleList.setModules(moduleList);
        UniqueModuleList newUniqueModuleList = new UniqueModuleList();
        newUniqueModuleList.add(CS2100);
        assertEquals(newUniqueModuleList, uniqueModuleList);
    }

    @Test
    public void setModules_listWithDuplicateModules_throwsDuplicateModuleException() {
        List<Module> listWithDuplicateModules = Arrays.asList(CS1101S, CS1101S);
        assertThrows(DuplicateModuleException.class, ()
            -> uniqueModuleList.setModules(listWithDuplicateModules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueModuleList.asUnmodifiableObservableList().remove(0));
    }
}
