# libscore

A siple, dependancy-free Java library for bowling scoring.

This library currently only contains 2 classes: Frame and Game. 

Frame represents a single frame of bowling, and can act as a normal frame or a tenth frame.
A Frame contains 3 integers representing the 3 possible balls, and a boolean indicating whether this is a tenth frame.
If a ball has not been used yet, it is represented as a -1.

Game represents a collection of 10 frames, 9 normal frames and 1 tenth frame.
A Game contains an ArrayList<Frame> containing all 10 frames.

## Building

I will try to keep the latest libscore.jar in the repository root, but the project can be manuall built by running `build.sh`.

## Issues

This project has not yet been thouroughly tested, so there may be bugs. Please create an issue if you find one.

## Frame 

### Constructors

>> Frame()
> Constructs a new (normal, not tenth) Frame.

>> Frame(boolean isTenth)
> Constructs a new Frame in normal mode if `isTenth` is false, or tenth mode if `isTenth` is true.

### Methods

>> boolean isComplete()
> Returns `true` if all nessecary shots have been populated.

>> boolean isStrike()
> Returns `true` if this frame is a strike (pinfall of 10 on first ball).

>> boolean isSpare()
> Returns `true` if this frame is a spare (combined pinfall of 10 over first and second ball) (does not take 3 ball of tenth frames into account).

>> boolean setNextBall(int i)
> Attemps to set the next unoccupied ball to the value specified by `i`. Corrects `i` if it is over or under the possible limit.
> If the ball is successfully set, returns `true`.

>> int totalPinfall()
> Returns the total real pinfall of this frame. Will **NOT** count negative the pinfall amounts used to denote an untaken ball.

>> boolean isTenth()
> Returns `true` if this frame is a tenth frame.

>> int pinfallFirst()
> Returns the pinfall amount of the first ball. If a pinfall amount is negative (used to denote an unused ball), will return 0.

>> int pinfallSecond()
> Returns the pinfall amount of the Second ball. If a pinfall amount is negative (used to denote an unused ball), will return 0.

>> int pinfallThird()
> Returns the pinfall amount of the third ball. If a pinfall amount is negative (used to denote an unused ball), will return 0.
> Will always return 0 if this frame is not a tenth frame.

>> boolean hasPinfallFirst()
> Returns `true` if the first ball has been taken.

>> boolean hasPinfallSecond()
> Returns `true` if the second ball has been taken.

>> boolean hasPinfallThird()
> Returns `true` if the third ball has been taken.

>> int rawPinfallFirst()
> Same as `int pinfallFirst()`, but will not change negative values.

>> int rawPinfallSecond()
> Same as `int pinfallSecond()`, but will not change negative values.

>> int rawPinfallThird()
> Same as `int pinfallThird()`, but will not change negative values.

>> void setIsTenth(boolean b)
> Sets whether this frame is a tenth frame. (true = tenth, false = normal)

>> void setPinfallFirst(int i)
> Sets pinfall for first ball. Will not correct impossible pinfall combinations.

>> void setPinfallSecond(int i)
> Sets pinfall for second ball. Will not correct impossible pinfall combinations.

>> void setPinfallThird(int i)
> Sets pinfall for third ball. Will not correct impossible pinfall combinations.

>> String toString()
> Returns a String in the format of: 
> Normal frame: `Frame[pinfallFirst, pinfallSecond]`
> Tenth frame: `Frame[pinfallFirst, pinfallSecond, pinfallThird]`

## Game

### Constructor

>> Game()
> Constructs a new Game with 10 new Frames.

### Methods

>> boolean isComplete()
> Returns `true` if all frames in this game are complete.

>> boolean setNextBall(int i)
> Attemps to set the next unoccupied ball of the next incomplete frame to the value specified by `i`. Corrects `i` if it is over or under the possible limit.
> If the ball is successfully set, returns `true`.

>> int getTotalScore()
> Returns this game's total score so far.
> Includes the incomplete scores of recent closed frames.

>> Frame currentFrame()
> Returns the next unfinished Frame.

>> int currentFrameIndex()
> Returns the index of the next unfinished Frame. Will be from 0-9.

>> int getScoreAtFrame(int i)
> Returns the score as of the Frame at index `i` (0-9).
> Will return 0 if i < 1 or i > 9.

>> ArrayList<Frame> getFrames()
> Returns the ArrayList of all 10 frames.

>> String toString()
> Returns a String in the format of `Game[frame1.toString(),frame2.toString(),...]`
