package com.youcode.garthergridjee.service;

import com.youcode.garthergridjee.entities.Category;
import com.youcode.garthergridjee.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class CategoryServiceTest {
    private CategoryService categoryService;


    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    void testgetAllCategories() {
        Mockito.when(categoryRepository.getAll()).thenReturn(List.of());
        List<Category> allCategories = categoryService.getAllCategories();
        assertNotNull(allCategories);
        assertEquals(0 ,allCategories.size());
        verify(categoryRepository,times(1)).getAll();
    }
    @Test
    void testDontDeleteCategories(){
        Mockito.when(categoryRepository.getById(1l)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, ()->categoryService.deleteCategory(1L));
        verify(categoryRepository,times(0)).delete(1L);
    }
    @Test
    void testDeleteCategoriees(){
        Mockito.when(categoryRepository.getById(1L)).thenReturn(new Category());
        Mockito.doNothing().when(categoryRepository).delete(1L);
        categoryService.deleteCategory(1L);
        verify(categoryRepository,times(1)).delete(1L);
    }
    @Test
    void testDoitModifier(){

        Category category = new Category("amina", "amina");
        Mockito.when(categoryRepository.getById(1L)).thenReturn(category);
        Mockito.doNothing().when(categoryRepository).update(category);
        categoryService.updateCategory(category);
        verify(categoryRepository,times(1)).update(category);

    }
    @Test
    void neDoitPasModifier(){
        Category category = new Category("amina", null);
        Mockito.when(categoryRepository.getById(1L)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, ()->categoryService.updateCategory(category));
        verify(categoryRepository,times(0)).update(category);
    }
    @Test
    void doitSouvgarder(){
        Category category = new Category("amina", "amina");
        Mockito.doNothing().when(categoryRepository).save(category);
        Mockito.doNothing().when(categoryRepository).save(category);
        categoryService.createCategory(category);
        verify(categoryRepository,times(1)).save(category);
    }
    @Test
    void neDoitPasSouvgarder(){
        Category category = new Category("amina", null);
        Mockito.doNothing().when(categoryRepository).save(category);
        assertThrows(IllegalArgumentException.class, ()->categoryService.createCategory(category));
        verify(categoryRepository,times(0)).save(category);

    }
    @Test
    void neDoitPasSouvgarderAvecObjetVide(){
        Category category =new Category();
        Mockito.doNothing().when(categoryRepository).save(category);
        assertThrows(IllegalArgumentException.class, ()->categoryService.createCategory(category));
        verify(categoryRepository,times(0)).save(category);

    }

}