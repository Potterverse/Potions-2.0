package pvdev.smek.potions.resources.step;

import pvdev.smek.potions.resources.step.enums.Direction;
import pvdev.smek.potions.resources.step.enums.Speed;

/**
 * Brewing step that involves the process of stirring the
 * cauldron in a particular direction a number of times into the recipe.
 */
public class StirStep extends Step {

    private final Direction direction;
    private final int rotations;
    private int rotationsRegistered;
    private final Speed speed;

    /**
     * Constructs a StirStep with the given
     * direction and direction needed to proceed.
     * @param direction    The stir direction needed.
     * @param rotations   The number of rotations.
     */
    public StirStep(Direction direction, int rotations, Speed speed) {
        this.direction = direction;
        this.rotations = rotations;
        this.rotationsRegistered = 0;
        this.speed = speed;
    }

    @Override
    public boolean processStep(Step step) {
        if (!(step instanceof StirStep stirStep)) return false;
        if (!stirStep.getDirection().equals(direction)) return false;
        if (stirStep.getRotations() + rotationsRegistered > rotations) return false;
        if (stirStep.getSpeed() != speed) return false;

        rotationsRegistered += stirStep.getRotations();
        if (rotationsRegistered == rotations) notifyRecipeNextStep();

        return true;
    }

    public String toString() {
        return "Stir " + direction.name() + " " + rotationsRegistered + "/" + rotations;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getRotations() {
        return rotations;
    }

    public Speed getSpeed() {
        return speed;
    }
}
