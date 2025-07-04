fun printTitle(char : Char = '=', title : String, panjang : Int = 50){
    val titleLength = title.length

    // ALGORITMA //
    // PENGECEKAN TERLEBIH DAHULU APAKAH PANJANG JUDUL LEBIH DARI PANJANG GARIS
    if (titleLength > panjang) {
        println(title) // MENCETAK JUDUL TANPA CHAR
        return
    }

    // MENGHITUNG SISA CHAR
    val remainingChars = panjang - titleLength

    // MENGHITUNG CHAR DISISI KIRIR
    val leftPadding = remainingChars / 2

    // MENGHITUNGG CHAR DI SISI KANAN (KASUS GANJIL)
    val rightPadding = remainingChars - leftPadding

    // PRINT PADDING KIRIR
    for (i in 0..< leftPadding) {
        print(char)
    }
    // PRINT JUDUL
    print(title)

    // PRINT PADDING KANAN
    for (i in 0..< rightPadding) {
        print(char)
    }
    println()
}

fun main() {
    // ARRAY DATA
    val hewan = arrayOf("Kucing", "Ikan", "kambing")

    // FUNCTION BIASA
    printTitle(title = "FUNCTION BIASA")
    printArray(hewan)
    println()

    // FUNCTION DEFAULT PARAMETER
    printTitle(title = "FUNCTION DEFAULT PARAMETER")
    printArray()
    println()

    // FUNCTION NAMED PARAMETER
    printTitle(title = "FUNCTION NAMED PARAMEETER")
    printArray(
        data = arrayOf("Bebek", "Ayam", "Dinosaurus")
    )
    println()


    // LUAS PESEGI PANJANG WITH SINGLE EXPRESIION
    printTitle(title = "LUAS PEERSEGI PANJANG SINGLE EXPRESION")
    println("Luas Perseegi = ${luasPersegi(sisi = 20)}")
    println()

    // SINGLE EXPRESSION
    printTitle(title = "SINGLE EXPRESSION")
    val name = "Andi"
    println(name.hello())

    // EXTENSION FUNCTION
    printTitle(title = "EXTENSION FUNCTION")
    println("Luas Persegi  = ${20.luasPersegiExtension()}")
    println()

    // LAMBDA EXPRESSION
    crud.printTitle(title = "LAMBDA EXPRESSION")
    val hitungLuasPersegi : (Int) -> Int = {sisi : Int -> sisi.luasPersegiExtension()}
    println("[LAMBDA EXP] Luas Persegi = ${hitungLuasPersegi(4)}")
    println()

    // LAMBDA EXPRESSION IT KEYWORD
    printTitle(title = "LAMBDA EXPRESSION 'IT' KEYWORD")
    val hitungLuasPersegiIt : (Int) -> Int = {it.luasPersegiExtension()}
    println("[LAMBDA EXP IT KEY] Luas Persegi = ${hitungLuasPersegiIt(5)}")
    println()

    // LAMBDA EXPRESSION METHOD REFERENCE
    printTitle(title = "LAMBDA EXPRESSION METHOD REFERENCES")
    val hitungLuasPersegiMethRef : (Int) -> Int = ::luasPersegi
    println("[LAMBDA EXP METHOD REFERENCE] Luas Persegi = ${hitungLuasPersegiMethRef(6)}")
    println()
}

fun printArray(data : Array<String> = arrayOf("Data 1", "Data 2" , "Data 3", "Data n")){
    for (i in data.indices) println("${i + 1}. ${data[i]}")
}

fun luasPersegi(sisi : Int = 4) : Int = sisi * sisi

fun Int.luasPersegiExtension() : Int = this * this

fun String.hello() : String = "Hello $this"
