package pvdev.smek.potions.resources.step;

/**
 * Brewing step that involves the process
 * of waiting and timing the next step.
 */
public class WaitStep extends Step {

    private Long startTime = null;
    private final float duration;
    private static final float ERROR_MARGIN = 0.9F;

    /**
     * Constructs a WaitStep with the given timeframe /
     * duration needed to continue the next step.
     * @param duration    The duration needed by this step.
     */
    public WaitStep(float duration) {
        this.duration = duration;
    }

    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public boolean processStep(Step step) {
        if (!hasStarted()) return false;
        float passedTime = (float) (System.currentTimeMillis() - startTime) / 1000;
        if (passedTime < duration || passedTime > duration + ERROR_MARGIN) return false;

        notifyRecipeNextStep();
        executeStep(step);

        return true;
    }

    public String toString() {
        return "Wait " + duration + " seconds";
    }

    public boolean hasStarted() {
        return startTime != null;
    }
}
