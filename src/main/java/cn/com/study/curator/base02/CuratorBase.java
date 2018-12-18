package cn.com.study.curator.base02;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 基础例子：curator连接zookeeper
 * 下一步封装思路：
 *    将连接参数封装成对象。
 * @author admin
 *
 */
public class CuratorBase {
	public static void main(String[] args) {
		String connectString = "192.168.65.10:2181";
		RetryPolicy retryPolicy = new  ExponentialBackoffRetry(1000,3);
		CuratorFramework curatorFramework=CuratorFrameworkFactory.builder().connectString(connectString).
                sessionTimeoutMs(4000).retryPolicy(retryPolicy).namespace("curator").build();
		//注:client必须通过start方法启动，如果不再使用后必须关闭。
        curatorFramework.start();
	}
}
