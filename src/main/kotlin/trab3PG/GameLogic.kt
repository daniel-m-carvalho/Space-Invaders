package trab3PG
import pt.isel.canvas.Canvas
import pt.isel.canvas.KeyEvent

fun createGame(canvas: Canvas):Game{
    val area = Area(canvas.width,canvas.height)
    return Game(area, emptyList(), Spaceship(Area(canvas.width/2,450),null),
        false, aliens = createAliens(), score = 0)
}

fun Game.moveAliens():Game {
    val limit = outLimit()
    if(limit.reached){
        return copy(aliens=aliens.map {it.copy(pos3 = Area(it.pos3.x,it.pos3.y + ALIENS_STEPDown), direction = limit.direction)}, score = score)
    }
    return copy(aliens = aliens.map {it.move()}, score = score)
}

fun Game.outLimit(): Limit{
        aliens.forEach {
            val limit = it.outLimit()
            if (limit.reached)
               return limit
        }
        return Limit(false, 0)
}


fun Game.alienCollision(): AlienType?{
    aliens.forEach { if(it.hit(ship.shot)) return it.type}
    return null
}

fun Game.aliensHit():List<Aliens>{
    if(ship.shot != null) {
        val alien = alienCollision()
        if (alien != null) {
            return aliens.filter { it.pos3.y < 500  && !it.hit(ship.shot) }
        }
    }
    return aliens.filter { it.pos3.y < 500}
}

fun Game.validAliens(): AlienType?{
    return alienCollision()
}

fun Game.shotsColl():Boolean{
    alienShots.forEach { if (it.center() == ship.shot!!.pos2) return true }
    return false
}

fun Game.count():Int{
    if (ship.shot != null) {
        if (shotsColl()) return score + 1
        when (validAliens()) {
            AlienType.A -> return score + 10
            AlienType.B -> return score + 20
            AlienType.C -> return score + 30
        }
    }
    return score
}

fun Game.moveShot():Shot?{
    if (ship.shot != null && aliens.map{ alienCollision() }.reduceOrNull { c, _ ->  c } == null) {
        if (!(ship.shot.pos2).touch(Area(0,0),area)) return null
    return ship.shot.move()
    }
    return null
}

fun Game.moveShip(x:Int): Game {
    val i= ship.move(x)
    return Game(this.area, this.alienShots, i, this.over, aliens = this.aliens, score = score)
}

fun Game.aliensShotMove():List<Shot>{
    if(ship.shot != null)
        return alienShots.map{ it.move()}.filter{ it.pos2.y< 500 && !it.center().touch(ship.shot.pos2, ship.shot.pos2+ Area(4, 7))}
    return alienShots.map{it.move()}.filter{it.pos2.y< 500}
}

fun Game.newShot(): Game {
    if (ship.shot!=null) return this
    val nextShot= Shot(Area(ship.left().x + (25), ship.left().y - (5)), -4)
    return copy(ship= ship.setShot(nextShot), score = score)
}


fun Game.newAlienShot(area:Area, speed:Int)= copy(alienShots= alienShots + listOf(Shot(area, speed)), score = score)

fun Game.development(): Game {
    if(over) return this
    return copy(
        alienShots = aliensShotMove(),
        ship = ship.copy(shot= moveShot()),
        over= ship.shotCollision(alienShots).or(aliens.isEmpty()).or(ship.alienCollision(aliens)),
        aliens = aliensHit(),
        score = count()
    )
}

fun Game.spaceKey(it: KeyEvent): Game =
    when (it.code) {
        ESCAPE_CODE -> newShot()
        else-> this
    }