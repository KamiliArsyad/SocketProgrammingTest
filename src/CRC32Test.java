import java.util.zip.CRC32;

public class CRC32Test {
    // Read a specified file up to 10 MB from the command line and print its CRC32
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java CRC32Test filename");
            return;
        }
        try {
            CRC32 crc = new CRC32();
            java.io.FileInputStream in = new java.io.FileInputStream(args[0]);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) >= 0) {
                crc.update(buf, 0, len);
            }
            in.close();
            System.out.println(crc.getValue());
        } catch (java.io.IOException e) {
            System.err.println(e);
        }
    }
}
