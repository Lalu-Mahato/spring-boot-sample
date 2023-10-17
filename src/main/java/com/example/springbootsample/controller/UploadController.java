package com.example.springbootsample.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.springbootsample.model.Fieldofficer;
import com.example.springbootsample.repository.FieldofficerRepository;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {

    @Autowired
    private FieldofficerRepository fieldofficerRepository;

    @PostMapping
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            System.out.println(multipartFile);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(multipartFile.getInputStream());
            XSSFSheet worksheet = xssfWorkbook.getSheet("Sheet1");
            System.out.println(worksheet);
            List<Fieldofficer> fieldofficers = new ArrayList<>();
            for (var i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = worksheet.getRow(i);
                Fieldofficer fieldofficer = new Fieldofficer();
                fieldofficer.setFoId((String) row.getCell(19).getStringCellValue());
                fieldofficer.setBmId((String) row.getCell(21).getStringCellValue());
                fieldofficer.setFoName((String) row.getCell(20).getStringCellValue());
                fieldofficer.setUsername("");
                fieldofficer.setPassword("");
                fieldofficer.setRole("fieldofficer");
                fieldofficer.setCreatedBy("branchmanager");

                fieldofficers.add(fieldofficer);
            }

            fieldofficerRepository.saveAll(fieldofficers);
            xssfWorkbook.close();
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
    }

    @PostMapping("/collections")
    public ResponseEntity<Object> collectionUpload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            IOUtils.setByteArrayMaxOverride(500000000); // 500MB

            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(multipartFile.getInputStream());
            XSSFSheet worksheet = xssfWorkbook.getSheet("Sheet1");

            if (worksheet != null) {
                System.out.println(worksheet.getPhysicalNumberOfRows());
                for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                    XSSFRow row = worksheet.getRow(i);
                    System.out.println(row.getCell(1).getStringCellValue());
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sheet 'Sheet1' not found in the workbook");
            }

            xssfWorkbook.close();
            return ResponseEntity.ok("File uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
    }
}
