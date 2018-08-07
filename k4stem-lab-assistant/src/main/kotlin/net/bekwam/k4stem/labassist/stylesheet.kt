package net.bekwam.k4stem.labassist
import javafx.geometry.Insets
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Color
import javafx.scene.paint.Color.rgb
import javafx.scene.paint.Paint
import javafx.scene.shape.StrokeLineCap
import javafx.scene.shape.StrokeType
import javafx.scene.text.Font
import tornadofx.*
import java.awt.geom.RoundRectangle2D

class Styles : Stylesheet() {
    companion object {

        val normalStyle by cssclass()
        val searchBarLabel by cssclass()

        val c1 = Color.web("#50F205")
        val c2 = Color.web("F2E205")
        val backColor = Color.web("#26261e")
        val oddCellColor = Color.web("#049dbf")
        val evenCellColor = Color.web("#f6f8f9")
    }

    init {
        normalStyle {
            menuBar{
                backgroundColor += backColor
                font = Font.font("Verdana")
                label{
                    textFill = c1
                    and(hover){
                        backgroundColor+= oddCellColor
                    }
                }
                menu{
                    and(hover){
                        backgroundColor+= oddCellColor
                    }

                    menuItem{
                        and(hover){
                            backgroundColor+= oddCellColor
                        }
                        backgroundColor += evenCellColor
                    }
                }
                and(selected){
                    backgroundColor+= oddCellColor
                }


            }
            tableView {
                tableColumn {
                    label {
                        fontSize = 24.px
                        textFill = evenCellColor
                        backgroundColor += oddCellColor
                        borderColor += box(backColor)
                    }
                }
                tableCell {
                    borderColor += box(backColor)
                    textFill = backColor
                    font = Font.font("Verdana")

                }
                tableRowCell {

                    and(odd) {
                        backgroundColor += oddCellColor
                        and(hover) {
                            backgroundColor += Color.PALEVIOLETRED
                        }
                        and(selected) {
                            backgroundColor += Color.ALICEBLUE
                        }
                    }
                    and (even){
                        backgroundColor += evenCellColor
                        and (hover) {
                            backgroundColor += Color.PALEVIOLETRED
                        }
                        and(selected){
                            backgroundColor += Color.ALICEBLUE
                        }

                    }


                }

                fixedCellSize = 48.px
                fontSize = 24.px
                font = Font.font("Verdana")
                backgroundColor += evenCellColor
            }
            button {
                backgroundColor += evenCellColor
                textFill = backColor
                font = Font.font("Verdana")
            }
            textField {
                backgroundColor += evenCellColor
                borderColor += box(backColor)
                font = Font.font("Verdana")
            }

            checkBox{
                textFill = c1
                fontSize = 16.0.px
                font = Font.font("Verdana")
                box{
                    backgroundColor += evenCellColor

                }
            }
            comboBox{
                backgroundColor += evenCellColor
                font = Font.font("Verdana")
                borderColor += box(c1)
                cell{
                    backgroundColor += evenCellColor
                    textFill = c1
                    borderColor += box(backColor)
                    and(hover){
                        backgroundColor += Color.PALEVIOLETRED
                    }
                }
            }
            label{
                textFill = c1
            }

        }
        searchBarLabel {
            font = Font.font("Verdana")
            textFill = Color.SIENNA
            fontSize = 32.0.px
        }



    }
}