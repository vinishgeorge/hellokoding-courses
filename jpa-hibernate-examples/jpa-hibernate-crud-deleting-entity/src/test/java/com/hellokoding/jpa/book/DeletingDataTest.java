package com.hellokoding.jpa.book;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.hellokoding.jpa.JpaApplication;
import com.hellokoding.jpa.configuration.RepositoryConfiguration;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(classes = {JpaApplication.class,RepositoryConfiguration.class})
//@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DeletingDataTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void createTestData(){
        /*categoryRepository.save(new Category("A", new Book("A1"), new Book("A2")));
        categoryRepository.save(new Category("B", new Book("B1"), new Book("B2")));*/
    }

   /* @Test
    public void testDelete_whenJPQL_thenSuccess() {
        bookRepository.deleteByCategoryId(1);
        Assert.assertEquals(0, bookRepository.findByCategoryId(1).size());
    }*/

    @Test
    public void testDelete_whenOrphanRemoval_thenSuccess() {
        Category category = categoryRepository.findById(1).get();
        Book book = category.getBooks().iterator().next();
        int bookId = book.getId();
        category.getBooks().remove(book);
        categoryRepository.flush();

        Assert.assertEquals(Optional.empty(), bookRepository.findById(bookId));
    }

    @Test
    public void testDelete_whenCascadeAll_thenSuccess() {
        Category category = categoryRepository.findById(1).get();
        categoryRepository.delete(category);

        Assert.assertEquals(0, bookRepository.findByCategoryId(1).size());
    }
}
