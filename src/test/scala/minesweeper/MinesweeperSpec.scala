package minesweeper

import minesweeper.acceptance.Minesweeper._
import org.scalatest.{Matchers, WordSpec}

class MinesweeperSpec extends WordSpec with Matchers {

  "Coord" when {
    "getSurrounding" should {
      "get perimeter for 3x3 and coord is center" in {
        Coord(1, 1).getSurrounding(Grid(3, 3)) shouldBe Seq(
          Coord(0, 0), Coord(0, 1), Coord(0, 2),
          Coord(1, 0), Coord(1, 2),
          Coord(2, 0), Coord(2, 1), Coord(2, 2)
        )
      }
    }
  }

  "Grid" when {
    "map" should {
      "map over all cells" in {
        Grid(3,3).map(c => c) shouldBe Seq(
          Coord(0,0),
          Coord(0,1),
          Coord(0,2),
          Coord(1,0),
          Coord(1,1),
          Coord(1,2),
          Coord(2,0),
          Coord(2,1),
          Coord(2,2)
        )
      }
    }
  }

  "Coord" when {
    "isSurrounding" should {
      "be true if coord is surrounding" in {
        val coord = Coord(1,1)
        coord.isSurrounding(Coord(0,0)) shouldBe true
        coord.isSurrounding(Coord(0,1)) shouldBe true
        coord.isSurrounding(Coord(0,2)) shouldBe true
        coord.isSurrounding(Coord(1,0)) shouldBe true
        coord.isSurrounding(Coord(1,2)) shouldBe true
        coord.isSurrounding(Coord(2,0)) shouldBe true
        coord.isSurrounding(Coord(2,1)) shouldBe true
        coord.isSurrounding(Coord(2,2)) shouldBe true
      }
      "be false if coord is the same" in {
        val coord = Coord(1,1)
        coord.isSurrounding(Coord(1,1)) shouldBe false
      }
      "be false if coord is not surrounding" in {
        val coord = Coord(1,1)
        coord.isSurrounding(Coord(3,0)) shouldBe false
        coord.isSurrounding(Coord(5,5)) shouldBe false
      }
    }
  }
}
