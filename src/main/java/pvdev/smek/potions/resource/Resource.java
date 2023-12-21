package pvdev.smek.potions.resource;

/***
 * An interface representing resources that need to be instantiated on startup for reference.
 * Implementations require that the name be used as a key of reference when retrieving.
 * Additionally, this interface is referenced when specifying the type for generics.
 */
public interface Resource {
    /**
     * Returns the name of the resource to be used as a point of reference.
     * @return The name of the resource.
     */
    String getName();
}
