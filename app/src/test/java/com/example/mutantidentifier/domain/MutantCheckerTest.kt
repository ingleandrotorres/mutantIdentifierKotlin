package com.example.mutantidentifier.domain

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MutantCheckerTest{

    private lateinit var mutantChecker: MutantChecker

    @Before
    fun setUp(){
        val mutantChecker = MutantChecker()
    }

    @Test
    fun`es mutante con una matriz 4 x4 con adn coinsidente `(){
        val mutantChecker = MutantChecker()
        assertEquals(true,mutantChecker.isMutant("aaas,ssss,eeee,rrrr"))
        assertEquals(true,mutantChecker.isMutant("aaaaa,sssss,eeeee,rrrrr"))
        assertEquals(true,mutantChecker.isMutant("aaassss,sssssss,eeeeeee,arrrrr"))
    }

    @Test
    fun`matriz no es mutante`(){
        val mutantChecker = MutantChecker()
        assertEquals(false,mutantChecker.isMutant("xsss,xsss,eewe,xrrr"))
        assertEquals(false,mutantChecker.isMutant("aaasS,xsssX,eeweV,xrrrV"))
        assertEquals(false,mutantChecker.isMutant("aaas,xsss,eewe,xrrVV"))
        assertEquals(false,mutantChecker.isMutant("aaas,xsss,eewe,xrrrV"))
        assertEquals(false,mutantChecker.isMutant("aaas,xsss,eewe,xrrrV"))
        assertEquals(false,mutantChecker.isMutant("aaas,xsss,eewe,xrrrV"))
    }
    @Test
    fun`matriz no es mutante 2x2 1x1 muy pequeña`(){
        val mutantChecker = MutantChecker()
        assertEquals(false,mutantChecker.isMutant("as,xs"))
        assertEquals(false,mutantChecker.isMutant("a,x"))
    }
    @Test
    fun`matriz no es mutante vacia`(){
        val mutantChecker = MutantChecker()
        assertEquals(false,mutantChecker.isMutant(""))
    }

    @Test
    fun`matriz erronea desigual`(){
        val mutantChecker = MutantChecker()
        assertEquals(false,mutantChecker.isMutant("xsss,xsss,eewe,xrr"))
        assertEquals(false,mutantChecker.isMutant("aaa,xX,z"))

    }
    @Test
    fun`matriz de mutante desigual`(){
        val mutantChecker = MutantChecker()
        assertEquals(true,mutantChecker.isMutant("aaa,xX,z,xrrrssssV"))

    }

}