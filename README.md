# FindingThesis
A programming puzzle !

## Problem Statement: 

One day a mathematician by name Ramakrishna was travelling on a Train 🚂  which is fully crowded 👬👬👬👬 
- there's no space to walk through the aisle, 

Ramakrishna is travelling with a purpose - to submit his final thesis paper 📄 

### Plot twist - 
His conversation with his fellow passenger Robert have lands him in a situation to solve an interesting problem 
- Robert hides the mathematician thesis paper in one of the compartments of the train
- Evil as he is, Robert is also a bit of a softy. Considering the train is packed Robert equips Ramkrishna with a JETPACK
- The Jetpack is a wearable that can be used to hop from one compartment(coach) to another
- In each compartment a threshold value for JETPACK is placed
- the JETPACK cannot jump over more coaches than the Threshold value

Eg: 
- if a JETPACK is in compartment 2 where threshold value is 4, the JETPACK in one hop can max move to compartment 6 from 2, 
- however user can choose to land anywhere between 3-6, 
- Ramakrishna has to use the JETPACK to find his Thesis - *in minimum possible number of hops.*

So, Let's write a program for this 

#### Input 
- number of compartments as inputs, 
- takes JETPACK threshold values for each compartment, 
- and the value of the compartment where the thesis paper is hidden, 

#### Output
- return the minimum hops Ramkrishna has to make to find his thesis paper.

Assumption: Ramakrishna must alwas start from compartment 1 in the train.
 
#### Example : 

The Thesis is hidden in compartment 5. Here is a sample set of compartments as input -> values in each box are their respective JetPack Thresholds.



#### Output:
2 

#### Reasoning:
Path: 
1->3>4->-5  : number of hops 3
1->2->5     : number of hops 2
