package com.testfairy.uploader.cli.options;

import com.testfairy.uploader.AndroidUploader;
import com.testfairy.uploader.IOSUploader;
import com.testfairy.uploader.Options;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

public class ProxyOptions implements AndroidOptions, IOSOptions {
	private OptionSpec<String> proxyHost;
	private OptionSpec<Integer> proxyPort;
	private OptionSpec<String> proxyUsername;
	private OptionSpec<String> proxyPassword;

	@Override
	public void configure(OptionParser parser) {
		proxyHost = parser.accepts("proxy-host", "Proxy host address or IP").withRequiredArg();
		proxyPort = parser.accepts("proxy-port", "Proxy host port").withRequiredArg().ofType(Integer.class);
		proxyUsername = parser.accepts("proxy-username", "Username credentials for proxy host (if required)").withRequiredArg();
		proxyPassword = parser.accepts("proxy-password", "Password credentials for proxy host (if required)").withRequiredArg();
	}

	@Override
	public void apply(OptionSet arguments, IOSUploader.Builder ios) {
		Integer port = extract(arguments, proxyPort);
		ios.setProxyHost(extract(arguments, proxyHost), port == null ? -1 : port);
		ios.setProxyCredentials(extract(arguments, proxyHost), extract(arguments, proxyHost));
	}

	@Override
	public void apply(OptionSet arguments, AndroidUploader.Builder android) {
		Integer port = extract(arguments, proxyPort);
		android.setProxyHost(extract(arguments, proxyHost), port == null ? -1 : port);
		android.setProxyCredentials(extract(arguments, proxyHost), extract(arguments, proxyHost));
	}

	private static <T> T extract(OptionSet arguments, OptionSpec<T> property) {
		return arguments.has(property) ? arguments.valueOf(property) : null;
	}
}
