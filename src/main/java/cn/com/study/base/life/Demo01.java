package cn.com.study.base.life;

import java.io.IOException;
import org.apache.zookeeper.ZooKeeper;
/**
 * 使用原生API连接zookeeper
 * 
 * @author admin
 *
 */
public class Demo01 {
	public static void main(String[] args) {
		try {
			//连接zookeeper
            ZooKeeper zooKeeper= new ZooKeeper("192.168.65.10:2181", 4000, null);
            
            System.out.println(zooKeeper.getState());//CONNECTING


            zooKeeper.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
