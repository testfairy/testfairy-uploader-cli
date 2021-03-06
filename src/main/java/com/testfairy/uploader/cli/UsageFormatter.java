package com.testfairy.uploader.cli;

import joptsimple.HelpFormatter;
import joptsimple.OptionDescriptor;
import joptsimple.internal.Rows;

import java.util.Map;

import static java.lang.System.getProperty;

class UsageFormatter implements HelpFormatter {
	private static final String LINE_SEPARATOR = getProperty( "line.separator" );

	@Override
	public String format(Map<String, ? extends OptionDescriptor> options) {
		StringBuilder formatted = new StringBuilder();
		formatted.append("TestFairy Uploader").append(LINE_SEPARATOR);
		formatted.append("Usage: testfairy-uploader [OPTIONS]... --api-key <api key> [PATH TO BUILD]")
			.append(LINE_SEPARATOR).append(LINE_SEPARATOR);

		Rows optionRows = new Rows(90, 2);
		optionRows.add("Startup:", "");
		optionRows.add("-?, -h, --help", "Show TestFairy uploader usage");
		optionRows.add("--api-key=STRING", "Your API application key. See https://app.testfairy.com/settings for details");

		optionRows.add("Options:", "");
		optionRows.add("--auto-update", "Allows easy upgrade of all users to current version");
		optionRows.add("--comment=STRING", "Additional release notes for this upload. This text will be added to email notifications. Can also pass @path to text file");
		optionRows.add("--custom=STRING", "Additional fields for this upload. E.g. --custom=field1=value1,field2=value2");
		optionRows.add("--max-duration=STRING", "Maximum session recording length, eg 20m or 1h. Default is \"10m\". Maximum 24h");
		optionRows.add("--notify", "Notify testers of updated build");
		optionRows.add("--symbols-file=PATH", "Path to symbols or proguard file");
		optionRows.add("--tester-group=LIST", "Comma-separated list of tester groups to be notified on the new build. Or \"all\" to notify all testers");
		optionRows.add("--video=STRING", "Video recording settings \"on\", \"off\" or \"wifi\" for recording video only when wifi is available. Default is \"on\"");
		optionRows.add("--video-quality=STRING", "Video quality settings, \"high\", \"medium\" or \"low\". Default is \"high\"");
		optionRows.add("--video-rate=FLOAT", "Video rate recording in frames per second");

		optionRows.add("Proxy Options:", "");
		optionRows.add("--proxy-host=STRING", "Proxy host address or IP");
		optionRows.add("--proxy-port=INTEGER", "Proxy host port. Defaults to -1");
		optionRows.add("--proxy-username=STRING", "Username credentials for proxy host (if required)");
		optionRows.add("--proxy-password=STRING", "Password credentials for proxy host (if required)");

		optionRows.add("Android Options:", "");
		optionRows.add("--anonymous", "When using this option, sessions are anonymous and account information is not collected from device");
		optionRows.add("--metrics=LIST", "Comma-separated list of metrics to recor");
		optionRows.add("--watermark", "Add a small watermark to app icon");
		optionRows.add("--instrumentation=STRING", "Skip instrumentation of app, \"on\", or \"off\". Defaults to \"on\"");

		optionRows.add("Android Keystore Options:", "(only required if --instrumentation=on)");
		optionRows.add("--keystore=PATH", "Keystore location");
		optionRows.add("--storepass=STRING", "Password for keystore integrity");
		optionRows.add("--keypass=STRING", "Password for private key (if different from storepass)");
		optionRows.add("--alias=STRING", "Keystore alias");
		optionRows.add("--digestalg=STRING", "Name of digest algorithm");
		optionRows.add("--sigalg=STRING", "Name of signature algorithm");
		optionRows.add("--jarsigner-path=PATH", "Path to jarsigner");

		optionRows.add("iOS Options:", "");
		optionRows.add("--metrics=LIST", "comma-separated list of metrics to record");

		optionRows.fitToWidth();
		formatted.append(optionRows.render()).append(LINE_SEPARATOR);
		formatted.append(LINE_SEPARATOR)
			.append("File bugs or suggestions on github @ https://github.com/testfairy/testfairy-uploader-cli")
			.append(LINE_SEPARATOR);

		return formatted.toString();
	}

	public static String version() {
		return new StringBuilder("TestFairy Uploader ")
			.append(Config.VERSION)
			.append(LINE_SEPARATOR)
			.append(LINE_SEPARATOR)
			.append("File bugs or suggestions on github @ https://github.com/testfairy/testfairy-uploader-cli")
			.append(LINE_SEPARATOR)
			.toString();
	}
}
