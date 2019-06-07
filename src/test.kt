import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.jupiter.api.Test as test

class TestSource2 {

    @test
    fun failOnSize() {

        val height= 4
        val width= 9
        val input =
        "........" +
        "....*..." +
        "...**..." +
        "........"

        assertFailsWith(InstantiationError::class) {
            createBoard(width, height, input)
        }
    }

    @test
    fun testGenerate() {

        val height= 2
        val width= 2
        val input =
            ".." +
            "*."

        val ok = BoardImpl(2,2,"..*.")
        val actual = createBoard(width, height, input)
        println( actual.toString())
        println("gyugyj")
        print(actual.getCell(1,1).toString())


        val empty = createEmptyBoard(2,2)
        println(empty.toString())


        assertEquals( ok.toString(), actual.toString())
    }

}