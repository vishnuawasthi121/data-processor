package com.ogivetechnology.processor.controller;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ogivetechnology.processor.entities.ShipmentDetail;
import com.ogivetechnology.processor.repository.RecordMetaDataRepository;
import com.ogivetechnology.processor.repository.ShipmentDetailRepository;
import com.ogivetechnology.processor.service.FileToObjectParserXLSXImpl;

@RestController
public class ShipmentDetailsController {

	private static final Logger LOG = LoggerFactory.getLogger(ShipmentDetailsController.class);

	@Autowired
	private ShipmentDetailRepository shipmentDetailRepository;

	@Autowired
	private RecordMetaDataRepository recordMetaDataRepository;

	@Autowired
	private FileToObjectParserXLSXImpl fileToObjectParserXLSXImpl;

	@Value("${QRcode.file.location}")
	private String fileLocation;

	@GetMapping(value = "/shipmentDetails")
	public Object shipmentDetail() {
		LOG.info("shipmentDetail()  - start");
		List<ShipmentDetail> allShipments = shipmentDetailRepository.findAll();

		if (null != allShipments) {
			LOG.info("shipmentDetail()  - end {}", allShipments);
			return new ResponseEntity(allShipments, HttpStatus.OK);
		}
		LOG.info("shipmentDetail()  - end - No data found");
		return new ResponseEntity<List<ShipmentDetail>>(HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@GetMapping(value = "/extract-qacode")
	public Object extractQRCode() {
		LOG.info("extractQRCode()  start");
		LOG.info("fileLocation  {} ", fileLocation);
		File file = new File(fileLocation);

		String extension = FilenameUtils.getExtension(file.getName());

		LOG.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ {}: ", extension);

		List<ShipmentDetail> records = (List<ShipmentDetail>) fileToObjectParserXLSXImpl.convert(file);

		LOG.info("Available records {}  ", records);

		if (!CollectionUtils.isEmpty(records)) {
			shipmentDetailRepository.saveAll(records);
		}
		LOG.info("extractQRCode()  end");
		return new ResponseEntity<List<ShipmentDetail>>(HttpStatus.OK);
	}

	@GetMapping(value = "/reset")
	public Object reset() {
		LOG.info("reset()  start");
		recordMetaDataRepository.deleteAll();
		fileToObjectParserXLSXImpl.reset(0);
		LOG.info("reset()  end");
		return new ResponseEntity<List<ShipmentDetail>>(HttpStatus.OK);
	}

	@PostMapping(value = "/reset-all")
	public Object removeAllQRCode() {
		LOG.info("removeAllQRCode()  start");
		recordMetaDataRepository.deleteAll();
		shipmentDetailRepository.deleteAll();
		fileToObjectParserXLSXImpl.reset(0);
		LOG.info("removeAllQRCode()  end");
		return new ResponseEntity<List<ShipmentDetail>>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{id}")
	public Object deleteById(@PathVariable(name = "id") Long id) {
		LOG.info("deleteById()  start");
		
		LOG.info("Deleting record for ID {} ", id);
		shipmentDetailRepository.deleteById(id);

		LOG.info("deleteById()  end");
		return new ResponseEntity<List<ShipmentDetail>>(HttpStatus.OK);
	}
}
