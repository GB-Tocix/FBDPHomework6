import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HBaseWork {

    static Configuration cfg = HBaseConfiguration.create();

    public static void main(String[] args) {

        int num_cf = 4;
        String tablename = "students";
        String[] columnfamily = new String[] {"ID", "Description", "Courses", "Home"};

        try {
            HBaseWork.create(tablename, num_cf, columnfamily);
            HBaseWork.put(tablename, "row1", "ID", "id", "001");
            HBaseWork.put(tablename, "row1", "Description", "Name", "Li Lei");
            HBaseWork.put(tablename, "row1", "Description", "Height", "176");
            HBaseWork.put(tablename, "row1", "Courses", "Chinese", "80");
            HBaseWork.put(tablename, "row1", "Courses", "Math", "90");
            HBaseWork.put(tablename, "row1", "Courses", "Physics", "95");
            HBaseWork.put(tablename, "row1", "Home", "Province", "Zhejiang");
			HBaseWork.put(tablename, "row2", "ID", "id", "002");
            HBaseWork.put(tablename, "row2", "Description", "Name", "Han Meimei");
            HBaseWork.put(tablename, "row2", "Description", "Height", "183");
            HBaseWork.put(tablename, "row2", "Courses", "Chinese", "88");
            HBaseWork.put(tablename, "row2", "Courses", "Math", "77");
            HBaseWork.put(tablename, "row2", "Courses", "Physics", "66");
            HBaseWork.put(tablename, "row2", "Home", "Province", "Beijing");
			HBaseWork.put(tablename, "row3", "ID", "id", "003");
            HBaseWork.put(tablename, "row3", "Description", "Name", "Xiao Ming");
            HBaseWork.put(tablename, "row3", "Description", "Height", "162");
            HBaseWork.put(tablename, "row3", "Courses", "Chinese", "90");
            HBaseWork.put(tablename, "row3", "Courses", "Math", "90");
            HBaseWork.put(tablename, "row3", "Courses", "Physics", "90");
            HBaseWork.put(tablename, "row3", "Home", "Province", "Shanghai");
            HBaseWork.scan(tablename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void create(String tablename, int num_cf, String[] columnfamily) throws Exception {
        HBaseAdmin admin = new HBaseAdmin(cfg);
        if(admin.tableExists(tablename)) {
            System.out.println("table exists!");
            System.exit(0);
        }
        else {
            HTableDescriptor tableDesc = new HTableDescriptor(tablename);
            for (int i = 0; i < num_cf; i++)
                tableDesc.addFamily(new HColumnDescriptor(columnfamily[i]));
            admin.createTable(tableDesc);
            System.out.println("create table success!");
        }
    }

    public static void put(String tablename, String row, String columnFamily, String column, String data) throws Exception{
        HTable table = new HTable(cfg, tablename);
        Put p1 = new Put(Bytes.toBytes(row));
        p1.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(data));
        table.put(p1);
        System.out.println("put '"+row+"', '"+columnFamily+":"+column+"', '"+data+"'");
    }

    public static void addfamily(String tablename, String columnfamily) throws Exception {
        HTableDescriptor t = new HTableDescriptor(tablename);
        t.addFamily(new HColumnDescriptor(columnfamily));
    }

    public static void scan(String tablename) throws Exception {
        HTable table = new HTable(cfg, tablename);
        Scan s =new Scan();
        ResultScanner rs = table.getScanner(s);
        for(Result r:rs) {
            System.out.println("Scan: "+r);
        }
    }

    public static boolean delete(String tablename) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(cfg);
        if(admin.tableExists(tablename)) {
            try {
                admin.disableTable(tablename);
                admin.deleteTable(tablename);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}