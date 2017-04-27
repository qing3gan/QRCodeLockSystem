package com.qrcls.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.qrcls.entity.UserInfo;

/**
 * 用户信息配置工具
 * 
 * 充当DAO,查询文件数据源
 * 
 * @author Agony
 *
 */
public class UserPropUtil {

	/**
	 * 文件数据源
	 */
	private static Properties diskProp = new Properties();

	private static Properties memoProp = new Properties();

	static {
		try {
			diskProp.load(new InputStreamReader(UserPropUtil.class
					.getClassLoader().getResourceAsStream("t.user.info"),
					"UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 注册用户
	 * 
	 * @param userInfo
	 */
	public static void addUserInfo(UserInfo userInfo) {
		memoProp.setProperty(userInfo.getIdcard(), userInfo.getName() + ","
				+ userInfo.getPassword());
	}

	/**
	 * 根据idcard判断用户是否已注册
	 * 
	 * @param idcard
	 * @return
	 */
	public static boolean isUserRegisted(String idcard) {
		return diskProp.containsKey(idcard) || memoProp.containsKey(idcard);
	}

	/**
	 * 根据idcard获取用户信息
	 * 
	 * @param idcard
	 * @return
	 */
	public static UserInfo getUserInfoByIdcard(String idcard) {
		UserInfo userInfo = null;
		if(diskProp.containsKey(idcard)){
			String[] value = diskProp.getProperty(idcard).split(",");
			String name = value[0];
			String password = value[1];
			userInfo = new UserInfo(idcard, name, password);
		}
		if(memoProp.containsKey(idcard)){
			String[] value = memoProp.getProperty(idcard).split(",");
			String name = value[0];
			String password = value[1];
			userInfo = new UserInfo(idcard, name, password);
		}
		return userInfo;
	}

	/**
	 * 获取所有用户信息
	 * 
	 * @return
	 */
	public static List<UserInfo> getAllUserInfo() {
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		for (Object key : diskProp.keySet()) {
			String idcard = (String) key;
			String[] value = diskProp.getProperty(idcard).split(",");
			String name = value[0];
			String password = value[1];
			UserInfo userInfo = new UserInfo(idcard, name, password);
			userInfos.add(userInfo);
		}
		for (Object key : memoProp.keySet()) {
			String idcard = (String) key;
			String[] value = memoProp.getProperty(idcard).split(",");
			String name = value[0];
			String password = value[1];
			UserInfo userInfo = new UserInfo(idcard, name, password);
			userInfos.add(userInfo);
		}
		return userInfos;
	}

	/**
	 * 将内存中的用户信息持久化
	 * 
	 * @return
	 */
	public static boolean storeToDisk() {
		FileOutputStream fos = null;
		BufferedWriter writer = null;
		try {
			if (memoProp.size() != 0) {
				File file = new File(UserPropUtil.class.getClassLoader()
						.getResource("t.user.info").toURI());
				fos = new FileOutputStream(file, true);// 追加
				writer = new BufferedWriter(
						new OutputStreamWriter(fos, "UTF-8"));
				writer.newLine();// 换行
				memoProp.store(writer, null);// 不写注释
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
