# PROJECT: YMBAH
##The Android 'Mini-Game' Game by Jan Geestman 10375406

You Must Build A House (YMBAH) is a game that features a mini game. The overall objective is to build a house. This is accomplished by collecting enough sand. The mini-game tasks the user to press on an image of a pile of sand. Do this enough times and you can build your house.


##Features

* More than X different mini-games that test your skill and precision! (Where X is dependent on production time)
* Stunning Graphics!

##Classes and Methods

* Game.class

Public Method | Return Type | Description
------------- | ----------- | -----------
GetResource(Resource) | int | Gets the collected amount of resource 'Resource'
CheckFinished() | Boolean | Checks if the user collected enough resources to build the house
saveGame() | void | Saves the collected resources, username, difficulty and game status to the shared preferences
GetLimit(Resource) | int | Calls GameRules.GetLimit(Resource) and returns the result

> At first, Game also implemented a SetResource(Resource) function which would be called to update the resources list. However I wasn't able to pass the Game object to other activities reliably so this was no longer necessary. Everytime the GameActivity starts a new Game object is created that reads the resource counters from Shared Preferences so DigSandActivity changes that information rather than directly from the Game class. Not a nice solution but it works.

* GameRules.class

Public Method | Return Type | Description
------------- | ----------- | -----------
GetLimit(Resource) | Int | Gets the necessary amount of resource 'Resource' needed to complete the game

> Originally this class had a lot more information stored in it but due to time constraints and technical difficulties it's a lot smaller than envisioned. 