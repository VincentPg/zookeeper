package cn.com.study.base.life;

import java.io.IOException;
import org.apache.zookeeper.ZooKeeper;
/**
 * 使用原生API连接zookeeper
 * zookeeper的连接状态：
 * 	NOT CONNECTED
 * 	CONNECTING
 * 	CONNECTED
 * 	CLOSED
 * @author admin
 *
 */
public class Demo03 {
	public static void main(String[] args) {
		try {
			//连接zookeeper
            ZooKeeper zooKeeper= new ZooKeeper("192.168.65.10:2181", 4000, null);
            
            System.out.println(zooKeeper.getState());//CONNECTING
            Thread.sleep(1000);
            System.out.println(zooKeeper.getState());//CONNECTED
            zooKeeper.close();
            System.out.println(zooKeeper.getState());//CLOSED

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
