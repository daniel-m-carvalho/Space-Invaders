package trab3PG
import pt.isel.canvas.*

fun Shot.draw(canvas: Canvas, color:Int){
    canvas.drawRect(pos2.x,pos2.y,4,7,color)
} // shot is drawn

fun Canvas.drawAliens(aliens: Aliens){
    val change = aliens.animChange * 112
    drawImage("invaders|$change,${aliens.type.id},$CELL_SIDE,$CELL_SIDEHeight",aliens.pos3.x,aliens.pos3.y,56,50)
}

fun scoreDraw(canvas: Canvas, score:Int){
    canvas.drawText(5,490,"$score", WHITE,20)
}

fun Game.draw(canvas: Canvas){
    if(!over){
        canvas.erase()
        ship.draw(canvas)
        for(alienShot in alienShots){
            alienShot.draw(canvas,RED)
        }
        for(aliens in aliens){
            canvas.drawAliens(aliens) }
        scoreDraw(canvas,score)
    }
    else{
        if (aliens == emptyList<Aliens>()) {canvas.drawText(250,490,"YOU WIN", GREEN,30)}
        else
        canvas.drawText(250,490,"GAME OVER",RED,30)
    }
} // the game is drawn

fun Spaceship.draw(canvas:Canvas){
    canvas.drawImage("spaceship",left().x,left().y,50,30)
    shot?.draw(canvas,WHITE)
} // the ship is drawn

