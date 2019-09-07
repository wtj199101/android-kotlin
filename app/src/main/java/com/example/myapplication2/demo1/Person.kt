package com.example.myapplication2.demo1

open class Animal(name: String)

class Person (name: String,userName: String) : Animal(name){
    fun add(x: Int,y: Int ): Int= x+y

}