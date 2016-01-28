#Day 2

Experimented a lot with animations. Just testing the waters; getting a feeling for how difficult it could be. Some issues came up with animations that adapt to the screen resolution. More difficult than first thought. However, MVP is getting the app to work on my own phone so I'll focus on that.

#Day 3

Started work on the structure on the app. Stopped early on in the process because I didn't have a good plan formed in my head yet. Watched a lot of tutorials online. Decided to use fragments for most of the screen layout. Right now limited to 2/3 activities with a lot of fragments in each one. Some concerns about performance but with the limited amount of views per mini-game it shouldn't be a problem. 

#Day 4

Did a lot of restructuring work again. Got a better grasp on Fragments and their use. Not entirely what I envisioned they would be. Finally decided on my eventual app layout. Main game activity shows the House the player is building. From there a drawer menu allows navigation towards the different mini-games. All of these activities have the same drawer menu. From every activity, the options menu is reachable as a fragment overlaying the activity. Drew the DOM for the app. Finished the Beta Product. 

#Day 5

Gave presentation explaining my app to the other students in the team.

##~~SICKNESS INTERLUDE~~

#Day 6

Reacquainted myself with the app. I had mostly forgotten where I stopped before I got the flu. Ditched the navigation drawer idea. It's just not practical for the use I have in mind for it. It'll just be boring buttons for now. Fixed most of the main menu today. Managed to get the Options Menu working as a fragment. It's still empty for now but at least it works and I can call on it within other activities easily. Set up a lot of front-end stuff for dealing with eventual user account and loading of old games. Nothing really functional yet but the framework is there.

#Day 7

Worked on the communication between activities. Most of it is stored in the SharedPreferences now. Also wrote the game logic. Had to refresh my memory about data storage types. Got the game semi-working. Rudimentary game logic works but remembers too much data between sessions. Put it on my to do list for tomorrow. Didn't feel to well so I went home early and did some work from there.

#Day 8

User can now enter a username. Not yet linked to saved game functionality but that will have to wait for now. Noticed a bug with the DigSand minigame that I needed to fix where the sand flew off screen. Back to working order now. Major issue with the Game.class. It was being created every time GameActivity was run. Made it serializable. Not sure what that means exactly but I can now pass it between activities without much problem so it suits my needs. I have also been migrating away from the Shared Preferences as this caused more trouble than it was worth. The fact that it's pretty much permanent means I have to clear it frequently. Now that the Game class can be passed along I don't need it as much anymore. Cleaned up a lot of communication stuff as well: more intent based. Still need to implement proper saved games. I'm thinking of a way to save it in a separate file but I don't have a concrete plan for it yet. Will think about it tonight and implement it tomorrow morning before the presentations.

#Day 9

Started cleaning code and fixing bugs. Changed the way the game object is constructed so it remains consistent between activities. More Shared Preferences I'm afraid. 

#Day 10 

Started work on the report and wrote comments. Did more bugfixing.

#Day 11 

Comments, debugging and more.

#Day 12

Finished the report and comments. Did one final adaptation to the code that cleans the userlist between games so it doesn't fill with duplicates.