package cn.com.study.curator.base03;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 基于线程安全的单例模式
 * 
 * @author admin
 *
 */
public class CuratorFactory {
	/**
	 * 管理者装配参数
	 */
	private CuratorParam curatorParam;

	public CuratorParam getCuratorParam() {
		return curatorParam;
	}

	public void setCuratorParam(CuratorParam curatorParam) {
		this.curatorParam = curatorParam;
	}

	public CuratorFactory() {

	}

	public CuratorFactory(CuratorParam curatorParam) {
		this.curatorParam = curatorParam;
	}

	private static CuratorFramework client = null;

	/**
	 * @MethodName:createCuratorFramework
	 * @Description:创建管理者
	 * @return 管理者
	 */
	public synchronized CuratorFramework createCuratorFramework() {

		if (client == null || client.getState() != CuratorFrameworkState.STARTED) {
			RetryPolicy retryPolicy = new ExponentialBackoffRetry(curatorParam.getBaseSleepTimes(),
					curatorParam.getMaxRetries());
			client = CuratorFrameworkFactory.builder().connectString(curatorParam.getConnect()).retryPolicy(retryPolicy)
					.namespace(curatorParam.getNameSpace()).build();

			client.start();

		}
		return client;
	}
}
