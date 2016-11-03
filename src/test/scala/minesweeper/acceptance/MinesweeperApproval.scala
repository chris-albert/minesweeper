package minesweeper.acceptance

import minesweeper.acceptance.Minesweeper._
import org.approvaltests.Approvals
import org.approvaltests.reporters.UseReporter
import org.approvaltests.reporters.macosx.DiffMergeReporter
import org.junit.Test

@UseReporter(Array(classOf[DiffMergeReporter]))
class MinesweeperApproval {

  @Test
  def createFunctionalGameOneBomb = {

    val grid = Grid(3, 5)
    val bombCoord = Coord(1, 2)
    val game = Game(grid, Seq(bombCoord), grid.coords)
    //validate game
    Approvals.verify(game)
  }

  @Test
  def createFunctionalGameTwoBomb = {

    val grid = Grid(3, 5)
    val bombCoord = Coord(1, 2)
    val otherBombCoord = Coord(1, 1)
    val game = Game(grid, Seq(bombCoord, otherBombCoord), grid.coords)
    //validate game
    Approvals.verify(game)
  }

  @Test
  def createFunctionalGameOpenTwo = {

    val grid = Grid(3, 5)
    val bombCoord = Coord(1, 2)
    val otherBombCoord = Coord(1, 1)
    val game = Game(grid, Seq(bombCoord, otherBombCoord), Seq(Coord(0, 0), bombCoord))
    //validate game
    Approvals.verify(game)
  }

  @Test
  def createFunctionalGamePlayTwo = {

    val grid = Grid(3, 5)
    val bombCoord = Coord(1, 2)
    val otherBombCoord = Coord(1, 1)
    val game = Game(grid, Seq(bombCoord, otherBombCoord))

    val moveOne = Coord(0, 0)
    val moveTwo = bombCoord
    val gameAfterMoveOne = game.openCoord(moveOne)
    val gameAfterMoveTwo = gameAfterMoveOne.openCoord(moveTwo)

    Approvals.verify(gameAfterMoveTwo)
  }

  @Test
  def createFunctionalGameCascadeOpen = {

    val game = Game(Grid(5,5), Seq(Coord(4, 4)))

    Approvals.verify(game.openCoord(Coord(0, 0)))
  }

  @Test
  def createFunctionalGameCascadeOpenSurround = {

    val game = Game(Grid(5,7), Seq(Coord(2, 2),Coord(2,4)))

    Approvals.verify(game.openCoord(Coord(0, 0)))
  }

}
