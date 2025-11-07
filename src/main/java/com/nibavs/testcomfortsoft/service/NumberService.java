package com.nibavs.testcomfortsoft.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NumberService {

    public int findNthSmallest(String path, int n) throws IOException {
        List<Integer> numbers = readNumbersFromExcel(path);
        if (n < 1 || n > numbers.size()) throw new IllegalArgumentException("N вне диапазона значений: " + n);
        return quickSelect(numbers, 0, numbers.size() - 1, n - 1);
    }

    private List<Integer> readNumbersFromExcel(String filePath) throws IOException {
        List<Integer> numbers = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    numbers.add((int) cell.getNumericCellValue());
                }
            }
        }
        return numbers;
    }

    private int quickSelect(List<Integer> list, int left, int right, int k) {
        if (left == right) return list.get(left);

        int pivotIndex = partition(list, left, right);
        if (k == pivotIndex) {
            return list.get(k);
        } else if (k < pivotIndex) {
            return quickSelect(list, left, pivotIndex - 1, k);
        } else {
            return quickSelect(list, pivotIndex + 1, right, k);
        }
    }

    private int partition(List<Integer> list, int left, int right) {
        int pivot = list.get(right);
        int i = left;

        for (int j = left; j < right; j++) {
            if (list.get(j) < pivot) {
                swap(list, i, j);
                i++;
            }
        }
        swap(list, i, right);
        return i;
    }

    private void swap(List<Integer> list, int i, int j) {
        int tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
