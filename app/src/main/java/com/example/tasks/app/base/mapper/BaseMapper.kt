package com.example.tasks.app.base.mapper

abstract class BaseMapper<in T, out R> {
    abstract fun map(value: T): R
}
