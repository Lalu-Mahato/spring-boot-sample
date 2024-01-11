package com.example.springbootsample.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.example.springbootsample.dto.BankDTO;
import com.example.springbootsample.dto.EmiDTO;
import com.example.springbootsample.dto.LoanDTO;
import com.example.springbootsample.dto.ProductDTO;
import com.example.springbootsample.dto.ProspectDTO;
import com.example.springbootsample.model.Bank;
import com.example.springbootsample.model.Fieldofficer;
import com.example.springbootsample.repository.BankRepository;
import com.example.springbootsample.repository.FieldofficerRepository;
import com.example.springbootsample.service.BankService;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {

    @Autowired
    private FieldofficerRepository fieldofficerRepository;
    @Autowired
    private BankService bankService;

    @Autowired
    private BankRepository bankRepository;

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

            List<ProductDTO> products = new ArrayList<>();
            List<ProspectDTO> prospects = new ArrayList<>();
            List<LoanDTO> loans = new ArrayList<>();
            List<EmiDTO> emis = new ArrayList<>();
            List<BankDTO> banks = new ArrayList<>();

            if (worksheet != null) {
                for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                    XSSFRow row = worksheet.getRow(i);

                    // Products
                    ProductDTO product = new ProductDTO();
                    product.setId((int) row.getCell(27).getNumericCellValue());
                    product.setName((String) row.getCell(26).getStringCellValue());
                    products.add(product);

                    // Prospect
                    ProspectDTO prospect = new ProspectDTO();
                    prospect.setName((String) row.getCell(1).getStringCellValue());
                    prospect.setAddress((String) row.getCell(29).getStringCellValue());
                    prospect.setMobileNumber((int) row.getCell(28).getNumericCellValue());
                    prospect.setCifId((int) row.getCell(30).getNumericCellValue());
                    prospects.add(prospect);

                    // Loan
                    LoanDTO loan = new LoanDTO();
                    loan.setAccountNumber((long) row.getCell(0).getNumericCellValue());
                    loan.setAmountSanctioned((double) row.getCell(2).getNumericCellValue());
                    loan.setDisbursalDate((Date) row.getCell(3).getDateCellValue());
                    loan.setRateOfInterest((double) row.getCell(23).getNumericCellValue());
                    loan.setTotalTenure((int) row.getCell(21).getNumericCellValue());
                    loan.setLoanEndDate((Date) row.getCell(19).getDateCellValue());
                    loans.add(loan);

                    // EMI
                    EmiDTO emi = new EmiDTO();
                    emi.setCurrentTenure((int) row.getCell(20).getNumericCellValue());
                    emi.setResidualTenure((int) row.getCell(22).getNumericCellValue());
                    emi.setTotalEmiAmount((double) row.getCell(8).getNumericCellValue());
                    emi.setPrincipalEmiAmount((double) row.getCell(9).getNumericCellValue());
                    emi.setInterestEmiAmount((double) row.getCell(10).getNumericCellValue());
                    emi.setEmiDueDate((Date) row.getCell(4).getDateCellValue());
                    emi.setTotalOutstanding((double) row.getCell(5).getNumericCellValue());
                    emi.setPrincipalOutstanding((double) row.getCell(6).getNumericCellValue());
                    emi.setInterestOutstanding((double) row.getCell(7).getNumericCellValue());
                    emi.setArrearAmount((double) row.getCell(11).getNumericCellValue());
                    emi.setPrincipalArrearAmount((double) row.getCell(12).getNumericCellValue());
                    emi.setInterestArrearAmount((double) row.getCell(13).getNumericCellValue());
                    emi.setOtherCharges((double) row.getCell(14).getNumericCellValue());
                    emi.setTotalCollectionAmount((double) row.getCell(15).getNumericCellValue());
                    emi.setUnpaidInstallment((int) row.getCell(24).getNumericCellValue());
                    emi.setPaidInstallment((int) row.getCell(25).getNumericCellValue());
                    emi.setLastPaidAmount((double) row.getCell(18).getNumericCellValue());
                    emi.setLastRepaymentDate((Date) row.getCell(17).getDateCellValue());
                    emi.setDpdInDays((int) row.getCell(16).getNumericCellValue());
                    emis.add(emi);

                    // Bank
                    int bankCode = (int) row.getCell(31).getNumericCellValue();
                    Optional<Bank> optionalBank = bankRepository.FindByCode(bankCode);

                    Bank bank;
                    if (optionalBank.isPresent()) {
                        bank = optionalBank.get();
                    } else {
                        BankDTO bankDTO = new BankDTO();
                        bankDTO.setCode((int) row.getCell(31).getNumericCellValue());
                        bankDTO.setName((String) row.getCell(32).getStringCellValue());
                        bank = bankService.create(bankDTO);

                    }
                }
                return ResponseEntity.status(HttpStatus.OK).body("Sheet 'Sheet1' not found in the workbook");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sheet 'Sheet1' not found in the workbook");
            }

            xssfWorkbook.close();
            return ResponseEntity.ok(loans);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
    }
}
