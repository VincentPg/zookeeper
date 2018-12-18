package cn.com.study.base.operation;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.Watcher.Event;
import org.apache.zookeeper.ZooDefs;

/**
 * 测试zookeeper修改结点
 *    可以配合zookeeper的命令行zkCli.sh测试
 * @author admin
 *
 */
public class ZkCrudDemo2 {
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

            Stat stat=new Stat();

            //得到当前节点的值
            byte[] bytes=zooKeeper.getData("/zk-persis-mic",null,stat);
            System.out.println(new String(bytes));

            //修改节点值
            zooKeeper.setData("/zk-persis-mic","1".getBytes(),stat.getVersion());

            //得到当前节点的值
            byte[] bytes1=zooKeeper.getData("/zk-persis-mic",null,stat);
            System.out.println(new String(bytes1));

            zooKeeper.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
			e.printStackTrace();
		}
	}
}
