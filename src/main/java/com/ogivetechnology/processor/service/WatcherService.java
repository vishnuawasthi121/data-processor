/*
 * package com.ogivetechnology.processor.service;
 * 
 * import java.io.File; import java.io.IOException; import
 * java.nio.channels.FileChannel; import java.nio.channels.FileLock; import
 * java.nio.file.Path; import java.nio.file.StandardOpenOption; import
 * java.util.List; import java.util.Set;
 * 
 * import org.apache.commons.io.FilenameUtils; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.devtools.filewatch.ChangedFile; import
 * org.springframework.boot.devtools.filewatch.ChangedFile.Type; import
 * org.springframework.boot.devtools.filewatch.ChangedFiles; import
 * org.springframework.boot.devtools.filewatch.FileChangeListener; import
 * org.springframework.context.annotation.Scope; import
 * org.springframework.stereotype.Component; import
 * org.springframework.util.CollectionUtils;
 * 
 * import com.ogivetechnology.processor.entities.ShipmentDetail; import
 * com.ogivetechnology.processor.repository.ShipmentDetailRepository;
 * 
 * @Component()
 * 
 * @Scope("singleton") public class WatcherService implements FileChangeListener
 * {
 * 
 * @Autowired private ShipmentDetailRepository shipmentDetailRepository;
 * 
 * 
 * 
 * private static volatile Integer counterPosition = 0;
 * 
 * @Autowired private FileToObjectParserXLSXImpl fileToObjectParserXLSXImpl;
 * 
 * @SuppressWarnings("unchecked")
 * 
 * @Override public void onChange(Set<ChangedFiles> changeSet) {
 * 
 * synchronized (changeSet) {
 * 
 * for (ChangedFiles cfiles : changeSet) {
 * 
 * for (ChangedFile cfile : cfiles.getFiles()) {
 * 
 * if ( (cfile.getType().equals(Type.MODIFY) || cfile.getType().equals(Type.ADD)
 * || cfile.getType().equals(Type.DELETE) ) &&
 * 
 * 
 * cfile.getType().equals(Type.MODIFY) && !isLocked(cfile.getFile().toPath())) {
 * 
 * File file = cfile.getFile();
 * 
 * // FileParserFactory.getInstance(cfile.getFile().getAbsoluteFile().)
 * 
 * String extension = FilenameUtils.getExtension(cfile.getFile().getName());
 * 
 * //FileToObjectParser parsar = FileParserFactory.getInstance(extension,
 * counterPosition);
 * 
 * //if (Objects.isNull(parsar)) {
 * 
 * /// System.out.println("File type not supported: Invalid file type"); //}
 * 
 * List<ShipmentDetail> records = (List<ShipmentDetail>)
 * fileToObjectParserXLSXImpl.convert(cfile.getFile());
 * 
 * System.out.println("Available records {}  " + records);
 * 
 * if (!CollectionUtils.isEmpty(records)) {
 * shipmentDetailRepository.saveAll(records); }
 * 
 * System.out.println("Operation: " + cfile.getType() + " On file: " +
 * cfile.getFile().getName() + " is done");
 * 
 * } } } } }
 * 
 * 
 * 
 * private boolean isLocked(Path path) { try (FileChannel ch =
 * FileChannel.open(path, StandardOpenOption.WRITE); FileLock lock =
 * ch.tryLock()) { return lock == null; } catch (IOException e) { return true; }
 * } }
 */