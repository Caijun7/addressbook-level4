package seedu.address.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.WrongPasswordException;
import seedu.address.model.alias.Alias;
import seedu.address.model.alias.exceptions.AliasNotFoundException;
import seedu.address.model.alias.exceptions.DuplicateAliasException;
import seedu.address.model.building.Building;
import seedu.address.model.building.exceptions.BuildingNotFoundException;
import seedu.address.model.building.exceptions.CorruptedVenueInformationException;
import seedu.address.model.building.exceptions.NoRoomsInBuildingException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyAddressBook newData);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Deletes the given person. */
    void deletePerson(Person target) throws PersonNotFoundException;

    /** Adds the given person */
    void addPerson(Person person) throws DuplicatePersonException;

    //@@author jingyinno
    /** Adds the given alias */
    void addAlias(Alias alias) throws DuplicateAliasException;

//    /** Resets alias list in addressbook with new alias list */
    void resetData(ReadOnlyAddressBook newData, HashMap<String, String> newAliasList);

    /** Returns a hashmap of command mapped to alias */
    HashMap<String, String> getAliasList();
    //@@author

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     *
     * @throws DuplicatePersonException if updating the person's details causes the person to be equivalent to
     *      another existing person in the list.
     * @throws PersonNotFoundException if {@code target} could not be found in the list.
     */
    void updatePerson(Person target, Person editedPerson)
            throws DuplicatePersonException, PersonNotFoundException;

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    //@@author Caijun7-reused
    /**
     * Remove {@code tag} from all {@code person}s in the {@code AddressBook}.
     * @param tag
     */
    void deleteTag(Tag tag);
    //@@author

    //@@author Caijun7
    /**
     * Imports specified {@code AddressBook} from filepath to current {@code AddressBook}
     */
    void importAddressBook(String filepath, byte[] password) throws DataConversionException, IOException,
            WrongPasswordException;

    /**
     * Exports the current view of {@code AddressBook} to the filepath.
     * @param filepath
     */
    void exportAddressBook(String filepath, Password password) throws IOException, WrongPasswordException;
    //@@author

    //@@author yeggasd
    /**
     * Updates the password with the given password.
     */
    void updatePassword(byte[] password);
    //@@author

    //@@author jingyinno
    /**
     * Removes alias given the alias string to remove.
     */
    void removeAlias(String toRemove) throws AliasNotFoundException;
    //@@author

    //@@author Caijun7
    /** Returns rooms for the given building */
    ArrayList<ArrayList<String>> retrieveAllRoomsSchedule(Building building)
            throws BuildingNotFoundException, CorruptedVenueInformationException, NoRoomsInBuildingException;
    //@@author
}
