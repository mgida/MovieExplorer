package com.example.movieexplorer.domain.mapper

interface IMapper<From, To> {
    fun map(input: From): To
}

