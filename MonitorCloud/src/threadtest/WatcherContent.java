
package threadtest;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatcherContent {

    private WatchService watchService;
    private Path directory;
    private WatchKey watchKey;

    public WatcherContent() throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
        this.directory = Paths.get("\\\\pttvfs1\\WxData_Proxy\\Image\\FOD Hourly CONUS 4km\\Temperature");
        this.watchKey = directory.register(watchService,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_CREATE);
    }

    public WatchService getWatchService() {
        return this.watchService;
    }

    public Path getDirectory() {
        return this.directory;
    }

    public WatchKey getWatchKey() {
        return this.watchKey;
    }
}
