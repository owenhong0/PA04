# Changes made to PA03 in PA04

## In BattleController.java
- Changed all the view methods to not take in the input 
- and rather passed in the inputstream to the view object 
- through its constructor -> REASON: multiple input streams
- were not allowing for the test to input multiple lines

## In Board.java
- Changed mapHorizontal and mapVertical so that if the 
- randomly generated spot on the board was not a valid
- horizontal or vertical spot, then just try mapping the 
- ship in the opposite orientation. Also, validHorizontal
- and validVertical method were changed to be a little
- more efficient -> REASON: trying to 
- recursively call the method in hopes that a valid spot
- would be generated created runtime infinite loops and
- prevented the original functionality of PA03 from working

## In Coord.java
- In the constructor, the x and y were swapped with the 
- column and row constructor parameters -> REASON: this
- was definitely the biggest change from PA03 and this 
- fixed the errors in that the board's col and row indicies
- were swapped from what they should have been and this
- fixed everything (this later helped with PA04 in sending
- ships and taking shots with correct coordinates)

## In Model interface
- The model interface from PA03 was split into a battleState
- and battleInteractions interface; each of the associated
- methods were distrubuted into each and interactions
- extends the state interface (interactions is then
- implemented by the battleModel) -> REASON: this was just
- for design purposes (mainly by the TA's advice)

## In BattleModel.java
- The randomShots method was changed along with the 
- creation of filterShots, filterMisses, and createShots ->
- REASON: randomShots was changed to take in all remaining
- possible spots on the board to shoot and randomly
- picks those spots and filterShots/Misses were the same
- code from PA03's reportDamage Comp class but
- were put into their own methods for organizing purposes.
- createShots generates all possible shots on a board to be
- later used by the randomShots methods and overall
- the takeShot method.

## In Comp.java
- There was an additional constructor added to not
- take in any shots which becomes the main
- constructor for the comp class. randomShots now is 
- passed possibleShots (a new field) in takeShots and
- the code for reportDamage and successfulHits were
- simplified -> REASON: To make a new Comp object in PA04,
- it was required to not have to take in any shots as
- this would happen before the setup could be called.
- randomShots was changed so that it would return a valid
- volley to the server and account for duplicates.
- The other two methods were simplified as more processing
- could just go in the BattleModel file.

## In the ships package
- An orientation enumeration was created to represent a
- ships orientation that would be later used in converting
- ships to their proper format in PA04

## In BattleView.java and the View interface
- All instances of the scanner were removed from the methods
- and as parameters from the methods -> REASON: This was
- done to help testing in which multiple lines of arguments
- were not able to be parsed without a single instance
- of the scanner being initialized in the constructor