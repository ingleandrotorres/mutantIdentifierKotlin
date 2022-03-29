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

    @Test
    fun`matriz de mutante vertical ok`(){
        val mutantChecker = MutantChecker()
        assertEquals(true,mutantChecker.isMutant("aaix,axxi,aeee,arrr"))
        assertEquals(true,mutantChecker.isMutant("caxx,watx,aaee,aarr"))
        assertEquals(true,mutantChecker.isMutant("caax,wgax,aeae,arar"))
        assertEquals(true,mutantChecker.isMutant("aaia,axxa,aeea,arra"))

    }

    @Test
    fun`matriz de NO mutante vertical `(){
        val mutantChecker = MutantChecker()
        assertEquals(false,mutantChecker.isMutant("iaix,axxi,aeee,arrr"))
        assertEquals(false,mutantChecker.isMutant("caxx,witx,aaee,aarr"))
        assertEquals(false,mutantChecker.isMutant("caax,wgax,ae8e,arar"))
        assertEquals(false,mutantChecker.isMutant("aaia,axxa,aeea,jr0i"))

    }
    @Test
    fun`matriz no es mutante Vertical  2x2 1x1 muy pequeña`(){
        val mutantChecker = MutantChecker()
        assertEquals(false,mutantChecker.isMutant("as,xs"))
        assertEquals(false,mutantChecker.isMutant("s,l"))
    }


    @Test
    fun`matriz de mutante diagonal ok`(){
        val mutantChecker = MutantChecker()
        assertEquals(true,mutantChecker.isMutant("aaix,0axi,ieae,zrra"))
        assertEquals(true,mutantChecker.isMutant("caxxz,watxz,aaeez,aarrz,qaswe"))
        assertEquals(true,mutantChecker.isMutant("caax,wgax,aeae,arar"))
        assertEquals(true,mutantChecker.isMutant("aaia,axxa,aeea,arra"))

    }

    @Test
    fun`matriz de NO mutante diagonal prueba en diagonal a la derecha`(){
        val mutantChecker = MutantChecker()
        assertEquals(false,mutantChecker.isMutant("abcd,efgh,ijkl,mnop"))
        assertEquals(false,mutantChecker.isMutant("abcde,fghij,klmno,pqrst,uvwxy"))
        assertEquals(true,mutantChecker.isMutant("0TGCGA,0AGGGC,0TATGT,lGAAGG,pCCCTA,TCACTG")) // g from position 3
    }

    @Test
    fun`matriz original de la kata mutante`(){
        val mutantChecker = MutantChecker()
        assertEquals(true,mutantChecker.isMutant("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG"))
    }
    @Test
    fun`matriz original de la kata NO mutante`(){
        val mutantChecker = MutantChecker()
        assertEquals(false,mutantChecker.isMutant("ATGCGA,CAGTGC,TTATTT,AGACGG,GCGTCA,TCACTG"))
    }


}