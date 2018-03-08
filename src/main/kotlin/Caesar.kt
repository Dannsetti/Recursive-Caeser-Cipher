import java.io.File
import java.util.*
import kotlin.collections.ArrayList

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
            if (currentChar > 'Z') {
                currentChar -= 26
            }
            // now check if currentChar is a lowercase letter:
        } else if (currentChar in 'a'..'z') {
            // add value of n
            currentChar += n
            // rotate if new value is greater than value of 'z'
            if (currentChar > 'z') {
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
        // Letters frequency took from Wikipedia
        val frequencyAlphabet = "etaoinshrdlcumwfgypbvkjxqz"

        // E is the most frequent and its index is 5 in the alphabet
        val letterEIndex = 5

        // Create an alphabet to get letter index
        val alphabet = "abcdefghijklmnopqrstuvwxyz"
        //println(alphabet)

        // the key is the length of the alphabet plus the index of the most frequent
        // as we are decrypting we need to go backwards so the index of the the letter with
        // most occurrences will be deducted from the key.
        var key = 26 + letterEIndex
        //println(key)

        // Create 2 var to hold the key and value of the most frequent letter
        var letterMaxOccur = ""
        var letterCounter = 0

        // Group the enciphered string to the numbers of occurrences of each char
        // to get the most frequent that could be letter E or e in accordance to
        // the probability of the use of the letter in English words
        val mostFrequentLetters = s.toLowerCase().groupingBy { it }.eachCount()
        //println(mostFrequentLetters)

        // iterate through the group to get the letter that occurs more
        for (i in mostFrequentLetters) {
            if (i.key.toString() in "a".."z") {
                if (letterCounter < i.value) {
                    letterCounter = i.value
                    letterMaxOccur = i.key.toString()
                }
            }
        }

        var extraIndex = 0

        // counter to get the index in English alphabet of the letter captured in the group
        var alphabetCounter = 0
        for (j in alphabet) {
            alphabetCounter += 1
            //println("al " + alphabetCounter)
            // when find it subtract the value index to discover the key that was used to encrypt
            if (j.toString() == letterMaxOccur) {
                key -= alphabetCounter
                //println(alphabetCounter)
                //println(key)

            }
        }

        if (key > 26) {
            for (k in 26..key) {
                //println("Here")
                extraIndex += 1
            }
            key = extraIndex
        }


        // call the encipher function to decipher using the key discovered.
        return encipher(s, key)

    }

    fun decipher2(s: String): String {
        // get wordList
        val frequentWords = createWordList()

        // create variables to compare words
        var index = 1
        var decrypted = ""
        var found = false

        // check wordList contains any word in sample decryption
        // will check every possible key in a loop
        for (i in 1..25) {
            val testResult = encipher(s, index)
            // obtain each word in decrypted sample
            val testResultWords = testResult.split(" ")
            // compare each word with frequent word list
            for (j in testResultWords) {
                if (frequentWords.contains(j)) {
                    found = true
                }

            }
            // if there is a match, add decrypted sample to possible solution
            if (found) {
                decrypted += testResult
                found = false
            }
            index++
        }
        // return all possible solutions
        return decrypted
    }

    fun createWordList(): ArrayList<String> {
        // create and load dictionary array of frequent words
        val PATH = System.getProperty("user.dir") + "/src/main/resources/"
        val input = Scanner(File(PATH + "100FrequentWords.txt"))
        val frequentWords = ArrayList<String>()
        var line = ""
        while (input.hasNext()) {
            line = input.nextLine()
            frequentWords += line
        }
        return frequentWords
    }

}

fun main(args: Array<String>) {
    val encrypt = Caesar().encipher("Caesar cipher? I prefer Caesar salad.", 25)
    println("Encrypt: " + encrypt)

    val decrypt1 = Caesar().decipher("Hu lkbjhapvu pz doha ylthpuz hmaly dl mvynla clyfaopun dl " +
            "ohcl slhyulk.")
    println("Decrypt: " + decrypt1)

    val decrypt2 = Caesar().decipher("Bzdrzq bhogdq? H oqdedq Bzdrzq rzkzc.")
    println("Decrypt: " + decrypt2)

    val decrypt3 = Caesar().decipher2("Hu lkbjhapvu pz doha ylthpuz hmaly dl mvynla clyfaopun dl " +
            "ohcl slhyulk.")
    println("Decrypt2: " + decrypt3)

    val decrypt4 = Caesar().decipher2("Bzdrzq bhogdq? H oqdedq Bzdrzq rzkzc.")
    println("Decrypt2: " + decrypt4)


}
