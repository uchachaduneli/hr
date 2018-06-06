package ge.economy.hr.services;

import ge.economy.hr.error.InvalidDataExeption;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;

/**
 * @author invisible
 */
@Service
public class FileService {

    //   private String dir = "C:\\Program Files\\glassfish4\\glassfish\\domains\\domain1\\docroot\\upload\\";
    private String dir = "/var/webapp/uploads/";

    public byte[] readFile(String group, String identifier) throws IOException {
        return fileTOBytesArray(getFilePath(group, identifier), false);
    }

    public boolean fileExists(String group, String identifier) throws IOException {
        return getFilePath(group, identifier) != null;
    }

    public void writeFile(String group, String identifier, String content) throws IOException, FileNotFoundException, InvalidDataExeption {
        if (content != null) {
            File directory = new File(getGroupPath(group));
            if (!directory.exists()) {
                directory.mkdir();
            }
//            String filePath = getFilePath(group, identifier);
//            if (filePath != null) {
//                File f = new File(filePath);
//                if (f.exists()) {
//                    f.delete();
//                }
//            }
            String fileName = getGroupPath(group) + "/" + identifier;
            base64TOfile(fileName, content);
        }
    }

    public void removeFile(String group, String identifier) {
        String fileName = getFilePath(group, identifier);
        File f = new File(fileName);
        if (f.exists()) {
            f.delete();
        }
    }

    private String getGroupPath(String group) {
        return dir + "/" + group;
    }

    private String getFilePath(String group, String identifier) {
        File f = new File(dir + "/" + group);
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (File _f : files) {
                if (FilenameUtils.removeExtension(_f.getName()).equals(identifier)) {
//                if (_f.getName().equals(identifier)) {
                    return _f.getPath();
                }
            }
        }
        return null;
    }

    private void base64TOfile(String fileName, String base64File) throws FileNotFoundException, IOException, InvalidDataExeption {

        if (base64File == null || base64File.isEmpty()) {
            throw new InvalidDataExeption(" data can not be null ");
        }
        String fileType = base64File.split(",")[0].split(";")[0].split(":")[1].split("/")[1];
        String imageDataBytes = base64File.substring(base64File.indexOf(",") + 1);
        byte[] data = Base64.getDecoder().decode(imageDataBytes);
        try (OutputStream stream = new FileOutputStream(fileName + "." + fileType)) {
            stream.write(data);
        }

    }

    private byte[] fileTOBytesArray(String fileName, boolean base64) throws FileNotFoundException, IOException {
        if (fileName != null) {
            File f = new File(fileName);
            if (f.exists()) {
                byte[] fileAsBytes = IOUtils.toByteArray(new FileInputStream(new File(fileName)));
                if (base64) {
                    fileAsBytes = Base64.getEncoder().encode(fileAsBytes);
                }
                return fileAsBytes;
            }
        }
        return new byte[0];
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

}
