package cn.com.study.base.life;

import java.io.IOException;
import org.apache.zookeeper.ZooKeeper;
/**
 * 使用原生API连接zookeeper
 * 两个区别状态：CONNECTING和CONNECTED
 * 注：CONNECTING连接状态，针对zk进行操作的时候会发生异常，这时需要加入watch机制。
 * @author admin
 *
 */
public class Demo02 {
	public static void main(String[] args) {
		try {
			//连接zookeeper
            ZooKeeper zooKeeper= new ZooKeeper("192.168.65.10:2181", 4000, null);
            
            System.out.println(zooKeeper.getState());//CONNECTING
            Thread.sleep(1000);
            System.out.println(zooKeeper.getState());//CONNECTED
            zooKeeper.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
