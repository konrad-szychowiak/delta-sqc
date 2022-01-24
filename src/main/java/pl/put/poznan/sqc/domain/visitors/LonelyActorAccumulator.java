package pl.put.poznan.sqc.domain.visitors;

import java.util.ArrayList;

/**
 * Creates a list of actors that do not appear in any steps.
 */
public class LonelyActorAccumulator extends ActorCount {

    /**
     * Get a list of actors that had 0 references in a scenario's steps.
     * @return A list of names of the unused ('lonely') actors.
     * @see ActorCount#getActorCounter()
     */
    public ArrayList<String>
    getLonely()
    {
        ArrayList<String> lonelyActors = new ArrayList<>();
        var ac = getActorCounter();
        for (var actor: ac.keySet()             ) {
            if (ac.get(actor) == 0) lonelyActors.add(actor);
        }
        return lonelyActors;
    }
}
