package com.example.demo.api;

import com.example.demo.model.Base;
import com.example.demo.model.BaseResponse;
import com.example.demo.utils.EndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@RestController
@RequestMapping(EndPoint.MANAGER)
@Tag(name = "Для менеджеров")
// список БД + список сущностей из бд
// работа с БД
public class ManagerController {
    @Autowired
    private Environment env;

    @GetMapping
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Доступен только авторизованным пользователям с ролью MANAGER")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("MANAGER");
    }

    @GetMapping(EndPoint.LIST_DB)
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Список БД")
    public ResponseEntity<BaseResponse> listDB() {
        List<Base> listDatabases = new ArrayList<>();
        Properties property = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/persistence-multiple-db.properties")) {
            property.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String i : property.stringPropertyNames()) {
            String prop = property.getProperty(i);
            if (i.contains("jdbc")) {
                List<String> list = Arrays.stream(prop.split("/")).toList();

                listDatabases.add(new Base(list.getLast()));
            }
        }
        BaseResponse baseResponse = new BaseResponse(listDatabases);
        return ResponseEntity.ok(baseResponse);
    }
}
