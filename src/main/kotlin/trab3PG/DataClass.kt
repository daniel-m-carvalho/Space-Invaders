package trab3PG

data class Area(val x:Int,val y:Int){
    operator fun plus(area: Area): Area {
        return Area(x + area.x, y + area.y)
    }
    operator fun minus(area: Area): Area {
        return Area(x - area.x, y - area.y)
    }
}

data class Limit(val reached:Boolean, val direction:Int)
data class Spaceship(val pos1: Area, val shot: Shot?)
data class Shot(val pos2: Area, val speed: Int)
data class Aliens(val pos3: Area, val direction: Int, val animChange: Int, val type: AlienType)
enum class AlienType(val id: Int, val points: Int) { A(0, 30), B(80, 20), C(160, 10) }

data class Game(val area: Area, val alienShots: List<Shot>, val ship: Spaceship,
                val over: Boolean, val score:Int = 0 ,val aliens: List<Aliens>)

const val ESCAPE_CODE = 32// for macbook, to Windows is 27
const val CELL_SIDE = 112
const val CELL_SIDEHeight = 80
const val ALIENS_STEP = 4
const val ALIENS_STEPDown = 20

