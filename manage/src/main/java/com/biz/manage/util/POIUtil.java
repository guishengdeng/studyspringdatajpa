package com.biz.manage.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class POIUtil {

    public static String getCellValue(Cell cell){
    	String str="";
        if(cell == null){
            return str;
        }
    	switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                str = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                str = Boolean.toString(cell
                        .getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    str = String.valueOf(cell
                            .getDateCellValue());
                } else {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String temp = cell.getStringCellValue();
                    if (temp.contains(".")) {
                        str = String.valueOf(new Double(temp))
                                .trim();
                    } else {
                        str = temp.trim();
                    }
                }
                break;
            case Cell.CELL_TYPE_STRING:
                str = cell.getStringCellValue().trim();
                break;
            case Cell.CELL_TYPE_ERROR:
                str = "";
                break;
            default:
                str = "";
                break;
            }
    	return str;
    }

}
