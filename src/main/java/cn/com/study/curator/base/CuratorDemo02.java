package cn.com.study.curator.base;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * 使用Curator连接zookeeper,并创建结点
 * 注意：此处引入包
 * 	 <groupId>org.apache.zookeeper:3.4.8</version>
 *   <groupId>org.apache.curator:4.0.0</version>
 *   <groupId>org.apache.curator:4.0.0</version>
 * @author admin
 *
 */
public class CuratorDemo02 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		CuratorFramework curatorFramework=CuratorFrameworkFactory.
                builder().connectString("192.168.65.10:2181").
                sessionTimeoutMs(4000).retryPolicy(new
                ExponentialBackoffRetry(1000,3)).
                namespace("curator").build();
		//注:client必须通过start方法启动，如果不再使用后必须关闭。
        curatorFramework.start();
        try {
        	//结果: /curator/mic/node1
        	//原生api中，必须是逐层创建，也就是父节点必须存在，子节点才能创建。
			curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/mic/node1","1".getBytes());
			Stat stat=new Stat();
			byte[] bytes=curatorFramework.getData().storingStatIn(stat).forPath("/mic/node1");
			System.out.println(new String(bytes));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        curatorFramework.close();

	}
}
