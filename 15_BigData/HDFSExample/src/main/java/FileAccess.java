import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FileAccess {
    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");
        return configuration;
    }


    /**
     * Initializes the class, using rootPath as "/" directory
     *
     * @param rootPath - the path to the root of HDFS,
     *                 for example, hdfs://localhost:32771
     */
    public FileAccess(String rootPath) {
        try {
            FileSystem hdfs = FileSystem.get(new URI(rootPath), getConfiguration());
            hdfs.close();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    /**
     * Creates empty file or directory
     *
     * @param path
     */
    public void create(String path) throws URISyntaxException, IOException {
        FileSystem hdfs = FileSystem.get(new URI("hdfs://HOST_NAME:8020"), getConfiguration());
        File file = new File(path);
        Path path1 = new Path(String.valueOf(file));
        if (file.isDirectory()) {
            file.mkdir();
            if (file.mkdir()) {
                System.out.println("Directory is created");
            } else {
                System.out.println("Directory cannot be created");
            }
        }
        else {
            hdfs.createNewFile(path1);
        }
    }

    /**
     * Appends content to the file
     *
     * @param path
     * @param content
     */
    public void append(String path, String content) {
        try {
            FileSystem hdfs = FileSystem.get(new URI(path), getConfiguration());
            Path file = new Path(content);
            if (!hdfs.exists(file)) {
                hdfs.createNewFile(file);
            }

            FSDataOutputStream fileOutputStream = hdfs.append(file);
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            br.append("Content: " + content + "\n");
            br.close();
        } catch (URISyntaxException | IOException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }

    /**
     * Returns content of the file
     *
     * @param path
     * @return
     */
    public String read(String path) throws URISyntaxException, IOException {

        FileSystem hdfs = FileSystem.get(new URI("hdfs://HOST_NAME:8020"), getConfiguration());
        Path file = new Path(path);
        FSDataInputStream inputStream = hdfs.open(file);
        String out = IOUtils.toString(inputStream, "UTF-8");
        inputStream.close();
        hdfs.close();
        return out;
    }

    /**
     * Deletes file or directory
     *
     * @param path
     */
    public void delete(String path) throws URISyntaxException, IOException {
        FileSystem hdfs = FileSystem.get(new URI("hdfs://HOST_NAME:8020"), getConfiguration());
        Path file = new Path(path);
        if (hdfs.exists(file)) {
            hdfs.delete(file, true);
        }
    }

    /**
     * Checks, is the "path" is directory or file
     *
     * @param path
     * @return
     */
    public boolean isDirectory(String path) {

        return Files.isDirectory(java.nio.file.Path.of(path));
    }

    /**
     * Return the list of files and subdirectories on any directory
     *
     * @param path
     * @return
     */
    public List<String> list(String path) {
        List<String> filePaths = new ArrayList<String>();
        FileSystem fs = null;
        Boolean recursive = true;
        try {
            Path file = new Path(path);
            fs = file.getFileSystem(getConfiguration());
            file = fs.resolvePath(file);

            if (recursive) {
                Queue<Path> fileQueue = new LinkedList<Path>();
                fileQueue.add(file);

                while (!fileQueue.isEmpty()) {
                    Path filePath = fileQueue.remove();
                    if (fs.isFile(filePath)) {
                        filePaths.add(filePath.toString());
                    } else {

                        FileStatus[] fileStatuses = fs.listStatus(filePath);
                        for (FileStatus fileStatus : fileStatuses) {
                            fileQueue.add(fileStatus.getPath());
                        }
                    }

                }

            } else {
                if (fs.isDirectory(file)) {
                    FileStatus[] fileStatuses = fs.listStatus(file);

                    for (FileStatus fileStatus : fileStatuses) {
                        if (fileStatus.isFile())
                            filePaths.add(fileStatus.getPath().toString());
                    }
                } else {

                    filePaths.add(file.toString());
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        } finally {
            try {
                if (fs != null)
                    fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return filePaths;
        }
    }
}
