package com.hellokoding.jpa.book;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@ToString(exclude = "category")

@Entity
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private @NonNull String name;
    
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Publisher> publishers;

    public Book(String name, Publisher... publishers) {
        this.name = name;
        this.publishers = Stream.of(publishers).collect(Collectors.toSet());
        this.publishers.forEach(x -> x.setBook(this));
    }
    
    @ManyToOne
    @JoinColumn
    private Category category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
