package com.spydevs.fiestonvirtual.framework.mapper

abstract class Mapper<I, F> {
    abstract fun convertFromInitial(i: I?): F
}