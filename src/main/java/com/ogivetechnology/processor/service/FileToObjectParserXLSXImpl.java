package com.ogivetechnology.processor.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ogivetechnology.processor.controller.ShipmentDetailsController;
import com.ogivetechnology.processor.entities.RecordMetaData;
import com.ogivetechnology.processor.entities.ShipmentDetail;
import com.ogivetechnology.processor.repository.RecordMetaDataRepository;

@Service
public class FileToObjectParserXLSXImpl implements FileToObjectParser {

	private static final Logger LOG = LoggerFactory.getLogger(ShipmentDetailsController.class);

	private static volatile AtomicInteger counter = new AtomicInteger();

	@Autowired
	private RecordMetaDataRepository recordMetaDataRepository;

	@Override
	public Object convert(File file) {
		LOG.info("convert()  - start");
		List<ShipmentDetail> records = new ArrayList<ShipmentDetail>();

		// Creating Workbook instance that refers to .xlsx file
		try (FileInputStream fis = new FileInputStream(file); XSSFWorkbook wb = new XSSFWorkbook(fis)) {
			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object

			if (null == sheet)
				return null;
			LOG.info("sheet.getLastRowNum()  :  {}", sheet.getLastRowNum());
			while (counter.intValue() <= sheet.getLastRowNum()) {
				LOG.info("Reading row at  {} ", counter.intValue());
				XSSFRow row = sheet.getRow(counter.getAndIncrement());
				LOG.info("After increament  {} ", counter.intValue());

				if (isEmptyRow(row)) {
					continue;
				}

				ShipmentDetail shipmentDetail = new ShipmentDetail();

				String line = getCellValue(row.getCell(0));
				// String line = row.getCell( 0).getStringCellValue();
				Map<String, String> map = new HashMap<>();

				if (!ObjectUtils.isEmpty(line)) {

					String[] keyValuePairStr = line.split(";");

					int index = 0;

					while (index < keyValuePairStr.length) {

						String eachPairStr = keyValuePairStr[index];

						if (!ObjectUtils.isEmpty(eachPairStr)) {

							String[] keyValue = eachPairStr.split("=");

							if (null != keyValue && keyValue.length == 2) {
								map.put(keyValue[0].trim(), keyValue[1]);
							}

						}

						index++;
					}

				}

				System.out.println(map);

				map.forEach((key, value) -> {
					System.out.println("KEY : " + key);
				});

				if (!map.isEmpty()) {

					// reciptId
					shipmentDetail.setReciptId(map.get("reciptId"));
					// userId
					shipmentDetail.setUserId(map.get("userId"));
					// sNo
					shipmentDetail.setSno(map.get("SNo"));

					shipmentDetail.setShipName(map.get("Ship Name"));

					shipmentDetail.setOccasion(map.get("Occasion"));

					//String key1 = "RA no of\r\n" + "SDRS or DL no of SDRS or Fax / OPDEF signal reference";

					shipmentDetail.setRaOrSaOrFxNumber(map.get("RA no of SDRS or DL no of SDRS or Fax / OPDEF signal reference"));

					shipmentDetail.setEquipmentName(map.get("Equipment Name"));

					shipmentDetail.setSubEquipmentName(map.get("Sub equipment / sub assembly name"));

					shipmentDetail.setNameDescriptionOfItem(map.get("Name and Description of the Item"));

					shipmentDetail
							.setTechnicalSpecificationDescription(map.get("Technical Specifications of the item"));

					shipmentDetail.setLocation(map.get("Location (where it is fitted onboard)"));
					// Department of NSRY (Kar) where it is to be landed
					shipmentDetail.setDeptTo(map.get("Department of NSRY (Kar) where it is to be landed"));

					// Centre No of the department
					shipmentDetail.setCentreNoOfTheDepartment(map.get("Centre No of the department"));

					shipmentDetail.setDescriptionOfDefect(map.get("Description of defect"));

					shipmentDetail.setShipStaffContactDetails(map.get("Ship staff contact person details"));

					shipmentDetail.setAnyOtherDetails(map.get("Any other details"));
					shipmentDetail.setStatusTracking(map.get("statusTracking"));

					shipmentDetail.setExtraField1(map.get("Extra Fild-1"));
					shipmentDetail.setExtraField2(map.get("Extra Fild-2"));
					shipmentDetail.setExtraField3(map.get("Extra field-3"));

					String createdDateStr = map.get("created_time");

					shipmentDetail.setCreatedDate(convertToDate(createdDateStr));

					shipmentDetail.setUpdatedDate(new Date());
					records.add(shipmentDetail);
					// As record has been added successfully into list. Now this is the time to
					// increase the counter value

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOG.info("convert()  - end");
		return records;
	}

	private String getCellValue(Cell cell) {
		DataFormatter formatter = new DataFormatter();
		String strValue = formatter.formatCellValue(cell);

		/**
		 * Object cellValue = null;
		 * 
		 * CellType cellType = cell.getCellType().equals(CellType.FORMULA) ?
		 * cell.getCachedFormulaResultType() : cell.getCellType();
		 * 
		 * if (cellType.equals(CellType.STRING)) { cellValue =
		 * cell.getStringCellValue(); } if (cellType.equals(CellType.NUMERIC)) {
		 * 
		 * if (DateUtil.isCellDateFormatted(cell)) {
		 * 
		 * cellValue = cell.getDateCellValue(); } else { cellValue =
		 * cell.getNumericCellValue(); } }
		 **/
		/**
		 * if (cellType.equals(CellType.BOOLEAN)) { return cell.getBooleanCellValue(); }
		 **/

		LOG.info("strValue   : " + strValue);
		return strValue;
	}

	private boolean isEmptyRow(Row row) {

		if (row == null) {
			return true;
		}

		if (row.getLastCellNum() <= 0) {
			return true;
		}

		boolean isEmpty = true;

		for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {

			Cell cell = row.getCell(cellNum);

			if (cell != null && cell.getCellType() == CellType.BLANK) {
				continue;
			} else {
				isEmpty = false;
			}
		}
		return isEmpty;
	}

	@PreDestroy
	public void shutsown() {
		RecordMetaData metaData = new RecordMetaData();
		metaData.setCounterPosition(counter.intValue());
		LOG.info("Saving position at  {} ", counter.intValue());
		metaData.setCreatedDate(new Date());
		recordMetaDataRepository.save(metaData);
	}

	@PostConstruct
	public void fetchCounterPosition() {
		Integer position = recordMetaDataRepository.getCounterPosition();
		LOG.info("position  : " + position);

		if (null != position) {
			counter.set(position);
		} else {
			counter.set(0);
		}
		LOG.info("counterPosition  : " + counter.intValue());
	}

	public void reset(Integer position) {
		counter.set(position);
	}

	public Date convertToDate(String dateStr) {
		Date parse = null;
		try {
			// dd-M-yyyy hh:mm:ss
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			parse = formatter.parse(dateStr);
		} catch (Exception e) {
			LOG.error("Unable to parse created date {}", dateStr);
			e.printStackTrace();
		}
		return parse;
	}

}
