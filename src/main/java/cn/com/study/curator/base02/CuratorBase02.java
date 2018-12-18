package cn.com.study.curator.base02;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 基础例子：封装zookeeper连接参数
 * 下一步封装思路：
 *     引入单例模式
 * @author admin
 *
 */
public class CuratorBase02 {
	private static CuratorParam curatorParam;
	
	public CuratorParam getCuratorParam() {
		return curatorParam;
	}

	public void setCuratorParam(CuratorParam curatorParam) {
		this.curatorParam = curatorParam;
	}
	
	public static void main(String[] args) {
		RetryPolicy retryPolicy = new  ExponentialBackoffRetry(curatorParam.getBaseSleepTimes(),
				curatorParam.getMaxRetries());
		CuratorFramework curatorFramework=CuratorFrameworkFactory.builder().connectString(curatorParam.getConnect()).
                sessionTimeoutMs(4000).retryPolicy(retryPolicy).namespace(curatorParam.getNameSpace()).build();
		//注:client必须通过start方法启动，如果不再使用后必须关闭。
        curatorFramework.start();
	}
}
