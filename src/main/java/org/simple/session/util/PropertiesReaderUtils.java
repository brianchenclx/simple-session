package org.simple.session.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import com.google.common.io.ByteSource;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author clx 2018/4/3.
 */
public class PropertiesReaderUtils {

	private static final Logger logger = LoggerFactory.getLogger(PropertiesReaderUtils.class);

	/**
	 * read properties from classpath
	 * 
	 * @param classpath
	 *            properties file classpath
	 * @return Properties object
	 */
	public static Properties read(String classpath) {
		final URL url = Resources.getResource(classpath);
		final ByteSource byteSource = Resources.asByteSource(url);
		final Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = byteSource.openBufferedStream();
			properties.load(inputStream);
		} catch (final IOException ioException) {
			logger.error("load properties fail. properties path={}", classpath);
			ioException.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (final IOException e) {
					logger.error("close inputStream fail.");
					e.printStackTrace();
				}
			}
		}
		return properties;
	}
}
