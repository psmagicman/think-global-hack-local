====SAM Getting Started====

To run the application in Windows, double click WindowsLauncher.bat. 
To run the application in Mac/Linux, double click the UNIXLauncher.sh.

You must have java installed to run the application. 

If the application did not launch, but instead opens a text file, right click the file and make it executable.
(e.g. Permissions -> Allow executing file as program)


====Game Developers====

To create new games, you need an entry point class called Game which should implement the IGame interface in module.IGame. This class needs to be called Game and must implement the method startGame().

The game that you write will need to have SAM.jar in the build path to use all the classes needed to integrate with SAM. To do this in Eclipse, just go to project Properties -> Build Path -> Libraries -> External Libraries and add the library SAM.jar.

Then, you will need to export your game as a jar (Eclipse does this with a right click on the project, Export). You then can "install" your game to SAM by putting this exported jar file into the Games directory.
