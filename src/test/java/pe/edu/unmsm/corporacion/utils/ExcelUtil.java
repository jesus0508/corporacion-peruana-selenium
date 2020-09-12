package pe.edu.unmsm.corporacion.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.edu.unmsm.corporacion.dto.ClienteDTO;
import pe.edu.unmsm.corporacion.dto.ProveedorDTO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;

public class ExcelUtil {
    private XSSFWorkbook xssfWorkbook;

    public ExcelUtil(String filename) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
        try {
            xssfWorkbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo");
        }
    }

    public LinkedList<Object[]> readData(String sheetName, int lastRow) {
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
        LinkedList<Object[]> data = new LinkedList<>();
        ArrayList<String> clientData;
        ClienteDTO clienteDto;
        Object[] objects;
        for (Row row : xssfSheet) {
            clientData = new ArrayList<>();
            if (!firstCellIsEmpty(row)) {
                for (Cell cell : row) {
                    clientData.add(getStringValue(cell));
                }
                clienteDto = new ClienteDTO(clientData);
                objects = new Object[]{clienteDto, row.getCell(lastRow).getStringCellValue()};
                data.add(objects);
            }
        }
        return data;
    }

    public LinkedList<Object[]> readProveedorData(String sheetName, int lastRow) {
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetName);
        LinkedList<Object[]> data = new LinkedList<>();
        ArrayList<String> clientData;
        ProveedorDTO proveedorDTO;
        Object[] objects;
        for (Row row : xssfSheet) {
            clientData = new ArrayList<>();
            if (!firstCellIsEmpty(row)) {
                for (Cell cell : row) {
                    clientData.add(getStringValue(cell));
                }
                proveedorDTO = new ProveedorDTO(clientData);
                objects = new Object[]{proveedorDTO, row.getCell(lastRow).getStringCellValue()};
                data.add(objects);
            }
        }
        return data;
    }

    private boolean firstCellIsEmpty(Row row) {
        Cell cell = row.getCell(0);
        return cellIsEmpty(cell);
    }

    private boolean cellIsEmpty(Cell cell) {
        return cell == null || cell.getCellType() == CellType.BLANK;
    }

    private String getStringValue(Cell cell) {
        if (cellIsEmpty(cell)) {
            return "";
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return  new DataFormatter().formatCellValue(cell);
        }
        return cell.getStringCellValue();
    }

}
