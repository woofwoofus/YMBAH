# PROJECT: YMBAH
##The Android 'Mini-Game' Game by Jan Geestman 10375406

You Must Build A House (YMBAH) is a game that features a mini game. The overall objective is to build a house. This is accomplished by collecting enough sand. The mini-game tasks the user to press on an image of a pile of sand. Do this enough times and you can build your house.


##Features

* More than 0 different mini-games that test your ability to click on things!
* Stunning(ly bad) Graphics!
* A main menu!
* No Ads!

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

## Process

### Prototype Product

The first week was a lot of exploration into the possibilities of the app. I experimented with animations, structure and fragments. Animations turned out to be easy to do but hard to get perfect. Consistency across different devices was also difficult to get right but the minimum viable product needed to work on my own device so I focussed on that. I wanted a drawer navigation for my app but this seemed to only work with fragments. Or if not I wasn't able to figure out how to get it to work otherwise. Ran into a lot of trouble with fragments as well. All tutorials were either too simplistic to be of further help or too difficult to understand. The prototype didn't need them though so I wasn't too worried yet. 

### Alpha product

Unfortunately I was sick for the entire second week of the product which sincerely cut into my production time. Changed my plan to make it easier to accomplish. Limited to one mini-game and changed the structure to activities rather than fragments. Drawer navigation was also out. 

### Beta Product

This week was the final sprint towards complete functionality. I have to say that due to my stress about missing a week of work and frustration about the lack of usable information online caused me to focus on the wrong things for a long time. I desperately tried to get fragments to work still and hoped to get a workable login function using Parse. Wednesday evening I realised that this was definitely not the right thing to focus on. The game logic came swiftly after that but I still had a lot of trouble getting it to work the way I wanted it to. Passing the Game object from one activity to another was a lot harder than it should have been. Making it Serializable only seemed to increase my troubles so I put that on the backburner for a while until I could figure out a solution. User accounts were also on the radar although I wanted them local. In the end I couldn't figure out a nice way to do it within the time remaining. I had some ideas about saving them in a text file and parsing it at startup but that took up too much of the time I had left to get the game working. The end product allows the user to create a username for themselves but it's not stored between sessions and has no impact on gameplay. I used the code from the GHOST project I did a few months back but it didn't work fully. Added a difficulty slider to increase the number of resources the player needs to collect. This was an easy to implement feature so I might as well include it.

### Final Product

Spent most of the last week commenting, bugfixing and writing the report. Got some last minute stuff working by doing inter-activity communication via the Shared Preferences again. It won't win any prizes but it works. I'm not completely satisfied with the final product. I feel like even though I missed a week of work I could have done better. The second week was poorly planned and caused even more delay in the important parts of the product. I was too focussed on doing complicated new things. I do feel like I learned a lot about Java and android coding during this project and the self-reflection will help a lot for future projects.