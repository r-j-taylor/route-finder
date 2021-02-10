# route-finder
A repository containing a program that finds the most efficient route to the end of a path of possible stops. An efficient route is one with the smallest penalty, which is defined as (x-y)^2, where x is the distance traveled and y is the target distance for one tick.

After compilation, this program can be run by entering `java RouteFinder [input file]`. Example input files are provided.

The first line of the output is the route that should be taken and the second line is the total penalty for the route found.
