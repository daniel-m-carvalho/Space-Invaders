package trab3PG
import pt.isel.canvas.BLACK
import pt.isel.canvas.Canvas
import pt.isel.canvas.onFinish
import pt.isel.canvas.onStart
import kotlin.random.Random

fun main(){
    val arena= Canvas(700, 500, BLACK)
    var game= createGame(arena)
    game.draw(arena)
    onStart {
        arena.onMouseMove {
            game=game.moveShip(it.x)
        }
        arena.onMouseDown {
            game= game.newShot()
        }
        arena.onKeyPressed {
            game=game.spaceKey(it)
        }
        arena.onTimeProgress(700){
           if(Random.nextBoolean() && game.aliens.isNotEmpty())
               game= game.newAlienShot(game.aliens.random().center(),Random.nextInt(1,5))
            game=game.moveAliens()
        }
        arena.onTimeProgress(1000/70){
            game.draw(arena)
            game=game.development()
        }
        onFinish { }
    }
}
