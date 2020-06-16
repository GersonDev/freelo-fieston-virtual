package com.spydevs.fiestonvirtual.framework.mapper

abstract class MapperFromInitial<I, F> {
    abstract fun convertFromInitial(i: I?): F
}