package trab3PG

fun Area.touch(topLeft: Area, botRight: Area)= x >= topLeft.x && x <= botRight.x && y >= topLeft.y && y <= botRight.y

fun Spaceship.move(deltaX:Int): Spaceship {
    val s= Spaceship(Area(deltaX,pos1.y), shot)
    return if (deltaX+25<=700 && deltaX-25>=0){s}
    else this
}

fun Spaceship.left(): Area = Area(pos1.x - 25,pos1.y - 15)

fun Spaceship.right(): Area = Area(pos1.x + 25, pos1.y)

fun Spaceship.shotCollision(alienShot:List<Shot>): Boolean {
    return (alienShot.map{ it.center().touch(left(),right())}.reduceOrNull{ c, b-> c || b}?: false)
}

fun Spaceship.alienCollision(aliens:List<Aliens>): Boolean {
    return (aliens.map { it.center().touch(left(), right()) }.reduceOrNull { c, b -> c || b } ?: false)}

fun Spaceship.setShot(s: Shot): Spaceship {
    return copy(shot= s)
}

