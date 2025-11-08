import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import kotlin.test.assertEquals

class FlowerFieldBoardTest {

    /*
     * See https://github.com/junit-team/junit4/wiki/Rules for information on JUnit Rules in general and
     * ExpectedExceptions in particular.
     */
    @Rule
    @JvmField
    var expectedException: ExpectedException = ExpectedException.none()

    @Test
    fun testInputBoardWithNoRowsAndNoColumns() {
        val inputBoard = emptyList<String>()
        val expectedNumberedBoard = emptyList<String>()
        val actualNumberedBoard = FlowerFieldBoard(inputBoard).withNumbers()

        assertEquals(expectedNumberedBoard, actualNumberedBoard)
    }

    @Test
    fun testInputBoardWithOneRowAndNoColumns() {
        val inputBoard = listOf("")
        val expectedNumberedBoard = listOf("")
        val actualNumberedBoard = FlowerFieldBoard(inputBoard).withNumbers()

        assertEquals(expectedNumberedBoard, actualNumberedBoard)
    }

    @Test
    fun testInputBoardWithNoFlowers() {
        val inputBoard = listOf(
                "   ",
                "   ",
                "   "
        )

        val expectedNumberedBoard = listOf(
                "   ",
                "   ",
                "   "
        )

        val actualNumberedBoard = FlowerFieldBoard(inputBoard).withNumbers()

        assertEquals(expectedNumberedBoard, actualNumberedBoard)
    }

    @Test
    fun testInputBoardWithOnlyFlowers() {
        val inputBoard = listOf(
                "***",
                "***",
                "***"
        )

        val expectedNumberedBoard = listOf(
                "***",
                "***",
                "***"
        )

        val actualNumberedBoard = FlowerFieldBoard(inputBoard).withNumbers()

        assertEquals(expectedNumberedBoard, actualNumberedBoard)
    }

    @Test
    fun testInputBoardWithSingleFlowerAtCenter() {
        val inputBoard = listOf(
                "   ",
                " * ",
                "   "
        )

        val expectedNumberedBoard = listOf(
                "111",
                "1*1",
                "111"
        )

        val actualNumberedBoard = FlowerFieldBoard(inputBoard).withNumbers()

        assertEquals(expectedNumberedBoard, actualNumberedBoard)
    }

    @Test
    fun testInputBoardWithFlowersAroundPerimeter() {
        val inputBoard = listOf(
                "***",
                "* *",
                "***"
        )

        val expectedNumberedBoard = listOf(
                "***",
                "*8*",
                "***"
        )

        val actualNumberedBoard = FlowerFieldBoard(inputBoard).withNumbers()

        assertEquals(expectedNumberedBoard, actualNumberedBoard)
    }

    @Test
    fun testInputBoardWithSingleRowAndTwoFlowers() {
        val inputBoard = listOf(" * * ")

        val expectedNumberedBoard = listOf("1*2*1")

        val actualNumberedBoard = FlowerFieldBoard(inputBoard).withNumbers()

        assertEquals(expectedNumberedBoard, actualNumberedBoard)
    }

    @Test
    fun testInputBoardWithSingleRowAndTwoFlowersAtEdges() {
        val inputBoard = listOf("*   *")

        val expectedNumberedBoard = listOf("*1 1*")

        val actualNumberedBoard = FlowerFieldBoard(inputBoard).withNumbers()

        assertEquals(expectedNumberedBoard, actualNumberedBoard)
    }

    @Test
    fun testInputBoardWithSingleColumnAndTwoFlowers() {
        val inputBoard = listOf(
                " ",
                "*",
                " ",
                "*",
                " "
        )

        val expectedNumberedBoard = listOf(
                "1",
                "*",
                "2",
                "*",
                "1"
        )

        val actualNumberedBoard = FlowerFieldBoard(inputBoard).withNumbers()

        assertEquals(expectedNumberedBoard, actualNumberedBoard)
    }

    @Test
    fun testInputBoardWithSingleColumnAndTwoFlowersAtEdges() {
        val inputBoard = listOf(
                "*",
                " ",
                " ",
                " ",
                "*"
        )

        val expectedNumberedBoard = listOf(
                "*",
                "1",
                " ",
                "1",
                "*"
        )

        val actualNumberedBoard = FlowerFieldBoard(inputBoard).withNumbers()

        assertEquals(expectedNumberedBoard, actualNumberedBoard)
    }

    @Test
    fun testInputBoardWithFlowersInCross() {
        val inputBoard = listOf(
                "  *  ",
                "  *  ",
                "*****",
                "  *  ",
                "  *  "
        )

        val expectedNumberedBoard = listOf(
                " 2*2 ",
                "25*52",
                "*****",
                "25*52",
                " 2*2 "
        )

        val actualNumberedBoard = FlowerFieldBoard(inputBoard).withNumbers()

        assertEquals(expectedNumberedBoard, actualNumberedBoard)
    }

    @Test
    fun testLargeInputBoard() {
        val inputBoard = listOf(
                " *  * ",
                "  *   ",
                "    * ",
                "   * *",
                " *  * ",
                "      "
        )

        val expectedNumberedBoard = listOf(
                "1*22*1",
                "12*322",
                " 123*2",
                "112*4*",
                "1*22*2",
                "111111"
        )

        val actualNumberedBoard = FlowerFieldBoard(inputBoard).withNumbers()

        assertEquals(expectedNumberedBoard, actualNumberedBoard)
    }

}
