package org.mac.sample.groovy.collectiontype.map

/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
 */

def textCorpus = '''
Look for the bare necessities
The simple bare necessities
Forget about your worries and your strife
I mean the bare necessities
Old mother Nature's recipes
That bring the bare necessities of life
'''
def words = textCorpus.tokenize()

def wordFrequency = [:]
words.each {word ->
    wordFrequency[word] = wordFrequency.get(word,0)+1
}
println wordFrequency

def wordList = wordFrequency.keySet().toList()
wordList.sort {wordFrequency[it]}
println wordList

def statistic = "\n"
wordList[-1..-6].each { word ->
    statistic+= word.padLeft(12) + ':'
    statistic+= wordFrequency[word] + "\n"
}

println statistic