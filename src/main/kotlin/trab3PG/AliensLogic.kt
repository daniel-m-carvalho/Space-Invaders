package trab3PG

fun Aliens.right(): Area = Area(pos3.x + 56,pos3.y + 50)

fun Aliens.outLimit():Limit {
    if(direction== 1)
        if(pos3.x + 56 >= 700) return Limit(true,- 1)
    if(direction == -1)
        if (pos3.x <= 0) return Limit(true,1)
    return Limit(false, direction)
}

fun Aliens.center() = Area(pos3.x + 28, pos3.y + 25)

fun Aliens.move(): Aliens {
    val newChange = (animChange+1)%2
    return copy(pos3 = Area(this.pos3.x + (ALIENS_STEP * this.direction), this.pos3.y),animChange= newChange)
}

fun createAliens(): List<Aliens> {
    val types = listOf(AlienType.A, AlienType.A, AlienType.B, AlienType.B, AlienType.C)
    val aliens = mutableListOf<Aliens>()

    types.forEachIndexed { index, type ->
        (0..10).mapTo(aliens) { createAlien(Area(it * 56, 50 + index * 50), type) }
    }

    return aliens
}

fun createAlien( area: Area, type: AlienType): Aliens {
    return Aliens(area, 1, 0, type)
}

fun Aliens.hit(shot: Shot?):Boolean{
    return shot!!.center().touch(pos3, right())

}
// suposto apagar alien se acertar lhe um tiro da nave