class Caesar {

    fun encipher(s: String, n: Int): String {
        // start reading message string, one char at a time:
        var currentChar = s[0]
        // create arraylist to hold result:
        val result = arrayListOf<Any>()
        // check curentChar is an uppercase letter:
        if (currentChar in 'A'..'Z') {
            // add value of n
            currentChar += n
            // rotate if new value is greater than value of 'Z'
            if(currentChar > 'Z') {
                currentChar -= 26
            }
            // now check if currentChar is a lowercase letter:
        } else if (currentChar in 'a'..'z') {
            // add value of n
            currentChar += n
            // rotate if new value is greater than value of 'z'
            if(currentChar > 'z') {
                currentChar -= 26
            }
        }
        // add curentChar to result
        result += currentChar
        // if currentChar is not the final char, call encipher recursively
        if (s.length > 1) {
            result += (encipher(s.substring(1), n))
        }
        // return result as a String
        return result.joinToString("")
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
