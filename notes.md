# Enemy movement

start: x = y = 20
end: x = 160, y = 80
distance: d > 0

## turn-points (x,y):

- 0: (160, 20)
- 1: (160, 50)
- 2: (20, 50)
- 3: (20, 80)

turnsX = listOf(20, 160)
turnsY = listOf(20, 50, 80)

## forwards or backwards?

forwards = turnsY.indexOf(y)!! % 2 == 0

## ( x < 160 && y == 20)

x = if(x+d > 160) {
moveY()
160
}
else {
x + d
}

# Persistence

- player-data
    - settings
        - language
        - resolution?
        - sound?
        - game-play
            - allow words more than once per game
            - difficulty? (maybe multiply enemy-health)
            - number of turns
- game-data
    - typed words
    - game-time
    - language
    - startHealth
    - endHealth
    - total word-damage

## TODO

- construct user-data-model
- implement JSON (de-)serialisation
- implement Player-Menu
- implement File-Management structure