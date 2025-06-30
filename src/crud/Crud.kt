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
val listIdBarang = mutableListOf<String>() // UNTUK MENAMPUNG ID BARANG
var number = listIdBarang.size

// CONDITION
fun runCrud(option: Int) {
    when (option) {
        0 -> tambahData()
        1 -> tampilData(true)
        2 -> editData()
        3 -> hapusData()
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

            // ID BARANG
            number++
            val getIdBarang = "BRG-$number"

            // PENAMBAHAN KE LIST
            listIdBarang.add(getIdBarang)
            listBarang.add(barang)
        } else {
            println("> $barang tidak berhasil ditambahkan di Data Barang anda.")
        }
    } else {
        println("> Penambahan data tidak berhasil dilakukan.")
    }
}

// TAMPIL DATA
fun tampilData(withTitle : Boolean){
    if (withTitle){
        printTitle(title = "List Barang")
    }

    // TABEL KOMPONEN
    val lebarNo = 5
    val lebarId = 7
    val lebarNamaBarang = 35

    // PRINT LINE HEADER
    val line = "+${"-".repeat(lebarNo + 1)}+${"-".repeat(lebarId + 1)}+${"-".repeat(lebarNamaBarang - 3)}+"
    println(line)
    println("| ${"No".padEnd(lebarNo - 1)} | ${"ID".padEnd(lebarId - 1)} | ${"Nama Barang".padEnd(lebarNamaBarang - 4)}|")
    println(line)

    // PRINT DATA
    if (listBarang.isEmpty()){
        println("| ${"Tidak ada barang tersedia.".padEnd(lebarNo + lebarId + lebarNamaBarang)}|")
    } else {
        for ((no, barang) in listBarang.withIndex()){
            val nomor = (no + 1).toString().padEnd(lebarNo - 1)
            val idBarang = listIdBarang[no].padEnd(lebarId - 1)
            val namaBarang = barang.padEnd(lebarNamaBarang - 5)
            println("| $nomor | $idBarang | $namaBarang |")
        }
    }

    // PRINT LINE FOOTER
    println(line)
}

// EDIT DATA
fun editData(){
    printTitle(title = "Edit Data")

    // PRINT DATA
    println()
    tampilData(false)
    println()

    // PILIH DATA
    print("> Masukan nama barang yang ingin diedit : ")
    val barangToEdit = readlnOrNull()

    if (listBarang.isEmpty()){
        println("> Data Barang masih kosong, silahkan tambahkan\n  barang terlebih dahulu")
        return
    }

    // CEK APAKAH BARANG YANG DIMASUKAN ADA DI DATA ATAU TIDAK
    val indexBarangKetemu = listBarang.indexOfFirst { it.equals(barangToEdit, ignoreCase = true) } // MENGEMBALIKAN 1 (TRUE) / -1 (FALSE)

    if (indexBarangKetemu != -1){ // JIKA BARANG DITEMUKAN
        println("> $barangToEdit ditemukan")
        println("> Silahkan edit barang : ")
        print("> Masukan nama barang : ")
        val updatedBarang = readlnOrNull()

        if (updatedBarang != null){
            println("> ${listBarang[indexBarangKetemu]} berhasil diperbarui menjadi $updatedBarang")
            listBarang[indexBarangKetemu] = updatedBarang
        } else {
            println("> Nama barang tidak boleh kosong, silahkan ulangi!")
        }
    } else { // JIKA BARANG TIDAK DITEMUKAN
        println("> $barangToEdit tidak ditemukan, silahkan coba lagi")
    }
}

// HAPUS DATA
fun hapusData(){
    printTitle(title = "Hapus Data")

    // PRINT DATA
    println()
    tampilData(false)
    println()

    // PILIH DATA
    print("> Masukan nama barang yang ingin hapus : ")
    val barangHapus = readlnOrNull()

    if (listBarang.isEmpty()){
        println("> Data Barang masih kosong, silahkan tambahkan\n  barang terlebih dahulu")
        return
    }

    // CEK APAKAH BARANG YANG DIMASUKAN ADA DI DATA ATAU TIDAK
    val indexBarangKetemu = listBarang.indexOfFirst { it.equals(barangHapus, ignoreCase = true) } // MENGEMBALIKAN 1 (TRUE) / -1 (FALSE)

    if (indexBarangKetemu != -1){ // JIKA BARANG DITEMUKAN
        println("> $barangHapus ditemukan")
        listBarang.removeAt(indexBarangKetemu)
        println("> $barangHapus berhasil di dihapus ")

    } else { // JIKA BARANG TIDAK DITEMUKAN
        println("> $barangHapus tidak ditemukan, silahkan coba lagi")
    }
}

// ARRAY MENU
val menu = arrayOf(
    "Tambah Data",
    "Tampil Data",
    "Edit Data",
    "Hapus Data",
)

fun start(){
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

// MAIN FUNCTION
fun main() {
    start()
}