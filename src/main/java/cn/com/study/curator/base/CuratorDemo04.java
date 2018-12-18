package cn.com.study.curator.base;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * 使用Curator连接zookeeper,并删除结点
 * 注意：此处引入包
 * 	 <groupId>org.apache.zookeeper:3.4.8</version>
 *   <groupId>org.apache.curator:4.0.0</version>
 *   <groupId>org.apache.curator:4.0.0</version>
 * @author admin
 *
 */
public class CuratorDemo04 {
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
			curatorFramework.delete().deletingChildrenIfNeeded().forPath("/mic/node1");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        curatorFramework.close();

	}
}
