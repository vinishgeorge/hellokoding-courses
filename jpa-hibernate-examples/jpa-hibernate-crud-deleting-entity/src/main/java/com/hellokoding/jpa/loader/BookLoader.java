package com.hellokoding.jpa.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hellokoding.jpa.book.Book;
import com.hellokoding.jpa.book.Category;
import com.hellokoding.jpa.book.CategoryRepository;
import com.hellokoding.jpa.book.Publisher;
 
@Component
public class BookLoader implements ApplicationListener<ContextRefreshedEvent> {
 
    private CategoryRepository categoryRepository;
     
 
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
 
    public void onApplicationEvent(ContextRefreshedEvent event) {
 
    	Category category=new Category("C", new Book("C1"), new Book("C2"));
     
        categoryRepository.save(category);
 
        System.out.println("Saved Shirt - id: " + category.getId());
 
        Category category2=new Category("D", new Book("D1",new Publisher("P1")), new Book("D2",new Publisher("P1")));
        categoryRepository.save(category2);
 
        System.out.println("Saved Mug - id:" + category2.getId());
    }
}