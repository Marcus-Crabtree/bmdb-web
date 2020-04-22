package com.bmdb.business;

import javax.persistence.*;

@Entity
public class MovieGenre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@ManyToOne
	@JoinColumn(name = "MovieID")
	private Movie movie;
	@ManyToOne
	@JoinColumn(name = "GenreID")
	private Genre genre;

	public MovieGenre(int id, Movie movie, Genre genre) {
		super();
		Id = id;
		this.movie = movie;
		this.genre = genre;
	}

	public MovieGenre() {
		super();
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "MovieGenre [Id=" + Id + ", movie=" + movie + ", genre=" + genre + "]";
	}

}
