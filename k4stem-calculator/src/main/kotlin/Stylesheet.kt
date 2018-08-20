import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.paint.Color
import tornadofx.*

class Styles : Stylesheet() {
    val opButton by cssclass()

    init {

        button {
            prefWidth = 60.px
            prefHeight = 50.px
            backgroundColor += Color.LIGHTGRAY
            Insets(0.0)
            backgroundRadius += box(0.px)
            borderColor += box(Color.web("#979797"))
            borderWidth += box(0.5.px)
            and(armed) {
                backgroundColor += Color.DARKGRAY
            }
        }

        root {
            fontSize = 18.px
        }
        textField {
            alignment = Pos.BOTTOM_RIGHT
            padding = box(20.px, 5.px, 5.px, 5.px)
            fontSize = 40.px
            textFill = Color.web("#e8e8e8")
            backgroundColor+= Color.web("#494b4e")
        }
        opButton{
            backgroundColor+= Color.web("#ff9d42")
            and(armed){
                backgroundColor+= Color.web("#bf6937")
            }
        }

    }
}