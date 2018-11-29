package inyu;

import java.io.Serializable;
import java.util.List;

/**
 * 资源下载
 */
public class AppResource implements Serializable {

    private List<Audio> audios;

    public List<Audio> getAudios() {
        return audios;
    }

    public void setAudios(List<Audio> audios) {
        this.audios = audios;
    }

    public class Audio {
        private String resource;
        private String filename;
        private String md5;
        private int length;
        private int file_num;
        private boolean wifi_only;
        private String fileExtension;

        public String getResource() {
            return resource;
        }

        public String getFilename() {
            return filename;
        }

        public String getMd5() {
            return md5;
        }

        public int getLength() {
            return length;
        }

        public int getFile_num() {
            return file_num;
        }

        public boolean isWifi_only() {
            return wifi_only;
        }

        public String getFileExtension() {
            return fileExtension;
        }
    }
}
