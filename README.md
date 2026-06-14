# Traffic-Management-System-using-Graphs
Java Program

A Traffic Management System using Graphs is a computer science project that uses Graph data structures to represent and manage road networks, traffic flow, and routes.

Basic Idea

A road network can be represented as a Graph:

   1.Vertices (Nodes) → Represent locations, junctions, signals, or cities
 
   2.Edges → Represent roads connecting two locations

   3.Edge Weight → Represents distance, travel time, traffic level, or cost
=====================================
    TRAFFIC MANAGEMENT SYSTEM
=====================================


===== Traffic Management System =====

1. Add Road
2. Display Graph
3. Find Shortest Route
4. Detect Traffic
5. Emergency Route
6. Exit


Enter Choice: 1


Start Junction: 1

End Junction: 2

Distance: 5

Road Added Successfully



Enter Choice: 1


Start Junction: 1

End Junction: 3

Distance: 10

Road Added Successfully



Enter Choice: 1


Start Junction: 2

End Junction: 4

Distance: 3

Road Added Successfully



Enter Choice: 1


Start Junction: 3

End Junction: 4

Distance: 7

Road Added Successfully



Enter Choice: 2


===== Traffic Graph =====

1 -> 2(5 km) 3(10 km)

2 -> 1(5 km) 4(3 km)

3 -> 1(10 km) 4(7 km)

4 -> 2(3 km) 3(7 km)



Enter Choice: 3


Enter Source Junction: 1


===== Shortest Route =====

1 -> 1 = 0 km

1 -> 2 = 5 km

1 -> 3 = 10 km

1 -> 4 = 8 km



Enter Choice: 4


===== Traffic Status =====


Road 1 -> 2 : MEDIUM Traffic 🟡

Road 1 -> 3 : HIGH Traffic 🔴

Road 2 -> 4 : LOW Traffic 🟢

Road 3 -> 4 : MEDIUM Traffic 🟡



Enter Choice: 5


Enter Start Junction: 1

Enter Destination Junction: 4


===== Emergency Route 🚑 =====


Start Junction : 1

Destination : 4

Fastest Time : 8 minutes

Route Status : CLEAR 🟢



Enter Choice: 6


System Closed
