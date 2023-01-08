package com.example.skypro3.controllers;

import com.example.skypro3.services.FilesService;
import com.example.skypro3.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
@Tag(name = "Загрузки", description = "Здесь можно скачать и загрузить каталоги рецептов")
public class FilesController {
    FilesService filesService;
    RecipeService recipeService;

    public FilesController(FilesService filesService, RecipeService recipeService) {
        this.filesService = filesService;
        this.recipeService = recipeService;
    }

    @GetMapping("/exportRecipes")
    @Operation(summary = "Здесь вы можете скачать список всех рецептов")
    public ResponseEntity<InputStreamResource> downloadRecipesCatalogue() throws FileNotFoundException {
        File file = filesService.getRecipesFile();
        InputStreamResource resource;
        if (file.exists()) {
            try {
                resource = new InputStreamResource(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new FileNotFoundException("Файл отсутствует на сервере");
            }
            return ResponseEntity.ok()
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipeCatalogue.json\"")
                    .body(resource);

        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/importRecipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Здесь вы можете загрузить свой список рецептов в наше хранилище")
    public ResponseEntity<Void> uploadRecipeCatalogue(@RequestParam MultipartFile recipesCatalogueFile) {
        filesService.cleanRecipesFile();
        try (FileOutputStream fos = new FileOutputStream(filesService.getRecipesFile())) {
            IOUtils.copy(recipesCatalogueFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            throw new IllegalArgumentException("Некорректный формат файла для загрузки");
        }
    }

    @PostMapping(value = "/importIngredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Здесь вы можете загрузить свой список ингредиентов в наше хранилище")
    public ResponseEntity<Void> uploadIngredientsCatalogue(@RequestParam MultipartFile ingredientsCatalogueFile) {
        filesService.cleanIngredientsFile();
        try (FileOutputStream fos = new FileOutputStream(filesService.getIngredientsFile())) {
            IOUtils.copy(ingredientsCatalogueFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            throw new IllegalArgumentException("Некорректный формат файла для загрузки");
        }
    }

    @GetMapping("/exportRecipesTXTFile")
    @Operation(summary = "Здесь вы можете скачать список всех рецептов в читаемом формате")
    public ResponseEntity<InputStreamResource> downloadRecipesCatalogueTXT() throws IOException {

        Path path = recipeService.getAllRecipesForDownload();
        if(Files.size(path)!=0){
            try {
                InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
                return ResponseEntity.ok()
                        .contentLength(path.toFile().length())
                        .contentType(MediaType.TEXT_PLAIN)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"recipesCatalogue.txt\"")
                        .body(resource);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }else {
            return ResponseEntity.noContent().build();
        }

    }

}
