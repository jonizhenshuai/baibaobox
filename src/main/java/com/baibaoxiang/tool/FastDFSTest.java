package com.baibaoxiang.tool;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;
import java.io.IOException;

/**
 * @author chenlin
 */
public class FastDFSTest {

    /**
     * 初始化
     * @return
     * @throws IOException
     * @throws MyException
     */
    private StorageClient1 init() throws IOException, MyException {
        String path = FastDFSTest.class.getResource("/properties/client.conf").getPath();
        //1.加载配置文件,配置文件内容就是tracker服务地址
        ClientGlobal.init(path);
        //2.创建一个TrackerClient对象。直接new一个
        TrackerClient trackerClient = new TrackerClient();
        // 3.使用TrackerClient对象创建连接，获得一个TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        // 4.创建一个StorageServer的引用，值为null
        StorageServer storageServer = null;
        // 5.创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
        return new StorageClient1(trackerServer, storageServer);
    }
    @Test
    public void testFileUpload() throws Exception {
        StorageClient1 storageClient = init();
        // 6、使用StorageClient对象上传图片。
        //扩展名不带“.”
        // 7、返回数组。包含组名和图片的路径。
        String jpg = storageClient.upload_appender_file1("D:\\develop\\IDEA\\Project\\baibaoxiang\\src\\main\\webapp\\images\\a7691515_s.jpg", "jpg", null);
        System.out.println(jpg);

    }

    @Test
    public void delete() throws Exception{
        StorageClient1 storageClient = init();
        storageClient.delete_file1("group1/M00/00/00/rBLEJVzyd4KEe2pcAAAAANEXUNE457.jpg");

    }
}
