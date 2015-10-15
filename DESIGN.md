#Design Document 
###Cell Society (Team 17)
----------
>See Images folder for attached screenshots and drawings.

###Introduction

The “problem” in question is to develop a Java program that can take input from an XML file and generate a 2D cellular automata (CA) simulation. While only three examples of CA’s are given in the Cell Society prompt, it is key that the program is developed with flexibility in mind with respect to the types of CA’s it can implement. Since various phenomena can be described using CA’s, the program should be flexible enough to handle different models with different cells that have different updating conditions. A general framework will be put in place that handles: generating a user interface, defining a grid-like structure, and determining an appropriate algorithm that will govern how the grid changes every iteration. This framework will be kept as open as possible to allow for maximum extension. For example, the way in which the backend or algorithm updates the individual cells in the grid ought to be closed to modification but the rules that inform the algorithm which cells need to change and how they need to change must remain open for extension. This will allow us, the designers of the software, to easily implement new CA’s without changing the structure of the program.

In general, the GUI will display various settings, buttons, and parameters that will adjust the behavior of the simulation. The buttons that govern universally needed controls (start, stop, pause, step) will be closed features, but tools that change CA specific characteristics, like relative starting quantities of certain cells, will be features that are built upon the underlying framework. The GUI will communicate user input to the backend/algorithm that will use that input in tandem with the rules that the individual cells follow to develop the update for the grid. The backend will then communicate that update to the grid, the grid will make the necessary changes and then present itself to the GUI to be displayed to the viewer.

----------

###Overview

####GUI
The Player interacts with the Graphical User Interface (GUI), the front-end component for this project. The Player will be able to see the simulation in effect and be able to implement commands that are relevant to the viewing experience (e.g. step, pause functions) as well as specific to the simulation or image itself (i.e. parameters, modifications). Taking in these commands, the GUI will relay the information to the Backend, which will call the appropriate methods to update the display of the Grid, which in turn will be updated in the GUI. The checking functionality will take place in the Backend, in line with the concept that any front-end GUI should be responsible only for front-end responsibilities with all its information processed by the Backend.

JavaFX will be used in this project in preference of other platforms such as AWT and Swing. To build up the GUI, the Stage class is called on which a Scene is initiated. On the Scene, several instances of the Node will be added – including ImageViews (to contain the grid) and Parents. Two subclasses of the Parent superclass will be created to match the GUI functionality – the Control and the Pane. The Control class accesses all the UI input from the user, e.g. Button, RadioButton, while the Pane has instances such as BorderPane, VBox. Interaction with the UI input will result in the calling of methods in the Backend to process and redraw the grid accordingly, e.g. to pause the simulation. Elements that fall into one group, e.g. a pane of pause/play/reset buttons, will occupy its own class, inheriting methods and variables from a superclass. For consistency of formatting in the GUI, a CSS file will used to control the styles of the nodes in view. 


####XML

The initial configurations will be present in the XML file that initializes a particular CA. First, the XML file will contain: the title of the simulation, the name of the simulation, the author of the simulation, the grid dimensions, and an initial state grid for all of the cells within the grid. This XML document will be interpreted by a Java class tentatively called XMLParser. XMLParser will contain a number of variables that represent the information in the file (strings for the name/title, int values for the dimensions of the grid, etc.). XMLParser will then package the information and then send that information to the GUI classes or Backend classes. For example, the parser should instantiate the GUI classes with the strings they need to title the user interface. The XML file will then will package the values needed to instantiate the Grid class and create a Grid object. The Grid class will be abstract; a concrete Grid object will be defined by the type of Cell class that makes the individual cells in the grid. Once an empty instantiation of the Grid object is made, the XML will define the initial states of the individual cells. Furthermore, depending on the name of the simulation, the XML will also instantiate a Rules object. The Rules class will be abstract class with abstract methods that will be overridden. These methods will require arguments that come from the Processor class but will be specific to the simulation. The XML file will also denote the dimensions need for the “slice” of the grid created by TrimGrid, so it will also be responsible for initializing a TrimGrid object.


####Backend
In the backend, we have classes interacting with each other--Rules Class, Grid Class, TrimGrid Class, Cell Class and Processor Class. 

The Rules class will be an abstract class with a method that takes information from the Processor class. This is the processed information of the slice of the grid in question (the TrimGrid) that is needed to make a decision regarding the state or movements of the center cell. An instantiation of the rules class will be specific to the particular simulation, and this will require the implementation of boolean methods to take the information fed in by the Processor class and return true/false regarding the new state of the center cell. These boolean methods will then determine what the Rules object returns for a state for the center cell.

The Cell class will be an abstract class which has a method that takes information about state types from the XML document. 

The Grid class will be an abstract class consisting of cells. The initialization of the Grid class will depend on the the information from the XML document. The Grid will be updated by the processor for each iteration of the simulation.

The TrimGrid class will read the number of neighbours from the XML document as the dimension of the TrimGrid and then iterate through each cell in the Grid and cut the TrimGrids. Each TrimGrid will be passed to the Processor class.

The Processor class will first initialize a new grid with empty cells. Then it will iterate through each cell, for which, an integer array of prime numbers will first be created. Then it will get the TrimGrids centered at that cell coordinates. The weighted number based on states in the TrimGrid will then be multiplied with the prime numbers. The result will be used to determine how many cells contain each state in the neighbourhood and where they are. This result will then be passed to different Rules class to get feedbacks about what should be the states of that cell for the next step. The new states will be updated to the corresponding new cell of the new grid. When the new grid is completely upgraded, it will be passed to the GUI.


----------


###User Interface

For the design of this project, it is useful to think of all the feature requirements as prioritised requirements, graded from P0 to P2. 

>P0 is used to represent the absolutely necessary, must-have baseline in order to meet the bare minimum.
>
>P1 for the purposes of this project is a requirement we must anticipate and prepare for in our design decisions, but is not necessary to implement right now. 
>
>P2 is a stretch goal that “would be good” to do if we had the time at the end. (unlikely to be prevalent in this project)
 
The User Interface will consist of a Scene class with several panes that depend on the future requirements of the project. The first pane will be the control box, where this class is extended by two subclasses: the model-independent controls (P0) and model-specific controls (P1). In the Model Independent class, basic functions will be implemented, including:

 - Run Button 
 - Frame Count Display
 -  Step Button 
 - Stop Button 
 - Speed Slider
 - Reset with Random Layout 
 - Reset with a Preset Layout 
 - Change Simulation

These are basic functions that are independent of any simulation that is played. The Run button will project the grid and start the simulation, while the frame count display will update the number of frames that has elapsed. The step button allows the user to step through each frame and look at the animation one by one. The Stop button pauses the animation, and the speed slider, with a range from fast to slow, will dynamically alter the speed at which the game runs. There are two Reset options. Reset with Random Layout generates a random proportion of all states possible and initialises the grid based on this randomness. Reset with a Preset Layout (P1) is anticipating an extensible feature where the XML file defines a preset for the states in each cell of the grid. This preset would be loaded and the grid would be redrawn. To change between the simulations, an options list will drop down and the user can choose the new simulation he/she wants to see. 

In addition to these basic controls, a class will extend this model independent class if needed by future specifications, providing functionality for the user to detect the coordinates of his/her mouse and import or save a bitmap image from the grid. An ImportImage class (P1) will divide the image into pixels in a grid corresponding to a relevant state and the rules of that particular simulation can be applied. 

For the model specific class (P1), which will be an abstract superclass, it will depend on the specific simulation being played. Subclasses that can extend this class include a User Input class, where the user can mouse left-click change state of a cell. To change the cell state, the user would choose a state from the possible drop-down states and left click at the appropriate cell. Some simulations have special features that may be added. For instance, the Game of Life gives rise to the following particular patterns.

 - Still Lifes (Beehive, Boat) 
 - Oscillators (Blinker, Pulsar) 
 - Spaceships (Glider)

In this fashion, the user can directly influence the simulation as it is happening, changing the outcome dynamically as the simulation is running. 

An abstract subclass RulesBox can be created under the model specific class to provide a pane for users to be able to turn rules on/off dynamically.This would involve unchecking or checking boxes next to the rules that the user would like to turn on or off. Finally, a Reset Parameters subclass can also be implemented to reset the grid to any states. If the user wishes to redraw the whole grid to one specific state, he/she can choose from a dropdown list and press the Reset button. Alternatively if the user wishes to redraw the whole grid to a proportion of each possible state, they may do so by adjusting a set of sliders that determine the relative amount of each state to be represented on the grid. 


----------


###Design Details 

####UI

#####ResourceBundle
The strings displayed to the user should be contained in one central location so that they are easily modifiable and changed, especially if the strings have to be localised to another language. An abstract Resource class will be created with the relevant variables for the title, descriptions of individual boxes and labels. These variables will be instantiated by the Resource_EN_US file. Each game will be a subclass of the Resource_EN_US abstract class to override the abstract class. 

####Backend

#####Rules Class
The Rules class will be abstract and it will have an abstract method that takes in a Integer count value and/or a HashMap with a key of the different states in the simulation and that mapped to a list of the prime number values that were assigned to our TrimGrid in the Processor class. The Rules class will be extended for every type of simulation. Each concrete Rules class will contain the boolean statements that will interpret the information in the HashMap and/or the count and will then determine what the new state of the center cell should be. For example, for the Spreading of Fire CA, the HashMap containing the three different states prime integer assigned values as a key with mappings to the prime integer assigned values of the North, South, East, and West cells will be passed to the FireRules class that inherits Rules. The FireRules class will then contain a boolean statement that checks whether the fire state key’s list contains all N,S,E,W coordinates from the TrimGrid slice. If so, it will then run a number generator and if that value is above the threshold, the Rules class will return the Fire state to the Processor.

#####Grid Class
The Grid class is an abstract class that represents a matrix. It is abstract since the individual cells will differ in type depending on the type of simulation that is run. As was mentioned in the overview, this class will be instantiated with information from XML file regarding grid dimensions and CA type.

#####TrimGrid Class
The TrimGrid class will iterate over each cell has part of the update loop to change the grid. It will “trim” the grid down to a certain 3x3 or 5x5 (depending on the type of simulation) swathe of the entire grid. This smaller grid will then be passed on to the Processor which will perform prime number assignments to the Cells of the TrimGrid.

#####Cell Class
The Cell class will be an abstract class and it will take the information about the number of states from the XML file and then allocated a prime number of integer type for each state.
	

#####Processor
The processor has several functions. Firstly, it initialises a new Grid of Cell objects and takes in TrimGrid objects for each cell on the grid and performs the relevant prime number calculations given the TrimGrid. The relevant prime numbers are assigned to each state and location that the state occupies, and a final multiple of all the state values and their corresponding locations is obtained. This number is then broken down by checks that determine each state and value, and one or two structures are then instantiated depending on the simulation. These include an array of state counts that surround the neighbour, and a location Map of the states as the key, and the locations as the value (denoted by the weighting). The two structures are passed to the individual instances of the Rules Class that checks the counts and the locations according to preset rules, and returns the appropriate state that the cell should be set. This state is then set in the cell of the new Grid and once the Grid is complete, it is sent back to the Grid class to be displayed by the GUI.


----------


###Design Considerations 

The team went to great detail, discussion and lengths to find a solution for incorporating rules in the most flexible way possible. The Fire simulation contained a rule that had a dependency on neighbouring cells, such that a conventional solution would have to call the checkRule method on each of the neighbouring cells. This can easily devolve into a messy structure if new, complex rules are created. It necessitates having many if statements and is open to modification. Hence, we discussed a method to process a neighbourhood of cells and return one single integer to the processing class. This single number is a multiple of prime integers, where each state and each location is assigned a prime number. As each number assigned is prime and each location where the cell could occupy is weighted, the processing class is able to take in the number and output two structures:

 - An array of the counts of each cell state. 
 - A Map of each cell state to its corresponding location(s).

This closes the implementation to modification, and any changes in the rules will occur only in the Rule class/subclasses, making the design more flexible and extensible. The responsibility of the rule class/subclasses is to receive the package of the counts array and location map, and determine the correct state for that cell. Hence, any addition to rules will be made in one location that is a layer removed from the grid access. 

The idea that cells can move appears difficult to implement initially, but the grid can contain objects of type Cell, and each time a cell is moved, its variables will be passed on to the new cell and the original cell parameters will be reset. 

Researching the individual simulations raises the common theme of dealing with edges. Restricting the grid to only the boundaries of the grid often proves problematic and disturbs the patterns already generated. Instead, the features that will move off the grid can wrap around in a toroidal fashion, reappearing on the opposite side of the grid. To detect this, there will be a buffer layer around the grid that determines whether this happens. 


----------


###Team Responsibilities

GUI development: Henry Yuen

Backend & Grid design: Wuming Zhang, Nick von Turkovich 

XML formatting & Backend: Nick von Turkovich

---------
