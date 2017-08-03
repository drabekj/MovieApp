package com.strvacademy.drabekj.moviestrv.model

class Movie {

    public val id: Int
    public val name: String
    public val director: String
    public val releaseDate: String
    public val description: String
    public val rating: String

    constructor(id: Int,name: String,director: String, releaseDate: String, description: String, rating: String) {
        this.id = id
        this.name = name
        this.director = director
        this.releaseDate = releaseDate
        this.description = description
        this.rating = rating
    }


}
