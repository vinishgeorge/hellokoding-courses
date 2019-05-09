package com.hellokoding.jpa.book;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString(exclude = "book")
@Entity
public class Publisher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private @NonNull String name;

	@ManyToOne
	private Book book;

	@ManyToOne
	@JoinColumn
	private Category category;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Publisher))
			return false;
		Publisher author = (Publisher) o;
		return Objects.equals(name, author.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

}
