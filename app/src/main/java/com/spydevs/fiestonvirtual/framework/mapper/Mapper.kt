package com.spydevs.fiestonvirtual.framework.mapper

abstract class Mapper<I, F> {
    abstract fun convertFromInitial(i: I?): F
    abstract fun convertFromFinal(f: F): I
}