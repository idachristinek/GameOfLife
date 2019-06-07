enum class Direction(height: Int, width: Int) {
    UP(1, 0),
    UPRIGHT(1, 1),
    RIGHT(0, 1),
    RIGHTDOWN(-1, 1),
    DOWN(-1, 0),
    DOWNLEFT(-1, -1),
    LEFT(0, -1),
    LEFTUP(1, -1);
}

enum class Status { DEAD, ALIVE }

