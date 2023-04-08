# ProjectPAO

This project is for the Advanced Object Oriented Programming course.

## Video Game Application

As the theme of the project I have chosen an implementation of some game concepts found in one of my favourite games, the MMO War simulator game Foxhole (https://www.foxholegame.com/). While the game is pretty complex, I have altered some of the aspects to better fit the type and scale of the project. In this shape, it resembles more of a tabletop strategy simulator rather than a MMO with thousand of players.

The game has a bunch of hexagon-shaped regions where the fighting occurs, split furthermore into sub-regions. In these sub-regions you will find World Bases (pre-built, cannot be built by players in other locations), as well as Bunkers (FOBs) which are player-built. A player has to spawn at one of these in order to get involved in the fighting (which is what the assignment of a soldier to a certain base tries to mimic). Every base has an inventory where items are held and these are available to players. For now I have chosen not to give players a personal inventory. Alongside items, you can also store vehicles in a base (unlike the game, but a decent option for the sake of simplicity). 

Players are usually organised in Regiments, hence the Operation class that tries to signify coordinated activities between players in the same regiment. A typical part of any operation is preparing and assigning equipment to be used. Depending on the scale, an operation can involve multiple branches working together (infantry, tanks, artillery etc). At the same time, players can choose their favourite branch, and Officers can receive a rating for their expertise in each of these types of activities.


### Objects

* Soldier
  * Officer
* Equipment
  * Weapon
* Vehicle
  * Transport (Utility) Vehicle
  * Combat Vehicle
* Base
  * World Base
  * FOB (Forward Operating Base)
* Operation
* Medal
* MainService
* Main


### Actions

* create_soldier
* create_equipment
* create_vehicle
* create_base
* create_operation
* create_medal

* assign_soldier
* assign_materiel
* load_equipment
* display_profile
* display_inventory
* assign_commander
* upgrade_base
