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

Worked on the communication between activities. Most of it is stored in the SharedPreferences now. Also wrote the game logic. Had to refresh my memory about data storage types.