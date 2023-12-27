package pvdev.smek.potions.resources.step;

import pvdev.smek.potions.resources.step.enums.Heat;

/**
 * Brewing step that involves the process
 * of applying heat for the cauldron.
 */
public class HeatStep extends Step {

    private final Heat heat;

    /**
     * Constructs a HeatStep with the given
     * heat needed to proceed.
     * @param heat    The heat needed.
     */
    public HeatStep(Heat heat) {
        this.heat = heat;
    }

    @Override
    public boolean processStep(Step step) {
        if (!(step instanceof HeatStep stirStep)) return false;
        if (stirStep.getHeat().equals(heat)) return false;

        notifyRecipeNextStep();
        return true;
    }

    public String toString() {
        return "Heat " + heat.name();
    }

    public Heat getHeat() {
        return heat;
    }
}
