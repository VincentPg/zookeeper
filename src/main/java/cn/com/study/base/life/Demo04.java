package cn.com.study.base.life;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
/**
 * 使用原生API连接zookeeper，引入watch机制，防止连接zookeeper没有连上进行操作产生的问题
 * 
 * @author admin
 *
 */
public class Demo04 {
	public static void main(String[] args) {
		try {
			final CountDownLatch countDownLatch=new CountDownLatch(1);
			//连接zookeeper，引入Watcher机制。
            ZooKeeper zooKeeper= new ZooKeeper("192.168.65.10:2181", 4000, new Watcher() {
				
				public void process(WatchedEvent event) {
					if(Event.KeeperState.SyncConnected==event.getState()){
                        //如果收到了服务端的响应事件，连接成功
						countDownLatch.countDown();
                    }
				}
			});
            
            countDownLatch.await();
            System.out.println(zooKeeper.getState());//CONNECTED
            
            zooKeeper.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
