package minesweeper.acceptance

import java.awt.event.{MouseEvent, MouseAdapter}
import java.awt.{Color, Graphics, Insets, Component}
import javax.swing.BorderFactory
import javax.swing.border.Border
import minesweeper.acceptance.Minesweeper.{Coord, Grid, Game}
import scala.swing.{GridPanel, Label, Dimension, MainFrame}

object MinesweeperUI {

  def main(args: Array[String]) = {
    val game = Game(Grid(5,7), Seq(Coord(2, 2),Coord(2,4)))
    val ui = new UI(game)
    ui.visible = true
    println("End of main function")
  }

}

class UI(game: Game) extends MainFrame {
  title = "Minesweeper"
  preferredSize = new Dimension(320,240)
  contents = new Grid(game)
}

class Grid[A,B](var game: Game) extends GridPanel(game.grid.row,game.grid.col) {

  val labelBorder = BorderFactory.createLineBorder(Color.BLACK)

  val coordMap = game.grid.map{ coord =>
    val lab: Label = createLabel(coord)
    contents += lab
    (lab.name,(lab,coord))
  }.toMap

  def createLabel(coord: Coord):Label = {
    val label = new Label("-") {
      border = labelBorder
      name = coord.toString
    }
    label.peer.addMouseListener(new MouseAdapter {
      override def mouseClicked(e: MouseEvent): Unit = {
        game = game.openCoord(coord)
        updateLabels
      }
    })
    label
  }

  def updateLabels = {
    println(coordMap.size)
    coordMap.foreach {case (cordName,(label,coord)) =>
      val s = game.coordState(coord)
      findCoord(cordName).foreach{case (l,_)=> l.peer.setText(s)}
    }
    println(game)
  }

  def findCoord(name: String): Option[(Label,Coord)] = coordMap.get(name)
}


