package com.obt.bcaaswallet.vo;

import com.obt.bcaaswallet.bean.SeedFullNodeBean;

import java.io.Serializable;
import java.util.List;

/**
 * Redis wallet 存儲資料(TTL)
 * @author Andy Wang
 */
public class WalletVO implements Serializable {

	private static final long serialVersionUID = 1l;

	private String walletAddress;

	private String accessToken;

	private String blockService;
	
	private String blockType;
	
	private int balance;
	
	private ClientIpInfoVO clientIpInfoVO;
	
	private List<SeedFullNodeBean> seedFullNodeList;

	// ========================================================================================================================
	// Constructors
	// ========================================================================================================================
	public WalletVO() {

	}

	public WalletVO(String walletAddress) {
		this.walletAddress = walletAddress;
	}

	public WalletVO(String walletAddress, String accessToken) {
		this.walletAddress = walletAddress;
		this.accessToken = accessToken;
	}

	public WalletVO(String walletAddress, String accessToken, ClientIpInfoVO clientIpInfoVO) {
		this.walletAddress = walletAddress;
		this.accessToken = accessToken;
		this.clientIpInfoVO = clientIpInfoVO;
	}

	public WalletVO(String walletAddress, String accessToken, String blockService, ClientIpInfoVO clientIpInfoVO, List<SeedFullNodeBean> seedFullNodeList) {
		this.walletAddress = walletAddress;
		this.accessToken = accessToken;
		this.blockService = blockService;
		this.clientIpInfoVO = clientIpInfoVO;
		this.seedFullNodeList = seedFullNodeList;
	}
	
	public WalletVO(String walletAddress, String accessToken, String blockService, ClientIpInfoVO clientIpInfoVO) {
		this.walletAddress = walletAddress;
		this.accessToken = accessToken;
		this.blockService = blockService;
		this.clientIpInfoVO = clientIpInfoVO;
	}

	// ========================================================================================================================
	// Get & Set
	// ========================================================================================================================
	public String getWalletAddress() {
		return walletAddress;
	}

	public void setWalletAddress(String walletAddress) {
		this.walletAddress = walletAddress;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public ClientIpInfoVO getClientIpInfoVO() {
		return clientIpInfoVO;
	}

	public void setClientIpInfoVO(ClientIpInfoVO clientIpInfoVO) {
		this.clientIpInfoVO = clientIpInfoVO;
	}

	public String getBlockService() { 
		return blockService;
	}

	public void setBlockService(String blockService) {
		this.blockService = blockService;
	}

	public List<SeedFullNodeBean> getSeedFullNodeList() {
		return seedFullNodeList;
	}

	public void setSeedFullNodeList(List<SeedFullNodeBean> seedFullNodeList) {
		this.seedFullNodeList = seedFullNodeList;
	}

	public String getBlockType() {
		return blockType;
	}

	public void setBlockType(String blockType) {
		this.blockType = blockType;
	}

	@Override
	public String toString() {
		return "WalletVO{" +
				"walletAddress='" + walletAddress + '\'' +
				", accessToken='" + accessToken + '\'' +
				", blockService='" + blockService + '\'' +
				", blockType='" + blockType + '\'' +
				", balance=" + balance +
				", clientIpInfoVO=" + clientIpInfoVO +
				", seedFullNodeList=" + seedFullNodeList +
				'}';
	}
}
