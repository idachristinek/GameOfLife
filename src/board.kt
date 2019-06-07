import Direction.*


data class Cell(val i: Int, val j: Int, var value: Status) {
    override fun toString(): String =
        if (value == Status.ALIVE) "*" else "."
}

interface Board {
    val width: Int
    val height: Int
    val input: String?

    fun getCell(i: Int, j: Int): Cell
    fun getAllCells(): Collection<Cell>
    fun countLivingNeighbours(cell: Cell): Int
    fun getStatus(cell: Cell): Status
    fun Cell.getNeighbour(direction: Direction): Status
    fun inputOk(): Boolean
}


fun createBoard(width: Int, height: Int, input: String): Board = BoardImpl(width, height, input)
fun createEmptyBoard(width: Int, height: Int): Board = BoardImpl(width, height, null)


open class BoardImpl(override val width: Int, override val height: Int, override val input: String?) : Board {

    var cells = mutableListOf<Cell>()

    init {

        if (input != null && !inputOk()) {
            throw InstantiationError()
        }

        for (i in 1..height) {
            for (j in 1..width)
                cells.add(
                    Cell(
                        i, j,
                        if (input?.get((i * j) - 1) == '*') Status.ALIVE else Status.DEAD
                    )
                )
        }
    }


    override fun inputOk(): Boolean =
        input?.length == width * height

    override fun countLivingNeighbours(cell: Cell): Int {

        var antall = 0

        Direction.values().forEach {
            if (cell.getNeighbour(it) == Status.ALIVE)
                antall += 1

        }
        return antall
    }

    override fun getStatus(cell: Cell): Status {
        val count = countLivingNeighbours(cell)
        return if (count < 2 || count > 3) {
            Status.DEAD
        } else if (count == 3) {
            Status.ALIVE
        } else {
            cell.value
        }
    }

    override fun getCell(i: Int, j: Int): Cell {
        return cells.filter { it.i == i }.first { it.j == j }
    }

    override fun getAllCells(): Collection<Cell> {
        return cells
    }

    override fun Cell.getNeighbour(direction: Direction): Status {
        return when (direction) {
            UP -> if (i - 1 < 1) Status.DEAD else cells.filter { it.i == i - 1 }.first { it.j == j }.value
            DOWN -> if (i + 1 > width) Status.DEAD else cells.filter { it.i == i + 1 }.first { it.j == j }.value
            LEFT -> if (j - 1 < 1) Status.DEAD else cells.filter { it.j == j - 1 }.first { it.i == i }.value
            RIGHT -> if (j + 1 > width) Status.DEAD else cells.filter { it.j == j + 1 }.first { it.i == i }.value
            UPRIGHT -> if (i - 1 < 1) Status.DEAD else cells.filter { it.i == i - 1 }.first { it.j == j }.value
            RIGHTDOWN -> if (i - 1 < 1) Status.DEAD else cells.filter { it.i == i - 1 }.first { it.j == j }.value
            DOWNLEFT -> if (i - 1 < 1) Status.DEAD else cells.filter { it.i == i - 1 }.first { it.j == j }.value
            LEFTUP -> if (i - 1 < 1) Status.DEAD else cells.filter { it.i == i - 1 }.first { it.j == j }.value
        }
    }

    override fun toString(): String {
        var l = ""
        this.getAllCells().forEach { l = "$l$it" }
        return l
    }

}
