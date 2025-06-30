package crud

// PRINT TITLE
fun printTitle(panjang : Int = 50, char : Char = '=', title : String){
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

// PRINT DIVIDEN
fun printDividen(panjang : Int = 50, char : Char = '-'){
    for (i in 0..<panjang){
        print(char)
    }
    println()
}

// PRINT MENU
fun printMenu(menu : Array<String>){
    for (i in menu.indices){
        println("${i + 1}. ${menu[i]}")
    }
    println("0. Keluar")
}

// GET MENU/CHOOSE MENU
fun getMenu(menu : Array<String>) : Int? {
    // VALIDASI
    val option : Int?
    var isValid = false

    while (!isValid){
        print("> Masukan pilihan : ")
        val input = readlnOrNull()

        val check = input?.toIntOrNull()

        if (check != null && check <= menu.size && check >= 0){
            option = check - 1
            return option
        } else {
            println("> Opsi yang anda pilih tidak ditemukan. \n> Silahkan ulangi!")
            isValid = true
        }
    }
    return null
}

// VARIABEL UNTUK MENAMPUNG DATA (MENGGUNAKAN MUTABLE LIST KARENA UKURANNYA BISA BERUBAH UBAH)
// BERBEDA DENGAN ARRAY YANG UKURANNYA TETAP/FIXED SIZE

val listBarang = mutableListOf<String>() // UNTUK MENAMPUNG DATA BARANG

// CONDITION
fun runCrud(option: Int) {
    when (option) {
        0 -> tambahData()
        1 -> tampilData()
        2 -> printTitle(title = "Edit Data")
        3 -> printTitle(title = "Delete Data")
    }
}

// TAMBAH DATA
fun tambahData(){
    printTitle(title = "Tambah Data")
    print("> Masukan nama barang : ")
    val barang = readlnOrNull()

    // PENGECEKAN BARANG BUKAN INTEGER
    val check = barang?.toIntOrNull()

    if (barang != null){
        if (check == null){
            println("> $barang berhasil ditambahkan di Data Barang anda.")
            listBarang.add(barang)
        } else {
            println("> $barang tidak berhasil ditambahkan di Data Barang anda.")
        }
    } else {
        println("> Penambahan data tidak berhasil dilakukan.")
    }
}

// TAMPIL DATA
fun tampilData(){
    printTitle(title = "List Barang")
    for (i in 0..< listBarang.size){
        println("${i + 1}. ${listBarang[i]}")
    }
}

// MAIN FUNCTION
fun main() {
    // ARRAY MENU
    val menu = arrayOf(
        "Tambah Data",
        "Tampil Data",
        "Edit Data",
        "Hapus Data",
    )

    // BOOLEAN VARIABEL UNTUK EXIT
    var isExit = false

    // MAIN LOOPING
    do {
        printTitle(title = "Main Menu")
        printMenu(menu)
        printDividen()
        val option = getMenu(menu)

        if (option != null) {
            if (option == -1){
                isExit = true
            } else {
                runCrud(option)
            }
        }

    } while (!isExit)
}