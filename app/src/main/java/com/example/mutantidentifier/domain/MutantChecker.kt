package com.example.mutantidentifier.domain

class MutantChecker {

    val NUMBER_CHARACTERS_BECOME_MUTANT = 4
    val startNumberOfLettersFounded = 1 //  empiezo con con una letra encontrada ya que solo se reciben
    var flagIsMutant = false
    var adnNumColumns = 0
    var adnNumRows = 0

    var matrix = mutableListOf<List<Char>>()

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

    private fun convertAdnStringToMatrix(adn: String){

    }
    private fun isValidAdnString(adn: String){

        val rows = getRowsOfAdnString(adn)
        setNumOfRows(rows.size)


        val array: Array<String> = adn.toCharArray().map { it.toString() }.toTypedArray()
    }
    // eeee,rrrr,2222,ssss = retorna lista de los array
    fun getRowsOfAdnString(adn:String) : List<String>{

        if (adn == "")
            return listOf(String())

        return adn.split(",").map { it -> it.trim() }
    }

    fun getAdnMatrix(listOfEachOfLetters:List<String>): MutableList<List<Char>> {

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

    fun setNumOfRows(x : Int){
        this.adnNumRows = x
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

        var y = 0

        for(listRow in matrix) {
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
            y++
        }
    }


}