package edu.supmti.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

public class HadoopFileStatus {

    public static void main(String[] args) throws Exception {

        if (args.length != 3) {
            System.out.println("Usage: <hdfs_dir> <old_file> <new_file>");
            System.exit(1);
        }

        String directory = args[0];
        String oldName = args[1];
        String newName = args[2];

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);

        Path file = new Path(directory + "/" + oldName);

        if (!fs.exists(file)) {
            System.out.println("File does not exist: " + file);
            fs.close();
            System.exit(1);
        }

        FileStatus status = fs.getFileStatus(file);

        System.out.println("Name: " + file.getName());
        System.out.println("Size: " + status.getLen());
        System.out.println("Owner: " + status.getOwner());
        System.out.println("Permissions: " + status.getPermission());
        System.out.println("Replication: " + status.getReplication());
        System.out.println("Block Size: " + status.getBlockSize());

        fs.rename(file, new Path(directory + "/" + newName));

        System.out.println("Renamed successfully!");
        fs.close();
    }
}
