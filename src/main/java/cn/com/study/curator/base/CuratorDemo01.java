package cn.com.study.curator.base;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 使用Curator连接zookeeper
 * 
 * @author admin
 *
 */
public class CuratorDemo01 {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		CuratorFramework curatorFramework=CuratorFrameworkFactory.
                builder().connectString("192.168.65.10:2181").
                sessionTimeoutMs(4000).retryPolicy(new
                ExponentialBackoffRetry(1000,3)).
                namespace("curator").build();
		//注:client必须通过start方法启动，如果不再使用后必须关闭。
        curatorFramework.start();
        System.out.println("namespace:"+curatorFramework.getNamespace());
        System.out.println("curator state:" + curatorFramework.getState().toString());
        System.out.println(curatorFramework.isStarted()); //true
        curatorFramework.close();

	}
}
