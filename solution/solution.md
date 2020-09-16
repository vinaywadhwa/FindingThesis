# Solution
A solution to the Finding Thesis programming puzzle

## Summary: 

This solution is recursive. There may be better solutions for this problem out there but you may find this particular one uniquely elegant.
Besides checking for sane input and handling edge cases, this solution relies mainly on a recursive function, namely findMinHops() to find the minimum hops from any given index (the first one, in this case) to the desired train compartment.


## How to provide input  
For simplicity, the following three constants simulate the user input (we don't want to get into the `Scanner` mess) 

- SAMPLE_INPUT_NO_OF_COMPARTMENTS - Represents no of compartments in the train
- SAMPLE_INPUT_NO_OF_COMPARTMENTS - The compartment number (0 index based, for the sake of simplification) where the Thesis exists.
- SAMPLE_INPUT_NO_OF_COMPARTMENTS - Jetpack Threshold values for each compartment.


## Notable Features
Here are some notable features of this solution

###Caching
This program implements a caching mechanism for the sake of performance optimisation. Notice the minHopsCache array.
- A cache which helps avoid duplicate traversals to find minimum hops from a given starting index in compartments to the SAMPLE_INPUT_THESIS_INDEX. 
- The index of the array represents the starting index and value at that index is the minimum no of hops required to reach SAMPLE_INPUT_THESIS_INDEX from that starting point/index.

### Edge cases handling
The solution assumes and handles some edge cases. More might be needed, but this is what we have right now
- if the thesis lies at zero'th index (first train compartment) itself, we dont need to hop at all! 
- if the thesis lies at first index (second train compartment), all we need is a single hop (since we are assuming jetpack threshhold is always non-zero, atleast ONE)

### Validation of user input
We also have some test cases (not JUNIT, just simple checks) to check if the input provided by the user/sample user input declared in this class is valid as per the assumptions stated in the problem statement.
- No of compartments can't be zero or negative
- Thesis can't be in a train compartment with negative index
- Thesis can't be in a train compartment with index greater than total no of train compartments