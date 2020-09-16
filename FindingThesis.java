package finding_thesis;

/**
 * This class provides a Solution to the following programming puzzle:
 * <p>
 * One day a mathematician by name Ramakrishna was travelling on a Train which is fully crowded with
 * no space to walk through the aisle, to submit his final thesis paper, his conversation with his
 * fellow passenger Robert have put him in a situation to solve an interesting problem , Robert
 * hides the mathematician thesis paper in one of the compartments of the train, warns Ramakrishna
 * the thesis will be burnt if he doesn't find them as soon as possible, considering the train is
 * packed Robert equips Ramkrishna with a JETPACK(this is a wearable that can be used to hop from
 * one compartment(coach) to another), in each compartment a threshold value for JETPACK is placed,
 * the significance of this value is JETPACK cannot jump over more than the Threshold value,
 * (meaning if a JETPACK is in compartment 2 where threshold value is 4, the JETPACK in one hop can
 * max move to compartment 6 from 2, however user can choose to land anywhere between 3-6),
 * Ramakrishna have to utilise JETPACK and find his Thesis in minimum hops.
 * <p>
 * Write a program which takes number of compartments as inputs, takes JETPACK threshold values for
 * each compartment, and the value of the compartment where the thesis paper is hidden, and return
 * the minimum hops Ramkrishna have to make to find his thesis paper.
 * <p>
 * Note: Always Ramakrishna have to start from compartment 1
 * <p>
 * Example :
 * <p>
 * Below is the input of 5 compartments , values in each box is JetPack Thresholds
 * <pre>
 * [Compartment 1 - threshold = 2] | [Compartment 2 - threshold = 3] |
 * [Compartment 3 - threshold = 1] | [Compartment 4 - threshold = 1] |
 * [Compartment 5 - threshold = 3]
 * </pre>
 * <p>
 * Its known that The Thesis is hidden in compartment <b>5</b>, say.
 * <p>
 * Output: 2
 * <p>
 * Reasoning: Path: 1->3>4->-5  : number of hops 3
 * <p>
 * 1->2->5       :number of hops 2
 * <p><br>
 * Other ASSUMPTIONS:
 * <li>Jetpack threshold in each compartment is always non-zero/at least 1.</li>
 */
class FindingThesis {
    /**
     * No of compartments in the train
     * <p>
     * [assumed as sample user input]
     */
    private static final int SAMPLE_INPUT_NO_OF_COMPARTMENTS = 7;
    /**
     * The compartment number (0 index, to simplify) where the Thesis exists.
     * <p>
     * [assumed as sample user input]
     */
    private static final int SAMPLE_INPUT_THESIS_INDEX = 6;
    /**
     * Jetpack Threshold values for each compartment. (Make sure you keep the no of elements in this
     * array consistent with {@link #SAMPLE_INPUT_NO_OF_COMPARTMENTS}.
     * <p>
     * [assumed as sample user input]
     */
    private static final int[] SAMPLE_INPUT_JETPACK_THRESHOLD_VALUES = {1, 2, 2, 1, 1, 2, 2};
    /**
     * A cache which helps avoid duplicate traversals to find minimum hops from a given starting
     * index in {@link #compartments} to the {@link #SAMPLE_INPUT_THESIS_INDEX}. The index of the
     * array represents the starting index and value at that index is the minimum no of hops
     * required to reach {@link #SAMPLE_INPUT_THESIS_INDEX} from that starting point/index.
     */
    private static int[] minHopsCache;
    /**
     * This array represents the compartments in the train.
     * <p>
     * [as per the sample input]
     */
    private static Compartment[] compartments;

    public static void main(String[] args) {
        //check if user input is valid. If its not, explain (via an exception) & exit.
        validateInputOrThrow();
        //create the compartments
        compartments = createTrainCompartments();
        //create the minHopsCache
        minHopsCache = new int[SAMPLE_INPUT_THESIS_INDEX];
        //first, check for edge cases
        int minimumHops = checkEdgeCases();
        if (minimumHops == -1) { //if no edge cases are found, proceed normally
            //call the recursive function which calculates no of hops to reach the target compartment
            // which contains the Thesis
            minimumHops = findMinHops(0);
        }
        //print the answer
        System.out.print(minimumHops);
    }

    /**
     * Test cases to check if the input provided by the user/sample user input declared in this
     * class is valid as per the assumptions stated in the problem statement.
     */
    private static void validateInputOrThrow() {
        if (SAMPLE_INPUT_NO_OF_COMPARTMENTS <= 0) {
            throw new RuntimeException(String.format(
                    "No of compartments can't be zero or negative " + "(value given = %d)",
                    SAMPLE_INPUT_NO_OF_COMPARTMENTS));
        } else if (SAMPLE_INPUT_THESIS_INDEX < 0) {
            throw new RuntimeException(String.format(
                    "Thesis can't be in a train compartment with negative index  (value "
                            + "given = %d)", SAMPLE_INPUT_THESIS_INDEX));
        } else if (SAMPLE_INPUT_THESIS_INDEX >= SAMPLE_INPUT_NO_OF_COMPARTMENTS) {
            throw new RuntimeException(String.format(
                    "Thesis can't be in a train compartment with index greater than " + "total no "
                            + "of compartments (value given = %d)", SAMPLE_INPUT_THESIS_INDEX));
        }
    }

    /**
     * Checks for edge cases for the given problem.
     *
     * @return minimum hops, if edge cases are found. Otherwise, -1.
     */
    private static int checkEdgeCases() {
        if (SAMPLE_INPUT_THESIS_INDEX == 0) {
            //if the thesis lies at zero'th index (first train compartment) itself, we dont need to
            // hop at all!
            return 0;
        } else if (SAMPLE_INPUT_THESIS_INDEX == 1) {
            //if the thesis lies at first index (second train compartment), all we need is a
            // single hop (since we are assuming jetpack threshhold is always non-zero, atleast
            // one)
            return 1;
        } else {
            //indicate to the caller, no edge conditions were met.
            return -1;
        }
    }

    /**
     * Instantiates the train compartments for given user input (i.e {@link
     * #SAMPLE_INPUT_NO_OF_COMPARTMENTS} in this case.)
     *
     * @return array of train {@link Compartment}s
     */
    private static Compartment[] createTrainCompartments() {
        Compartment[] compartments = new Compartment[SAMPLE_INPUT_NO_OF_COMPARTMENTS];
        for (int i = 0; i < SAMPLE_INPUT_NO_OF_COMPARTMENTS; i++) {
            compartments[i] = new Compartment(SAMPLE_INPUT_JETPACK_THRESHOLD_VALUES[i]);
        }
        return compartments;
    }

    /**
     * Recursive function that finds the minimum number of hops needed to arrive at the target (i .e
     * {@link #SAMPLE_INPUT_THESIS_INDEX}), taking into account the jetpack threshold value at each
     * compartment (as per {@link #SAMPLE_INPUT_JETPACK_THRESHOLD_VALUES}).
     *
     * @param startingIndex index from which this function must start hopping
     * @return minimum hops required from startingIndex to thesis index.
     */
    private static int findMinHops(int startingIndex) {
        int result = retrieveMinHopsFromCache(startingIndex);
        if (result == 0) { //ignore the edge case :thesis in first compartment (already handled)
            //get the threshold value for initial train compartment
            int jetpackThresholdValue = SAMPLE_INPUT_JETPACK_THRESHOLD_VALUES[startingIndex];
            //check if we have enough threshold to reach the target
            if (jetpackThresholdValue >= SAMPLE_INPUT_THESIS_INDEX - startingIndex) {
                result = 1; //if thesis is at the first index itself/we are already there
            } else {
                int min = SAMPLE_INPUT_THESIS_INDEX - startingIndex; //max possible hops
                for (int i = 1; i <= compartments[startingIndex].jetpackThreshold; i++) {
                    min = Math.min(min, findMinHops(startingIndex + i));
                }
                result = 1 + min;
            }
            updateCache(startingIndex, result);
            System.out.printf(
                    "startingIndex = %d | thesisIndex = %d | jetpackThresholdValue = %d | "
                            + "minValue = %d\n", startingIndex, SAMPLE_INPUT_THESIS_INDEX,
                    jetpackThresholdValue, result);
        }
        return result;
    }

    /**
     * Enables retrieval of cache values for min hops from given starting indexes. See {@link
     * #minHopsCache} for more info.
     *
     * @param startingIndex the starting index for the cached minimum hops value.
     * @return the minimum hops value for the given starting index.
     */
    private static int retrieveMinHopsFromCache(final int startingIndex) {
        return minHopsCache[startingIndex];
    }

    /**
     * Enables caching of values for min hops from given starting indexes. See {@link #minHopsCache}
     * for more info.
     *
     * @param startingIndex the starting index against which minimum hops value must be cached.
     * @param minHops       minimum hops value to be cached.
     */
    private static void updateCache(final int startingIndex, final int minHops) {
        minHopsCache[startingIndex] = minHops;
    }

    /**
     * This class represents a train compartment and its contents.
     */
    private static class Compartment {
        /**
         * The Jetpack Threshold / Jetpack Fuel units available in this compartment.
         */
        int jetpackThreshold;

        /**
         * Creates an instance of a train {@link Compartment}
         *
         * @param jetpackThreshold the jetpack threshold for this train compartment.
         */
        Compartment(int jetpackThreshold) {
            this.jetpackThreshold = jetpackThreshold;
        }
    }
}
