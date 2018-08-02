package net.bekwam.k4stem.labassist
import javafx.geometry.Insets
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.shape.StrokeLineCap
import javafx.scene.shape.StrokeType
import javafx.scene.text.Font
import tornadofx.*
import java.awt.geom.RoundRectangle2D

class Styles : Stylesheet() {
    companion object {
        val hoverColor by cssclass()
        val normalStyle by cssclass()
        val searchBarLabel by cssclass()
    }

    init {
        normalStyle {
            menuBar{
                backgroundColor += Color.ALICEBLUE
                font = Font.font("Verdana")
                label{
                    textFill = Color.SIENNA
                }
            }
            tableView {
                tableColumn {
                    label {
                        fontSize = 24.px
                        textFill = Color.IVORY
                        backgroundColor += Color.BURLYWOOD
                        borderColor += box(Color.SIENNA)
                    }
                }
                tableCell {
                    borderColor += box(Color.SIENNA)
                    textFill = Color.SIENNA
                    font = Font.font("Verdana")

                }
                tableRowCell {

                    and(odd) {
                        backgroundColor += Color.BURLYWOOD
                        and(hover) {
                            backgroundColor += Color.PALEVIOLETRED
                        }
                        and(selected) {
                            backgroundColor += Color.ALICEBLUE
                        }
                    }
                    and (even){
                        backgroundColor += Color.IVORY
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
                backgroundColor += Color.IVORY
            }
            button {
                backgroundColor += Color.LIGHTGOLDENRODYELLOW
                textFill = Color.SIENNA
                font = Font.font("Verdana")
            }
            textField {
                backgroundColor += Color.IVORY
                borderColor += box(Color.SIENNA)
                font = Font.font("Verdana")
            }

            checkBox{
                textFill = Color.SIENNA
                fontSize = 16.0.px
                font = Font.font("Verdana")
                box{
                    backgroundColor += Color.ALICEBLUE

                }
            }
            comboBox{
                backgroundColor += Color.IVORY
                font = Font.font("Verdana")
                borderColor += box(Color.SIENNA)
                cell{
                    backgroundColor += Color.IVORY
                    textFill = Color.SIENNA
                    borderColor += box(Color.SIENNA)
                    and(hover){
                        backgroundColor += Color.PALEVIOLETRED
                    }
                }
            }
            label{
                textFill = Color.SIENNA
            }

        }
        searchBarLabel {
            font = Font.font("Verdana")
            textFill = Color.SIENNA
            fontSize = 32.0.px
        }
        alert{
            backgroundColor += Color.POWDERBLUE
            font = Font.font("Verdana")
            fontSize = 24.px
            textFill = Color.SIENNA
        }


    }
}