package com.example.mutantidentifier.domain

class MutantChecker {

    private val NUMBER_CHARACTERS_BECOME_MUTANT = 4
    private val startNumberOfLettersFounded = 1 //  empiezo con con una letra encontrada ya que solo se reciben
    private var flagIsMutant = false
    private val minimumCharactersValid = 11

    private var matrix = mutableListOf<List<Char>>()

    fun isMutant(adn:String) : Boolean{
        matrix.clear()
        flagIsMutant = false

        getAdnMatrix(getRowsOfAdnString(adn))

        verifyHorizontalFeature()
        verifyVerticalFeature()
        verifyDiagonalFeature()

        return flagIsMutant
    }

    private fun verifyHorizontalFeature() {

        if (flagIsMutant) return // don't verify



        for(listRow in matrix) {
            var i = 0
            var counterLetter = startNumberOfLettersFounded

            for (letter in listRow) {
                if (i + 1 < listRow.size) { // valida si tiene siguiente posicion
                    if (letter == listRow[i + 1]) {
                        counterLetter++

                        if (counterLetter == NUMBER_CHARACTERS_BECOME_MUTANT) {
                            flagIsMutant = true
                            break
                        }
                    } else {
                        counterLetter = startNumberOfLettersFounded
                    }
                }else{
                    break
                }
                i++
            }
        }
    }

    //example string = eeee,rrrr,2222,ssss = retorna lista de los array
    private fun getRowsOfAdnString(adn:String) : List<String>{

        if (adn == "")
            return listOf(String())

        return adn.split(",").map { it -> it.trim() }
    }

    private fun getAdnMatrix(listOfEachOfLetters:List<String>): MutableList<List<Char>> {

        for(stringOfLetters in listOfEachOfLetters) {

                //convert this string in slots in array
                var eachRowOfLetters = stringOfLetters.toCharArray().map { it }
                /*eachRowOfLetters.forEach {
                    matrix.addAll(i, listOf(it))
                }*/
                matrix.add(eachRowOfLetters)

            }
        return matrix
    }

    private fun verifyVerticalFeature() {

        if (flagIsMutant) return

        for((y, listRow) in matrix.withIndex()) {
            var x = 0

            for (letter in listRow) {
                if (y + 3 < matrix.size) { // valida si tiene siguiente posicion para no preguntar por una posicion que no existe  y/o seguir con el algoritmo si no es necesario
                    if (letter == matrix[y + 1][x] && letter == matrix[y + 2][x] && letter == matrix[y + 3][x] ) {
                            flagIsMutant = true
                            break
                    }
                }else{
                    break
                }
                x++
            }
        }
    }

    private fun verifyDiagonalFeature() {

        if (flagIsMutant) return

        for((y, listRow) in matrix.withIndex()) {
            var x = 0

            for (letter in listRow) {
                if (y + 3 < matrix.size && x + 3 < listRow.size ) { // valida si tiene siguiente posicion para no preguntar por una posicion que no existe  y/o seguir con el algoritmo si no es necesario
                    if (letter == matrix[y + 1][x+1] && letter == matrix[y + 2][x+2] && letter == matrix[y + 3][x+3] ) {
                        flagIsMutant = true
                        break
                    }
                }else{
                    break
                }
                x++
            }
        }
    }

    fun isValidInput(adn: String): Boolean {

        if (verifyCorrectFormat(adn)) return false

        if (verifyMinimumCharactersAllowed(adn)) return false

        if (verifyCharactersAllowed(adn)) return false

        if (verifySameDimention(adn)) return false

        return true
    }

    private fun verifyCorrectFormat(adn: String): Boolean {
        if (!adn.contains(","))
            return true
        return false
    }

    private fun verifyMinimumCharactersAllowed(adn: String): Boolean {
        var andInArray = adn.toCharArray().map { it }
        if (andInArray.size < minimumCharactersValid)
            return true
        return false
    }

    private fun verifyCharactersAllowed(adn: String): Boolean {
        //val regex = Regex(pattern = "[^ATGC]")
        val regex = Regex(pattern = "[ATGC]")

        if (!adn.contains(regex))
            return true
        return false
    }

    private fun verifySameDimention(adn: String): Boolean {

        val rows = adn.split(",")

        if (rows != null && rows[0] != null) {
            val initialDimension = rows[0].length

            for (y in rows) {
                val x = y.toCharArray().map { it }
                if (x.size != initialDimension)
                    return true
            }
        }
        return false
    }
}