package com.lbg.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lbg.library.domain.Item;
import com.lbg.library.repo.ItemRepo;

@Service
public class ItemService {

	private ItemRepo repo;

	public ItemService(ItemRepo repo) {
		super();
		this.repo = repo;
	}

	public ResponseEntity<Item> createItem(Item newItem) {
		Item created = this.repo.save(newItem);
		return new ResponseEntity<Item>(created, HttpStatus.CREATED);
	}

	public List<Item> getItem() {
		return this.repo.findAll();
	}

	public ResponseEntity<Item> getItem(int id) {
		Optional<Item> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Item body = found.get();
		return ResponseEntity.ok(body);
	}

	public boolean deleteItem(int id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public ResponseEntity<Item> updatePlayer(int id, Item newItem) {
		Optional<Item> found = this.repo.findById(id);

		if (found.isEmpty()) {
			return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
		}
		Item existing = found.get();

		if (newItem.getTitle() != null) {
			existing.setTitle(newItem.getTitle());
		}

		if (newItem.getAuthor() != null) {
			existing.setAuthor(newItem.getAuthor());
		}

		if (newItem.getPages() != 0) {
			existing.setPages(newItem.getPages());
		}

		Item updated = this.repo.save(existing);

		return ResponseEntity.ok(updated);
	}

}
