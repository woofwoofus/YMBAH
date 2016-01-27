# PROJECT: YMBAH
##The Android 'Mini-Game' Game by Jan Geestman 10375406

You Must Build A House (YMBAH) is a game that features a variety of different mini games. The overall objective is to build a house. This is accomplished by several mini-games which simulate different construction steps (Chopping trees, Building a frame, Mining for materials, etc.). These mini-games make use of touch-screen finger tracking where the users have to perform tasks with varying levels of difficulty and precision. 


##Features

* More than X different mini-games that test your skill and precision! (Where X is dependent on remaining production time)
* Progressively more difficult levels that test you speed and managing skills!
* Survival and Zen game modes!
* Stunning Graphics! (maybe...)

##Classes and Methods

* Game.class

Public Method | Return Type | Description
------------- | ----------- | -----------
GetResource(Resource) | int | Gets the collected amount of resource 'Resource'
CheckFinished() | Boolean | Checks if the user collected enough resources to build the house
saveGame() | void | Saves the collected resources, username, difficulty and game status to the shared preferences
GetLimit(Resource) | int | Calls GameRules.GetLimit(Resource) and returns the result


* GameRules.class

Public Method | Return Type | Description
------------- | ----------- | -----------
GetLimit(Resource) | Int | Gets the necessary amount of resource 'Resource' needed to complete the game



