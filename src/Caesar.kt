class Caesar {

    fun encipher(s: String, n: Int): String {
        TODO()
    }

    fun decipher(s: String): String {
        TODO()
    }
}

fun main(args: Array<String>) {
    val encrypt = Caesar().encipher("Caesar cipher? I prefer Caesar salad.", 25)
    println(encrypt)

    val decrypt = Caesar().decipher("Hu lkbjhapvu pz doha ylthpuz hmaly dl mvynla clyfaopun dl ohcl slhyulk.")
    println(decrypt)
}