package com.lbg.library.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public abstract class Item implements Comparable<Item> {

	public abstract String size();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String author;
	private int pages;
	private boolean available;

	@JsonManagedReference
	@OneToMany(mappedBy = "item")
	private List<Person> Players;

	public Item() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", Title=" + title + ", Author=" + author + ", Pages=" + pages + ", Available="
				+ available + "]";
	}

	@Override
	public int compareTo(Item o) {
		return id - o.id;
	}

}
