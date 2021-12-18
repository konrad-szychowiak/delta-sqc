package pl.put.poznan.sqc.domain.scenario;

import pl.put.poznan.sqc.domain.visitors.Visitor;

/**
 * Allow being visited by a Visitor.
 *
 * @see Visitor
 */
public interface Visitable {
    /**
     * Accept a visitor and pass the current instance's content to it.
     *
     * @param visitor Visitor to give information to.
     * @see Visitor
     */
    void accept(Visitor visitor);
}
