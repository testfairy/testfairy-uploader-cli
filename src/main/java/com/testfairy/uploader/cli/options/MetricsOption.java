package com.testfairy.uploader.cli.options;

import com.testfairy.uploader.AndroidUploader;
import com.testfairy.uploader.IOSUploader;
import com.testfairy.uploader.Metrics;
import com.testfairy.uploader.Options;
import com.testfairy.uploader.cli.StringUtils;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

public class MetricsOption implements AndroidOptions, IOSOptions {

	private OptionSpec<String> metrics;

	@Override
	public void configure(OptionParser parser) {
		this.metrics = parser.accepts("metrics", "Comma-separated list of metrics to record").withRequiredArg();
	}

	@Override
	public void apply(OptionSet arguments, AndroidUploader.Builder android) {
		android.setMetrics(extract(arguments));
	}

	@Override
	public void apply(OptionSet arguments, IOSUploader.Builder ios) {
		ios.setMetrics(extract(arguments));
	}

	private Metrics extract(OptionSet optionSet) {
		if (! optionSet.has(this.metrics))
			return null;

		String all = optionSet.valueOf(this.metrics);
		if (StringUtils.isEmpty(all))
			return null;

		Metrics.Builder metrics = null;
		for (String metric : all.split(",")) {
			if ("cpu".equals(metric)) {
				metrics = metrics(metrics).addCpu();
			} else if ("opengl".equals(metric)) {
				metrics = metrics(metrics).addOpenGl();
			} else if ("memory".equals(metric)) {
				metrics = metrics(metrics).addMemory();
			} else if ("network".equals(metric)) {
				metrics = metrics(metrics).addNetwork();
			} else if ("phone-signal".equals(metric)) {
				metrics = metrics(metrics).addPhoneSignal();
			} else if ("logcat".equals(metric)) {
				metrics = metrics(metrics).addLogcat();
			} else if ("gps".equals(metric)) {
				metrics = metrics(metrics).addGps();
			} else if ("battery".equals(metric)) {
				metrics = metrics(metrics).addBattery();
			} else if ("mic".equals(metric)) {
				metrics = metrics(metrics).addMic();
			} else if ("wifi".equals(metric)) {
				metrics = metrics(metrics).addWifi();
			}
		}

		return (metrics == null) ? null : metrics.build();
	}

	private static Metrics.Builder metrics(Metrics.Builder metrics) {
		return (metrics == null) ? new Metrics.Builder() : metrics;
	}
}
