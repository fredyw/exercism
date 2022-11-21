class Robot(var gridPosition: GridPosition, var orientation: Orientation) {
    constructor() : this(GridPosition(x = 0, y = 0), Orientation.NORTH)

    fun simulate(instructions: String) {
        instructions.forEach {
            when (it) {
                'L' -> orientation.left()
                'R' -> orientation.right()
                else -> advance()
            }
        }
    }

    private fun advance() {
        gridPosition = when (orientation) {
            Orientation.NORTH -> GridPosition(x = gridPosition.x, y = gridPosition.y + 1)
            Orientation.EAST -> GridPosition(x = gridPosition.x + 1, y = gridPosition.y)
            Orientation.SOUTH -> GridPosition(x = gridPosition.x, y = gridPosition.y - 1)
            Orientation.WEST -> GridPosition(x = gridPosition.x - 1, y = gridPosition.y)
        }
    }

    private fun Orientation.left() {
        orientation = when (this) {
            Orientation.NORTH -> Orientation.WEST
            Orientation.EAST -> Orientation.NORTH
            Orientation.SOUTH -> Orientation.EAST
            Orientation.WEST -> Orientation.SOUTH
        }
    }

    private fun Orientation.right() {
        orientation = when (this) {
            Orientation.NORTH -> Orientation.EAST
            Orientation.EAST -> Orientation.SOUTH
            Orientation.SOUTH -> Orientation.WEST
            Orientation.WEST -> Orientation.NORTH
        }
    }
}
