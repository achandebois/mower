# Mower
This exercise consists in moving mower over a lawn.
The configuration for the lawn and the mowers are provided by a file.
The lawn is a grid with increment of 1.
The mowers have to move over the lawn 3 potentials movements (Forward, turn left, turn right).

Forward increment by 1 the mower position according to the direction.
Turn left or right just make a rotation and change the direction of the mower.

Input file example
The first line correspond to the size of the lawn.
Line 2 is the initial position and direction of the mower.
Line 3 is the commands associated to the previous mower.
The next lines are a tuple of other mowers position and theirs commands associated.
```
5 5
1 2 N
LFLFLFLFF
3 3 E
FFRFFRFRRF
```

## Techno
- spring boot 2 (starter core and test)
- java 11
- lombok
- assertJ

## Usage

##### In IntelliJ
- Running MowerApplication main class. 
You just have to edit program arguments in order to add path to the file. 
There is a sample file input.txt in the resources directory
- Running or modifying test from the MowerService

##### Command line
`java -jar {jar_name} {path_to_the_file}`

## Comment
- My main app class and the run method inside the mower service are maybe not enough split
- The package structure is pretty simple and the approach could be more DDD like
- I added sneaky throws annotation to avoid checked exception
