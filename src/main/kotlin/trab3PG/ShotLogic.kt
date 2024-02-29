package trab3PG

fun Shot.center() = Area(pos2.x+2,pos2.y+7/2)

fun Shot.move(): Shot {
    val q= Area(pos2.x, pos2.y + speed)
    return copy(pos2=q)
}
