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
 * 测试zookeeper新增加节点
 *    可以配合zookeeper的命令行zkCli.sh测试
 * @author admin
 *
 */
public class ZkCrudDemo {
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
            //添加节点：结点路径、结点内容、ACL、结点类型(临时结点/永久结点)
            zooKeeper.create("/zk-persis-mic","0".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            Thread.sleep(1000);
            Stat stat=new Stat();

            //得到当前节点的值
            byte[] bytes=zooKeeper.getData("/zk-persis-mic",null,stat);
            System.out.println(new String(bytes));

            
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
