package pe.edu.unmsm.corporacion.utils;

import org.testng.annotations.DataProvider;

import java.util.Iterator;

public class ProveedorDataProvider {
    @DataProvider(name = "ProveedorDataProvider")
    public static Iterator<Object[]> getDataFromDataprovider() {
        ExcelUtil excelUtil = new ExcelUtil("CE-003.xlsx");
        return excelUtil.readProveedorData("CP-003", 5).iterator();
    }
}
