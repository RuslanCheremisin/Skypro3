package com.example.skypro3.controllers;

import com.example.skypro3.services.FilesService;
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

@RestController
@RequestMapping("/files")
@Tag(name = "Загрузки", description = "Здесь можно скачать и загрузить каталоги рецептов")
public class FilesController {
    FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @GetMapping("/exportRecipes")
    @Operation(summary = "Здесь вы можете скачать список всех рецептов")
    public ResponseEntity<InputStreamResource> downloadRecipesCatalogue() {
        File file = filesService.getRecipesFile();
        InputStreamResource resource = null;
        if (file.exists()) {
            try {
                resource = new InputStreamResource(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
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
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/importIngredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Здесь вы можете загрузить свой список ингредиентов в наше хранилище")
    public ResponseEntity<Void>uploadIngredientsCatalogue(@RequestParam MultipartFile ingredientsCatalogueFile){
        filesService.cleanIngredientsFile();
        try(FileOutputStream fos = new FileOutputStream(filesService.getIngredientsFile())) {
            IOUtils.copy(ingredientsCatalogueFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
