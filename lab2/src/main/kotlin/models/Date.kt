package models





class Date(val year: Int, val month: Int, val day: Int) {
    override fun toString(): String {
        return "Date(year=$year, month=$month, day=$day)"
    }
}


fun Date.leapYear() : Boolean = this.year % 4 == 0

fun Date.validDate() : Boolean = this.month in 1..12 && this.day in 1 .. 31 && this.year > 0


