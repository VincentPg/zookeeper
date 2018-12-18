package cn.com.study.curator.base03;

public class CuratorParam {
	
	private String nameSpace;
	
	private Integer maxRetries;
	
	private Integer baseSleepTimes;
	/**zookeeper连接信息**/
	private String connect;

	/**
	 * nameSpace.
	 *
	 * @return the nameSpace
	 */

	public String getNameSpace() {
		if (nameSpace == null || nameSpace.isEmpty()) {
			this.nameSpace = "jwcrawlersystem";
		}
		return nameSpace;
	}

	/**
	 * nameSpace.
	 *
	 * @param nameSpace
	 *            the nameSpace to set
	 */

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	/**
	 * maxRetries.
	 *
	 * @return the maxRetries
	 */

	public int getMaxRetries() {
		if (maxRetries == null || maxRetries == 0) {
			this.maxRetries = 3;
		}
		return maxRetries;
	}

	/**
	 * maxRetries.
	 *
	 * @param maxRetries
	 *            the maxRetries to set
	 */

	public void setMaxRetries(Integer maxRetries) {
		this.maxRetries = maxRetries;
	}

	/**
	 * baseSleepTimes.
	 *
	 * @return the baseSleepTimes
	 */

	public int getBaseSleepTimes() {
		if (baseSleepTimes == null || baseSleepTimes == 0) {
			this.baseSleepTimes = 3000;
		}
		return baseSleepTimes;
	}

	/**
	 * baseSleepTimes.
	 *
	 * @param baseSleepTimes
	 *            the baseSleepTimes to set
	 */

	public void setBaseSleepTimes(Integer baseSleepTimes) {
		this.baseSleepTimes = baseSleepTimes;
	}

	/**
	 * connect.
	 *
	 * @return the connect
	 */

	public String getConnect() {
		return connect;
	}

	/**
	 * connect.
	 *
	 * @param connect
	 *            the connect to set
	 */

	public void setConnect(String connect) {
		this.connect = connect;
	}
}
